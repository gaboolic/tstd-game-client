package tk.gbl.core.script.work.suipian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称-落凤坡
 * 触发条件-需完成「宝刀未老」的任务
 * 触发地点-荆北、江陵城
 * 流程-
 * 1.进入江陵官府与诸葛亮交谈后，答应辅助。
 * 2.前往白水关(左进场景)与刘备交谈后，答应辅助。
 * 3.前往白水树林(督军长场景)与刘备交谈后，前往落凤坡。
 * 4.移动至(140,530)后会与张任、郑度、刘贵、东州兵x7进行战斗。
 * 5.战斗胜利后，返回白水树林与刘备交谈，答应辅助。
 * 6.前往江陵官府与诸葛亮交谈，即可完成任务。
 * ->获得物品:经验值若干(2%)
 * <p/>
 * Date: 2017/6/14
 * Time: 16:13
 *
 * @author Tian.Dong
 */
public class S7_luofengpo extends ScriptAction{
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "【落凤坡】1=21302=2=590,440=0=0==0=True=12=0=0\n" +
                "【落凤坡】2=25433=1=390,460=0=0==0=True=15=0=0\n" +
                "【落凤坡】3=25435=8=910,840=0=0==0=False=9=0=0\n" +
                "【落凤坡】4=25437=0==1=3==0=True=11=8=0\n" +
                "【落凤坡】5=25435=8=910,840=0=0==0=True=7=21=0\n" +
                "【落凤坡】6=21302=2=590,440=0=0==0=True=27=0=0";
    }
}
