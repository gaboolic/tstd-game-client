package tk.gbl.core.script.work.shiyixin;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:张飞斗张合
 * 地区:益州、瓦口树林
 * 任务条件:需完成「瓦口大战」的任务
 * 流程:
 * 1.前往瓦口树林(炎汉战士场景)左上方与张飞交谈后，答应协助。
 * 2.沿着瓦口栈道、瓦口栈道2路上皆会在该场景与瓦口伏兵x?(有增援,乱敏)进行战斗。
 * 3.战斗结束后前往瓦口关阶梯中间触发交谈，会与张郃、瓦口伏兵x5进行战斗。
 * 4.战斗结束后返回瓦口树林与张飞交谈，再前往瓦口关。
 * 5.移动到瓦口关阶梯中间触发剧情后，会与瓦口守兵、瓦口伏兵x5(增援：瓦口伏兵x4)进行战斗。
 * 6.战斗结束后，即可完成任务
 * 任务奖品:经验值若干(2%)
 * 备注:
 * <p/>
 * Date: 2017/5/25
 * Time: 19:26
 *
 * @author Tian.Dong
 */
public class X7_zhangfeidouzhanghe extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps222 = "张飞斗张合-瓦口栈道=25446=0=840,400=1=3==0\n" +
                "张飞斗张合-瓦口栈道2=25445=0=1220,480=1=3==0\n" +
                "张飞斗张合-瓦口关=25443=0=600,840=1=3==0\n" +
                "张飞斗张合-瓦口张飞=25447=7=360,280=0=0==0";

        String steps = "1张飞斗张郃-25447=25447=7=270,260=0=0=0=0\n" +
                "2张飞斗张郃-25446=25446=0=1482,175=1=3=0=0\n" +
                "3张飞斗张郃-25445=25445=0=1362,555=1=3=0=0\n" +
                "4张飞斗张郃-25443=25443=0=642,915=1=3=0=0\n" +
                "5张飞斗张郃-25447=25447=7=270,260=0=0=0=0\n" +
                "6张飞斗张郃-25443=25443=0=642,915=1=3=0=0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成张飞斗张合");
    }
}