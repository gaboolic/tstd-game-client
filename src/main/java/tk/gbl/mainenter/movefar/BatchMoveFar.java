package tk.gbl.mainenter.movefar;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.fightai.QingliuAi;
import tk.gbl.core.script.ShoujingpoAction;
import tk.gbl.core.script.pass.JieQiaoAction;
import tk.gbl.util.FileReadUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Date: 2017/6/7
 * Time: 17:45
 *
 * @author Tian.Dong
 */
public class BatchMoveFar {
    public static void main(String[] args) throws IOException {
        //594     148
        int serverIndex = 1;
        String username = "wp27166,wp27167";
        String password = "234567,234567";

//        int serverIndex = 2;
//        String username = "WP86601,WP86602,WP86603";
//        String password = "137474688a,137474688a,137474688a";

        String allAccountFileName = "account_createRole.txt";
        String doneAccountFileName = "account_createRole_tiejia.txt";

        //        String allAccountFileName = "account_trade_11xin.txt";
//        String doneAccountFileName = "account_pickup_suipiancailiao_move.txt";

//        String allAccountFileName = "account2qu_25.txt";
//        String doneAccountFileName = "account_pickup_2q_suipiancailiao_move.txt";


        String[] usernames = username.split(",");
        String[] passwords = password.split(",");

        GameTeam gameTeam = new GameTeam();
        GameClient gameClient1 = new GameClient();
        gameTeam.setTeamLeader(gameClient1);
        gameClient1.setGameTeam(gameTeam);
        gameClient1.getGameConfig().setQingliu(true);
        gameClient1.setIndex(1);
        gameClient1.setServerIndex(serverIndex);
        gameClient1.setConsoleOutput(true);
        gameClient1.setFileOutput(true);
        gameClient1.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient1.setUsername(usernames[0]);
        gameClient1.setPassword(passwords[0]);
        gameClient1.setInitMapId(0);
        gameClient1.init();
        gameClient1.setTeamLeaderId(gameClient1.getUserId());
        gameClient1.waitForLoginSuccess();
        gameClient1.moveFar(12001);

        //设置其他大号，如果有的话
        List<GameClient> bigGameClientList = new ArrayList<>();
        if (usernames.length > 1) {
            for (int i = 1; i < usernames.length; i++) {
                String singleUsername = usernames[i];
                String singlePassword = passwords[i];

                GameClient bigGameClient = new GameClient();
                bigGameClient.setIndex(i + 1);
                bigGameClient.setServerIndex(serverIndex);
                bigGameClient.setConsoleOutput(false);
                bigGameClient.setFileOutput(true);
                bigGameClient.getGameConfig().setGameConfigType(GameConfigType.work);
                bigGameClient.setUsername(singleUsername);
                bigGameClient.setPassword(singlePassword);
                bigGameClient.setInitMapId(0);
                bigGameClient.init();
                bigGameClient.setTeamLeaderId(gameClient1.getUserId());
                bigGameClient.waitForLoginSuccess();
                bigGameClient.moveFar(12001);
                bigGameClientList.add(bigGameClient);
                gameClient1.getAllowTeamUsers().add(bigGameClient.getUserId());
            }
        }
        //其他大号队长设置为gameClient1
        if (bigGameClientList.size() > 1) {
            for (int i = 0; i < bigGameClientList.size(); i++) {
                GameClient bigGameClient = bigGameClientList.get(i);
                bigGameClient.setTeamLeaderId(gameClient1.getUserId());
            }
        }
        List<GameClient> teamGameClientList = new ArrayList<>();
        teamGameClientList.addAll(bigGameClientList);
        gameTeam.setGameClientList(teamGameClientList);


        System.out.println("开始！！！！");

        //训练好的小号列表
        List<String> gameClientDoneList = FileReadUtil.getDoneGameClientList(doneAccountFileName);
        LinkedList<GameClient> gameClientList = new LinkedList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(allAccountFileName)));
        String line = null;
        while ((line = fileReader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            GameClient gameClient = new GameClient();
            gameClient.setUsername(line.split(" ")[0]);
//            gameClient.setPassword(line.split(" ")[1]);
            gameClient.setPassword("123456");
            gameClientList.add(gameClient);
        }

        while (gameClientList.size() > 0) {
            System.out.println("进入while (gameClientList.size() > 0)");
            gameClient1.getAllowTeamUsers().clear();
            for (GameClient gameClient : bigGameClientList) {
                gameClient1.getAllowTeamUsers().add(gameClient.getUserId());
            }

            //本次训练的小号
            List<GameClient> teamMemberList = new ArrayList<>();
            for (int i = 0; i < 4 - bigGameClientList.size(); i++) {
                GameClient gameClient = gameClientList.poll();
                if (gameClient == null) {
                    break;
                }
                while (gameClientDoneList.contains(gameClient.getUsername())) {
                    gameClient = gameClientList.poll();
                    if (gameClient == null) {
                        break;
                    }
                }
                if (gameClient == null) {
                    break;
                }
                gameClient.setConsoleOutput(true);
                gameClient.setFileOutput(true);
                gameClient.setIndex(5 - i);
                gameClient.setServerIndex(serverIndex);
                gameClient.setTeamLeaderId(gameClient1.getUserId());
                gameClient.setInitMapId(0);
                gameClient.init();
                gameClient.waitForLoginSuccess();
                if (gameClient.getCurrentMapId() >= 25000) {
                    gameClient.close();
                    i--;
                    continue;
                }
                gameClient.moveFar(12001);
                teamMemberList.add(gameClient);
                gameClient1.getAllowTeamUsers().add(gameClient.getUserId());
            }


            StringBuilder teamMemberString = new StringBuilder();
            for (GameClient gameClient : teamMemberList) {
                teamMemberString.append(gameClient.getUsername());
                teamMemberString.append(" ");
            }
            System.out.println("本次练级:" + teamMemberString.toString());
            System.out.println(gameClient1.getAllowTeamUsers());
            System.out.println(gameClient1.getTeamUsers());

            List<GameClient> gameTeamGameClientList = new ArrayList<>();
            gameTeamGameClientList.addAll(bigGameClientList);
            gameTeamGameClientList.addAll(teamMemberList);
            gameTeam.setGameClientList(gameTeamGameClientList);
            gameTeam.setTeamLeader(gameClient1);

            //检查队员是否和队长在一个地图

            gameClient1.waitForLoginSuccess();
            for (GameClient gameClient : gameTeamGameClientList) {
                gameClient.waitForLoginSuccess();
            }
            gameClient1.waitJoinTeam();

            gameClient1.doScriptAction(new JieQiaoAction());
            System.out.println("开始向12432移动");

            gameClient1.moveFar(12432);
            System.out.println("移动到12432");

            for (GameClient gameClient : teamMemberList) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(doneAccountFileName), true));
                    bufferedWriter.write(gameClient.getUsername());
                    bufferedWriter.write("\r\n");
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    gameClient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            gameClient1.sleep(1000);
            gameClient1.getAllowTeamUsers().clear();
            for (GameClient gameClient : bigGameClientList) {
                gameClient.waitForLoginSuccess();
                gameClient1.getAllowTeamUsers().add(gameClient.getUserId());
            }
            System.out.println("移动完毕，等待大号组队");
            System.out.println("准备回涿郡");
            gameClient1.moveFar(12001);
            System.out.println("回到涿郡,执行完一个流程");
        }
        System.out.println("运行完毕");
    }
}
