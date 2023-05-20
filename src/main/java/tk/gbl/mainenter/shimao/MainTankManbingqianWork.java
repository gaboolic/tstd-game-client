package tk.gbl.mainenter.shimao;

import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.script.pass.PubanjinAction;
import tk.gbl.core.script.pass.XuzhouKuashengAction;
import tk.gbl.core.script.work.shimao.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date: 2017/5/6
 * Time: 9:13
 *
 * @author Tian.Dong
 */
public class MainTankManbingqianWork {
    final static GameClient gameClient1 = new GameClient();
    final static GameClient gameClient2 = new GameClient();
    final static GameClient gameClient3 = new GameClient();

    public static void main(String[] args) throws IOException {
//        int initMapId = 12001;
        int initMapId = 0;

        gameClient1.setIndex(1);
        gameClient1.setConsoleOutput(false);
        gameClient1.setFileOutput(true);
        gameClient1.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient1.setUsername("wp47810");
        gameClient1.setPassword(ProcessConstant.password);
        gameClient1.setInitMapId(initMapId);
        gameClient1.init();
        gameClient1.setTeamLeaderId(0);

        gameClient2.setIndex(2);
        gameClient2.setConsoleOutput(false);
        gameClient2.setFileOutput(true);
        gameClient2.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient2.setUsername("wp47812");
        gameClient2.setPassword(ProcessConstant.password);
        gameClient2.setInitMapId(initMapId);
        gameClient2.init();
        gameClient2.setTeamLeaderId(0);

        gameClient3.setIndex(3);
        gameClient3.setConsoleOutput(false);
        gameClient3.setFileOutput(true);
        gameClient3.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient3.setUsername("wp47813");
        gameClient3.setPassword(ProcessConstant.password);
        gameClient3.setInitMapId(initMapId);
        gameClient3.init();
        gameClient3.setTeamLeaderId(0);


        String allAccountFileName = "account_25.txt";
        String accountDoneFileName = "account_manbingqian.txt";

        List<String> gameClient25LevelList = getDoneGameClientList(accountDoneFileName);
        Queue<GameClient> gameClientList = new LinkedList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(allAccountFileName)));
        String line = null;
        while ((line = fileReader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            GameClient gameClient = new GameClient();
            gameClient.setUsername(line.split(" ")[0]);
            gameClient.setPassword("123456");
            gameClientList.add(gameClient);
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(accountDoneFileName), true));

        while (gameClientList.size() > 0) {
            GameClient gameClient = gameClientList.poll();
            if (gameClient == null) {
                break;
            }
            while (gameClient25LevelList.contains(gameClient.getUsername())) {
                gameClient = gameClientList.poll();
                if (gameClient == null) {
                    break;
                }
            }
            if (gameClient == null) {
                break;
            }
            workSingle(gameClient, bufferedWriter, gameClient1, gameClient2, gameClient3);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bufferedWriter.close();
    }

    public static void workSingle(GameClient gameClient, BufferedWriter bufferedWriter, GameClient... gameClients) throws IOException {
        System.out.println("本次执行" + gameClient.getUsername());
        gameClient.setIndex(5);
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient.setInitMapId(0);
        gameClient.init();
        for (GameClient bigGameClient : gameClients) {
            gameClient.getAllowTeamUsers().add(bigGameClient.getUserId());
            bigGameClient.setTeamLeaderId(gameClient.getUserId());
        }
        gameClient.setTeamLeaderId(gameClient.getUserId());


        while (!gameClient.isLoginSuccess()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("登录成功");
        if (gameClient.getCurrentMapId() != gameClient1.getCurrentMapId()) {
            gameClient1.moveFar(gameClient.getCurrentMapId());
        }
        if (gameClient.getCurrentMapId() != gameClient2.getCurrentMapId()) {
            gameClient2.moveFar(gameClient.getCurrentMapId());
        }
        if (gameClient.getCurrentMapId() != gameClient3.getCurrentMapId()) {
            gameClient3.moveFar(gameClient.getCurrentMapId());
        }

        //组队
        while (gameClient.getTeamUsers().size() != gameClient.getAllowTeamUsers().size()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("组队成功");

        boolean success = false;
        while (!success) {
            try {
                doFlow(gameClient);
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        System.out.println("all done!!!");
        gameClient.close();

        bufferedWriter.write(gameClient.getUsername());
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    private static void doFlow(GameClient gameClient) throws IOException {
        gameClient.doScriptAction(new T0_getWorkMaterialAction());

        gameClient.doScriptAction(new XuzhouKuashengAction());
        gameClient.doScriptAction(new PubanjinAction());

        gameClient.doScriptAction(new T1_PingmanzhizhangtuAction());
        gameClient.doScriptAction(new T2_lvkaijishouyongchangAction());
        gameClient.doScriptAction(new T3_1manbingqian_zhugeliangdianbingzhengnanmanAction());


        gameClient.doScriptAction(new T3_2manbinghou_zhugeliangdianbingzhengnanmanAction());

    }

    private static List<String> getDoneGameClientList(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
        List<String> usernameList = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            usernameList.add(line);
        }
        reader.close();
        return usernameList;
    }
}
