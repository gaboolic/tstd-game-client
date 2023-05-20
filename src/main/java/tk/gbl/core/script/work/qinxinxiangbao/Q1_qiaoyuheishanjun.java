package tk.gbl.core.script.work.qinxinxiangbao;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:巧遇黑山軍
 * 地区:青州、東郡
 * 任务条件:無
 * 流程:
 * 1.進入東郡樹林前，會與于氐根發生戰鬥。
 * 2.打贏後，在進入東郡之前，會再與黃龍發生戰鬥。
 * 3.打贏後即可玩進入。
 * 任务奖品:無
 * 备注:無
 * Date: 2017/6/5
 * Time: 10:21
 *
 * @author Tian.Dong
 */
public class Q1_qiaoyuheishanjun extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "巧遇黑山軍1(於氐根) - 11801=11801=1=290,2410=1=6==0\n" +
                "巧遇黑山軍2(黃龍) - 11801=11801=2=290,2410=1=5==0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成巧遇黑山軍");
    }
}
