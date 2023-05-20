package tk.gbl.core;

import tk.gbl.core.frame.MainFrame;
import tk.gbl.core.script.pass.JieQiaoAction;
import tk.gbl.core.script.pass.OpenMapAction;
import tk.gbl.core.script.pass.PubanjinAction;
import tk.gbl.core.script.pass.XuzhouKuashengAction;
import tk.gbl.core.script.taskgainlevel.DaocaorenAction;
import tk.gbl.core.script.taskgainlevel.HuguanTiejiaAction;
import tk.gbl.core.script.taskgainlevel.SongyongAction;
import tk.gbl.core.script.work.badouyao.T1_kuangtingAction;
import tk.gbl.core.script.work.horse.HanxuebaomaAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Date: 2017/4/10
 * Time: 10:48
 *
 * @author Tian.Dong
 */
public class GameTeam {

    MainFrame mainFrame;
    int initMapId;
    String usernames;
    GameClient teamLeader;
    List<GameClient> gameClientList;

    public void init() {
        String[] usernameArray = usernames.split(" ");
        teamLeader = new GameClient();
        teamLeader.setGameTeam(this);
        teamLeader.setUsername(usernameArray[0]);
        teamLeader.setPassword("123456");
        long teamLeaderUserId = Long.parseLong(teamLeader.getUsername().substring(2));
        teamLeader.setTeamLeaderId(teamLeaderUserId);
        teamLeader.setInitMapId(initMapId);
        gameClientList = new ArrayList<>();
        List<Long> allowTeamUserIds = new ArrayList<>();
        for (int i = 1; i < usernameArray.length; i++) {
            GameClient gameClient = new GameClient();
            gameClient.setUsername(usernameArray[i]);
            gameClient.setPassword("123456");
            gameClient.setTeamLeaderId(teamLeaderUserId);
            gameClient.setInitMapId(initMapId);
            gameClientList.add(gameClient);
            allowTeamUserIds.add(Long.parseLong(gameClient.getUsername().substring(2)));
        }
        teamLeader.setAllowTeamUsers(allowTeamUserIds);

        teamLeader.init();
        if (gameClientList != null) {
            for (GameClient gameClient : gameClientList) {
                gameClient.init();
            }
        }
    }

    public void teamSuccess() throws IOException {
        new Thread() {
            public void run() {
                try {
//                    teamLeader.doScriptAction(new OpenMapAction());

//                    teamLeader.doScriptAction(new DaocaorenAction());
//                    teamLeader.doScriptAction(new SongyongAction());
//                    teamLeader.doScriptAction(new JieQiaoAction());
//                    teamLeader.doScriptAction(new HuguanTiejiaAction());
//                    teamLeader.doScriptAction(new PubanjinAction());
//                    teamLeader.doScriptAction(new T1_kuangtingAction());
//                    teamLeader.doScriptAction(new HanxuebaomaAction());
//                    teamLeader.moveFar(12001);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 全部25级
     */
    public boolean all25() {
        for (GameClient gameClient : gameClientList) {
            if (gameClient.getRoleInfo().getExp() < 78490) {
                return false;
            }
        }
        return true;
    }

    public void closeTeamMember() {
        for (GameClient gameClient : gameClientList) {
            try {
                gameClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshMainFrame(String str) {
        if(mainFrame != null) {
            mainFrame.refresh(this,str);
        }
    }

    public void qingliu() {

    }

    public long getNextUserId(int index) {
        return 0;
    }



    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public GameClient getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(GameClient teamLeader) {
        this.teamLeader = teamLeader;
    }

    public List<GameClient> getGameClientList() {
        return gameClientList;
    }

    public void setGameClientList(List<GameClient> gameClientList) {
        this.gameClientList = gameClientList;
    }

    public String getUsernames() {
        return usernames;
    }

    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }

    public int getInitMapId() {
        return initMapId;
    }

    public void setInitMapId(int initMapId) {
        this.initMapId = initMapId;
    }
}
