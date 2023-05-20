package tk.gbl.core.script.pass;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/4/26
 * Time: 14:21
 *
 * @author Tian.Dong
 */
public class PubanjinAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始过蒲阪津渡口");
        gameClient.moveFar(14441);
        while (gameClient.getCurrentMapId() != 14441) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("来到14441蒲阪津渡口");

        gameClient.simpleMove(8);
        while (gameClient.getCurrentMapId() != 22000) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("来到22000关中");
    }
}
