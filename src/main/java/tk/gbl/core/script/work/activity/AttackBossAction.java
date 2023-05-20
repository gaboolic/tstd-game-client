package tk.gbl.core.script.work.activity;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/5/26
 * Time: 19:11
 *
 * @author Tian.Dong
 */
public class AttackBossAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String step = "BOSS挑战员 - 12001=12001=59=1870,1180=0=0=1=0=False=0=0=0";
        gameClient.doDoDoScriptAction(step);
        gameClient.simpleSleep();
    }
}
