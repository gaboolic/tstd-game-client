package tk.gbl.core.script.work.baoji;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 甘興霸-荊楚酒=19241=1=350,400=5=0=1=0=False=6-20=0=-1
 * 甘興霸-風乾羊肉=12242=1=490,400=5=0=1=0=False=3-20=0=-1
 * <p/>
 * * 答應收集「江鱷刀(LV15)」與「百鍛戟(LV15)」各一
 * 答应收集「荊楚酒(江陵兵)」、「风乾羊肉(民兵)」各20份
 * Date: 2017/6/6
 * Time: 14:02
 *
 * @author Tian.Dong
 */
public class Ganxingba0Action extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始获取任务材料");

        while (!checkItem(gameClient)) {
            System.out.println("身上材料不够");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("获取任务材料完成");
    }

    private boolean checkItem(GameClient gameClient) {
        if (gameClient.getItemCount(26233) < 20) {
            return false;
        }
        if (gameClient.getItemCount(26050) < 20) {
            return false;
        }
        if (gameClient.getItemCount(15015) < 1) {
            return false;
        }
        if (gameClient.getItemCount(10016) < 1) {
            return false;
        }
        return true;
    }
}
