package tk.gbl.script;

import tk.gbl.core.GameClient;

import java.io.IOException;

/**
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class MainTank3 {
    public static void main(String[] args) throws IOException {
        final GameClient gameClient = new GameClient();
        gameClient.setIndex(3);
        gameClient.setUsername("WP00115039");
        gameClient.setPassword("123456");
//        gameClient.setUsername("WP00114245");
//        gameClient.setPassword("123456");
        gameClient.setTeamLeaderId(115038);
        gameClient.setInitMapId(12808);
        gameClient.init();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5秒以后");
    }

}
