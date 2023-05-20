package tk.gbl.mainenter.boss;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.NpcIdType;
import tk.gbl.core.GameClient;
import tk.gbl.core.frame.CloseMainFrame;
import tk.gbl.mainenter.boss.core.BeigouAttackBossUtil;
import tk.gbl.util.FileReadUtil;
import tk.gbl.util.OutputUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date: 2017/8/4
 * Time: 16:09
 *
 * @author Tian.Dong
 */
public class BatchRecycleEquip {
    public static void main(String[] args) throws IOException {
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

        if (judgeAllHaveEquipItems(storeGameClient.getBagItems(), storeItemList)) {
            System.out.println(storeGameClient.getUserId()+"有全部装备");
            storeGameClient.close();
            return;
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
            gameClient.init();
            gameClient.waitForLoginSuccess();
            gameClient.moveFar(12001);
            //归还装备
            int petIndex = gameClient.getPetIndex(NpcIdType.beigou.getId());
            if (petIndex == -1) {
                OutputUtil.output("没有卑枸，下一个",gameClient,true);
                gameClient.close();
                continue;
            }
            for (int i = 1; i <= 6; i++) {
                gameClient.unequipItemPet(petIndex, i);
                gameClient.sleep(500);
            }
            if (!judgeAllHaveEquipItems(gameClient.getBagItems(), storeItemList)) {
                for (int i = 1; i <= 6; i++) {
                    gameClient.unequipItemPet(petIndex, i);
                    gameClient.sleep(500);
                }
            }
            storeGameClient.waitForLoginSuccess();
            gameClient.genTradeItemIndexList(storeItemList);
            gameClient.tradeRequest(storeGameClient.getUserId());
            gameClient.sleep(1000);
            if (!judgeAllHaveEquipItems(storeGameClient.getBagItems(), storeItemList)) {
                OutputUtil.output("物品不足！！！！！", storeGameClient, true);
            }
            gameClient.close();

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

    private static boolean judgeAllHaveEquipItems(BagItem[] bagItems, BagItem[] equipItems, List<Integer> storeItemList) {
        boolean isAllHave = true;
        for (Integer itemId : storeItemList) {
            boolean isHave = false;
            for (int i = 0; i < bagItems.length; i++) {
                BagItem bagItem = bagItems[i];
                if (bagItem == null || bagItem.getCount() == 0) {
                    continue;
                }
                if (bagItem.getId() == itemId) {
                    isHave = true;
                }
            }
            for (int i = 0; i < equipItems.length; i++) {
                BagItem bagItem = equipItems[i];
                if (bagItem == null || bagItem.getCount() == 0) {
                    continue;
                }
                if (bagItem.getId() == itemId) {
                    isHave = true;
                }
            }
            if (!isHave) {
                isAllHave = false;
            }
        }
        return isAllHave;
    }

    private static boolean judgeAllHaveEquipItems(BagItem[] equipItems, List<Integer> storeItemList) {
        boolean isAllHave = true;
        for (Integer itemId : storeItemList) {
            boolean isHave = false;
            for (int i = 0; i < equipItems.length; i++) {
                BagItem bagItem = equipItems[i];
                if (bagItem == null || bagItem.getCount() == 0) {
                    continue;
                }
                if (bagItem.getId() == itemId) {
                    isHave = true;
                }
            }
            if (!isHave) {
                isAllHave = false;
            }
        }
        return isAllHave;
    }
}
