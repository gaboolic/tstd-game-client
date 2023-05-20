package tk.gbl.core.script.work.shuaqian;

import tk.gbl.bean.BagItem;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * Date: 2017/4/6
 * Time: 14:29
 *
 * @author Tian.Dong
 */
public class PickUpPearAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setAutoMergeBag(false);
        gameClient.moveFar(12064);
        while (gameClient.getCurrentMapId() != 12064) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int allSellMoney = 0;
        while (true) {
            long startMoney = gameClient.getRoleInfo().getMoney();
            gameClient.moveFar(12064);
            gameClient.moveTo(1010, 1160);//邺城广场 捡东西
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("开始捡东西");

            for (int count = 0; count < 8; count++) {
                for (int i = 0; i < 256; i++) {
                    if (gameClient.getBagItems() != null) {
                        BagItem bagItem = gameClient.getBagItems()[25];
                        if (bagItem != null) {
                            if (bagItem.getCount() == 50) {
                                OutputUtil.output("物品栏已满", gameClient, true);
                                break;
                            }
                        }
                    }
                    gameClient.pickUp(i);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            gameClient.moveFar(12061);
            gameClient.segmentMoveTo(1002, 615);//邺城城门杂货商人
            //贩卖
            gameClient.getChooseQueue().clear();
            gameClient.getChooseQueue().add(2);
            gameClient.clickNPC(2);
            gameClient.sleep(500);
            int sellCount = 0;
            for (int i = 1; i <= 25; i++) {
                if (gameClient.getBagItems() == null) {
                    break;
                }
                BagItem bagItem = gameClient.getBagItems()[i];
                if (bagItem == null) {
                    continue;
                }
                gameClient.sell(i, bagItem.getCount());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sellCount += bagItem.getCount();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gameClient.eventOk();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //贩卖完毕
            allSellMoney += (gameClient.getRoleInfo().getMoney() - startMoney);
            OutputUtil.output("得到金币" + (gameClient.getRoleInfo().getMoney() - startMoney) + "个,总：" + gameClient.getRoleInfo().getMoney(), gameClient, true);
            if(gameClient.getRoleInfo().getMoney() > 9900000) {
                break;
            }
        }
        OutputUtil.output("总共得到金币" + allSellMoney, gameClient, true);
    }
}