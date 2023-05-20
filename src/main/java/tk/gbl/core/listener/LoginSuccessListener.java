package tk.gbl.core.listener;

import tk.gbl.bean.BagItem;
import tk.gbl.bean.Context;
import tk.gbl.core.AutoUseItemThread;
import tk.gbl.core.GameClient;
import tk.gbl.core.script.InitToZJGCAction;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * Date: 2017/4/6
 * Time: 17:48
 *
 * @author Tian.Dong
 */
public class LoginSuccessListener extends BaseListener {
    @Override
    public void doAction(final GameClient gameClient, Context context) throws IOException {
        if (gameClient.getCurrentMapId() >= 10965) {
            gameClient.setLoginSuccess(true);
        }
        new Thread() {
            public void run() {
                System.out.println("LoginSuccessListener");
                if (gameClient.isNewRole()) {
                    OutputUtil.output("新角色，执行InitToZJGCAction" + gameClient.getCurrentMapId(), gameClient, true);
                    try {
                        gameClient.doScriptAction(new InitToZJGCAction());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    gameClient.setLoginSuccess(true);
                }
                try {
                    gameClient.openJiguan();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                OutputUtil.output("登录成功" + gameClient.getCurrentMapId(), gameClient, true);

                if (gameClient.getCurrentMapId() < 10965) {
                    gameClient.setNewRole(true);
                    OutputUtil.output("getCurrentMapId<10965执行InitToZJGCAction" + gameClient.getCurrentMapId(), gameClient, true);
                    try {
                        gameClient.doScriptAction(new InitToZJGCAction());
                        gameClient.setLoginSuccess(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                gameClient.autoUseAndThrow();
                new AutoUseItemThread(gameClient).start();

                if (gameClient.getInitMapId() != 0 && gameClient.getInitMapId() != gameClient.getCurrentMapId()) {
                    OutputUtil.output("登录成功,开始移动到" + gameClient.getInitMapId(), gameClient, true);
                    try {
                        gameClient.moveFar(gameClient.getInitMapId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gameClient.setInitMapId(0);
                }

                if (gameClient.getUserId() == gameClient.getTeamLeaderId()) {
                    if (gameClient.getAllowTeamUsers().size() == 0) {
                        try {
                            if (gameClient.getGameTeam() != null) {
                                gameClient.getGameTeam().teamSuccess();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                }
                if (gameClient.getTeamLeaderId() == 0) {
                    return;
                }
                OutputUtil.output("while (!gameClient.isJoinTeam())前", gameClient, true);
                while (gameClient.isLoginSuccess() && !gameClient.isJoinTeam()) {
                    try {
                        OutputUtil.output("请求:" + gameClient.getUserId() + "尝试申请入队" + gameClient.getTeamLeaderId(), gameClient,true);
                        gameClient.joinTeam(gameClient.getTeamLeaderId());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                OutputUtil.output("while (!gameClient.isJoinTeam())后", gameClient, true);
            }
        }.start();
    }


}
