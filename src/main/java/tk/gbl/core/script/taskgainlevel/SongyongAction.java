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
public class SongyongAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始打宋勇");
        System.out.println("向12001开始移动");
        gameClient.moveFar(12001);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("移动到12001");
        System.out.println("当前地图id:"+gameClient.getCurrentMapId());
        gameClient.moveTo(162,1515);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.getGameConfig().setGameConfigType(GameConfigType.gainLevel);
        while (gameClient.getTeamUsers().size() == gameClient.getAllowTeamUsers().size()) {
            if(gameClient.getCurrentMapId() != 12001) {
                System.out.println("飞了");
                break;
            }
            gameClient.clickNPC(2);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameClient.simpleChoose(1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("宋勇脚本执行完成");
    }
}
