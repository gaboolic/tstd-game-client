package tk.gbl.mainenter.compound;

import tk.gbl.bean.BagItem;
import tk.gbl.bean.Goods;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.util.ItemIdUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/6/8
 * Time: 10:35
 *
 * @author Tian.Dong
 */
public class BuyAndCompoundMainTank {
    public static void main(String[] args) throws IOException {
        String username = "41590";
        String password = ProcessConstant.password;

        final GameClient gameClient = new GameClient();
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.setIndex(5);
        gameClient.setUsername(username);
        gameClient.setPassword(password);
        gameClient.setTeamLeaderId(0);
        gameClient.init();

        gameClient.waitForLoginSuccess();

        //50 鐵胎弓 攻+9 敏+3 $5900 河東 合成
        //1                          铁胎弓 栖凤木 山越樟 千年栋 西川
        String baoliuItemStr = "46164,16007,34025,34008,34010,34007";
        List<Integer> baoliuItemList = new ArrayList<>();
        for (String storeItemStr : baoliuItemStr.split(",")) {
            baoliuItemList.add(Integer.parseInt(storeItemStr));
        }
        gameClient.moveFar(20201);
        gameClient.moveTo(310, 340);

        gameClient.showBag();
        int zhiId = 16007;
        System.out.println("开始买东西合成");

        int count = 0;
        while (true) {
            System.out.println(count++);
            if (gameClient.getRoleInfo().getMoney() < 100000) {
                System.out.println("钱不够了");
                break;
            }
            int cailiaoCount = getItemCount(gameClient, zhiId);
            if (cailiaoCount < 2) {
                gameClient.getChooseQueue().clear();
                gameClient.clickNPC(1);
                gameClient.simpleChoose(1);
                gameClient.buyItem(7, 2 - cailiaoCount);
                gameClient.choose(0x28);
                gameClient.eventOk();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //循环退出
            int bagIndexCount = 0;
            for (BagItem bagItem : gameClient.getBagItems()) {
                if (bagItem == null) {
                    continue;
                }
                if (bagItem.getCount() > 0) {
                    bagIndexCount++;
                }
            }
            if (bagIndexCount == 25) {
                System.out.println("身上满了");
                break;
            }
            //汉兴纸不够了
            int hanxingzhiCount = getItemCount(gameClient, zhiId);
            if (hanxingzhiCount < 2) {
                System.out.println("材料不够");
                break;
            }

            //把汉兴纸放到24 25
            if (gameClient.getBagItems()[24] == null || gameClient.getBagItems()[24].getCount() == 0) {
                for (int i = 1; i <= 23; i++) {
                    BagItem bagItem = gameClient.getBagItems()[i];
                    if (bagItem == null) {
                        continue;
                    }
                    if (bagItem.getCount() == 0) {
                        continue;
                    }
                    if (bagItem.getId() != zhiId) {
                        continue;
                    }
                    gameClient.splitItem(i, 24, 1);
                    break;
                }
            }
            if (gameClient.getBagItems()[25] == null || gameClient.getBagItems()[25].getCount() == 0) {
                for (int i = 1; i <= 23; i++) {
                    BagItem bagItem = gameClient.getBagItems()[i];
                    if (bagItem == null) {
                        continue;
                    }
                    if (bagItem.getCount() == 0) {
                        continue;
                    }
                    if (bagItem.getId() != zhiId) {
                        continue;
                    }
                    gameClient.splitItem(i, 25, 1);
                    break;
                }
            }

            gameClient.compoundItem(24, 1, 25, 1);


            //扔掉垃圾
            for (int i = 1; i <= 25; i++) {
                BagItem bagItem = gameClient.getBagItems()[i];
                if (bagItem == null) {
                    continue;
                }
                if (bagItem.getCount() == 0) {
                    continue;
                }
                //如果不是汉兴纸 机关盒 烽火燎原符
                if (!baoliuItemList.contains(bagItem.getId())) {
                    Goods goods = ItemIdUtil.getGoods(bagItem.getId());
                    if (!goods.getName().contains("扇")) {
                        gameClient.throwAway(i, bagItem.getCount());
                    }
                }
            }
            gameClient.mergeBag();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("执行完毕");
        gameClient.showBag();
        gameClient.close();
    }

    public static int getItemCount(GameClient gameClient, int zhiId) {
        int hanxingzhiCount = 0;
        for (int i = 1; i <= 23; i++) {
            BagItem bagItem = gameClient.getBagItems()[i];
            if (bagItem == null) {
                continue;
            }
            if (bagItem.getCount() == 0) {
                continue;
            }
            if (bagItem.getId() == zhiId) {
                hanxingzhiCount += bagItem.getCount();
            }
        }
        return hanxingzhiCount;
    }
}
