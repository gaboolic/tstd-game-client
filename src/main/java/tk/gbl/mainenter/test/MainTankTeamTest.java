package tk.gbl.mainenter.test;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.frame.MainFrame;
import tk.gbl.core.script.taskgainlevel.DaocaorenAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/7/8
 * Time: 17:29
 *
 * @author Tian.Dong
 */
public class MainTankTeamTest {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        GameTeam gameTeam = new GameTeam();
        gameTeam.setMainFrame(mainFrame);
        GameClient teamLeader = new GameClient();
        teamLeader.setIndex(1);
        teamLeader.setConsoleOutput(true);
        teamLeader.setFileOutput(true);
        teamLeader.getGameConfig().setGameConfigType(GameConfigType.work);
        teamLeader.setUsername("115037");
        teamLeader.setPassword("123456");
        teamLeader.init();
        teamLeader.setTeamLeaderId(teamLeader.getUserId());
        teamLeader.getAllowTeamUsers().add(115038L);
        teamLeader.getAllowTeamUsers().add(115039L);
        teamLeader.getAllowTeamUsers().add(115040L);
        teamLeader.getAllowTeamUsers().add(115041L);
        teamLeader.setGameTeam(gameTeam);
        gameTeam.setTeamLeader(teamLeader);

        List<GameClient> gameClientList = new ArrayList<>();
        for (int i = 115038; i <= 115041; i++) {
            GameClient gameClient = new GameClient();
            gameClient.setIndex(i - 115038 + 2);
            gameClient.setConsoleOutput(false);
            gameClient.setFileOutput(true);
            gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
            gameClient.setUsername(i+"");
            gameClient.setPassword("123456");
            gameClient.setInitMapId(0);
            gameClient.init();
            gameClient.setTeamLeaderId(teamLeader.getUserId());
            gameClientList.add(gameClient);
        }
        gameTeam.setGameClientList(gameClientList);

        teamLeader.waitForLoginSuccess();
        teamLeader.waitJoinTeam();

        try {
            teamLeader.doScriptAction(new DaocaorenAction());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
