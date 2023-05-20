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
public class BuyAndCompoundDaoJiMainTank {
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
        gameClient.getGameConfig().setQuickNPCDialog(false);
        gameClient.init();



        gameClient.waitForLoginSuccess();
        gameClient.moveTo(490,400);
        for(int i=0;i<15;i++) {
            //1                                  买硬弓5=13202=1=442,395=5=0=1-1=0=False=4-6=0=0
//            gameClient.doDoDoScriptAction("甘興霸-荊楚酒=19241=1=350,400=5=0=1-1=0=False=6-20=0=0");
            //甘興霸-風乾羊肉=12242=1=490,400=5=0=1=0=False=3-20=0=-1
            gameClient.clickNPC(1);
            gameClient.simpleChoose(1);
            gameClient.buyItem(2,50);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameClient.choose(0x28);
            gameClient.eventOk();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameClient.close();
        System.exit(0);

        gameClient.moveFar(11201);

        //15015	百锻戟 10016	江鳄刀 都是5级
        String baoliuItemStr = "46164,15015,10016,26050,10005,12005";
        List<Integer> baoliuItemList = new ArrayList<>();
        for (String storeItemStr : baoliuItemStr.split(",")) {
            baoliuItemList.add(Integer.parseInt(storeItemStr));
        }

        gameClient.showBag();
        int zhiId = 12005;
        System.out.println("开始买东西合成");

        int count = 0;
        while (true) {
            System.out.println(count++);
            if (gameClient.getRoleInfo().getMoney() < 100000) {
                System.out.println("钱不够了");
                break;
            }
            int cailiaoCount = getItemCount(gameClient, zhiId);
            if (cailiaoCount < 3) {
                gameClient.getChooseQueue().clear();
                gameClient.clickNPC(1);
                gameClient.eventOk();
                gameClient.simpleChoose(1);
                for(int i=0;i<3-cailiaoCount;i++) {
                    gameClient.buyItem(6, 1);
                }
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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //汉兴纸不够了
            int hanxingzhiCount = getItemCount(gameClient, zhiId);
            if (hanxingzhiCount < 3) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            hanxingzhiCount = getItemCount(gameClient, zhiId);
            if (hanxingzhiCount < 3) {
                System.out.println("材料不够");
                break;
            }

            //把材料放到24 25
            if (gameClient.getBagItems()[23] == null || gameClient.getBagItems()[23].getCount() == 0) {
                for (int i = 1; i <= 22; i++) {
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
                    gameClient.splitItem(i, 23, 1);
                    break;
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (gameClient.getBagItems()[24] == null || gameClient.getBagItems()[24].getCount() == 0) {
                for (int i = 1; i <= 22; i++) {
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
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (gameClient.getBagItems()[25] == null || gameClient.getBagItems()[25].getCount() == 0) {
                for (int i = 1; i <= 22; i++) {
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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("拆分完物品开始合成");
            gameClient.showBag();
            gameClient.compoundItem(23, 1, 24, 1, 25, 1);


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
