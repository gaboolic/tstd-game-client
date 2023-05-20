package tk.gbl.core.script.work.qinxinxiangbao;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:匡亭追擊戰
 * 地区:徐州、相縣樹林
 * 任务条件:需完成黃巾討伐戰任務
 * 流程:
 * 1.前往徐州的相縣樹林，進入樹林往左下方走，進入第二層，與劉詳交談，會與劉詳、俞涉、荀正、袁嗣進入戰鬥。
 * 2.勝利後在前往盱眙水洞迷宮，在洞內會遇見惠衢，與他交談，會與惠衢、萇奴、韓胤、荀正、鄭泰進入戰鬥。
 * 3.勝利後，走出迷宮，在盱眙水寨的軍帳內與袁術交談，會與袁術、雷薄、紀靈、張勳、閻象、陳蘭、楊弘進入戰鬥。
 * 4.勝利後，滿寵會出現請你東郡找曹操即可完成任務。
 * 任务奖品:大福神紋章、大福神、10隻福神
 * 备注:無
 * <p/>
 * Date: 2017/6/5
 * Time: 10:22
 *
 * @author Tian.Dong
 */
public class Q4_kuangtingzhuijizhan extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "匡亭追擊戰1(劉詳) - 15823=15823=8=2890,920=0=0==0\n" +
                "匡亭追擊戰2(惠衢) - 15541=15541=5=2110,340=0=0==0\n" +
                "匡亭追擊戰3(袁術) - 15443=15443=1=370,420=0=0==0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成匡亭追击战");
    }
}
