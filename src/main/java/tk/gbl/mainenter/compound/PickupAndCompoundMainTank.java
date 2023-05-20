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
 * Date: 2017/6/17
 * Time: 9:46
 *
 * @author Tian.Dong
 */
public class PickupAndCompoundMainTank {
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

        //26050	风乾羊肉	17	HP+37
        //26220	吉祥花生	17	HP+37
        String baoliuItemStr = "26220,26050";
        List<Integer> baoliuItemList = new ArrayList<>();
        for (String storeItemStr : baoliuItemStr.split(",")) {
            baoliuItemList.add(Integer.parseInt(storeItemStr));
        }
        gameClient.showBag();
        int zhiId = 26220;
        System.out.println("开始捡东西合成");

        int count = 0;
        while (true) {
            System.out.println(count++);

            gameClient.pickUpAll();
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
            int hanxingzhiCount = gameClient.getItemCount(zhiId);
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
                    gameClient.splitItem(i, 24, bagItem.getCount());
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
                    gameClient.splitItem(i, 25, bagItem.getCount());
                    break;
                }
            }

            gameClient.compoundItem(24, 50, 25, 50);


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
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("执行完毕");
        gameClient.showBag();
        gameClient.close();
    }
}
