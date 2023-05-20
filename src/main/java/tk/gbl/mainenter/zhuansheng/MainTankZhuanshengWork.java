package tk.gbl.mainenter.zhuansheng;

import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.script.work.zhuansheng.*;

import java.io.IOException;

/**
 * Date: 2017/5/6
 * Time: 9:13
 *
 * @author Tian.Dong
 */
public class MainTankZhuanshengWork {
    final static GameClient gameClient1 = new GameClient();
    final static GameClient gameClient2 = new GameClient();
    final static GameClient gameClient3 = new GameClient();
    final static GameClient gameClient4 = new GameClient();

    public static void main(String[] args) throws IOException {
        String finalUsername = "WP00114022";
//        String finalUsername = "114022";//114022
//        String finalUsername = "wp115037";
        String finalPassword = ProcessConstant.p55;

        final GameClient gameClient = new GameClient();
        gameClient.setIndex(5);
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient.setUsername(finalUsername);
        gameClient.setPassword(finalPassword);
        gameClient.setInitMapId(0);
        gameClient.init();
        gameClient.setTeamLeaderId(gameClient.getUserId());
        gameClient.getAllowTeamUsers().add(47809L);
        gameClient.getAllowTeamUsers().add(47810L);
        gameClient.getAllowTeamUsers().add(47812L);
        gameClient.getAllowTeamUsers().add(47813L);

        gameClient1.setIndex(1);
        gameClient1.setConsoleOutput(false);
        gameClient1.setFileOutput(true);
        gameClient1.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient1.setUsername("wp47810");
        gameClient1.setPassword(ProcessConstant.password);
        gameClient1.setInitMapId(0);
        gameClient1.init();
        gameClient1.setTeamLeaderId(gameClient.getUserId());

        gameClient2.setIndex(2);
        gameClient2.setConsoleOutput(false);
        gameClient2.setFileOutput(true);
        gameClient2.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient2.setUsername("wp47812");
        gameClient2.setPassword(ProcessConstant.password);
        gameClient2.setInitMapId(0);
        gameClient2.init();
        gameClient2.setTeamLeaderId(gameClient.getUserId());

        gameClient3.setIndex(3);
        gameClient3.setConsoleOutput(false);
        gameClient3.setFileOutput(true);
        gameClient3.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient3.setUsername("wp47813");
        gameClient3.setPassword(ProcessConstant.password);
        gameClient3.setInitMapId(0);
        gameClient3.init();
        gameClient3.setTeamLeaderId(gameClient.getUserId());

        gameClient4.setIndex(4);
        gameClient4.setConsoleOutput(false);
        gameClient4.setFileOutput(true);
        gameClient4.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient4.setUsername("wp47809");
        gameClient4.setPassword(ProcessConstant.password);
        gameClient4.setInitMapId(0);
        gameClient4.init();
        gameClient4.setTeamLeaderId(gameClient.getUserId());


        while(!gameClient.isLoginSuccess()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("登录成功");
        if(gameClient.getCurrentMapId() == 12001) {
            gameClient.segmentMoveTo(400, 1600);
        }

        //组队
        waitJoinTeam(gameClient);
        System.out.println("组队成功");
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
//        gameClient.doScriptAction(new Z1_sipanguAction());
//        gameClient.doScriptAction(new Z2_sicangqiongAction());
        gameClient.doScriptAction(new Z3_pantaoyanshouAction());
        gameClient.doScriptAction(new Z4_yaochiqiuxianjia());
        gameClient.doScriptAction(new Z5_shenmideqinruzhe());
        gameClient.doScriptAction(new Z6_weijizhuanji());
        gameClient.moveFar(59411);
//        gameClient.moveFar(12001);
        System.out.println("all done!!!");

        gameClient.close();
        gameClient1.close();
        gameClient2.close();
        gameClient3.close();
        gameClient4.close();
    }

    private static void waitJoinTeam(GameClient gameClient) {
        gameClient1.waitForLoginSuccess();
        gameClient2.waitForLoginSuccess();
        gameClient3.waitForLoginSuccess();
        gameClient4.waitForLoginSuccess();
        if (gameClient.getCurrentMapId() != gameClient1.getCurrentMapId()) {
            gameClient1.moveFar(gameClient.getCurrentMapId());
        }
        if (gameClient.getCurrentMapId() != gameClient2.getCurrentMapId()) {
            gameClient2.moveFar(gameClient.getCurrentMapId());
        }
        if (gameClient.getCurrentMapId() != gameClient3.getCurrentMapId()) {
            gameClient3.moveFar(gameClient.getCurrentMapId());
        }
        if (gameClient.getCurrentMapId() != gameClient4.getCurrentMapId()) {
            gameClient4.moveFar(gameClient.getCurrentMapId());
        }

        //组队
        while (gameClient.getTeamUsers().size() != gameClient.getAllowTeamUsers().size()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
