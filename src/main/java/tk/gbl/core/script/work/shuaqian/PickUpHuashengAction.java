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
public class PickUpHuashengAction extends ScriptAction {
    long teamLeaderId = 0;

    public PickUpHuashengAction() {

    }

    public PickUpHuashengAction(long teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }

    @Override
    public void doAction(GameClient gameClient) throws IOException {
        OutputUtil.output("开始执行捡花生卖钱(start do pickuphuasheng)",gameClient,true);
        teamLeaderId = 0;
        gameClient.getGameConfig().setAutoMergeBag(false);
        gameClient.moveFar(12064);
        int allSellMoney = 0;
        while (true) {
            if (gameClient.getRoleInfo().getMoney() > 9900000) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            long startMoney = gameClient.getRoleInfo().getMoney();
            gameClient.moveFar(12064);
            if (teamLeaderId == 0) {
                gameClient.segmentMoveTo(1350, 80);//邺城广场 捡东西
            } else {
                gameClient.joinTeam(teamLeaderId);
                gameClient.sleep(1000);
                gameClient.exitTeam();
            }
            OutputUtil.output("开始捡东西", gameClient, true);

            for (int count = 0; count < 5; count++) {
                if (gameClient.getBagItems() != null) {
                    BagItem bagItem = gameClient.getBagItems()[25];
                    if (bagItem != null) {
                        if (bagItem.getCount() == 50) {
                            OutputUtil.output("物品栏已满", gameClient, true);
                            break;
                        }
                    }
                }
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
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            gameClient.exitTeam();
            OutputUtil.output("开始贩卖", gameClient, true);
            gameClient.moveFar(12061);
            gameClient.segmentMoveTo(1002, 615);//邺城城门杂货商人
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
//                gameClient.sell(i, bagItem.getCount());
                gameClient.sell(i, 50);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sellCount += bagItem.getCount();
            }
            gameClient.eventOk();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //贩卖完毕

            allSellMoney += (gameClient.getRoleInfo().getMoney() - startMoney);
            OutputUtil.output("得到金币" + (gameClient.getRoleInfo().getMoney() - startMoney) + "个,总：" + gameClient.getRoleInfo().getMoney(), gameClient, true);
        }
//        OutputUtil.output("总共得到金币" + allSellMoney, gameClient, true);
    }
}