package tk.gbl.mainenter.activity;

import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.frame.MainFrame;
import tk.gbl.mainenter.MainTankJianlaji;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 来袭
 * 47809高飞   龙王 庞统 祝融
 * 47810布鲁托 龙王 陈宫 黑屠
 * 47813唐纳德 龙王 曹睿 黑屠
 *
 * 27166嗷小毛 龙王 曹冲 龙王
 * 27167嗷大毛 龙王
 * 38253风MM 徐庶
 * 39259火MM 龙王 张梁
 * 39260地MM 杨修
 * <p/>
 * Date: 2017/5/6
 * Time: 9:13
 *
 * @author Tian.Dong
 */
public class MainTankLaixiActivityWork {
    final static GameClient gameClient1 = new GameClient();
    final static GameClient gameClient2 = new GameClient();
    final static GameClient gameClient3 = new GameClient();
    final static GameClient gameClient4 = new GameClient();
    final static GameClient gameClient5 = new GameClient();

    final static int npcId = 8;

    public static void main(final String[] args) throws IOException {
        /*new Thread(){
            public void run() {
                try {
                    MainTankJianlaji.main(args);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/

        gameClient1.getGameConfig().setNotFightReconnect(true);
        gameClient1.setIndex(1);
        gameClient1.setConsoleOutput(true);
        gameClient1.setFileOutput(true);
        gameClient1.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient1.getGameConfig().setNotFightReconnectTime(480000);
        gameClient1.setUsername("wp39259");
        gameClient1.setPassword(ProcessConstant.password);
        gameClient1.setInitMapId(0);
        gameClient1.init();
        gameClient1.setTeamLeaderId(gameClient1.getUserId());
        gameClient1.getAllowTeamUsers().add(47810L);
        gameClient1.getAllowTeamUsers().add(47813L);
        gameClient1.getAllowTeamUsers().add(27166L);
        gameClient1.getAllowTeamUsers().add(47809L);

        gameClient2.setIndex(2);
        gameClient2.setConsoleOutput(false);
        gameClient2.setFileOutput(false);
        gameClient2.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient2.getGameConfig().setNotFightReconnectTime(480000);
        gameClient2.setUsername("wp47810");
        gameClient2.setPassword(ProcessConstant.password);
        gameClient2.setInitMapId(0);
        gameClient2.init();
        gameClient2.setTeamLeaderId(gameClient1.getUserId());

        gameClient3.setIndex(3);
        gameClient3.setConsoleOutput(false);
        gameClient3.setFileOutput(false);
        gameClient3.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient3.getGameConfig().setNotFightReconnectTime(480000);
        gameClient3.setUsername("wp47813");
        gameClient3.setPassword(ProcessConstant.password);
        gameClient3.setInitMapId(0);
        gameClient3.init();
        gameClient3.setTeamLeaderId(gameClient1.getUserId());

        gameClient4.setIndex(4);
        gameClient4.setConsoleOutput(false);
        gameClient4.setFileOutput(false);
        gameClient4.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient4.getGameConfig().setNotFightReconnectTime(480000);
        gameClient4.setUsername("wp27166");
        gameClient4.setPassword(ProcessConstant.password);
        gameClient4.setInitMapId(0);
        gameClient4.init();
        gameClient4.setTeamLeaderId(gameClient1.getUserId());

        gameClient5.setIndex(5);
        gameClient5.setConsoleOutput(false);
        gameClient5.setFileOutput(false);
        gameClient5.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient5.getGameConfig().setNotFightReconnectTime(480000);
        gameClient5.setUsername("wp47809");
        gameClient5.setPassword(ProcessConstant.password);
        gameClient5.setInitMapId(0);
        gameClient5.init();
        gameClient5.setTeamLeaderId(gameClient1.getUserId());

        GameTeam gameTeam = new GameTeam();
        gameClient1.setGameTeam(gameTeam);
        gameTeam.setTeamLeader(gameClient1);
        List<GameClient> gameClientList = new ArrayList<>();
        gameClientList.add(gameClient2);
        gameClientList.add(gameClient3);
        gameClientList.add(gameClient4);
        gameClientList.add(gameClient5);
        gameTeam.setGameClientList(gameClientList);
        MainFrame mainFrame = new MainFrame();
        gameTeam.setMainFrame(mainFrame);

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
        gameClient1.moveFar(12421);

        //组队
        waitJoinTeam(gameClient1);
        System.out.println("组队成功");

        boolean isDoing = true;
        while (isDoing) {
            try {
                gameClient1.getAllowTeamUsers().clear();
                gameClient1.getAllowTeamUsers().add(47809L);
                gameClient1.getAllowTeamUsers().add(47810L);
                gameClient1.getAllowTeamUsers().add(47813L);
                gameClient1.getAllowTeamUsers().add(27166L);

                System.out.println("waitJoinTeam");
                waitJoinTeam(gameClient1);

                System.out.println("doFlow");
                doFlow(gameClient1);

                System.out.println("退队领奖");
                gameClient1.waitForLoginSuccess();
                gameClient1.exitTeam();
                gameClient1.getAllowTeamUsers().clear();
                gameClient1.getTeamUsers().clear();
                Thread.sleep(1000);

                gameClient1.getChooseQueue().clear();
                gameClient1.getChooseQueue().add(2);
                gameClient1.clickNPC(npcId);

                gameClient2.getChooseQueue().clear();
                gameClient2.getChooseQueue().add(2);
                gameClient2.clickNPC(npcId);

                gameClient3.getChooseQueue().add(2);
                gameClient3.clickNPC(npcId);

                gameClient4.getChooseQueue().clear();
                gameClient4.getChooseQueue().add(2);
                gameClient4.clickNPC(npcId);

                gameClient5.getChooseQueue().clear();
                gameClient5.getChooseQueue().add(2);
                gameClient5.clickNPC(npcId);
                System.out.println("全部领完奖");
                Thread.sleep(1000);
                for (int i = 0; i < 2; i++) {
                    gameClient1.autoUseAndThrow();
                    gameClient2.autoUseAndThrow();
                    gameClient3.autoUseAndThrow();
                    gameClient4.autoUseAndThrow();
                    gameClient5.autoUseAndThrow();
                    Thread.sleep(500);
                }
                System.out.println("autoUseAndThrow结束");
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            System.out.println("刷完一次");
        }

        System.out.println("all done!!!");
    }

    private static void doFlow(GameClient gameClient1) throws IOException {
//        gameClient1.segmentMoveTo(570, 430);
        gameClient1.segmentMoveTo(870, 560);
        gameClient1.getChooseQueue().clear();
        gameClient1.getChooseQueue().add(1);
        gameClient1.clickNPC(npcId);
        gameClient1.simpleSleep();
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
