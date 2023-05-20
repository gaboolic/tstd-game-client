package tk.gbl.core.script.work.qinxinxiangbao;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:清水慘敗
 * 地区:兗州、宛城
 * 任务条件:需完成奉迎天子任務
 * 流程:
 * 1.到宛城小屋內曹安民交談，在去找張繡交談。
 * 2.再回去找曹安民交談，會與六個威武軍進入戰鬥。
 * 3.勝利後前往舞陰樹林與曹操交談，會與張先、雷敘、張碩、鄧濟進入戰鬥。
 * 4.勝利後，再前往千毒洞，再洞內遇見典韋，與他交談，會與韓晞、張繡、胡車兒、文聘、賈詡、陳生、黃射、張虎、劉虎、蔡瑁、劉表進入戰鬥。
 * 5.勝利後再回許昌城閱兵台與曹操交談即可完成任務。
 * 任务奖品:心係兌換卷、惡魔兌換卷、背包兌換卷
 * 备注:無
 * <p/>
 * Date: 2017/6/5
 * Time: 10:23
 *
 * @author Tian.Dong
 */
public class Q8_qingshuicanbai extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "清水慘敗1(曹安民) - 13133=13133=2=350,340=0=0==0\n" +
                "清水慘敗2(張繡) - 13105=13105=2=510,300=0=0==0\n" +
                "清水慘敗3(曹安民) - 13133=13133=2=350,340=0=0==0\n" +
                "清水慘敗4(曹操) - 13861=13861=7=1090,980=0=0==0\n" +
                "清水慘敗5(典韋) - 13524=13524=2=450,280=0=0==0\n" +
                "清水慘敗6(曹操) - 13014=13014=1=830,380=0=0==0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }

        System.out.println("开始清水慘敗7东郡官府(曹操)");
        gameClient.moveFar(11002);
        gameClient.simpleMove(3);
        gameClient.segmentMoveTo(480, 360);
        gameClient.clickNPC(1);
        gameClient.simpleSleep();

        gameClient.doDoDoScriptAction("清水惨败8進官府=11002=0=170,570=1=3==0");

        System.out.println("完成清水慘敗");
    }
}
