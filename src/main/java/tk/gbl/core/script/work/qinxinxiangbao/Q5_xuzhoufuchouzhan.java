package tk.gbl.core.script.work.qinxinxiangbao;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:徐州復仇戰
 * 地区:青州、東郡
 * 任务条件:需完成匡亭追擊戰任務
 * 流程:
 * 1.進入東郡官府與曹操交談，答應協助他。
 * 2.前往徐州的下蔡樹林，進入後往左上方走，與曹豹交談，會與曹豹、呂由、陳登、陳珪、應邵進入戰鬥。
 * 3.勝利後，前往跨州山洞，在洞內會遇見受傷的士兵，與他交談，會與劉備、關羽、張飛、趙雲進入戰鬥。
 * 4.勝利後，在回去東郡找曹操即可完成任務。
 * 任务奖品:亂擊紋章
 * 备注:無
 * <p/>
 * Date: 2017/6/5
 * Time: 10:22
 *
 * @author Tian.Dong
 */
public class Q5_xuzhoufuchouzhan extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始徐州復仇戰1(曹操)");
        gameClient.moveFar(11002);
        gameClient.simpleMove(3);
        gameClient.segmentMoveTo(480, 360);
        gameClient.clickNPC(1);
        gameClient.simpleSleep();

        String steps = "徐州復仇戰2(曹豹) - 15861=15861=13=250,220=0=0==0\n" +
                "徐州復仇戰3(受傷的士兵) - 15506=15506=3=2290,2180=0=0==0";
        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }

        System.out.println("开始徐州復仇戰4(曹操)");
        gameClient.moveFar(11002);
        gameClient.simpleMove(3);
        gameClient.segmentMoveTo(480, 360);
        gameClient.clickNPC(1);
        gameClient.simpleSleep();

        System.out.println("完成徐州复仇战");
    }
}
