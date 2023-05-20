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
 * Date: 2017/6/4
 * Time: 18:08
 *
 * @author Tian.Dong
 */
public class CompoundMainTank {
    public static void main(String[] args) throws IOException {
//        String username = "41590";
//        String password = ProcessConstant.password;
        String username = "120111";
        String password = ProcessConstant.p123456;

        final GameClient gameClient = new GameClient();
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.setIndex(6);
        gameClient.setUsername(username);
        gameClient.setPassword(password);
        gameClient.setTeamLeaderId(0);
        gameClient.init();

        gameClient.waitForLoginSuccess();

        for(int j=0;j<1000;j++) {
            for (int i = 1; i < 10; i++) {
                gameClient.pickUp(i);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.close();
        System.exit(0);

        //1                  汉兴纸 机关盒 烽火燎原符
//        String baoliuItemStr = "41040,46164,25044,41034,41039,25058,25012,25044,25029";
        String baoliuItemStr = "40008,40015,46164";
        List<Integer> baoliuItemList = new ArrayList<>();
        for (String storeItemStr : baoliuItemStr.split(",")) {
            baoliuItemList.add(Integer.parseInt(storeItemStr));
        }

        int zhiId = 40015;
        while (true) {
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
//            if (bagIndexCount > 23) {
//                break;
//            }
            //汉兴纸不够了
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
            if(hanxingzhiCount < 2) {
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

            gameClient.compoundItem(24,1,25,1);


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
        gameClient.showBag();
        gameClient.close();
    }
}
