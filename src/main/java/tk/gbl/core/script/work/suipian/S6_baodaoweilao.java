package tk.gbl.core.script.work.suipian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称-宝刀未老
 * 触发条件-需完成「庞统三计」的任务
 * 触发地点-益州、白水关
 * 流程-
 * 1.与刘备交谈后，答应协助。
 * 2.进入阴平山洞(3500,1550)时会与袭肃、赤甲士x2、黑甲士x2进行战斗。
 * 3.战斗胜利后，前往白水树林(督军长场景930,920)时进行动画后，会与邓贤、冷苞、武射军x3、先登团x3进行战斗。
 * 4.战斗胜利后与黄忠交谈，答应收集一个「大水梨」(关城山洞-武都营)。
 * 5.收集齐全与彭羕交谈后，至白水关与刘备交谈后，答应协助。
 * 6.返回白水树林与黄忠交谈后，答应协助。
 * 7.前往白水树林(雒城军场景1200,1000)触发交谈后，会与冷苞、关中长x4、勇斗兵x3进行战斗。
 * 8.战斗胜利后即可完成任务。
 * ->获得物品:经验值若干(2%)
 * <p/>
 * <p/>
 * Date: 2017/6/14
 * Time: 16:13
 *
 * @author Tian.Dong
 */
public class S6_baodaoweilao extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "【宝刀未老】1=25433=1=390,460=0=0==0=True=2=0=0\n" +
                "【宝刀未老】2=25504=0==1=3==0=True=3=6=0\n" +
                "【宝刀未老】3=25435=0==1=4==0=True=7=20=0\n" +
                "【宝刀未老】4=25435=9=1030,1000=0=0==0=True=4=0=0\n" +
                "【宝刀未老】4-检查点==0==12=0==26309=False=69=0=0\n" +
                "【宝刀未老】5=25433=1=390,460=0=0==0=True=12=0=0\n" +
                "【宝刀未老】6=25435=0==1=4==0=False=1=0=0\n" +
                "【宝刀未老】7=25435=7=1010,900=0=0==0=False=4=0=0\n" +
                "【宝刀未老】8=25440=0==1=5==0=True=5=9=0";

    }
}
