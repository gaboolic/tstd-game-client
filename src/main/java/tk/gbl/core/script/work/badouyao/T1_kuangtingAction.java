package tk.gbl.core.script.work.badouyao;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 匡亭之战
 * 1.进入卢江水寨的最后一个军帐内，与孙策交谈，答应协助他。
 * 2.前往盱眙水洞迷宫，在洞内会遇见受伤的士兵，与他交谈，会与曹仁、曹洪、刘晔、吕度、朱灵进入战斗，胜利后会立即与夏侯惇、夏侯渊等人进入战斗。
 * 3.胜利后即可完成任务。
 * 地区：扬州、卢江水寨
 * 获得物品：火箭纹章
 * <p/>
 * Date: 2017/4/26
 * Time: 15:51
 *
 * @author Tian.Dong
 */
public class T1_kuangtingAction extends ScriptAction {

    /**
     * 格式
     * （名称）=（场景ID）=（NPC场景排序）=（坐标）=（移动触发方式1=1408/2=1404）=（移动触发排序）=（选择选项）=（捡取物品ID）
     * <p/>
     * 地盘古任务-盘古巨兽 - 13519=13519=11=2390,990=1=2=1-2=0=True=1=0=0
     * 1匡亭之战 - 孙策=15847=2=370,400=0=0=1=0
     * 1匡亭之战 - 受伤的士兵=15543=3=3950,1600=0=0==0
     */
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始匡亭之战");
        gameClient.moveFar(15847);
        System.out.println("来到15847庐江寨");
        gameClient.segmentMoveTo(370, 400);
        gameClient.clickNPC(2);
        System.out.println("点孙策");
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);
        gameClient.simpleSleep();

        gameClient.moveFar(15543);
        System.out.println("来到15543盱眙水洞迷宫");
        gameClient.segmentMoveTo(3900, 1600);
        gameClient.clickNPC(3);
        System.out.println("点受伤士兵");
        gameClient.simpleSleep();
        System.out.println("匡亭之战完毕");
    }
}
