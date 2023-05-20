package tk.gbl.core.script.work.qinxinxiangbao;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:奉迎天子
 * 地区:兗州、穎川村
 * 任务条件:需完成兗州爭奪戰
 * 流程:
 * 1.進入穎川村茶店旁的小屋內與董昭交談。
 * 2.再到洛陽皇陵，進入皇陵前，會遭到韓暹阻擋，與韓暹交談，會與李樂、韓暹、胡才進入戰鬥，勝利後即可進入。
 * 3.再洞內第一個十字路口往上直走，與楊奉交談，會與史喚、徐晃、楊奉、宋果、韓浩進入戰鬥。
 * 4.勝利後，再往洞內走道最底，與飛熊將軍交談，李傕會出現，與李傕交談，選擇上前助陣，會與崔勇、李別、李傕、郭汜、李暹、夏育進入戰鬥。
 * 5.勝利後，回到許昌城點兵台與曹操交談即可完成任務。
 * 任务奖品:遁甲天書(知+14)
 * 备注:無
 * <p/>
 * Date: 2017/6/5
 * Time: 10:23
 *
 * @author Tian.Dong
 */
public class Q7_fengyingtianzi extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "奉迎天子1(董昭) - 13178=13178=3=370,320=0=0==0=True=3=0=0\n" +
                "奉迎天子2(韩暹) - 13435=13435=1=430,220=0=1==0=True=9=9=0\n" +
                "奉迎天子3(韩暹) - 13435=13435=1=430,220=1=1==0=True=1=0=0\n" +
                "奉迎天子4(杨奉) - 13561=13561=1=1130,280=0=0==0=True=6=7=0\n" +
                "奉迎天子5(飞熊将军) - 13566=13566=1=270,960=0=0==0=True=1=0=0\n" +
                "奉迎天子6(李傕) - 13566=13566=2=270,960=0=0=1=0=True=2=12=0\n" +
                "奉迎天子7(曹操) - 13014=13014=1=830,380=0=0==0=True=16=0=0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成奉迎天子");
    }
}
