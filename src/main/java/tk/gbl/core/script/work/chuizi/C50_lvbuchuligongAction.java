package tk.gbl.core.script.work.chuizi;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:呂布初立功     查看前提
 * 地区:并州、上黨城
 * 任务条件:無
 * 流程:
 * 1.到并州上黨城城門口與并州士交談。
 * 2.再到上黨樹林與馬伕交談，在與呂布交談。
 * 3.前往西河山洞，在洞內與匈奴刀兵交談，會與呼廚泉、去卑、匈奴刀兵x4、匈奴槍兵x4進入戰鬥。
 * 4.勝利後即可完成任務。
 * 任务奖品:駿馬x1、呂布娃娃x10、五十斤鉛錘、游雲戟(Lv.50)
 * 备注:無
 * <p/>
 * Date: 2017/4/26
 * Time: 16:11
 *
 * @author Tian.Dong
 */
public class C50_lvbuchuligongAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "呂布初立功 - 1=20001=5=330,560=0=0==0\n" +
                "呂布初立功 - 2=20871=1=290,1000=0=0==0\n" +
                "呂布初立功 - 3=20871=5=250,1040=0=0==0\n" +
                "呂布初立功 - 4=20528=2=370,280=0=0==0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成吕布初立功");
    }
}
