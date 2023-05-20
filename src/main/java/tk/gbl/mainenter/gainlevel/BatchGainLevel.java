package tk.gbl.mainenter.gainlevel;

import tk.gbl.bean.Point;
import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameClientMap;
import tk.gbl.core.GameTeam;
import tk.gbl.core.frame.MainFrame;
import tk.gbl.core.script.ShoujingpoAction;
import tk.gbl.core.script.pass.JieQiaoAction;
import tk.gbl.core.script.pass.XuzhouKuashengAction;
import tk.gbl.core.script.taskgainlevel.HuguanTiejiaAction;
import tk.gbl.core.script.taskgainlevel.PaoyeAction;
import tk.gbl.util.OutputUtil;

import java.io.*;
import java.util.*;

/**
 * Date: 2017/4/28
 * Time: 10:44
 *
 * @author Tian.Dong
 */
public class BatchGainLevel {
    static final GameTeam gameTeam = new GameTeam();
    static List<GameClient> bigGameClientList = new ArrayList<>();

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("ShutdownHook");
                for (Map.Entry<Long, GameClient> entry : GameClientMap.getGameClientMap().entrySet()) {
                    GameClient gameClient = entry.getValue();
                    if (gameClient != null) {
                        gameClient.close();
                    }
                }
            }
        });
//        new MainFrame();
//        String username = "WP0047813";
//        String password = ProcessConstant.password;

//        String username = "WP00114018,WP41590";
//        String password = "";

//        String username = "WP27167,WP27166";
//        String password = "";

//        String username = "WP86601,WP86602";
//        String password = "137474688a,137474688a";

        String username = "WP86603,WP86604";
        String password = "137474688a,137474688a";
        int serverIndex = 2;

        String allAccountFileName = "account2qu.txt";
        String trainingAccountFileName = "account2qu_training.txt";
        String account25FileName = "account2qu_25.txt";

//        String allAccountFileName = "account.txt";
//        String trainingAccountFileName = "account_training.txt";
//        String account25FileName = "account_25.txt";
        if (args != null && args.length == 2) {
            username = args[0];
            password = args[1];
        }

        String[] usernames = username.split(",");
        String[] passwords = password.split(",");
        //设置队长
        GameClient gameClient1 = new GameClient();
        gameTeam.setTeamLeader(gameClient1);
        gameClient1.setGameTeam(gameTeam);
        gameClient1.setServerIndex(serverIndex);
        gameClient1.setIndex(1);
        gameClient1.setConsoleOutput(true);
        gameClient1.setFileOutput(true);
        gameClient1.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient1.getGameConfig().setNotFightReconnectTime(60000);
        gameClient1.getGameConfig().setQingliu(true);
        gameClient1.getGameConfig().setNotFightReconnect(true);
        gameClient1.setUsername(usernames[0]);
        gameClient1.setPassword(passwords[0]);
        gameClient1.setInitMapId(0);
        gameClient1.init();
        gameClient1.setTeamLeaderId(gameClient1.getUserId());
        while (!gameClient1.isLoginSuccess()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //设置其他大号，如果有的话

        if (usernames.length > 1) {
            for (int i = 1; i < usernames.length; i++) {
                String singleUsername = usernames[i];
                String singlePassword = passwords[i];

                GameClient bigGameClient = new GameClient();
                bigGameClient.setServerIndex(serverIndex);
                bigGameClient.setIndex(i + 1);
                bigGameClient.setConsoleOutput(false);
                bigGameClient.setFileOutput(false);
                bigGameClient.getGameConfig().setGameConfigType(GameConfigType.work);
                bigGameClient.setUsername(singleUsername);
                bigGameClient.setPassword(singlePassword);
                bigGameClient.setInitMapId(0);
                bigGameClient.init();
                bigGameClient.setTeamLeaderId(gameClient1.getUserId());
                while (!bigGameClient.isLoginSuccess()) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                bigGameClientList.add(bigGameClient);
            }
        }
        //其他大号队长设置为gameClient1
        if (bigGameClientList.size() > 1) {
            for (int i = 0; i < bigGameClientList.size(); i++) {
                GameClient bigGameClient = bigGameClientList.get(i);
                bigGameClient.setTeamLeaderId(gameClient1.getUserId());
                gameClient1.getAllowTeamUsers().add(bigGameClient.getUserId());
            }
        }


        //训练好的小号列表
        List<String> gameClient25LevelList = getGameClientList(account25FileName);
        //所有需要训练的小号队列
        Queue<GameClient> gameClientList = new LinkedList<>();
//        Stack<GameClient> gameClientList = new Stack<>();
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(new File(allAccountFileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fileReader == null) {
            return;
        }

        while (true) {
            String line = null;
            try {
                line = fileReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            if (line == null) {
                break;
            }
            if (line.equals("")) {
                continue;
            }
            line = line.trim();
            GameClient gameClient = new GameClient();
            gameClient.setUsername(line.split(" ")[0]);
//            gameClient.setPassword(line.split(" ")[1]);
            gameClient.setPassword("123456");
            gameClientList.add(gameClient);
        }


        while (gameClientList.size() > 0) {
            gameClient1.getAllowTeamUsers().clear();
            gameClient1.getTeamUsers().clear();
            for (GameClient gameClient : bigGameClientList) {
                gameClient1.getAllowTeamUsers().add(gameClient.getUserId());
                gameClient1.getTeamUsers().add(gameClient.getUserId());
            }

            //本次训练的小号
            List<GameClient> teamMemberList = new ArrayList<>();
            for (int i = 0; i < 4 - bigGameClientList.size(); i++) {
                GameClient gameClient = gameClientList.poll();
                if (gameClient == null) {
                    break;
                }
                //正在训练的小号列表
                List<String> gameClientTrainingList = getGameClientList(trainingAccountFileName);

                //取小号
                while (true) {
                    gameClient = gameClientList.poll();
                    if (gameClient == null) {
                        break;
                    }
                    //不是25级，并且也没有训练中
                    if (!gameClient25LevelList.contains(gameClient.getUsername())
                            && !gameClientTrainingList.contains(gameClient.getUsername())) {
                        break;
                    }
                }
                if (gameClient == null) {
                    break;
                }
                gameClient.setServerIndex(serverIndex);
                gameClient.setConsoleOutput(false);
                gameClient.setFileOutput(true);
                gameClient.setIndex(5 - i);
                gameClient.setTeamLeaderId(gameClient1.getUserId());
                gameClient.setInitMapId(0);
                gameClient.getGameConfig().setNotFightReconnect(true);
                teamMemberList.add(gameClient);
                gameClient1.getAllowTeamUsers().add(gameClient.getUserId());
            }
            if (teamMemberList.size() < 4 - bigGameClientList.size()) {
                System.out.println("剩余小号不足训练个数" + (4 - bigGameClientList.size()));
                break;
            }
            StringBuilder teamMemberString = new StringBuilder();
            for (GameClient gameClient : teamMemberList) {
                teamMemberString.append(gameClient.getUsername());
                teamMemberString.append(" ");
            }
            System.out.println("本次练级:" + teamMemberString.toString());
            for (GameClient gameClient : teamMemberList) {
                try {
                    BufferedWriter trainingBufferedWriter = new BufferedWriter(new FileWriter(new File(trainingAccountFileName), true));
                    trainingBufferedWriter.write(gameClient.getUsername());
                    trainingBufferedWriter.write("\r\n");
                    trainingBufferedWriter.flush();
                    trainingBufferedWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (GameClient gameClient : teamMemberList) {
                gameClient.init();
            }
            for (GameClient gameClient : teamMemberList) {
                gameClient.waitForLoginSuccess();
                gameClient.autoUseAndThrow();
            }
            List<GameClient> gameTeamGameClientList = new ArrayList<>();
            gameTeamGameClientList.addAll(bigGameClientList);
            gameTeamGameClientList.addAll(teamMemberList);
            gameTeam.setGameClientList(gameTeamGameClientList);

            System.out.println("开始检查队员是否和队长在一个地图");
            //检查队员是否和队长在一个地图
            while (true) {
                boolean isInOneMap = true;
                for (GameClient gameClient : gameTeamGameClientList) {
                    gameClient.waitForLoginSuccess();
                    if (gameClient.getCurrentMapId() != gameClient1.getCurrentMapId()) {
                        isInOneMap = false;
                        System.out.println(gameClient.getUserId() + "gameClient moveFar 12001");
                        gameClient.moveFar(12001);
                    }
                }
                if (!isInOneMap) {
                    gameClient1.waitForLoginSuccess();
                    gameClient1.moveFar(12001);
                }
                if (isInOneMap) {
                    break;
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("while (gameClient1.getTeamUsers().size() != 4) ");
            //组队
            while (gameClient1.getTeamUsers().size() != 4) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("if (gameClient1.getCurrentMapId() != 12432) ");
            if (gameClient1.getCurrentMapId() != 12432) {
                try {
                    gameClient1.doScriptAction(new JieQiaoAction());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            OutputUtil.output("1-25打铁甲", gameClient1, true);
            gameClient1.waitJoinTeam();
            //没有25级
            while (!allRoleLevel(gameTeam,25)) {
                try {
                    if (gameClient1.getTeamUsers().size() == 4 && gameClient1.isLoginSuccess()) {
                        gameClient1.doScriptAction(new HuguanTiejiaAction() {
                            @Override
                            public boolean customJudge() {
                                return allRoleLevel(gameTeam, 25);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    gameClient1.clear();
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameClient1.waitForLoginSuccess();
                boolean isInOneMap = true;
                for (GameClient gameClient : gameTeam.getGameClientList()) {
                    gameClient.waitForLoginSuccess();
                    if (gameClient1.getCurrentMapId() != gameClient.getCurrentMapId()) {
                        isInOneMap = false;
                        if (gameClient.getCurrentMapId() == 12003) {
                            try {
                                gameClient.exitTeam();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            gameClient.setJoinTeam(false);
                        }
                        gameClient.moveFar(12001);
                    }
                }
                if (!isInOneMap) {
                    gameClient1.moveFar(12001);
                }

                if (gameClient1.getTeamUsers().size() < 4) {

                }
            }

            OutputUtil.output("全部25级了", gameClient1, true);
            //全部25级了
            gameClient1.moveFar(12001);

            for (GameClient gameClient25Level : teamMemberList) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(account25FileName), true));
                    bufferedWriter.write(gameClient25Level.getUsername());
                    bufferedWriter.write("\r\n");
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for (GameClient gameClient : teamMemberList) {
                try {
                    gameClient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            gameClient1.getAllowTeamUsers().clear();
            gameClient1.getTeamUsers().clear();
        }
        OutputUtil.output("全部运行完毕", gameClient1, true);
    }

    private static boolean allRoleLevel(GameTeam gameTeam, int level) {
        boolean isAll25 = true;
        if (gameTeam.getTeamLeader().getRoleInfo().getLevel() < level) {
            isAll25 = false;
        }
        for (GameClient gameClient : gameTeam.getGameClientList()) {
            if (gameClient.getRoleInfo().getLevel() < level) {
                isAll25 = false;
            }
        }
        return isAll25;
    }

    private static List<String> getGameClientList(String fileName) {
        File file = new File(fileName);
        List<String> usernameList = new ArrayList<>();
        if (!file.exists()) {
            return usernameList;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                usernameList.add(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usernameList;
    }

}
