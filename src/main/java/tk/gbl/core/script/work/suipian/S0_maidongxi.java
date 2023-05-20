package tk.gbl.core.script.work.suipian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 【锄头】=12204=1=590,400=5=0=1=0=False=1-1=0=0 涿郡
 * 【左传】=20021=1=210,360=5=0=1=0=False=3-1=0=0 并州解良村
 * 【太初历】=24012=1=910,420=5=0=1=0=False=3-1=0=0 凉州敦煌
 * 【疗伤膏】=20021=1=210,360=5=0=1=0=False=1-1=0=0 并州解良村
 * 【晶透玉杯】=21012=2=850,660=5=0=1=0=False=9-1=0=0 荆北江陵大街
 * 【夜光玉杯】=25012=1=150,380=5=0=1=0=False=8-1=0=0 益州南郑大街
 * <p/>
 * 【山越樟木】东吴广纳贤良1=18011=6=690,460=0=0==0=True=5=0=0
 * 【山越樟木】东吴广纳贤良2=18174=1=350,280=0=0=2-3,1-5,3-1,3-1,1-1,1-3=0=True=8=0=0
 * 【山越樟木】东吴广纳贤良3=18434=5=910,780=0=0=1-2,1-1,3-1,3-1,1-3=0=True=0=0=0
 * 【山越樟木】东吴广纳贤良4=18434=6=970,740=0=0=3-1,2-1,3-5=0=True=0=0=0
 * <p/>
 * Date: 2017/6/14
 * Time: 16:45
 *
 * @author Tian.Dong
 */
public class S0_maidongxi extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "【锄头】=12204=1=590,400=5=0=1=0=False=1-1=0=0\n" +
                "【左传】=20021=1=210,360=5=0=1=0=False=3-1=0=0\n" +
                "【疗伤膏】=20021=1=210,360=5=0=1=0=False=1-1=0=0\n" +
                "【太初历】=24012=1=910,420=5=0=1=0=False=3-1=0=0\n" +
                "【晶透玉杯】=21012=2=850,660=5=0=1=0=False=9-1=0=0\n" +
                "【夜光玉杯】=25012=1=150,380=5=0=1=0=False=8-1=0=0\n" +
                "【山越樟木】东吴广纳贤良1=18011=6=690,460=0=0==0=True=5=0=0\n" +
                "【山越樟木】东吴广纳贤良2=18174=1=350,280=0=0=2-3,1-5,3-1,3-1,1-1,1-3=0=True=8=0=0\n" +
                "【山越樟木】东吴广纳贤良3=18434=5=910,780=0=0=1-2,1-1,3-1,3-1,1-3=0=True=0=0=0\n" +
                "【山越樟木】东吴广纳贤良4=18434=6=970,740=0=0=3-1,2-1,3-5=0=True=0=0=0";
        gameClient.doStepsDoDoScriptAction(steps);
        System.out.println("完成买东西");
    }
}
