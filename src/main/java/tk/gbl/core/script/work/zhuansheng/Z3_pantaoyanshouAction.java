package tk.gbl.core.script.work.zhuansheng;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/6/5
 * Time: 18:57
 *
 * @author Tian.Dong
 */
public class Z3_pantaoyanshouAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "蟠桃延寿-1=59451=1=530,820=0=0==0=True=6=19=0\n" +
                "蟠桃延寿-2=59433=1=970,140=0=0==0=True=5=11=0\n" +
                "蟠桃延寿-3=59453=1=470,280=0=0==0=True=10=0=0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成蟠桃延寿");
    }
}
