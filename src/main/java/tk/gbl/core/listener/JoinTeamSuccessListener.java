package tk.gbl.core.listener;

import tk.gbl.bean.Context;
import tk.gbl.core.GameClient;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * Date: 2017/4/7
 * Time: 19:02
 *
 * @author Tian.Dong
 */
public class JoinTeamSuccessListener extends BaseListener {
    @Override
    public void doAction(GameClient gameClient, Context context) throws IOException {
        long teamLeaderUserId = context.getFromUserId();
        long userId = context.getToUserId();
        //队长
        if (gameClient.getTeamLeaderId() == gameClient.getUserId()) {
            //不是自己的队伍
            if (gameClient.getUserId() != teamLeaderUserId) {
                return;
            }
            gameClient.getTeamUsers().add(userId);
            OutputUtil.output("入队成功信息" + userId + "入队", gameClient, true);
            OutputUtil.output("组队人数" + (gameClient.getTeamUsers().size() + "," + gameClient.getAllowTeamUsers().size()), gameClient, true);
            if (gameClient.getAllowTeamUsers().size() > 0 && gameClient.getAllowTeamUsers().get(0) == userId) {
                gameClient.confirmTeamAdviser(userId);
                OutputUtil.output("设置军师" + userId, gameClient, true);
            }
            if (gameClient.getTeamUsers().size() == gameClient.getAllowTeamUsers().size()) {
                if (gameClient.getGameTeam() != null) {
                    OutputUtil.output("teamSuccess!!!!!!!!", gameClient, true);
                    gameClient.getGameTeam().teamSuccess();
                }
            }
        } else {
            //不是自己
            if (userId != gameClient.getUserId()) {
                return;
            }
            gameClient.setJoinTeam(true);
            OutputUtil.output("!!!!!!!!自己" + userId + "入队成功", gameClient, true);
            OutputUtil.output("isJoinTeam:" + gameClient.isJoinTeam(), gameClient, true);
        }
    }
}
