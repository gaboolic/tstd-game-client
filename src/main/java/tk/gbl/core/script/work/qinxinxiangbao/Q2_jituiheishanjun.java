package tk.gbl.core.script.work.qinxinxiangbao;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:擊退黑山軍
 * 地区:青州、東郡
 * 任务条件:需完成巧遇黑山軍的任務
 * 流程:
 * 1.進入官府與王肱交談，答應幫他尋找救兵。
 * 2.到野外孔廟後方的與曹操交談，曹操加入隊伍。
 * 3.進入東郡城門前，會雷公阻檔，發生第一場戰鬥，勝利後得【泊江金沙】。
 * 4.第一場勝利後，隨即再度與李大目發生戰鬥，勝利後即可進入，得【薏仁漿】。
 * 5.進入官宅與王肱交談。
 * 6.到東郡野外上方的頓丘樹林，一進入便會與五鹿發生戰鬥，勝利後即可進入。
 * 7.往右下方走，會與睦固發生戰鬥。
 * 9.勝利後，會與于毒、牛角、白雀發生戰鬥，勝利後得【細流鞋】。
 * 10.再回去找王肱即可完成任務。
 * 任务奖品:金創藥、泊江金沙、薏仁漿、細流鞋(敏+5、Lv.10)
 * 备注:無
 * <p/>
 * Date: 2017/6/5
 * Time: 10:22
 *
 * @author Tian.Dong
 */
public class Q2_jituiheishanjun extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始擊退黑山軍1(王肱)");
        gameClient.moveFar(11002);
        gameClient.simpleMove(3);
        gameClient.segmentMoveTo(470, 380);
        gameClient.clickNPC(8);
        gameClient.simpleSleep();

        String steps = "擊退黑山軍2(曹操) - 11841=11841=1=2390,740=0=0==0\n" +
                "擊退黑山軍3(雷公) - 11001=11001=6=290,580=1=3==0\n" +
                "擊退黑山軍4(李大目) - 11001=11001=7=510,460=1=4==0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }

        System.out.println("开始擊退黑山軍5(王肱)");
        gameClient.moveFar(11002);
        gameClient.simpleMove(3);
        gameClient.segmentMoveTo(470, 380);
        gameClient.clickNPC(8);
        gameClient.simpleSleep();

        String steps2 = "擊退黑山軍6(五鹿) - 11811=11811=15=2350,290=1=3==0\n" +
                "擊退黑山軍7(眭固) - 11811=11811=13=2350,290=1=4==0\n" +
                "擊退黑山軍8(於毒) - 11811=11811=14=2350,290=1=5==0";

        for (String step : steps2.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }

        System.out.println("开始擊退黑山軍9(王肱)");
        gameClient.moveFar(11002);
        gameClient.simpleMove(3);
        gameClient.segmentMoveTo(470, 380);
        gameClient.clickNPC(8);
        gameClient.simpleSleep();

        System.out.println("完成擊退黑山軍");
    }
}
