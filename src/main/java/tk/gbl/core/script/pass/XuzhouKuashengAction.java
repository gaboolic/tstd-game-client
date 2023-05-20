package tk.gbl.core.script.pass;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/4/26
 * Time: 13:50
 *
 * @author Tian.Dong
 */
public class XuzhouKuashengAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始青州跨省迷宫到徐州");
        gameClient.moveFar(12000);
        gameClient.simpleMove(30);
        while (gameClient.getCurrentMapId() != 11000) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("来到11000青州");

        gameClient.moveFar(15507);
        gameClient.simpleSleep();
        System.out.println("来到15507跨省迷宫");
        gameClient.moveFar(15510);
        gameClient.simpleSleep();
        System.out.println("来到15510跨省迷宫");

        gameClient.moveFar(15000);
        gameClient.simpleSleep();
        System.out.println("来到15000徐州");
    }
}
