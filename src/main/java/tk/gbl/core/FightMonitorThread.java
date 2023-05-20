package tk.gbl.core;

import tk.gbl.util.OutputUtil;

import java.net.Socket;
import java.util.Date;

/**
 * 监控战斗
 * <p/>
 * Date: 2017/3/27
 * Time: 19:29
 *
 * @author Tian.Dong
 */
public class FightMonitorThread extends Thread {

    GameClient gameClient;

    public FightMonitorThread(GameClient gameClient) {
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
            if (new Date().getTime() - gameClient.getFightTime().getTime() > gameClient.getGameConfig().getNotFightReconnectTime()) {
                OutputUtil.output("120秒没进入战斗自动断线",gameClient,true);
                gameClient.clear();
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
