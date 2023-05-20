package tk.gbl.mainenter.movefar;

import tk.gbl.core.GameClient;
import tk.gbl.core.frame.CloseMainFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 2017/6/7
 * Time: 17:45
 *
 * @author Tian.Dong
 */
public class BatchSingleMoveFar {
    public static void main(String[] args) throws IOException {
        new CloseMainFrame();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(30);

//        String allAccountFileName = "account_25.txt";
        String allAccountFileName = "account_pickup_suipiancailiao.txt";
//        String allAccountFileName = "account_shenghuadan.txt";
//        Queue<GameClient> gameClientList = new LinkedList<>();
        Stack<GameClient> gameClientList = new Stack<>();
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
            final GameClient gameClient = gameClientList.pop();
            if (gameClient == null) {
                break;
            }
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    doFlow(gameClient);
                }
            });
        }


        System.out.println("运行完毕");
    }

    private static void doFlow(GameClient gameClient) {
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.setIndex(2);
        gameClient.setTeamLeaderId(0);
        gameClient.setInitMapId(0);
        gameClient.init();
        System.out.println("本次登录:" + gameClient.getUserId());

        gameClient.waitForLoginSuccess();
        if (gameClient.getCurrentMapId() == 12001) {
            gameClient.close();
            return;
        }
        if (gameClient.getCurrentMapId() < 13000) {
            gameClient.moveFar(12001);
            gameClient.close();
            return;
        }
        try {
            gameClient.move(255);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.waitForLoginSuccess();
        gameClient.moveFar(12001);
        gameClient.close();
    }
}
