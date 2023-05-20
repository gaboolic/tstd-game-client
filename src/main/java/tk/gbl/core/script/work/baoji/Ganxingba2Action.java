package tk.gbl.core.script.work.baoji;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 答應收集「江鱷刀(LV15)」與「百鍛戟(LV15)」各一
 * 答应收集「荊楚酒(江陵兵)」、「风乾羊肉(民兵)」各20份
 * Date: 2017/6/6
 * Time: 14:02
 *
 * @author Tian.Dong
 */
public class Ganxingba2Action extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        while (gameClient.getItemCount(29269) < 5) {
            String steps2 = "孤有甘兴霸-拾料1=15457=0=1182,2155=2=0==29269=False=0=0=0\n" +
                    "孤有甘兴霸-拾料2=15460=0=440,395=2=0==29269=False=0=0=0\n" +
                    "孤有甘兴霸-拾料3=15452=0=442,915=2=0==29269=False=0=0=0\n" +
                    "孤有甘兴霸-拾料4=15462=0=182,635=2=0==29269=False=0=0=0";
            for (String step : steps2.split("\n")) {
                gameClient.doDoDoScriptAction(step);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (gameClient.getItemCount(29269) >= 5) {
                    break;
                }
            }
        }

        String steps =
                "4孤有甘兴霸-孙权=15457=5=1010,3000=0=0==0\n" +
                        "4孤有甘兴霸-孙权（再）=15457=5=1010,3000=0=0==0\n" +
                        "4孤有甘兴霸-孙权（再再）=15457=5=1010,3000=0=0==0\n" +
                        "4孤有甘兴霸-张辽=15581=0=2230,2420=1=3==0\n" +
                        "4孤有甘兴霸-孙权2=15457=5=1010,3000=0=0==0\n" +
                        "4孤有甘兴霸-甘宁=15459=1=510,400=0=0==0\n" +
                        "4孤有甘兴霸-甘宁(再)=15459=1=510,400=0=0==0\n" +
                        "4孤有甘兴霸-甘宁(再再)=15459=1=510,400=0=0==0\n" +
                        "4孤有甘兴霸-甘宁触发=15462=0=410,820=1=2==0\n" +
                        "4孤有甘兴霸-战1=15462=0=630,500=1=3==0\n" +
                        "4孤有甘兴霸-战2=15462=0=1100,350=1=4==0\n" +
                        "4孤有甘兴霸-战3=15462=0=1100,350=1=5==0\n" +
                        "4孤有甘兴霸-战4=15462=0=1200,700=1=6==0\n" +
                        "4孤有甘兴霸-战5=15462=0=900,850=1=7==0\n" +
                        "4孤有甘兴霸-战6=15462=0=800,550=1=8==0\n" +
                        "4孤有甘兴霸-战7=15462=0=400,400=1=9==0\n" +
                        "4孤有甘兴霸-战8=15462=0=50,500=1=10==0\n" +
                        "4孤有甘兴霸-战9=15462=0=350,850=1=2==0\n" +
                        "4孤有甘兴霸-战10=15462=0=250,200=1=11==0\n" +
                        "4孤有甘兴霸-交任务=15462=0=410,820=1=2==0\n" +
                        "4孤有甘兴霸-孙权领刀=15457=5=1010,3000=0=0==0";
        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
    }
}
