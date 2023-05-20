package tk.gbl.core.listener;

import tk.gbl.bean.Context;
import tk.gbl.core.GameClient;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * Date: 2017/4/6
 * Time: 17:48
 *
 * @author Tian.Dong
 */
public class TeamDismissListener extends BaseListener {
    @Override
    public void doAction(final GameClient gameClient, Context context) throws IOException {
        new Thread() {
            public void run() {
                if (gameClient.getUserId() == gameClient.getTeamLeaderId()) {
                    return;
                }
                if (gameClient.getTeamLeaderId() == 0) {
                    return;
                }
                while (!gameClient.isJoinTeam()) {
                    if(gameClient.getTeamLeaderId() != gameClient.getUserId()) {
                        try {
                            OutputUtil.output("请求:" + gameClient.getUserId() + "尝试申请入队" + gameClient.getTeamLeaderId(), gameClient);
                            gameClient.joinTeam(gameClient.getTeamLeaderId());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


}
