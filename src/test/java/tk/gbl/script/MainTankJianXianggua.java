package tk.gbl.script;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.SocketReadThread;
import tk.gbl.show.ShowUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class MainTankJianXianggua {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("114.112.97.137", 6414);

        GameClient gameClient = new GameClient();
        gameClient.setConsoleOutput(false);
        gameClient.setSocket(socket);
        gameClient.connect();

        InputStream is = null;
        try {
            is = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        byte[] results = new byte[ProcessConstant.cacheMaxLength];
        int respLength = is.read(results);
        System.out.println(ShowUtil.show(results));

        gameClient.setUsername("WP00114244");
        gameClient.setPassword("123456");
        gameClient.login();


//        if (loginSuccess) {
        SocketReadThread socketReadThread = new SocketReadThread(gameClient);
        socketReadThread.start();
//        }


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int allSellCount = 0;
        while (true) {
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
                                System.out.println("物品栏已满");
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
            System.out.println("捡完了");
//            gameClient.moveTo(622, 1395);
//            gameClient.moveTo(442, 1515);//移动到邺城广场左下角
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameClient.simpleMove(2);//到邺城王宫
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameClient.simpleMove(1);//到邺城大街
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            gameClient.moveTo(502, 435);//邺城大街502,435
//            gameClient.moveTo(22, 475);//邺城大街左下角
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameClient.simpleMove(1);//到邺城城门
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameClient.moveTo(1002, 615);//邺城城门杂货商人
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("开始贩卖");
            //贩卖
            gameClient.clickNPC(2);
            gameClient.choose(0x1F);
            gameClient.eventOk();
            int sellCount = 0;
            for (int i = 1; i <= 25; i++) {
                if (gameClient.getBagItems() == null) {
                    break;
                }
//                BagItem bagItem = gameClient.getBagItems()[i];
//                if (bagItem == null) {
//                    continue;
//                }
//                if (bagItem.getId() != 26044) {
//                    continue;
//                }
//                gameClient.sell(i, bagItem.getCount());
                gameClient.sell(i, 50);
                gameClient.getBagItems()[i] = null;
                sellCount += 50;
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
            allSellCount += sellCount;
            System.out.println("卖完了" + sellCount + "个,总：" + allSellCount);
            System.out.println("开始移动!!!!");
            gameClient.simpleMove(2);//到邺城主街道

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameClient.simpleMove(2);//到邺城王宫
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameClient.simpleMove(3);//到邺城广场
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行完一个流程");
        }
    }

    private static void createNewRole(GameClient gameClient) throws IOException {
        gameClient.checkName("嗷大猫");
    }
}
