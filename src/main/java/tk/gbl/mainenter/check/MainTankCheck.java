package tk.gbl.mainenter.check;

import tk.gbl.constant.ItemIdType;
import tk.gbl.core.GameClient;
import tk.gbl.core.script.work.activity.AttackBossAction;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 2017/6/26
 * Time: 11:30
 *
 * @author Tian.Dong
 */
public class MainTankCheck {
    public static void main(String[] args) throws Exception {
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
            if(gameClientList.size() > 350) {
                break;
            }
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
                    gameClient.setIndex(6);
                    gameClient.setTeamLeaderId(0);
                    gameClient.setInitMapId(0);
                    gameClient.init();
                    System.out.println("本次登录:" + gameClient.getUserId());

                    gameClient.waitForLoginSuccess();


                    if(gameClient.getItemCount(ItemIdType.heisongmu.getId()) >= 5) {
                        try {
                            BufferedWriter trainingBufferedWriter = new BufferedWriter(new FileWriter(new File("account_heisongmu"), true));
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
}
