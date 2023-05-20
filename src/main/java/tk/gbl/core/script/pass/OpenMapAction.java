package tk.gbl.core.script.pass;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/4/26
 * Time: 14:35
 *
 * @author Tian.Dong
 */
public class OpenMapAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.doScriptAction(new JieQiaoAction());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.doScriptAction(new XuzhouKuashengAction());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.doScriptAction(new PubanjinAction());

        gameClient.moveFar(12001);
        while (gameClient.getCurrentMapId() != 12001) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("来到12001涿郡");
    }
}
