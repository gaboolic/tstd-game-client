package tk.gbl.mainenter.activity;

import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.script.work.activity.NPCLeitaiAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/5/6
 * Time: 9:13
 *
 * @author Tian.Dong
 */
public class MainTankActivityNPCLeitaiWork {
    final static GameClient gameClient1 = new GameClient();
    final static GameClient gameClient2 = new GameClient();
    final static GameClient gameClient3 = new GameClient();
    final static GameClient gameClient4 = new GameClient();
    final static GameClient gameClient5 = new GameClient();

    public static void main(String[] args) throws IOException {

        GameTeam gameTeam = new GameTeam();
        gameTeam.setTeamLeader(gameClient1);
        List<GameClient> gameClientList = new ArrayList<>();
        gameClientList.add(gameClient2);
        gameClientList.add(gameClient3);
        gameClientList.add(gameClient4);
        gameClientList.add(gameClient5);
        gameTeam.setGameClientList(gameClientList);
        gameClient1.setGameTeam(gameTeam);
        gameClient1.getGameConfig().setQingliu(true);
        gameClient1.setIndex(1);
        gameClient1.setConsoleOutput(true);
        gameClient1.setFileOutput(true);
        gameClient1.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient1.setUsername("wp38253");
        gameClient1.setPassword(ProcessConstant.password);
        gameClient1.setInitMapId(0);
        gameClient1.setUserId(47809);
        gameClient1.init();
        gameClient1.setTeamLeaderId(gameClient1.getUserId());

        gameClient2.setIndex(2);
        gameClient2.setConsoleOutput(false);
        gameClient2.setFileOutput(false);
        gameClient2.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient2.setUsername("wp39260");
        gameClient2.setPassword(ProcessConstant.password);
        gameClient2.setInitMapId(0);
        gameClient2.init();
        gameClient2.setTeamLeaderId(gameClient1.getUserId());

        gameClient3.setIndex(3);
        gameClient3.setConsoleOutput(false);
        gameClient3.setFileOutput(true);
        gameClient3.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient3.setUsername("wp47813");
        gameClient3.setPassword(ProcessConstant.password);
        gameClient3.setInitMapId(0);
        gameClient3.init();
        gameClient3.setTeamLeaderId(gameClient1.getUserId());

        gameClient4.setIndex(4);
        gameClient4.setConsoleOutput(false);
        gameClient4.setFileOutput(false);
        gameClient4.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient4.setUsername("wp39259");
        gameClient4.setPassword(ProcessConstant.password);
        gameClient4.setInitMapId(0);
        gameClient4.init();
        gameClient4.setTeamLeaderId(gameClient1.getUserId());

        gameClient5.setIndex(5);
        gameClient5.setConsoleOutput(false);
        gameClient5.setFileOutput(false);
        gameClient5.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient5.setUsername("wp27167");
        gameClient5.setPassword(ProcessConstant.password);
        gameClient5.setInitMapId(0);
        gameClient5.init();
        gameClient5.setTeamLeaderId(gameClient1.getUserId());

        while (!gameClient1.isLoginSuccess()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("登录成功");
        if (gameClient1.getCurrentMapId() == 12001) {
            gameClient1.segmentMoveTo(400, 1600);
        }
        /*try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        gameClient1.getAllowTeamUsers().clear();
        gameClient1.getAllowTeamUsers().add(gameClient2.getUserId());
        gameClient1.getAllowTeamUsers().add(gameClient3.getUserId());
        gameClient1.getAllowTeamUsers().add(gameClient4.getUserId());
        gameClient1.getAllowTeamUsers().add(gameClient5.getUserId());

        //组队
        waitJoinTeam(gameClient1);
        System.out.println("组队成功");

        gameClient1.doScriptAction(new NPCLeitaiAction());

        System.out.println("all done!!!");
    }


    private static void waitJoinTeam(GameClient gameClient) {
        gameClient1.waitForLoginSuccess();
        gameClient2.waitForLoginSuccess();
        gameClient3.waitForLoginSuccess();
        gameClient4.waitForLoginSuccess();
        gameClient5.waitForLoginSuccess();
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
        if (gameClient.getCurrentMapId() != gameClient5.getCurrentMapId()) {
            gameClient5.moveFar(gameClient.getCurrentMapId());
        }

        //组队
        while (gameClient.getTeamUsers().size() != gameClient.getAllowTeamUsers().size()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
