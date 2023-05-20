package tk.gbl.core.script.work.shiyixin;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:曹操兴兵取汉中
 * 地区:兖州、许昌城
 * 任务条件:LV25
 * 流程:
 * 1.至许昌皇宫与曹操交谈后，答应协助。
 * 2.分别前往陈留城门与夏侯淳、汜水关曹仁交谈后，返回许昌。
 * 3.接近曹操家前时触发交谈后，会与曹仁进行战斗(需一回合内完成)。
 * 4.胜利后前往皇宫13308与曹操交谈，即可完成任务。
 * <p/>
 * Date: 2017/5/25
 * Time: 19:13
 *
 * @author Tian.Dong
 */
public class X1_caocaoxingbingquhanzhong extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "1曹操兴兵取汉中-13308=13308=2=830,200=0=0=0=0\n" +
                "2曹操兴兵取汉中-13031=13031=7=750,580=0=0=0=0\n" +
                "3曹操兴兵取汉中-13421=13421=5=370,600=0=0=0=0\n" +
                "4曹操兴兵取汉中-13013=13013=0=662,555=1=8=0=0\n" +
                "5曹操兴兵取汉中-13308=13308=2=830,200=0=0=0=0";

        for(String step:steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成曹操兴兵取汉中");
    }
}
