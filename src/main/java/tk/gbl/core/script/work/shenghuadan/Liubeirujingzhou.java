package tk.gbl.core.script.work.shenghuadan;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:刘备入荆州
 * 地区:荆北、鹊尾山洞
 * 任务条件:Lv.25
 * 流程:
 * 1.到荆北鹊尾山洞内与刘备交谈。
 * 2.在与陈矫交谈，会与进入陈矫、夏侯尚、吕度、夏侯兰、路招战斗。
 * 3.胜利后，在洞内往回走过画面遇叉路往下走，与夏侯尚交谈，会与满宠、许攸、李通、张郃、高览进入战斗。
 * 4.胜利后，在洞内往回走过画面遇叉路往上走，与夏侯尚交谈，会与贾诩、曹纯、乐进、夏侯惇、夏侯渊进入战斗。
 * 5.胜利后，到襄阳城皇宫与刘表交谈，答应协助。
 * 6.在回鹊尾山洞与刘备交谈即可完成任务。
 * 任务奖品:升华胆
 * 备注:
 * <p/>
 * <p/>
 * Date: 2017/5/22
 * Time: 14:18
 *
 * @author Tian.Dong
 */
public class Liubeirujingzhou extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String step1 = "荆北鹊尾山洞内与刘备=21508=1=1430,800=0=0==0";
        String step2 = "荆北鹊尾山洞内与陈矫=21508=7=1200,760=0=0==0";
        String step3 = "与夏侯尚交谈1=21509=1=590,1040=0=0==0";
        String step4 = "与夏侯尚交谈2=21506=1=1910,180=0=0==0";
        //刘表=21301=1=390,280=0=0==0=False=0=0=0
        String step5 = "襄阳城皇宫与刘表=21301=1=390,280=0=0==0";
        String step6 = "鹊尾山洞与刘备=21508=1=1430,800=0=0==0";
        gameClient.doDoDoScriptAction(step1);
        gameClient.doDoDoScriptAction(step2);
        gameClient.doDoDoScriptAction(step3);
        gameClient.doDoDoScriptAction(step4);
        gameClient.doDoDoScriptAction(step5);
        gameClient.doDoDoScriptAction(step6);
        System.out.println("完成");
    }
}
