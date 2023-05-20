package tk.gbl.core.script.work.shiyixin;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:初战汉中
 * 地区:兖州、许昌城
 * 任务条件:须完成「曹操兴兵取汉中」的任务
 * 流程:
 * 1.完成曹操兴兵取汉中任务后会立即触发，答应协助。
 * 2.前往斜谷树林4(左偏将场景，4720,350)时触发交谈后，会与杨任、杨昂、益州兵x2、南郑兵x2进行战斗。
 * 3.胜利后前往益州北山寨触发交谈，答应协助。
 * 4.前往北山脚(箭楼下方)触发交谈后，再移动至右上方触发交谈，会与杨任、杨昂、蜀兵x2、汉中兵x2进行战斗(须五回合内完成)。
 * 5.胜利后前往北山寨触发交谈后，即可完成任务。
 * <p/>
 * 任务奖品:经验值若干(0.3%)、[随机取得]五爪龙布x10、天蚕精纱x10
 * <p/>
 * Date: 2017/5/25
 * Time: 19:23
 *
 * @author Tian.Dong
 */
public class X2_chuzhanhanzhong extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "初戰漢中-曹操=13308=2=830,200=0=0==0\n" +
                "1出战汉中-14832=14832=0=4700,290=1=5=0=0\n" +
                "2出战汉中-25822=25822=0=162,535=1=6=0=0\n" +
                "3出战汉中-25821=25821=0=2262,375=1=3=0=0\n" +
                "4出战汉中-25821=25821=0=2262,375=1=4=0=0\n" +
                "5出战汉中-25822=25822=0=162,535=1=6=0=0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成初战汉中");
    }
}
