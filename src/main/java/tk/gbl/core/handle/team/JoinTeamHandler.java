package tk.gbl.core.handle.team;

import tk.gbl.bean.Context;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 有人申请入队
 * F4 44 06 00 0D 01 46 BE 01 00
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class JoinTeamHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 06 00 0D 01 46 BE 01 00
        if (data[4] == (ResponseProtocolConstant.joinTeamRequest.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.joinTeamRequest.getId() & 0xFF)) {
            long userId = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            OutputUtil.output("!!!!" + userId + "申请入队 ", gameClient);
            Context context = new Context();
            context.setFromUserId(userId);
            gameClient.fire("joinTeam", context);
            return true;
        }


        if (
//                (data[4] == (ResponseProtocolConstant.u0D04.getId() >> 8 & 0xFF)
//                        && data[5] == (ResponseProtocolConstant.u0D04.getId() & 0xFF))//别人的信息
                (data[4] == (ResponseProtocolConstant.u0D07.getId() >> 8 & 0xFF)
                        && data[5] == (ResponseProtocolConstant.u0D07.getId() & 0xFF))
                ) {
            long userId = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
//            long userId = MultiByteToIntUtil.from(data[10], data[11], data[12], data[13]);
            OutputUtil.output(userId + "设置军师成功 ", gameClient);
            Context context = new Context();
            context.setToUserId(userId);
//            gameClient.fire("joinTeamSuccess", context);
            return true;
        }

        //队长收到的
        //                  6  7  8  9  10 11 12 13
        //F4 44 0A 00 0D 05 5D C1 01 00 5E C1 01 00
        //                 队长             队员
        //F4 44 0A 00 0D 05 5D C1 01 00 5E C1 01 00
        //申请入队成功通知信息
        //F4 44 0A 00 0D 05 5D C1 01 00 5E C1 01 00
        if (data[4] == (ResponseProtocolConstant.joinTeamSuccess.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.joinTeamSuccess.getId() & 0xFF)) {
            long teamLeaderUserId = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            long userId = MultiByteToIntUtil.from(data[10], data[11], data[12], data[13]);
            OutputUtil.output(userId + "申请入队" + teamLeaderUserId + "成功", gameClient, false);

            Context context = new Context();
            context.setFromUserId(teamLeaderUserId);
            context.setToUserId(userId);
            gameClient.fire("joinTeamSuccess", context);
            OutputUtil.output("触发joinTeamSuccess", gameClient, false);
            return true;
        }

        //F4 44 06 00 0D 0B 5E C1 01 00
        if (data[4] == (ResponseProtocolConstant.u0D04.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0D04.getId() & 0xFF)) {
            long userId = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            if (gameClient.getTeamLeaderId() != gameClient.getUserId()) {
                if (userId == gameClient.getTeamLeaderId()) {
                    OutputUtil.output("队长解散队伍", gameClient, true);
                    gameClient.setJoinTeam(false);
                    gameClient.fire("teamDismiss");
                }
                return true;
            }
            if (!gameClient.getTeamUsers().contains(userId)) {
                return true;
            }
            OutputUtil.output(userId + "退出队伍", gameClient, true);
            for (Iterator<Long> iterator = gameClient.getTeamUsers().iterator(); iterator.hasNext(); ) {
                Long teamUser = iterator.next();
                if (userId == teamUser) {
                    iterator.remove();
                }
            }
            OutputUtil.output("队伍人数" + (gameClient.getTeamUsers().size() + 1), gameClient);
            OutputUtil.output("u0D04 ", gameClient);
            return true;
        }
        return false;
    }
}
