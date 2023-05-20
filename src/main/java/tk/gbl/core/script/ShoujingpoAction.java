package tk.gbl.core.script;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/5/6
 * Time: 9:08
 *
 * @author Tian.Dong
 */
public class ShoujingpoAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始收惊婆");
        gameClient.moveFar(12001);
        System.out.println("来到12001");
        gameClient.segmentMoveTo(1102, 995);
        gameClient.clickNPC(21);
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("收惊婆完毕");
    }
}
