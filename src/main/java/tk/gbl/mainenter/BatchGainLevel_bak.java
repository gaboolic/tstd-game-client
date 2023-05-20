package tk.gbl.mainenter;

import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.script.pass.JieQiaoAction;
import tk.gbl.core.script.taskgainlevel.HuguanTiejiaAction;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date: 2017/4/28
 * Time: 10:44
 *
 * @author Tian.Dong
 */
public class BatchGainLevel_bak {
    public static void main(String[] args) throws IOException {
        String username = "WP0047809";
        String password = ProcessConstant.password;

        String allAccountFileName = "account.txt";
        String trainingAccountFileName = "account_training.txt";
        String account25FileName = "account_25.txt";
        if (args != null && args.length == 2) {
            username = args[0];
            password = args[1];
        }

        String[] usernames = username.split(",");
        String[] passwords = password.split(",");
        final GameClient gameClient1 = new GameClient();
        for (int i = 0; i < usernames.length; i++) {
            String singleUsername = usernames[i];
            String singlePassword = passwords[i];
            gameClient1.setIndex(1);
            gameClient1.setConsoleOutput(false);
            gameClient1.setFileOutput(false);
            gameClient1.getGameConfig().setGameConfigType(GameConfigType.work);
            gameClient1.setUsername(username);
            gameClient1.setPassword(password);
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
        }

        List<String> gameClientTrainingList = getGameClientList(trainingAccountFileName);
        List<String> gameClient25LevelList = getGameClientList(account25FileName);
        Queue<GameClient> gameClientList = new LinkedList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(allAccountFileName)));
        String line = null;
        while ((line = fileReader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            GameClient gameClient = new GameClient();
            gameClient.setUsername(line.split(" ")[0]);
            gameClient.setPassword(line.split(" ")[1]);
            gameClientList.add(gameClient);
        }

        BufferedWriter trainingBufferedWriter = new BufferedWriter(new FileWriter(new File(trainingAccountFileName), true));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(account25FileName), true));

        while (gameClientList.size() > 0) {
            gameClient1.getAllowTeamUsers().clear();
            final GameTeam gameTeam = new GameTeam();
            List<GameClient> teamMemberList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                GameClient gameClient = gameClientList.poll();
                if (gameClient == null) {
                    break;
                }
                while (gameClientTrainingList.contains(gameClient.getUsername())) {
                    gameClient = gameClientList.poll();
                    if (gameClient == null) {
                        break;
                    }
                }
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
                gameClient.setConsoleOutput(false);
                gameClient.setFileOutput(false);
                gameClient.setIndex(i + 2);
                gameClient.setTeamLeaderId(gameClient1.getUserId());
                gameClient.setInitMapId(0);
                gameClient.init();
                teamMemberList.add(gameClient);
                gameClient1.getAllowTeamUsers().add(gameClient.getUserId());
            }
            if (teamMemberList.size() < 4) {
                break;
            }
            System.out.println("本次练级:" + gameClient1.getAllowTeamUsers());
            for (GameClient gameClient : teamMemberList) {
                trainingBufferedWriter.write(gameClient.getUsername());
                trainingBufferedWriter.newLine();
                trainingBufferedWriter.flush();
            }

            gameTeam.setGameClientList(teamMemberList);
            gameTeam.setTeamLeader(gameClient1);
            boolean isInOneMap = true;
            for (GameClient gameClient : teamMemberList) {
                while (!gameClient.isLoginSuccess()) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (gameClient.getCurrentMapId() != gameClient1.getCurrentMapId()) {
                    isInOneMap = false;
                    gameClient.moveFar(12001);
                }
            }
            if (!isInOneMap) {
                gameClient1.moveFar(12001);
            }

            //组队
            while (gameClient1.getTeamUsers().size() != 4) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (gameClient1.getCurrentMapId() != 12432) {
                gameClient1.doScriptAction(new JieQiaoAction());
            }

            //没有25级
            while (!gameTeam.all25()) {
                try {
                    if (gameClient1.getTeamUsers().size() == 4) {
                        gameClient1.doScriptAction(new HuguanTiejiaAction() {
                            @Override
                            public boolean customJudge() {
                                return gameTeam.all25();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isInOneMap = true;
                for (GameClient gameClient : gameTeam.getGameClientList()) {
                    if (gameClient1.getCurrentMapId() != gameClient.getCurrentMapId()) {
                        isInOneMap = false;
                        gameClient.moveFar(12001);
                    }
                }
                if (!isInOneMap) {
                    gameClient1.moveFar(12001);
                }
            }

            //全部25级了
            gameClient1.moveFar(12001);

            for (GameClient gameClient25Level : gameTeam.getGameClientList()) {
                bufferedWriter.write(gameClient25Level.getUsername());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

            gameTeam.closeTeamMember();
            gameClient1.getAllowTeamUsers().clear();
            gameClient1.getTeamUsers().clear();
        }
        System.out.println("运行完毕");
        bufferedWriter.close();
    }

    private static List<String> getGameClientList(String fileName) throws IOException {
        File file = new File(fileName);
        List<String> usernameList = new ArrayList<>();
        if (!file.exists()) {
            return usernameList;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = reader.readLine()) != null) {
            usernameList.add(line);
        }
        reader.close();
        return usernameList;
    }
}
