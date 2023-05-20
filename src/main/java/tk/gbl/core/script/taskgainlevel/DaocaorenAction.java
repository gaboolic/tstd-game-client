package tk.gbl.core.script.taskgainlevel;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/4/11
 * Time: 18:03
 *
 * @author Tian.Dong
 */
public class DaocaorenAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始打稻草人");
        System.out.println("向12808开始移动");
        gameClient.moveFar(12808);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("移动到12808");
        System.out.println("当前地图id:"+gameClient.getCurrentMapId());
        gameClient.moveTo(702, 1700);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.getGameConfig().setGameConfigType(GameConfigType.gainLevel);
        while (gameClient.getTeamUsers().size() == gameClient.getAllowTeamUsers().size()) {
            gameClient.clickNPC(1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("稻草人脚本执行完成");
    }
}
