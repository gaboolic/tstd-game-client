package tk.gbl.mainenter.gainlevel;

import tk.gbl.core.GameClient;
import tk.gbl.core.GameClientMap;
import tk.gbl.core.frame.CloseMainFrame;
import tk.gbl.core.script.NewZhenchashuAction;
import tk.gbl.core.script.ShoujingpoAction;
import tk.gbl.core.script.pass.JieQiaoAction;
import tk.gbl.util.FileReadUtil;
import tk.gbl.util.OutputUtil;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 2017/6/13
 * Time: 8:46
 *
 * @author Tian.Dong
 */
public class BatchCreateRole {
    public static void main(String[] args) throws IOException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);


        new CloseMainFrame();

        String allAccountFileName = "account.txt";
        String trainingAccountFileName = "account_training.txt";
        String account25FileName = "account_25.txt";
        final String accountCreateRoleFileName = "account_createRole.txt";

//        String allAccountFileName = "account2qu.txt";
//        String trainingAccountFileName = "account2qu_training.txt";
//        String account25FileName = "account2qu_25.txt";
//        final String accountCreateRoleFileName = "account2qu_createRole.txt";

        int serverIndex = 1;

        //训练好的小号列表
        List<String> gameClient25LevelList = FileReadUtil.getDoneGameClientList(account25FileName);
        //所有需要训练的小号队列
        Queue<GameClient> gameClientList = new LinkedList<>();
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
            gameClient.setPassword(line.split(" ")[1]);
            gameClientList.add(gameClient);
        }
        for (int i = 0; i < 100; i++) {
            gameClientList.poll();
        }
        int count = 0;
        while (gameClientList.size() > 0) {
            count++;
            System.err.println("while循环内count:" + count);
            //本次训练的小号
            GameClient gameClient = gameClientList.poll();

            if (gameClient == null) {
                break;
            }
            //正在训练的小号列表
            List<String> gameClientTrainingList = FileReadUtil.getDoneGameClientList(trainingAccountFileName);
            List<String> gameClientCreateRoleList = FileReadUtil.getDoneGameClientList(accountCreateRoleFileName);
            //取小号
            while (true) {
                gameClient = gameClientList.poll();
                if (gameClient == null) {
                    break;
                }
                //不是25级，并且也没有训练中
                if (!gameClient25LevelList.contains(gameClient.getUsername())
                        && !gameClientTrainingList.contains(gameClient.getUsername())
                        && !gameClientCreateRoleList.contains(gameClient.getUsername())
                        ) {
                    break;
                }
            }
            if (gameClient == null) {
                break;
            }
            gameClient.setServerIndex(serverIndex);
            final GameClient finalGameClient = gameClient;
            final int finalCount = count;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.err.print("*开始执行" + (finalCount));
                    try {
                        doFlow(finalGameClient, accountCreateRoleFileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.err.print("*结束执行" + (finalCount));
                }
            });

        }
    }

    public static void doFlow(GameClient gameClient, String accountCreateRoleFileName) {
        System.out.println("本次创建:" + gameClient.getUsername());
        gameClient.setReConnect(true);
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.setIndex(6);
        gameClient.setInitMapId(0);
        gameClient.init();
        int retryCount = 10;
        while (retryCount-- > 0 && !gameClient.isLoginSuccess()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            gameClient.doScriptAction(new NewZhenchashuAction());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (gameClient.isLoginSuccess()) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(accountCreateRoleFileName), true));
                bufferedWriter.write(gameClient.getUsername());
                bufferedWriter.write("\r\n");
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (Exception e) {

            }
        }
        gameClient.close();

    }
}
