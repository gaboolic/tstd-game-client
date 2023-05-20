package tk.gbl.mainenter.distribute;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ChatCommandConstant;
import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ItemIdType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.util.FileReadUtil;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date: 2017/6/28
 * Time: 19:34
 *
 * @author Tian.Dong
 */
public class SingleCailiaoMainTank {
    public static void main(String[] args) throws IOException {
        String storehousePassword = "123456";
        String password = "123456";

        String storehouseAccount = "WP00118050";//通天魔豆
        String chatCommand = "giveme-46070-1";
        int itemId = ItemIdType.tongtianmodou.getId();
        int itemCount = 1;

        GameClient storeGameClient = new GameClient();
        storeGameClient.setIndex(1);
        storeGameClient.setConsoleOutput(false);
        storeGameClient.setFileOutput(true);
        storeGameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        storeGameClient.setUsername(storehouseAccount);
        storeGameClient.setPassword(storehousePassword);
        storeGameClient.setInitMapId(12001);
        storeGameClient.init();
        storeGameClient.setTeamLeaderId(0);
        while (!storeGameClient.isLoginSuccess()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storeGameClient.moveFar(12001);
        storeGameClient.mergeBag();

        Queue<GameClient> gameClientList = new LinkedList<>();
        String usernames = "47809,47810,47812,47813";
        for(String username:usernames.split(",")) {
            final GameClient gameClient = new GameClient();
            gameClient.setServerIndex(1);
            gameClient.setUsername(username);
            gameClient.setPassword(ProcessConstant.password);
            gameClientList.add(gameClient);
        }

        while (gameClientList.size() > 0) {
            GameClient gameClient = gameClientList.poll();
            if (gameClient == null) {
                break;
            }
            gameClient.setConsoleOutput(true);
            gameClient.setFileOutput(true);
            gameClient.setIndex(2);
            gameClient.setTeamLeaderId(0);
            gameClient.setInitMapId(0);
            gameClient.init();
            System.out.println("本次登录:" + gameClient.getUserId());


            while (!gameClient.isLoginSuccess()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (gameClient.getCurrentMapId() != 12001) {
                gameClient.close();
                continue;
            }

            while (!isHave(gameClient, itemId, itemCount)) {
                System.out.println("没有需要的物品，开始chatMi");
                gameClient.showBag();
                gameClient.chatMi(storeGameClient.getUserId(), chatCommand);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gameClient.mergeBag();

            boolean isMore = false;
            BagItem[] bagItems = gameClient.getBagItems();
            for (int i = 1; i <= 25; i++) {
                BagItem bagItem = bagItems[i];
                if (bagItem == null) {
                    continue;
                }
                if (bagItem.getId() == itemId) {
                    if (bagItem.getCount() > itemCount) {
                        isMore = true;
                        gameClient.splitItem(i, 25, bagItem.getCount() - itemCount);
                        System.out.println("物品多余，拆分物品");
                        break;
                    }
                }
            }
            if (isMore) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("多余了");
                gameClient.getTradeItemIndexList().add(25);
                gameClient.tradeRequest(storeGameClient.getUserId());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        storeGameClient.mergeBag();
        System.out.println("运行完毕");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        storeGameClient.close();
    }

    private static boolean isHave(GameClient gameClient, int itemId, int itemCount) {
        BagItem[] bagItems = gameClient.getBagItems();
        int totalCount = 0;
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            if (bagItem.getId() == itemId) {
                totalCount += bagItem.getCount();
            }
        }
        return totalCount >= itemCount;
    }
}
