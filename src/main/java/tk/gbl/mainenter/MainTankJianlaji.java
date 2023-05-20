package tk.gbl.mainenter;

import tk.gbl.bean.BagItem;
import tk.gbl.core.GameClient;

import java.io.IOException;

/**
 * Date: 2017/5/28
 * Time: 12:32
 *
 * @author Tian.Dong
 */
public class MainTankJianlaji {
    public static void main(String[] args) {
        String shuaqianUsername = "WP00114245";
        if(args.length > 0) {
            shuaqianUsername = args[0];
        }

        GameClient gameClient = new GameClient();
        gameClient.setConsoleOutput(false);
        gameClient.setFileOutput(false);
        gameClient.setIndex(2);
        gameClient.setInitMapId(0);
        gameClient.setUsername(shuaqianUsername);
        gameClient.setPassword("123456");
        gameClient.init();

        gameClient.waitForLoginSuccess();
        while (true) {
            try {
                gameClient.waitForLoginSuccess();
                gameClient.moveFar(12011);
//                gameClient.segmentMoveTo(570, 430);
                gameClient.segmentMoveTo(522, 495);
//                gameClient.moveFar(12001);
//                gameClient.segmentMoveTo(222, 1555);

                System.out.println("开始捡东西");

                for (int i = 0; i < 256; i++) {
                    if (gameClient.getBagItems() != null) {
                        BagItem bagItem = gameClient.getBagItems()[25];
                        if (bagItem != null) {
                            if (bagItem.getCount() > 0) {
                                break;
                            }
                        }
                    }
                    gameClient.pickUp(i);
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                gameClient.moveFar(12061);
                gameClient.segmentMoveTo(1002, 615);
                System.out.println("开始贩卖");
                //贩卖
                gameClient.getChooseQueue().clear();
                gameClient.getChooseQueue().add(2);
                gameClient.clickNPC(2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 1; i <= 25; i++) {
                    BagItem bagItem = gameClient.getBagItems()[i];
                    if (bagItem == null) {
                        continue;
                    }
                    gameClient.sell(i, gameClient.getBagItems()[i].getCount());
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
                System.out.println("卖完了");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 1; i <= 25; i++) {
                    BagItem bagItem = gameClient.getBagItems()[i];
                    if (bagItem == null) {
                        continue;
                    }
                    gameClient.throwAway(i, bagItem.getCount());
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }
}
