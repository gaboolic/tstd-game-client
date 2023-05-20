package tk.gbl.core.script.work.suipian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:张松之密谋
 * 地区:益州、成都城
 * 任务条件:LV25
 * 流程:
 * 1.至成都广场的普户与张松交谈，答应协助。
 * 2.前往官府与刘璋交谈后，前往白帝城。
 * 3.与城门口的益州兵进行交谈后，前往剑阁关。
 * 4.走到张松出现处(约在阶梯的中间)进行交谈后，前往葭萌关。
 * 5.走到张松出现处(约在阶梯的中间)进行交谈后，前往阴平峡谷。
 * 6.到阴平峡谷与张松(1460,375)交谈后，再至雒城与雒城军交谈，即可完成任务。
 * 任务奖品:大福神x1[此项随机]、安宫牛黄丸x25、极冰续命水x25
 * <p/>
 * Date: 2017/6/14
 * Time: 16:21
 *
 * @author Tian.Dong
 */
public class S2_zhangsongzhimimou extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "【张松之密谋】1=25133=1=350,300=0=0==0=True=10=0=0\n" +
                "【张松之密谋】2=25302=1=290,340=0=0==0=True=6=0=0\n" +
                "【张松之密谋】3=25021=1=510,420=0=0==0=True=7=0=0\n" +
                "【张松之密谋】4=25421=1==1=3==0=True=7=0=0\n" +
                "【张松之密谋】5=25411=1==1=3==0=True=7=0=0\n" +
                "【张松之密谋】6-Pre=25882=0==2=0==0=False=0=0=0\n" +
                "【张松之密谋】6=25881=7=1470,380=0=0==0=True=6=0=0\n" +
                "【张松之密谋】7=25041=1=570,480=0=0==0=True=13=0=0\n" +
                "【张松之密谋】7-检查点==0==11=0==27097=False=27=0=0";
        gameClient.doStepsDoDoScriptAction(steps);
        System.out.println("完成张松之密谋");
    }
}
