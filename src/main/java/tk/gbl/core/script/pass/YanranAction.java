package tk.gbl.core.script.pass;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/7/1
 * Time: 16:44
 *
 * @author Tian.Dong
 */
public class YanranAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "打马腾=14411=0=1070,770=1=2==0\n" +
                "安定关=24835=0=1350,1210=1=2==0\n" +
                "卫昊=31101=1=330,340=0=0==0\n" +
                "宇文弘=31241=2=710,620=0=0==0\n" +
                "通天兵=31421=0=2350,310=1=4==0\n" +
                "飞云山贼=31425=0=2170,1830=1=4==0\n" +
                "護心境1=15444=1=550,340=0=0==0\n" +
                "何夔=15568=5=1110,1800=0=0==0\n" +
                "閻象=15569=3=3030,940=0=0==0\n" +
                "徐廖=15841=3=610,300=0=0==0";
        gameClient.doStepsDoDoScriptAction(steps);
    }
}
