package tk.gbl.mainenter.boss;

import tk.gbl.bean.RoleInfo;
import tk.gbl.core.GameClient;
import tk.gbl.core.script.work.activity.AttackBossAction;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 2017/4/28
 * Time: 10:44
 *
 * @author Tian.Dong
 */
public class BatchAttackBoss {
    public static void main(String[] args) throws IOException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        String allAccountFileName = "account_25.txt";
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


        while (true) {
            final GameClient gameClient = gameClientList.poll();
            if (gameClient == null) {
                break;
            }
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    gameClient.setConsoleOutput(true);
                    gameClient.setFileOutput(false);
                    gameClient.setIndex(2);
                    gameClient.setTeamLeaderId(0);
                    gameClient.setInitMapId(0);
                    gameClient.init();
                    System.out.println("本次登录:" + gameClient.getUserId());

                    gameClient.waitForLoginSuccess();

                    if (gameClient.getCurrentMapId() != 12001) {
                        gameClient.close();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return;
                    }

                    try {
                        gameClient.doScriptAction(new AttackBossAction());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(gameClient.getItemCount(55601) > 0) {
                        try {
                            BufferedWriter trainingBufferedWriter = new BufferedWriter(new FileWriter(new File("account_zlls"), true));
                            trainingBufferedWriter.write(gameClient.getUsername());
                            trainingBufferedWriter.write("\r\n");
                            trainingBufferedWriter.flush();
                            trainingBufferedWriter.close();
                        } catch (Exception e) {

                        }
                    }
                    gameClient.close();
                }
            });

        }
        System.out.println("运行完毕");
    }

    private static boolean haveManbing(GameClient gameClient) {
        boolean isHaveManbing = false;
        for (int i = 0; i < gameClient.getPetList().length; i++) {
            RoleInfo pet = gameClient.getPetList()[i];
            if (pet == null) {
                continue;
            }
            if (pet.getId() == 17771) {
                isHaveManbing = true;
            }
        }
        return isHaveManbing;
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
