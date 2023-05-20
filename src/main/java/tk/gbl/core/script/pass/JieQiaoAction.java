package tk.gbl.core.script.pass;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/4/19
 * Time: 16:09
 *
 * @author Tian.Dong
 */
public class JieQiaoAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始过界桥");
        gameClient.moveFar(12000);
        while (gameClient.getCurrentMapId() != 12000) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("来到12000");
        gameClient.move(16);
        while (gameClient.getCurrentMapId() != 12441) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameClient.moveAfter();
        System.out.println("来到12441界桥");
        gameClient.move(2);
        gameClient.moveAfter();
        while (gameClient.getCurrentMapId() != 12000) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("过了界桥");
    }
}
