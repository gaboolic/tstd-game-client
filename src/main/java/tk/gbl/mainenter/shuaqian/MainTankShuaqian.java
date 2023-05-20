package tk.gbl.mainenter.shuaqian;

import tk.gbl.core.GameClient;
import tk.gbl.core.script.PickUpPearAction;
import tk.gbl.core.script.ThrowPearAction;

import java.io.IOException;

/**
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class MainTankShuaqian {
    public static void main(String[] args) throws IOException {
        args = new String[1];
//        String str = "wp115557,123456;wp115558,123456;-wp114244;wp114246;wp115559;wp115560;wp115561";
        String str = "wp115558,123456;-wp114243;wp114244;wp114245;wp114246;";
//        String str = "wp115557,123456;-;";
        args[0] = str;
        final String rengUsernames = args[0].split("-")[0];
        final String shuaqianUsernames = args[0].split("-")[1];



        for (final String singleReng : rengUsernames.split(";"))
            new Thread() {
                public void run() {
                    GameClient gameClient = new GameClient();
                    gameClient.getGameConfig().setQuickNPCDialog(false);
                    gameClient.setConsoleOutput(false);
                    gameClient.setFileOutput(false);
                    gameClient.setIndex(1);
                    gameClient.setInitMapId(12064);
                    gameClient.setUsername(singleReng.split(",")[0]);
                    gameClient.setPassword(singleReng.split(",")[1]);
                    gameClient.init();

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (true) {
                        gameClient.waitForLoginSuccess();
                        try {
                            gameClient.doScriptAction(new ThrowPearAction());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();

        for (final String shuaqianUsernameSingle : shuaqianUsernames.split(";")) {
            new Thread() {
                public void run() {
                    GameClient gameClient = new GameClient();
                    gameClient.setConsoleOutput(false);
                    gameClient.setFileOutput(false);
                    gameClient.setIndex(2);
                    gameClient.setInitMapId(12064);
                    gameClient.setUsername(shuaqianUsernameSingle);
                    gameClient.setPassword("123456");
                    gameClient.setTeamLeaderId(0);
                    gameClient.init();

                    gameClient.waitForLoginSuccess();
                    while (true) {
                        gameClient.waitForLoginSuccess();

                        try {
                            gameClient.doScriptAction(new PickUpPearAction());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(gameClient.getRoleInfo().getMoney() > 9900000) {
                            break;
                        }
                    }

                }
            }.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
