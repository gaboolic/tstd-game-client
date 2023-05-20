package tk.gbl;

import tk.gbl.core.GameClient;
import tk.gbl.core.SocketReadThread;
import tk.gbl.core.script.PickUpPearAction;
import tk.gbl.core.script.ThrowPearAction;

import java.io.IOException;
import java.net.Socket;

/**
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class MainTankShuaqian {
    public static void main(String[] args) throws IOException {
        final String shuaqianUsername;
        if (args.length > 0) {
            shuaqianUsername = args[0];
        } else {
            shuaqianUsername = "WP00114244";//44 45 46
        }

        new Thread() {
            public void run() {
                GameClient gameClient = new GameClient();
                gameClient.setConsoleOutput(false);
                gameClient.setIndex(1);
                gameClient.setInitMapId(12064);
                gameClient.setUsername("WP00113312");
                gameClient.setPassword("123456");
                gameClient.init();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        gameClient.doScriptAction(new ThrowPearAction());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();


        new Thread() {
            public void run() {
                GameClient gameClient = new GameClient();
                gameClient.setConsoleOutput(false);
                gameClient.setIndex(2);
                gameClient.setInitMapId(12064);
                gameClient.setUsername(shuaqianUsername);
                gameClient.setPassword("123456");
                gameClient.init();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        gameClient.doScriptAction(new PickUpPearAction());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


    }

}
