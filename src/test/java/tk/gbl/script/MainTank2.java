package tk.gbl.script;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.listener.FightEndListener;
import tk.gbl.core.listener.RoundStartListener;

import java.io.IOException;

/**
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class MainTank2 {
    public static void main(String[] args) throws IOException {
        final GameClient gameClient = new GameClient();
        gameClient.setIndex(6);
//        gameClient.setConsoleOutput(true);
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient.setUsername("WP00115038");
        gameClient.setPassword("123456");
        gameClient.setTeamLeaderId(0);
        gameClient.setInitMapId(12001);
        gameClient.init();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5秒以后");
        System.out.println(gameClient.getCurrentMapId());
    }

}
