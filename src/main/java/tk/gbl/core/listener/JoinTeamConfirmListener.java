package tk.gbl.core.listener;

import tk.gbl.bean.Context;
import tk.gbl.core.GameClient;

import java.io.IOException;

/**
 * Date: 2017/4/7
 * Time: 19:02
 *
 * @author Tian.Dong
 */
public class JoinTeamConfirmListener extends BaseListener {
    @Override
    public void doAction(GameClient gameClient, Context context) throws IOException {
        //不是自己的队伍
        long userId = context.getFromUserId();
        if (!gameClient.getAllowTeamUsers().contains(userId)) {
            return;
        }

    }
}
