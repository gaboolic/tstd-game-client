package tk.gbl.script;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.listener.FightEndListener;
import tk.gbl.core.listener.JoinTeamRequestListener;
import tk.gbl.core.listener.RoundStartListener;
import tk.gbl.core.script.taskgainlevel.DaocaorenAction;
import tk.gbl.core.script.taskgainlevel.SongyongAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class MainTank1 {
    public static void main(String[] args) throws IOException {
        GameTeam gameTeam = new GameTeam();
        final GameClient gameClient = new GameClient();
        gameClient.setIndex(1);
        gameClient.setConsoleOutput(true);
        gameTeam.setTeamLeader(gameClient);
        gameClient.setGameTeam(gameTeam);
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient.setUsername("WP00115037");
        gameClient.setPassword("123456");
        gameClient.setTeamLeaderId(0);
        gameClient.setInitMapId(0);

        List<Long> allowTeamUsers = new ArrayList<>();
//        allowTeamUsers.add(115038L);
//        allowTeamUsers.add(115039L);
//        allowTeamUsers.add(115040L);
//        allowTeamUsers.add(41590L);
//        allowTeamUsers.add(47810L);
//        allowTeamUsers.add(47811L);
//        allowTeamUsers.add(47812L);
//        allowTeamUsers.add(47813L);
        gameClient.setAllowTeamUsers(allowTeamUsers);
        gameClient.init();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5秒以后");
//        gameClient.doScriptAction(new SongyongAction());
        /*System.out.println(gameClient.getPoint());
        gameClient.segmentMoveTo(0, 0);
        System.out.println(gameClient.getPoint());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gameClient.segmentMoveTo(4000, 4000);
        System.out.println(gameClient.getPoint());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(gameClient.getPoint());*/
        gameClient.doScriptAction(new DaocaorenAction());
    }

}
