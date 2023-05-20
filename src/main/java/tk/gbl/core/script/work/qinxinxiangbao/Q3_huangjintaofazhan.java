package tk.gbl.core.script.work.qinxinxiangbao;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:黃巾討伐戰
 * 地区:青州、東郡
 * 任务条件:需完成擊退黑山軍任務
 * 流程:
 * 1.進入東郡官府與王肱交談，答應前去微山樹林找曹操。
 * 2.前往微山樹林與曹操交談，答應前往朝歌山洞。
 * 3.進入朝歌山洞內，在洞內會遇見卞喜，與他交談，會與卞喜、王忠、于毒、白繞、於夫羅進入戰鬥。
 * 4.勝利後在繼續往洞內走，會遇見蔡陽，與他交談，會與蔡陽、車冑、路招、秦琪、馮楷進入戰鬥。
 * 5.勝利後，曹操與劉曄會出現，在答應前往相縣樹林，即可完成任務。
 * 任务奖品:行軍帳x5
 * 备注:無
 * <p/>
 * Date: 2017/6/5
 * Time: 10:22
 *
 * @author Tian.Dong
 */
public class Q3_huangjintaofazhan extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始黃巾討伐戰1(王肱)");
        gameClient.moveFar(11002);
        gameClient.simpleMove(3);
        gameClient.segmentMoveTo(470, 380);
        gameClient.clickNPC(8);
        gameClient.simpleSleep();

        String steps = "黃巾討伐戰2(曹操) - 11841=11841=1=2390,740=0=0=1=0\n" +
                "黃巾討伐戰3(卞喜) - 12587=12587=2=430,280=0=0==0\n" +
                "黃巾討伐戰4(蔡陽) - 12589=12589=10=1230,1640=0=0==0\n" +
                "黃巾討伐戰5(曹操) - 12589=12589=11=990,1640=0=0==0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成黄巾讨伐战");
    }
}
