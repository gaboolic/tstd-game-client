package tk.gbl.mainenter.boss.core;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameClientMap;
import tk.gbl.core.frame.CloseMainFrame;
import tk.gbl.util.FileReadUtil;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 卑枸 批量打boss
 * Date: 2017/4/28
 * Time: 10:44
 *
 * @author Tian.Dong
 */
public class BatchBeigouAttackBoss2 {

    public static int huaxiangCount = 0;

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("ShutdownHook");
                System.out.println("huaxiangCount:" + huaxiangCount);
            }
        });
        new CloseMainFrame();

        final String storeUsernames1 = "119248";
        final String itemListStr1 = "21766,22956,10529,19245,20932,23101";

        final String storeUsernames2 = "138221,138222,138223,138224,138225,138226,138227,138228";
        //装备                                         纯冰巾
        final String itemListStr2 = "21766,22956,10529,19245,20709,23101";
        final String storePassword = "123456";

        final int allCount = 9;
        int index = 0;
        for (final String storeUsername : storeUsernames2.split(",")) {
            final int finalIndex = index;
            new Thread() {
                @Override
                public void run() {
                    try {
                        doFlow(allCount, finalIndex, storeUsername, storePassword, itemListStr2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            index++;
        }

        for (final String storeUsername : storeUsernames1.split(",")) {
            final int finalIndex = index;
            new Thread() {
                @Override
                public void run() {
                    try {
                        doFlow(allCount, finalIndex, storeUsername, storePassword, itemListStr1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            index++;
        }
        System.out.println("一共得到画像" + huaxiangCount + "个");
    }

    public static void doFlow(int allCount, int index, String storeUsername, String storePassword, String itemListStr) throws IOException {
        final GameClient storeGameClient = new GameClient();
        storeGameClient.setIndex(1);
        storeGameClient.setConsoleOutput(true);
        storeGameClient.setFileOutput(true);
        storeGameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        storeGameClient.setUsername(storeUsername);
        storeGameClient.setPassword(storePassword);
        storeGameClient.setInitMapId(0);
        storeGameClient.init();
        storeGameClient.setTeamLeaderId(0);
        storeGameClient.waitForLoginSuccess();
        storeGameClient.moveFar(12001);

        final List<Integer> storeItemList = new ArrayList<>();
        for (String storeItemStr : itemListStr.split(",")) {
            storeItemList.add(Integer.parseInt(storeItemStr));
        }


        String allAccountFileName = "account_trade_beigou_money.txt";
        final String doneAccountFileName = "account_trade_beigou_work_attackboss.txt";
        List<String> doneGameClientList = FileReadUtil.getDoneGameClientList(doneAccountFileName);
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
            if (doneGameClientList.contains(gameClient.getUsername())) {
                continue;
            }
            if (gameClient.getUserId() % allCount != index) {
                continue;
            }

            try {
                BeigouAttackBossUtil.doFlow(gameClient, storeGameClient, storeItemList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Integer gainBagItemCount = gameClient.getGameParam().getGainBagItemCountMap().get(46859);
            if (gainBagItemCount != null) {
                huaxiangCount += gainBagItemCount;
            }

            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(doneAccountFileName), true));
                bufferedWriter.write(gameClient.getUsername());
                bufferedWriter.write("\r\n");
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
