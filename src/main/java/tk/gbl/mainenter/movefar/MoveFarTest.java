package tk.gbl.mainenter.movefar;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/6/14
 * Time: 12:37
 *
 * @author Tian.Dong
 */
public class MoveFarTest {
    public static void main(String[] args){
        String username = "wp27166,wp27167";
        String password = "198410,198410";
        String[] usernames = username.split(",");
        String[] passwords = password.split(",");

        GameTeam gameTeam = new GameTeam();
        GameClient gameClient1 = new GameClient();
        gameTeam.setTeamLeader(gameClient1);
        gameClient1.setGameTeam(gameTeam);
        gameClient1.getGameConfig().setQingliu(true);
        gameClient1.setIndex(1);
        gameClient1.setConsoleOutput(true);
        gameClient1.setFileOutput(true);
        gameClient1.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient1.setUsername(usernames[0]);
        gameClient1.setPassword(passwords[0]);
        gameClient1.setInitMapId(0);
        gameClient1.getAllowTeamUsers().add(27167L);
        gameClient1.init();
        gameClient1.setTeamLeaderId(gameClient1.getUserId());
        gameClient1.waitForLoginSuccess();

        GameClient gameClient2 = new GameClient();
        gameClient2.setGameTeam(gameTeam);
        gameClient2.getGameConfig().setQingliu(true);
        gameClient2.setIndex(1);
        gameClient2.setConsoleOutput(true);
        gameClient2.setFileOutput(true);
        gameClient2.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient2.setUsername(usernames[1]);
        gameClient2.setPassword(passwords[1]);
        gameClient2.setInitMapId(0);
        gameClient2.init();
        gameClient2.setTeamLeaderId(gameClient1.getUserId());
        gameClient2.waitForLoginSuccess();

        List<GameClient> gameClientList = new ArrayList<>();
        gameClientList.add(gameClient2);
        gameTeam.setGameClientList(gameClientList);

        while (gameClient1.getAllowTeamUsers().size() != gameClient1.getTeamUsers().size()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameClient1.moveFar(12001);
    }
}
