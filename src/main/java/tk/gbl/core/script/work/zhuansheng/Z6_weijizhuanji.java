package tk.gbl.core.script.work.zhuansheng;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/6/5
 * Time: 18:59
 *
 * @author Tian.Dong
 */
public class Z6_weijizhuanji extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "危机转机-1=59453=1=470,280=0=0==0=True=6=0=0\n" +
                "危机转机-2=59454=1=330,340=0=0==0=True=16=17=0\n" +
                "危机转机-3=59411=0=110,790=2=0==0=False=0=5=0\n" +
                "危机转机-4=59454=0=330,340=1=2==0=False=0=0=0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成危机转机");
    }
}
