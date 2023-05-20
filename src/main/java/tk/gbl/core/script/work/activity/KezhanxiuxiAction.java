package tk.gbl.core.script.work.activity;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/6/7
 * Time: 18:54
 *
 * @author Tian.Dong
 */
public class KezhanxiuxiAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String step = "店小二 - 12007=12007=1=330,400=0=0=2=0=False=0=0=0";
        gameClient.doDoDoScriptAction(step);
        gameClient.eventU1F01();
        gameClient.eventOk();
    }
}
