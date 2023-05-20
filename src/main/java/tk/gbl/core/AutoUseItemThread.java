package tk.gbl.core;

import tk.gbl.bean.BagItem;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.net.Socket;

/**
 * 使用物品
 * <p/>
 * Date: 2017/3/27
 * Time: 19:29
 *
 * @author Tian.Dong
 */
public class AutoUseItemThread extends Thread {


    GameClient gameClient;

    public AutoUseItemThread(GameClient gameClient) {
        this.gameClient = gameClient;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!gameClient.isLoginSuccess()) {
                if (!gameClient.reConnect) {
                    break;
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            try {
                gameClient.autoUseAndThrow();
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
}
