package tk.gbl.core.script.pass;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/6/2
 * Time: 10:46
 *
 * @author Tian.Dong
 */
public class PenglaiAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.moveFar(11000);
        gameClient.simpleMove(11);
        while (gameClient.getCurrentMapId() != 55000) {
            gameClient.simpleMove(1);
            gameClient.simpleMove(11);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("来到55000");

        String step1 = "仙童1 - 55002=55002=2=330,440=0=0=1-2=0=True=6=0=0";
        String step2 = "仙童2 - 55002=55002=2=330,440=0=0=1-10=0=True=4=0=0";
        gameClient.doDoDoScriptAction(step1);
        gameClient.doDoDoScriptAction(step2);
    }
}
