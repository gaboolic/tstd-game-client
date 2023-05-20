package tk.gbl.helper;

import tk.gbl.constant.ProtocolConstant;
import tk.gbl.constant.ServerInfo;
import tk.gbl.core.GameClient;
import tk.gbl.util.FileReadUtil;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 2017/7/3
 * Time: 15:07
 *
 * @author Tian.Dong
 */
public class BatchCheckRole {
    public static void main(String[] args) throws IOException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);


        String allAccountFileName = "check/水.txt";
        final String accountDoneFileName = "check/account_no_role.txt";

        List<String> gameClient25LevelList = FileReadUtil.getDoneGameClientList(accountDoneFileName);
        Queue<GameClient> gameClientList = new LinkedList<>();
//        Stack<GameClient> gameClientList = new Stack<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(allAccountFileName)));
        String line = null;
        while ((line = fileReader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            GameClient gameClient = new GameClient();
            gameClient.setUsername(line.split(" ")[0]);
            gameClient.setPassword("44444444");
            gameClientList.add(gameClient);
        }

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
            final GameClient finalGameClient = gameClient;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        worksingle(finalGameClient,accountDoneFileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void worksingle(GameClient gameClient,String accountDoneFileName) throws IOException {
        gameClient.getGameConfig().setNewRoleClose(true);
        gameClient.init();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(gameClient.getGameParam().isNewRole()) {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(accountDoneFileName), true));
            bufferedWriter.write(gameClient.getUsername());
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        gameClient.close();
    }
}
