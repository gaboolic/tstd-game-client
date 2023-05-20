package tk.gbl.core.script.work.suipian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称-初治益州
 * 触发条件-需完成‘三分天下有其一’的任务
 * 触发地点-益州、成都城
 * 流程-
 * 1.进入官府后与刘巴交谈后(取得【蜀科】)，再与诸葛亮交谈，答应收集‘烙铜屑(21,象山矿坑)’、‘扬铜屑(20,桂山矿坑)’各五个。
 * 2.收集齐全后与诸葛亮交谈，答应收集‘锄头’、‘栖凤木(瞿塘峡谷-西蜀水师)’各一个。
 * 3.收集齐全后再与诸葛亮交谈后，即可完成任务。
 * ->获得物品:经验值若干(1.5%)、[此项随机]天珠碎片
 * <p/>
 * Date: 2017/6/14
 * Time: 16:17
 *
 * @author Tian.Dong
 */
public class S13_chuzhiyizhou extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "【初治益州】1=25301=0==1=2==0=True=1=0=0\n" +
                "【初治益州】2=25301=8=1050,500=0=0==0=True=6=0=0\n" +
                "【初治益州】2-检查点==0==11=0==29240=False=112=0=0\n" +
                "【初治益州】3=25301=2=290,320=0=0==0=True=9=0=0\n" +
                "【初治益州】4=25301=2=290,320=0=0==0=True=5=0=0\n" +
                "【初治益州】4-检查点==0=290,320=12=0==37229,37230=False=131=0=0\n" +
                "【初治益州】5=25301=2=290,320=0=0==0=True=9=0=0\n" +
                "【初治益州】5-检查点==0==11=0==46050,30083,30084,30085=False=134=0=0";
    }
}
