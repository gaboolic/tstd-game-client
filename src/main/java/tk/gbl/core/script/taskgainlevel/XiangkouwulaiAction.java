package tk.gbl.core.script.taskgainlevel;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:倪老爷人呢
 * 地区:荆州、襄阳城
 * 任务条件:Lv.14
 * 流程:
 * 1.到襄阳城大街与家仆交谈，答应协助。
 * 2.再到广场与倪老爷交谈，会与地痞x1、巷口无赖x2进入战斗。
 * 3.胜利后即可完成任务。
 * 任务奖品:百年人蔘x30
 * 备注:
 * <p/>
 * 21001襄阳
 * 25级
 * 巷口无赖
 * 54 53 54
 * Date: 2017/6/23
 * Time: 14:49
 *
 * @author Tian.Dong
 */
public class XiangkouwulaiAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始打巷口无赖");
        gameClient.moveFar(21002);
        System.out.println("当前地图id:" + gameClient.getCurrentMapId());
        gameClient.segmentMoveTo(1430,640);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.getGameConfig().setGameConfigType(GameConfigType.gainLevel);
        while (gameClient.getTeamUsers().size() == gameClient.getAllowTeamUsers().size()
                && !customJudge()) {
            System.out.println("clickNPC");
            gameClient.clickNPC(44);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("循环条件"+gameClient.getTeamUsers().size() + " " +gameClient.getAllowTeamUsers().size() +" " +!customJudge());
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("巷口无赖脚本执行完成");
    }

    public boolean customJudge(){
        return true;
    }
}
