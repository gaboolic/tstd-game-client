package tk.gbl.mainenter.boss.core;

import tk.gbl.bean.BagItem;
import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.util.FileReadUtil;
import tk.gbl.util.OutputUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 卑枸 批量打boss
 * Date: 2017/4/28
 * Time: 10:44
 *
 * @author Tian.Dong
 */
public class BatchBeigouAttackBoss {
    public static void main(String[] args) throws IOException {
        String storeUsername = "119248";
        String storePassword = "123456";
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

        //装备                护腕   鞋    刀     甲    头    攻锦囊
//        String itemListStr = "21766,22956,10529,19245,20932,23344";
        //装备                                              定神
        String itemListStr1 = "21766,22956,10529,19245,20932,23101";
        //装备                                         纯冰巾
        String itemListStr2 = "21766,22956,10529,19245,20709,23101";
        final List<Integer> storeItemList = new ArrayList<>();
        for (String storeItemStr : itemListStr1.split(",")) {
            storeItemList.add(Integer.parseInt(storeItemStr));
        }


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);

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
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        BeigouAttackBossUtil.doFlow(gameClient, storeGameClient, storeItemList);
                    } catch (IOException e) {
                        e.printStackTrace();
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
            });
        }
        System.out.println("运行完毕");
    }


}
