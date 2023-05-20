package tk.gbl.core.script.work.suipian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称-三分天下有其一
 * 触发条件-需完成‘挑灯夜战’的任务
 * 触发地点-益州、雒城
 * 流程-
 * 1.进入富户与吴懿交谈后，前往成都城。
 * 2.移动至城门触发剧情后，返回雒城富户。
 * 3.与吴懿交谈后，答应收集‘太初历(LV40)’、‘左传(LV40)’各一，再与吴懿交谈后，答应协助。
 * 4.前往成都城门(750,630)触发剧情后，前往富户与黄权交谈后，再前往广场普户与刘巴交谈，即可完成任务。
 * ->获得物品:经验值若干(1.5%)、[此项随机]转生专用特技纹章
 * <p/>
 * Date: 2017/6/14
 * Time: 16:14
 *
 * @author Tian.Dong
 */
public class S12_sanfentianxiayouqiyi extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "【三分天下有其一】1=25107=1=370,340=0=0==0=True=10=0=0\n" +
                "【三分天下有其一】2=25001=0==4=3==0=True=23=0=0\n" +
                "【三分天下有其一】3=25107=1=370,340=0=0==0=True=11=0=0\n" +
                "【三分天下有其一】4=25107=1=370,340=0=0==0=True=7=0=0\n" +
                "【三分天下有其一】5=25001=0==4=3==0=True=13=0=0\n" +
                "【三分天下有其一】6=25101=1=270,340=0=0==0=True=8=0=0\n" +
                "【三分天下有其一】7=25172=1=430,340=0=0==0=True=10=0=0";
    }
}
