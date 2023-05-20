package tk.gbl.mainenter.suipian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;
import tk.gbl.core.thread.PickupItemThread;
import tk.gbl.util.FileReadUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 2017/6/9
 * Time: 10:39
 *
 * @author Tian.Dong
 */
public class PickupCailiaoMainTank {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(15);

//        String password = "qazwsx";
//        String allAccountFileName = "account_pickup_szy_suipiancailiao_move.txt";
//        String doneAccountFileName = "account_pickup_szy_suipiancailiao.txt";
//        String errorAccountFileName = "account_pickup_szy_suipiancailiao_error.txt";

        String password = "123456";
        String allAccountFileName = "account_pickup_suipiancailiao_move.txt";
        String doneAccountFileName = "account_pickup_suipiancailiao.txt";
        String errorAccountFileName = "account_pickup_suipiancailiao_error.txt";

        List<String> doneGameClientList = new ArrayList<>();
        List<String> errorGameClientList = new ArrayList<>();
        try {
            doneGameClientList = FileReadUtil.getDoneGameClientList(doneAccountFileName);
            errorGameClientList = FileReadUtil.getDoneGameClientList(errorAccountFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Queue<GameClient> gameClientList = new LinkedList<>();
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(new File(allAccountFileName)));
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                GameClient gameClient = new GameClient();
                gameClient.setUsername(line.split(" ")[0]);
                gameClient.setPassword(password);
                gameClientList.add(gameClient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int taskId = 0;
        while (true) {
            final GameClient gameClient = gameClientList.poll();
            if (gameClient == null) {
                break;
            }
            if (doneGameClientList.contains(gameClient.getUsername())) {
                continue;
            }
            if (errorGameClientList.contains(gameClient.getUsername())) {
                continue;
            }
            gameClient.getGameConfig().setFightReconnect(true);
            gameClient.getGameConfig().setNotGetItemReconnect(true);
            gameClient.getGameConfig().setAutoPickupItem(true);
            gameClient.setConsoleOutput(false);
            gameClient.setFileOutput(false);
            taskId++;
            PickupItemThread pickupItemThread = new PickupItemThread(gameClient,taskId);
            pickupItemThread.setErrorAccountFileName(errorAccountFileName);
            pickupItemThread.setDoneAccountFileName(doneAccountFileName);
            fixedThreadPool.execute(pickupItemThread);
            System.out.println("taskid:"+taskId);
        }
        System.out.println("运行完毕");
    }
}
