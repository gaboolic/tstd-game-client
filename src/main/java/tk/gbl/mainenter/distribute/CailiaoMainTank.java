package tk.gbl.mainenter.distribute;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ChatCommandConstant;
import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ItemIdType;
import tk.gbl.core.GameClient;
import tk.gbl.util.FileReadUtil;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date: 2017/5/16
 * Time: 20:38
 *
 * @author Tian.Dong
 */
public class CailiaoMainTank {
    public static void main(String[] args) throws IOException {
        String allAccountFileName = "account_25.txt";
//        String allAccountFileName = "国士小号2.txt";
        String storehousePassword = "123456";
        String password = "123456";

//        String storehouseAccount = "WP00115026";//石田螺
//        String accountDoneFileName = "account_trade_shitianluo.txt";
//        String chatCommand = ChatCommandConstant.givemeshitianluo.getCommand();
//        int itemId = ItemIdType.shitianluo.getId();

//        String storehouseAccount = "WP00115028";//硬厚皮
//        String accountDoneFileName = "account_trade_yinghoupi.txt";
//        String chatCommand = ChatCommandConstant.givemeyinghoupi.getCommand();
//        int itemId = ItemIdType.yinghoupi.getId();

//        String storehouseAccount = "WP00115027";//黑松木
//        String accountDoneFileName = "account_trade_heisongmu.txt";
//        String chatCommand = ChatCommandConstant.givemeheisongmu.getCommand();
//        int itemId = ItemIdType.heisongmu.getId();

//        String storehouseAccount = "WP00116692";//轻染布
//        String accountDoneFileName = "account_trade_qingranbu.txt";
//        String chatCommand = ChatCommandConstant.givemeqingranbu.getCommand();
//        int itemId = ItemIdType.qingranbu.getId();

//        String storehouseAccount = "WP00116693";//天竹纸
//        String accountDoneFileName = "account_trade_hongbao.txt";
//        String chatCommand = ChatCommandConstant.givemetianzhuzhi.getCommand();
//        int itemId = ItemIdType.tianzhuzhi.getId();

//        String storehouseAccount = "WP00119260";//将军酒
//        String accountDoneFileName = "account_trade_jiangjunjiu.txt";
//        String chatCommand = "giveme-26054-3";
//        int itemId = 26054;

//        String storehouseAccount = "WP00119261";//治伤丸
//        String accountDoneFileName = "account_trade_zhishangwan2.txt";
//        String chatCommand = "giveme-27041-3";
//        int itemId = 27041;

//        String storehouseAccount = "WP00119263";//江鱷刀百鍛戟荊楚酒(江陵兵)」、「风乾羊肉(
//        String accountDoneFileName = "account_trade_baodaobaojia.txt";
//        String chatCommand = "giveme-10016-1";
//        int itemId = 10016;//江鳄刀

//        String storehouseAccount = "WP00119263";//江鱷刀百鍛戟荊楚酒(江陵兵)」、「风乾羊肉(
//        String accountDoneFileName = "account_trade_baodaobaojia2.txt";
//        String chatCommand = "giveme-15015-1";
//        int itemId = 15015;//百锻戟

//        String storehouseAccount = "WP00119262";//江鱷刀百鍛戟荊楚酒(江陵兵)」、「风乾羊肉(
//        String accountDoneFileName = "account_trade_baodaobaojia3.txt";
//        String chatCommand = "giveme-26233-20";
//        int itemId = 26233;//荆楚酒

        String storehouseAccount = "WP00119262";//江鱷刀百鍛戟荊楚酒(江陵兵)」、「风乾羊肉(
        String accountDoneFileName = "account_trade_baodaobaojia4.txt";
        String chatCommand = "giveme-26050-20";
        int itemId = 26050;//风干羊肉

        int itemCount = 20;

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


        List<String> gameClientDoneLevelList = FileReadUtil.getDoneGameClientList(accountDoneFileName);
        Queue<GameClient> gameClientList = new LinkedList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(allAccountFileName)));
        String line = null;
        while ((line = fileReader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            GameClient gameClient = new GameClient();
            gameClient.setUsername(line.split(" ")[0]);
//            gameClient.setPassword(line.split(" ")[1]);
            gameClient.setPassword(password);
            gameClientList.add(gameClient);
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(accountDoneFileName), true));

        while (gameClientList.size() > 0) {
            GameClient gameClient = gameClientList.poll();
            if (gameClient == null) {
                break;
            }
            if (gameClientDoneLevelList.contains(gameClient.getUsername())) {
                continue;
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

            bufferedWriter.write(gameClient.getUsername());
            bufferedWriter.newLine();
            bufferedWriter.flush();
            gameClient.close();
            System.out.println(gameClient.getUserId() + "完成");
        }
        storeGameClient.mergeBag();
        System.out.println("运行完毕");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        storeGameClient.close();
        bufferedWriter.close();
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
