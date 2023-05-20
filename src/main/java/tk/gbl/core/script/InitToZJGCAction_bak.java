package tk.gbl.core.script;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * 从网吧到涿郡广场
 * <p/>
 * Date: 2017/4/4
 * Time: 18:22
 *
 * @author Tian.Dong
 */
public class InitToZJGCAction_bak extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.moveTo(1234, 692);
        gameClient.moveTrigger(1);
        gameClient.move(1);
        gameClient.eventOk(4);
        gameClient.moveAfter();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.moveTo(1242, 215);//移动到南斗星君
        gameClient.clickNPC(2);
        gameClient.choose(0x1F);
        gameClient.eventOk(9);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.moveTo(1422, 768);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.moveTrigger(2);
        gameClient.move(2);
        gameClient.choose(0x1E);
        gameClient.eventOk();
        gameClient.moveAfter();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        OutputUtil.output("来到涿郡广场，自动使用物品", gameClient, true);
        gameClient.autoUseAndThrow();
        gameClient.moveFar(12001);
    }
}
