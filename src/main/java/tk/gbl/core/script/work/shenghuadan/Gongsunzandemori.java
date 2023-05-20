package tk.gbl.core.script.work.shenghuadan;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 公孙瓒的末日(Lv.20,升华丹)
 * 1.到幽州的易县树林，进入后走到最上方，与赵云交谈，答应协助。
 * 2.再到大兴山洞，在洞内与淳于琼交谈，会与曲义、淳于琼、冯礼、焦触、张南进入战斗。
 * 3.胜利后，再到卢龙口雪地第三层与蒋义渠交谈，会与辛评、蒋义渠、韩范、梁岐、王门进入战斗。
 * 4.胜利后，再回到易县树林与赵云交谈即可完成任务。
 * 获得物品:升华丹
 * <p/>
 * Date: 2017/5/22
 * Time: 14:18
 *
 * @author Tian.Dong
 */
public class Gongsunzandemori extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String step1 = "易县树林-赵云=12806=12=1490,520=0=0==0";
        String step2 = "大兴山洞-淳于琼=12504=2=3030,300=0=0==0";
        String step3 = "卢龙口雪地第三层-蒋义渠=12817=9=2430,320=0=0==0";
        String step4 = "易县树林-赵云=12806=12=1490,520=0=0==0";
        gameClient.doDoDoScriptAction(step1);
        gameClient.doDoDoScriptAction(step2);
        gameClient.doDoDoScriptAction(step3);
        gameClient.doDoDoScriptAction(step4);
        System.out.println("完成");
    }
}
