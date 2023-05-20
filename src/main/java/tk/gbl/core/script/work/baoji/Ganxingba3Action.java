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
public class Ganxingba3Action extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps3 = "5折兵-曹休=15456=0=1300,700=1=3==0\n" +
                "5折兵-嘘嘘寨交谈=15457=0=1050,2900=1=5==0\n" +
                "5折兵-泞沼=15463=0=170,170=1=4==0\n" +
                "5折兵-交谈2=15463=0=550,200=1=2==0\n" +
                "5折兵-孙权加入=15463=0=2200,1150=1=5==0";
        for (String step : steps3.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }

        //负伤的孙权出战
        gameClient.chuzhan(13129);

        String steps4 = "孙权折兵濡须口-张辽交战=15463=0=2150,850=1=8==0=False=0=0=0\n" +
                "孙权折兵濡须口-徐晃交战=15463=0=1600,700=1=7==0=False=0=0=0\n" +
                "孙权折兵濡须口-许诸交战=15463=0=1200,650=1=6==0=False=0=0=0\n" +
                "孙权折兵濡须口-触发交谈4（沼泽）=15463=0=500,250=1=2==0=False=0=0=0\n" +
                "孙权折兵濡须口-触发交谈4（沼泽）=15463=0=500,250=1=2==0=False=0=0=0\n" +
                "孙权折兵濡须口-张辽交战2=15463=0=1800,1500=1=3==0=False=0=0=0\n" +
                "孙权折兵濡须口-曹操交战=15463=0=500,250=1=2==0=False=0=0=0";
        for (String step : steps4.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
    }
}
