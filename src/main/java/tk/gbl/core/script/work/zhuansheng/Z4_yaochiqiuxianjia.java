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
public class Z4_yaochiqiuxianjia extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "瑶池求仙甲-1=59453=1=470,280=0=0==0=True=12=0=0\n" +
                "瑶池求仙甲-2=59422=1=1210,560=0=0==0=True=10=7=0\n" +
                "瑶池求仙甲-3=59453=1=470,280=0=0==0=True=12=0=0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成瑶池求仙甲");
    }
}
