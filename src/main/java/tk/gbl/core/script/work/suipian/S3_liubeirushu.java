package tk.gbl.core.script.work.suipian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:刘备入蜀
 * 地区:益州、成都城
 * 任务条件:需完成「摆驾迎张松」的任务
 * 流程:
 * 1.进入成都普户与张松交谈后，答应协助。
 * 2.前往官府2与刘璋交谈后，依序回答刘表、赤壁之战、「吕布、袁绍...马超、韩遂」、徐州、衣带诏。
 * 3.回答完成后，答应前往江陵城。
 * 4.进入江陵富户与刘备进行交谈后，再交谈一次后答应前往。
 * 5.前往江陵官府与庞统交谈后，答应黄忠的请求(取得【黄忠的亲笔信】)。
 * 6.前往长沙普户与黄叙交谈后，答应协助。
 * 7.依序与阿国与张大哥交谈后，返回与黄叙交谈，答应前往江陵城。
 * 8.进入贫户与霍峻交谈后，答应收集「山越樟木」(江陵树林-江陵兵)及「千年栋梁」(麦城树林-先锋队)各一。
 * 9.收集齐全后返回贫户与霍峻交谈，再交谈一次即可完成任务。
 * 任务奖品:经验值若干(2%)、[依属性取得]弓(攻+15、敏+4、属性+2)
 * 备注:
 * <p/>
 * Date: 2017/6/14
 * Time: 16:12
 *
 * @author Tian.Dong
 */
public class S3_liubeirushu extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "【刘备入蜀】1=25133=1=350,300=0=0==0=True=24=0=0\n" +
                "【刘备入蜀】2=25302=1=290,340=0=0=1-1,2-3,1-3,3-0,3-7=0=False=17=0=0\n" +
                "【刘备入蜀】3=21107=1=330,360=0=0==0=True=23=0=0\n" +
                "【刘备入蜀】4=21107=1=330,360=0=0==0=True=18=0=0\n" +
                "【刘备入蜀】5=21302=0==1=2==0=True=2=0=0\n" +
                "【刘备入蜀】6=21302=10=530,380=0=0==0=True=23=0=0\n" +
                "【刘备入蜀】6-检查点==0==11=0==29210=False=36=0=0\n" +
                "【刘备入蜀】7=23132=1=310,340=0=0==0=True=7=0=0\n" +
                "【刘备入蜀】8=23002=4=290,640=0=0==0=True=5=0=0\n" +
                "【刘备入蜀】9=23003=1=990,540=0=0==0=True=5=0=0\n" +
                "【刘备入蜀】10=23132=1=310,340=0=0==0=True=6=0=0\n" +
                "【刘备入蜀】11=21173=1=530,360=0=0==0=True=8=0=0\n" +
                "【刘备入蜀】12=21173=1=530,360=0=0==0=True=4=0=0\n" +
                "【刘备入蜀】12-检查点==0==12=0==34008,34010=False=43=0=0\n" +
                "【刘备入蜀】13=21173=1=530,360=0=0==0=True=4=0=0\n" +
                "【刘备入蜀】13-检查点==0==11=0==16709,16710,16711,16712=False=50=0=0";
        gameClient.doStepsDoDoScriptAction(steps);
        System.out.println("完成刘备入蜀");
    }
}
