package tk.gbl.mainenter.activity;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.StoreConfigUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 把身上的物品放仓库
 * <p/>
 * Date: 2017/6/25
 * Time: 18:25
 *
 * @author Tian.Dong
 */
public class MainTankStoreWork {
    public static void main(String[] args) throws IOException {
        Map<String,GameClient> gameClientMap = new HashMap<>();
        for(Map.Entry<Object, Object> entry:StoreConfigUtil.getProperties().entrySet()) {
            String key = (String) entry.getKey();
            String storeUsername = key.split("_")[1];
            GameClient gameClient = new GameClient();
            gameClient.setUsername(storeUsername);
            gameClient.setPassword("123456");
            gameClient.init();
            gameClientMap.put(storeUsername,gameClient);
        }
        for(Map.Entry<String,GameClient> entry:gameClientMap.entrySet()) {
            entry.getValue().waitForLoginSuccess();
            entry.getValue().moveFar(12001);
        }
        List<String> usernameList = new ArrayList<>();
        usernameList.add("47809");
        usernameList.add("47810");
        usernameList.add("47811");
        usernameList.add("47812");
        usernameList.add("47813");
        usernameList.add("27166");
        usernameList.add("27167");
        usernameList.add("39259");
        usernameList.add("38253");
        usernameList.add("39260");
        final String password = ProcessConstant.password;

        for(String username:usernameList) {
            final GameClient gameClient = new GameClient();
            gameClient.setConsoleOutput(true);
            gameClient.setFileOutput(true);
            gameClient.setIndex(5);
            gameClient.setUsername(username);
            gameClient.setPassword(password);
            gameClient.setTeamLeaderId(0);
            gameClient.init();
            gameClient.waitForLoginSuccess();
            gameClient.autoUseAndThrow();
            gameClient.moveFar(12001);
            gameClient.sleep(1000);
            for(Map.Entry<Object, Object> entry:StoreConfigUtil.getProperties().entrySet()) {
                String key = (String) entry.getKey();
                String storeUsername = key.split("_")[1];
                GameClient storeGameClient = gameClientMap.get(storeUsername);
                String value = (String) entry.getValue();
                List<Integer> storeItemList = new ArrayList<>();
                for (String storeItemStr : value.split(",")) {
                    storeItemList.add(Integer.parseInt(storeItemStr));
                }

                List<Integer> itemIndexList = new ArrayList<>();
                BagItem[] bagItems = gameClient.getBagItems();
                for (int i = 1; i <= 25; i++) {
                    BagItem bagItem = bagItems[i];
                    if (bagItem == null) {
                        continue;
                    }
                    if (storeItemList.contains(bagItem.getId())) {
                        itemIndexList.add(i);
                    }
                }
                gameClient.setTradeItemIndexList(itemIndexList);
                gameClient.tradeRequest(storeGameClient.getUserId());

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                boolean isHave = false;
                bagItems = gameClient.getBagItems();
                for (int i = 1; i <= 25; i++) {
                    BagItem bagItem = bagItems[i];
                    if (bagItem == null) {
                        continue;
                    }
                    if (storeItemList.contains(bagItem.getId())) {
                        System.out.println("有" + bagItem.getId() + "(" + ItemIdUtil.getItemName(bagItem.getId()) + ")");
                        isHave = true;
                    }
                }
                if (isHave) {
                    System.out.println("交易失败");
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gameClient.close();
        }


        for(Map.Entry<String,GameClient> entry:gameClientMap.entrySet()) {
            entry.getValue().close();
        }
    }
}
