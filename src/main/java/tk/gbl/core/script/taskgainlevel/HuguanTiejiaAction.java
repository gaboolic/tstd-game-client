package tk.gbl.core.script.taskgainlevel;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 壶关铁甲军
 * <p/>
 * Date: 2017/4/25
 * Time: 19:25
 *
 * @author Tian.Dong
 */
public class HuguanTiejiaAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始打铁甲军");
        System.out.println("向12432开始移动");
        gameClient.moveFar(12432);
        System.out.println("移动到12432");
        System.out.println("当前地图id:" + gameClient.getCurrentMapId());
        gameClient.segmentMoveTo(122,575);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.getGameConfig().setGameConfigType(GameConfigType.gainLevel);
        while (gameClient.getTeamUsers().size() == gameClient.getAllowTeamUsers().size()
                && !customJudge()) {
            System.out.println("clickNPC");
//            gameClient.clickNPC(2);
            gameClient.clickNPC(3);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("循环条件"+gameClient.getTeamUsers().size() + " " +gameClient.getAllowTeamUsers().size() +" " +!customJudge());
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("铁甲军脚本执行完成");
    }

    public boolean customJudge(){
        return true;
    }
}
