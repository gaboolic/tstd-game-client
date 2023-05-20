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
public class Z5_shenmideqinruzhe extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "神秘的侵入者-1=59411=1=610,440=0=0==0=True=11=0=0\n" +
                "神秘的侵入者-2=59832=1=1450,980=0=0==0=True=2=5=0\n" +
                "神秘的侵入者-3=59812=1=170,2360=0=0==0=True=2=5=0\n" +
                "神秘的侵入者-4=59822=1=1550,420=0=0==0=True=2=5=0\n" +
                "神秘的侵入者-5=59802=1=170,2340=0=0==0=True=2=5=0\n" +
                "神秘的侵入者-6=59411=1=610,440=0=0==0=True=5=0=0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成神秘的侵入者");
    }
}
