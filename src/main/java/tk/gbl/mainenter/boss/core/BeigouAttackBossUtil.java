package tk.gbl.mainenter.boss.core;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.NpcIdType;
import tk.gbl.core.GameClient;
import tk.gbl.core.script.work.activity.AttackBossAction;
import tk.gbl.core.script.work.activity.KezhanxiuxiAction;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.OutputUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Date: 2017/7/7
 * Time: 15:17
 *
 * @author Tian.Dong
 */
public class BeigouAttackBossUtil {
    public static void doFlow(GameClient gameClient, GameClient storeGameClient, List<Integer> storeItemList) throws IOException {
        //仓库号校验装备耐久，自动修理
        autoRepairEquip(storeGameClient);

        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.setIndex(5);
        gameClient.setTeamLeaderId(0);
        gameClient.init();
        gameClient.waitForLoginSuccess();
        gameClient.autoUseAndThrow();
        gameClient.sleep(1000);
        gameClient.moveFar(12001);
        int petIndex = gameClient.getPetIndex(NpcIdType.beigou.getId());
        if (petIndex == -1) {
            OutputUtil.output("没有卑枸，下一个",gameClient,true);
            gameClient.close();
            return;
        }
        gameClient.chuzhan(NpcIdType.beigou.getId());
        while (gameClient.getPet() == null) {
            gameClient.sleep(1000);
        }

        //46578
        BagItem[] bagItems = gameClient.getBagItems();
        for (int i = 0; i < bagItems.length; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            if (bagItem.getCount() == 0) {
                continue;
            }
            if(bagItem.getId() == 46578) {
                OutputUtil.output("自动使用物品" + i + " " + bagItem.getId() + " " + bagItem.getCount(), gameClient, true);
                try {
                    gameClient.useItem(i, bagItem.getCount());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        storeGameClient.waitForLoginSuccess();
        BagItem[] equipItems = gameClient.getPet().getEquipItems();
        if (!judgeAllHaveEquipItems(equipItems, gameClient.getBagItems(), storeItemList)) {
            storeGameClient.genTradeItemIndexList(storeItemList);
            storeGameClient.tradeRequest(gameClient.getUserId());
            storeGameClient.sleep(1000);
        }

        if (!judgeAllHaveEquipItems(equipItems, storeItemList)) {
            //宠物装备物品
            for (int i = 1; i <= 25; i++) {
                BagItem bagItem = gameClient.getBagItems()[i];
                if (bagItem == null || bagItem.getCount() == 0) {
                    continue;
                }
                if (storeItemList.contains(bagItem.getId())) {
                    gameClient.equipItemPet(petIndex, i);
                }
            }
            gameClient.sleep(1000);
        }
        if (!judgeAllHaveEquipItems(equipItems, storeItemList)) {
            OutputUtil.output("装备不够！！！！！", storeGameClient, true);
            gameClient.close();
            return;
        }
        attackBoss(gameClient);
//
        lingjiang(gameClient);

        if (gameClient.getItemCount(55601) > 0 //智力灵石
                || gameClient.getItemCount(48507) > 0 //狂光刘备画像
                || gameClient.getItemCount(46859) > 0 //暗冰队长画像
                || haveBaoji(gameClient) //有霸X
                ) {
            try {
                BufferedWriter trainingBufferedWriter = new BufferedWriter(new FileWriter(new File("account_boss_goods"), true));
                trainingBufferedWriter.write(gameClient.getUsername());
                trainingBufferedWriter.write("\r\n");
                trainingBufferedWriter.flush();
                trainingBufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        gameClient.moveFar(12001);
        //归还装备
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

        //仓库号校验装备耐久，自动修理
        autoRepairEquip(storeGameClient);
    }

    private static boolean haveBaoji(GameClient gameClient) {
        if (gameClient.getBagItems() != null) {
            for (BagItem bagItem : gameClient.getBagItems()) {
                if (bagItem != null) {
                    String name = ItemIdUtil.getItemName(bagItem.getId());
                    if (name.contains("霸")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void lingjiang(GameClient gameClient) {
        for (int i = 0; i < 10; i++) {
            try {
                gameClient.event4906();
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameClient.sleep(500);
            gameClient.autoUseAndThrow();
        }
    }

    private static void attackBoss(GameClient gameClient) throws IOException {
        //宠物装备上全部装备
        gameClient.doScriptAction(new KezhanxiuxiAction());
        for (int i = 1; i <= 5; i++) {
            boolean result = checkWastage(gameClient);
            if (!result) {
                OutputUtil.output("装备损坏,当前次数" + i, gameClient, true);
                break;
            }
            System.out.println("打boss第" + i + "次");
            gameClient.doScriptAction(new AttackBossAction());
            gameClient.simpleSleep2();
            gameClient.event4906();
            gameClient.sleep(500);
            gameClient.autoUseAndThrow();
            System.out.println("客栈休息");
            gameClient.doScriptAction(new KezhanxiuxiAction());
            gameClient.simpleSleep2();

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

    private static boolean checkWastage(GameClient gameClient) {
        boolean result = true;
        if (gameClient.getPet() != null) {
            BagItem[] petEquipItems = gameClient.getPet().getEquipItems();
            for (int i = 1; i <= 5; i++) {
                if (petEquipItems[i].getWastage() > 200) {
                    System.out.println(petEquipItems[i].getId() + "损坏");
                    result = false;
                }
            }
        }
        return result;
    }

    private static void autoRepairEquip(GameClient storeGameClient) {
        OutputUtil.output("自动修复装备判断",storeGameClient,true);
        int xinmaoId = 24004;
        if (storeGameClient.getBagItems() == null) {
            return;
        }
        BagItem[] bagItems = storeGameClient.getBagItems();
        for (int i = 1; i <= 24; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null || bagItem.getCount() == 0) {
                continue;
            }
            if (bagItem.getWastage() > 200) {
                if (bagItems[25] != null && bagItems[25].getId() == xinmaoId) {

                } else {
                    moveXinmaoTo25(storeGameClient, xinmaoId);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (bagItems[25] != null && bagItems[25].getId() == xinmaoId) {
                    try {
                        storeGameClient.compoundItem(i, 1, 25, 1);
                        OutputUtil.output("修理装备!", storeGameClient, true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void moveXinmaoTo25(GameClient storeGameClient, int xinmaoId) {
        OutputUtil.output("moveXinmaoTo25!", storeGameClient, true);
        BagItem[] bagItems = storeGameClient.getBagItems();
        for (int i = 1; i <= 24; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null || bagItem.getCount() == 0) {
                continue;
            }
            if (bagItem.getId() == xinmaoId) {
                try {
                    storeGameClient.splitItem(i, 25, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
