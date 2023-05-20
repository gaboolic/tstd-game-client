package tk.gbl.mainenter.shenghuadan;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.util.FileReadUtil;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date: 2017/6/21
 * Time: 11:34
 *
 * @author Tian.Dong
 */
public class MainTankEatShenghuadan {
    public static void main(String[] args) throws IOException {
//        int npcId = 45331;//狂曹操
        int npcId = 45299;//狂赵云
        int itemId = 46063;//升华胆
        String storehouseAccount = "WP00113312";//放宠物的号
        String storehousePassword = "234567";
        String password = "123456";
        GameClient storeGameClient = new GameClient();
        storeGameClient.setIndex(1);
        storeGameClient.setConsoleOutput(true);
        storeGameClient.setFileOutput(true);
        storeGameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        storeGameClient.setUsername(storehouseAccount);
        storeGameClient.setPassword(storehousePassword);
        storeGameClient.setInitMapId(0);
        storeGameClient.init();
        storeGameClient.setTeamLeaderId(0);
        storeGameClient.waitForLoginSuccess();
        storeGameClient.moveFar(12001);

        String allAccountFileName = "account_shenghuadan.txt";
        String accountDoneFileName = "account_trade_shenghuadan.txt";

        List<String> gameClientDoneList = FileReadUtil.getDoneGameClientList(accountDoneFileName);
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

        while (gameClientList.size() > 0) {
            GameClient gameClient = gameClientList.poll();
            if (gameClient == null) {
                break;
            }
            while (gameClientDoneList.contains(gameClient.getUsername())) {
                gameClient = gameClientList.poll();
                if (gameClient == null) {
                    break;
                }
            }
            if (gameClient == null) {
                break;
            }
            gameClient.init();
            gameClient.waitForLoginSuccess();
            gameClient.mergeBag();
            if (gameClient.getCurrentMapId() < 13000) {
                gameClient.moveFar(12001);
            } else {
                gameClient.move(255);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameClient.waitForLoginSuccess();
                gameClient.moveFar(12001);
            }

            //1 仓库号交易给 gameClient
            storeGameClient.getGameConfig().setAutoTradePet(false);
            int petIndex = storeGameClient.getPetIndex(npcId);
            storeGameClient.tradePetRequest(gameClient.getUserId());
            storeGameClient.tradePet(petIndex);
            storeGameClient.tradePetConfirm();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //2 gameClient吃升华丹
            int petIndex2 = gameClient.getPetIndex(npcId);
            BagItem[] bagItems = gameClient.getBagItems();
            for (int i = 1; i <= 25; i++) {
                if(bagItems == null) {
                    continue;
                }
                BagItem bagItem = bagItems[i];
                if (bagItem == null) {
                    continue;
                }
                if (bagItem.getCount() == 0) {
                    continue;
                }
                if (bagItem.getId() != itemId) {
                    continue;
                }
                gameClient.useItem(i, bagItem.getCount(), petIndex2);
            }

            //3 交易回仓库号
            gameClient.getGameConfig().setAutoTradePet(false);
            storeGameClient.getGameConfig().setAutoTradePet(true);
            gameClient.tradePetRequest(storeGameClient.getUserId());
            gameClient.tradePet(petIndex2);
            gameClient.tradePetConfirm();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println("done!!!");


            if (gameClient.getItemCount(itemId) == 0) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(accountDoneFileName), true));
                bufferedWriter.write(gameClient.getUsername());
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
            } else {
                System.out.println("吃完了");
                gameClient.close();
                break;
            }
            gameClient.close();
        }
        storeGameClient.close();
    }
}
