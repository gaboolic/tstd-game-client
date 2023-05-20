package tk.gbl.core.script.taskgainlevel;

import tk.gbl.bean.Point;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.ScriptAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/6/25
 * Time: 22:10
 *
 * @author Tian.Dong
 */
public class PaoyeAction extends ScriptAction {

    boolean isPet = true;
    int endExp;
    int endLevel;

    int mapId;

    List<Point> pointList = new ArrayList<>();

    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.moveFar(mapId);

        int count = 0;
        while (true) {
            int index = count % pointList.size();
            Point point = pointList.get(index);
            gameClient.segmentMoveTo(point.getX(), point.getY());
            gameClient.simpleSleep2();
            if (gameClient.getTeamUsers().size() == gameClient.getAllowTeamUsers().size() && customJudge(gameClient)) {
                break;
            }
            count++;
        }
        System.out.println("循环条件" + gameClient.getTeamUsers().size() + " " + gameClient.getAllowTeamUsers().size() + " " + customJudge(gameClient));
        System.out.println("跑野脚本执行完成");
    }

    private boolean customJudge(GameClient theGameClient) {
        GameTeam gameTeam = theGameClient.getGameTeam();
        boolean isAll25 = true;
        if (isPet) {
            if (gameTeam.getTeamLeader().getPet().getLevel() < endLevel) {
                isAll25 = false;
            }
        } else {
            if (gameTeam.getTeamLeader().getRoleInfo().getLevel() < endLevel) {
                isAll25 = false;
            }
        }
        for (GameClient gameClient : gameTeam.getGameClientList()) {
            if (isPet) {
                if (gameClient.getPet().getLevel() < endLevel) {
                    isAll25 = false;
                }
            } else {
                if (gameClient.getRoleInfo().getLevel() < endLevel) {
                    isAll25 = false;
                }
            }
        }
        return isAll25;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    public int getEndExp() {
        return endExp;
    }

    public void setEndExp(int endExp) {
        this.endExp = endExp;
    }

    public int getEndLevel() {
        return endLevel;
    }

    public void setEndLevel(int endLevel) {
        this.endLevel = endLevel;
    }

    public boolean isPet() {
        return isPet;
    }

    public void setPet(boolean isPet) {
        this.isPet = isPet;
    }
}
