package tk.gbl.core.script;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/5/6
 * Time: 9:17
 *
 * @author Tian.Dong
 */
public class NewZhenchashuAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始学侦察术");
        gameClient.moveFar(12136);
        gameClient.segmentMoveTo(502, 315);
        gameClient.clickNPC(1);
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);
        gameClient.getChooseQueue().add(3);
        gameClient.getChooseQueue().add(3);
        gameClient.getChooseQueue().add(2);
        gameClient.simpleSleep();
        System.out.println("学完侦察术");
    }
}
