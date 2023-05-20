package tk.gbl.script;

import tk.gbl.core.GameClient;

import java.io.IOException;

/**
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class MainTank {
    public static void main(String[] args) throws IOException {
        final GameClient gameClient = new GameClient();
        gameClient.setIndex(3);
        gameClient.setUsername("47813");
        gameClient.setPassword("198410");
        gameClient.setTeamLeaderId(0);
        gameClient.init();

        gameClient.waitForLoginSuccess();
        if (gameClient.getCurrentMapId() == 12003) {
            gameClient.exitTeam();
            gameClient.setJoinTeam(false);
        }
       System.out.println("当前地图："+gameClient.getCurrentMapId());
        //23级箭舞九天
//        gameClient.addSkill(2,14037,23);
        //23级狂热
//        gameClient.addSkill(3,12025,23);
//        gameClient.addSkill(3,12003,28);
        //19级万马
//        gameClient.addSkill(2,10013,19);
        //19级鼓舞
        gameClient.addSkill(3,14013,19);
        System.out.println("!!!!!!!!!!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.close();
        System.out.println("close!");
    }

}
