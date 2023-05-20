package tk.gbl.core.script.work.suipian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称-庞统三计
 * 触发条件-需完成「截江夺阿斗」的任务
 * 触发地点-益州、葭萌关
 * 流程-
 * 1.至葭萌通道(大地图左方进入)与刘备交谈，答应协助。
 * 2.进入白水关卡时自行交谈后，返回葭萌通道。
 * 3.与刘备交谈后，前往关城山洞与向充交谈后，会与高沛、杨怀、巨斧兵x2、鳞甲兵x2进行战斗。
 * 4.战斗胜利后，至白水关(大地图右方进入)进行一段剧情后，再与刘备(由白水树林进入)交谈后即可完成任务。
 * ->获得物品:经验值若干(2%)
 * <p/>
 * Date: 2017/6/14
 * Time: 16:12
 *
 * @author Tian.Dong
 */
public class S5_pangtongsanji extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "【庞统三计】1=25412=1=790,400=0=0==0=True=13=0=0\n" +
                "【庞统三计】2=25431=0==1=1==0=False=4=0=0\n" +
                "【庞统三计】3=25412=1=790,400=0=0==0=True=17=0=0\n" +
                "【庞统三计】4-Pre=25514=0==2=0==0=False=0=0=0\n" +
                "【庞统三计】4=25511=2=2910,240=0=0==0=True=9=5=0\n" +
                "【庞统三计】5=25431=0==1=2==0=True=10=0=0\n" +
                "【庞统三计】6=25433=1=390,460=0=0==0=True=36=0=0";
    }
}
