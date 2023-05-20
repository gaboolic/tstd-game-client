package tk.gbl.mainenter.distribute;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.util.FileReadUtil;
import tk.gbl.util.ItemIdUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 批量交易 整合
 * <p/>
 * Date: 2017/5/9
 * Time: 16:01
 *
 * @author Tian.Dong
 */
public class BatchTradeMerge {
    public static void main(String[] args) throws IOException {
//        String storehouseAccount = "WP00118049";//巴豆妖兑换券
//        String storehouseAccount = "WP00119244";//锤子
//        String storehouseAccount = "WP00118052";//白蛇怒  姜太公兵法 倚天剑
//        String storehouseAccount = "WP00119243";//姜太公兵法 倚天剑
//        String storehouseAccount = "WP00118051";//10毛 11心
//        String storehouseAccount = "WP00124000";//2区 10毛 11心
//        String storehouseAccount = "WP00119245";//boss饰品
//        String storehouseAccount = "WP00118050";//垃圾毛 心
//        String storehouseAccount = "WP00119240";//红包
//        String storehouseAccount = "WP00119241";//回忆锦囊
        String storehouseAccount = "WP00119250";//甘兴霸 WP00119248
        String storehousePassword = "123456";
        String password = "123456";
        int serverIndex = 1;


        //心 背包 恶魔
//        String storeItemListStr = "31007,31022,31023,31024,31025,31026";
//        String storeItemListStr = "23092,23093,23094,23098,23099,23165";//锤子
//        String storeItemListStr = "11066,11035,18535";//白蛇怒 倚天剑 姜太公兵法
//        String storeItemListStr = "23341,23342,23344,23345,23347,23348,23350";//boss饰品
//        String storeItemListStr = "45022,24065";//11心 10毛
        String storeItemListStr = "10529,19245";//甘兴霸
//        String storeItemListStr = "46034";//回忆锦囊
//        String storeItemListStr = "31023," +
//                "46185,46186,46187,46188,46189,46190," +//领悟丹
//                "30075,30076,30077,30078,30079,30080,30081,30082," +//泥人
//                "51269,51265,51192,51141,";//红包东西
        List<Integer> storeItemList = new ArrayList<>();
        for (String storeItemStr : storeItemListStr.split(",")) {
            storeItemList.add(Integer.parseInt(storeItemStr));
        }
//        String allAccountFileName = "account_25.txt";
//        String allAccountFileName = "account_work_11xin.txt";
//        String accountDoneFileName = "account_trade_11xin.txt";

        String allAccountFileName = "account_work_ganxingba.txt";
        String accountDoneFileName = "account_trade_ganxingba.txt";

//        String allAccountFileName = "account2qu_work_11xin.txt";
//        String accountDoneFileName = "account2qu_trade_11xin.txt";
//        String allAccountFileName = "account_trade_beigou.txt";
//        String allAccountFileName = "account_work_nanmanhou_zj.txt";
//        String allAccountFileName = "account_work_ganxingba.txt";
//        String allAccountFileName = "account_work_qinxin.txt";
//        String allAccountFileName = "国士小号2.txt";
//        String accountDoneFileName = "account_trade_yitianjian.txt";
//        String accountDoneFileName = "account_trade_baishenu.txt";
//        String accountDoneFileName = "account_trade_lajimao.txt";
//        String accountDoneFileName = "account_trade_99chuizi.txt";


//        String accountDoneFileName = "account_trade_lajixinmao.txt";
//        String accountDoneFileName = "account_trade_10mao.txt";
//        String accountDoneFileName = "account_trade_huiyi.txt";
//        String accountDoneFileName = "account_trade_boss_shipin.txt";


        GameClient storeGameClient = new GameClient();
        storeGameClient.setIndex(1);
        storeGameClient.setServerIndex(serverIndex);
        storeGameClient.setConsoleOutput(true);
        storeGameClient.setFileOutput(true);
        storeGameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        storeGameClient.setUsername(storehouseAccount);
        storeGameClient.setPassword(storehousePassword);
        storeGameClient.setInitMapId(0);
        storeGameClient.init();
        storeGameClient.setTeamLeaderId(0);
        while (!storeGameClient.isLoginSuccess()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storeGameClient.mergeBag();
        storeGameClient.moveFar(12001);

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
            gameClient.setServerIndex(serverIndex);
            gameClient.setTeamLeaderId(0);
            gameClient.setInitMapId(0);
            gameClient.init();
            System.out.println("本次登录:" + gameClient.getUserId());
            while (!gameClient.isLoginSuccess()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (gameClient.getCurrentMapId() < 13000) {
                gameClient.moveFar(12001);
            } else {
                gameClient.close();
                continue;
            }
            gameClient.autoUseAndThrow();
            //登录成功,开始交易
            List<Integer> itemIndexList = new ArrayList<>();
            BagItem[] bagItems = gameClient.getBagItems();
            for (int i = 1; i <= 25; i++) {
                BagItem bagItem = bagItems[i];
                if (bagItem == null) {
                    continue;
                }
                if (storeItemList.contains(bagItem.getId())) {
                    itemIndexList.add(i);
                } else {
                    String name = ItemIdUtil.getItemName(bagItem.getId());
                    if (!name.equals("白翎羽甲")) {
                        if (name.contains("羽") || name.contains("毛") || name.contains("冰晶")) {
                            itemIndexList.add(i);
                        }
                    }
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
                gameClient.close();
                continue;
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(accountDoneFileName), true));
            bufferedWriter.write(gameClient.getUsername());
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
            gameClient.close();
            System.out.println(gameClient.getUserId() + "完成");
        }
        storeGameClient.mergeBag();
        System.out.println("运行完毕");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        storeGameClient.close();
    }
}
