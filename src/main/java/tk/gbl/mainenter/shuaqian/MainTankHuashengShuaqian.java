package tk.gbl.mainenter.shuaqian;

import tk.gbl.core.GameClient;
import tk.gbl.core.script.PickUpPearAction;
import tk.gbl.core.script.ThrowPearAction;
import tk.gbl.core.script.work.shuaqian.PickUpHuashengAction;
import tk.gbl.core.script.work.shuaqian.ThrowHuashengAction;

import java.io.IOException;

/**
 * 115560
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class MainTankHuashengShuaqian {
    public static void main(String[] args) throws IOException {
        args = new String[1];
//        String str = "wp115557,123456;wp115558,123456;-wp114244;wp114246;wp115559;wp115560;wp115561";
//        String str = "wp115558,123456;wp115559,123456;-wp114243;wp114244;wp114245;wp114246;";
        String str = "wp115558,123456;wp115559,123456;-wp114240;wp114243;wp114244;wp114245;wp114246";
        args[0] = str;
        final String rengUsernames = args[0].split("-")[0];
        final String shuaqianUsernames = args[0].split("-")[1];

        final long teamLeaderId = Integer.parseInt(rengUsernames.split(";")[0].split(",")[0].substring(2));
        long sleepTime = 5000 / shuaqianUsernames.split(";").length;
        //捡东西3秒 贩卖4秒
        System.out.println(sleepTime);
        for (final String singleReng : rengUsernames.split(";"))
            new Thread() {
                public void run() {
                    GameClient gameClient = new GameClient();
                    gameClient.getGameConfig().setQuickNPCDialog(false);
                    gameClient.getGameConfig().setForceAllowTeamRequest(true);
                    gameClient.setConsoleOutput(false);
                    gameClient.setFileOutput(false);
                    gameClient.setIndex(1);
                    gameClient.setInitMapId(12064);
                    gameClient.setUsername(singleReng.split(",")[0]);
                    gameClient.setPassword(singleReng.split(",")[1]);
                    gameClient.init();
                    gameClient.setTeamLeaderId(gameClient.getUserId());

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (true) {
                        gameClient.waitForLoginSuccess();
                        try {
                            gameClient.doScriptAction(new ThrowHuashengAction());
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

        int shuaqianCount = shuaqianUsernames.split(";").length;
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
                    gameClient.init();
                    gameClient.setTeamLeaderId(gameClient.getUserId());

                    gameClient.waitForLoginSuccess();
                    while (true) {
                        gameClient.waitForLoginSuccess();
                        try {
                            if (gameClient.getRoleInfo().getMoney() > 9900000) {
                                try {
                                    Thread.sleep(10000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                continue;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            gameClient.doScriptAction(new PickUpHuashengAction(teamLeaderId));
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

            try {
                Thread.sleep(17000 / shuaqianCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
