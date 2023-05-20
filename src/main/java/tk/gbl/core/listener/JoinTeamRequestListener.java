package tk.gbl.core.listener;

import tk.gbl.bean.Context;
import tk.gbl.core.GameClient;

import java.io.IOException;
import java.util.Map;

/**
 * Date: 2017/4/7
 * Time: 19:02
 *
 * @author Tian.Dong
 */
public class JoinTeamRequestListener extends BaseListener {
    @Override
    public void doAction(GameClient gameClient, Context context) throws IOException {
        if (gameClient.getTeamLeaderId() != gameClient.getUserId()) {
            return;
        }
        long userId = context.getFromUserId();
        if (!gameClient.getGameConfig().isForceAllowTeamRequest() && !gameClient.getAllowTeamUsers().contains(userId)) {
            return;
        }
        gameClient.acceptTeam(userId);
        System.out.println("同意入队" + userId);
    }
}
