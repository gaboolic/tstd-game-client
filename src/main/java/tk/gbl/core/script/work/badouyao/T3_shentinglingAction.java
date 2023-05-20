package tk.gbl.core.script.work.badouyao;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 神亭岭大战
 * 1.前往卢江寨的第二个军帐内与周瑜交谈，答应协助他。
 * 2.前往牛堵树林军帐内与刘繇交谈，会与刘繇、苲融、薛礼、陈横、樊能、于糜进入战斗，胜利后会立即与太史慈、周泰、蒋钦、朱皓、陈武、张英在次进入战斗。
 * 3.胜利后，出军帐后会再度与太史慈、张英、陈武、蒋钦进入战斗。
 * 4.胜利后在回去卢江寨找周瑜即可完成任务。
 * 任务条件：须完成濡须收复战任务
 * 地区：扬州、卢江水寨
 * 获得物品：冰剑纹章
 * <p/>
 * Date: 2017/4/26
 * Time: 15:52
 *
 * @author Tian.Dong
 */
public class T3_shentinglingAction extends ScriptAction {

    /**
     * 3神亭岭大战 - 周瑜=15846=2=190,340=0=0==0
     * 3神亭岭大战 - 转图=15000=0=1570,1890=1=25==0
     * 3神亭岭大战 - 刘繇=18802=1=382,415=0=0==0
     * 3神亭岭大战 - 出帐=18802=0=250,470=1=1==0
     * 3神亭岭大战 - 触发战斗=18801=0=2730,1310=1=4==0
     * 3神亭岭大战 - 交任务=15846=2=190,340=0=0==0
     */
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始神亭岭大战");
        gameClient.moveFar(15846);
        System.out.println("来到15846庐江寨");
        gameClient.segmentMoveTo(190, 340);
        gameClient.clickNPC(2);
        System.out.println("点周瑜");
        gameClient.simpleSleep();

        gameClient.moveFar(18802);
        System.out.println("来到18802牛渚树林");
        gameClient.segmentMoveTo(382, 415);
        gameClient.clickNPC(1);
        System.out.println("点刘繇");
        gameClient.simpleSleep();

        /**
         * 3神亭岭大战 - 出帐=18802=0=250,470=1=1==0
         * 3神亭岭大战 - 触发战斗=18801=0=2730,1310=1=4==0
         */
        gameClient.moveTrigger(1);
        gameClient.move(1);
        gameClient.moveAfter();
        System.out.println("出帐来到18801牛渚树林");
        //触发战斗
        gameClient.move(4);
        System.out.println("触发战斗打太史慈");
        gameClient.simpleSleep();
        gameClient.simpleSleep();

        /**
         * 3神亭岭大战 - 交任务=15846=2=190,340=0=0==0
         */
        //回去交任务
        gameClient.moveFar(15846);
        System.out.println("来到15846庐江寨");
        gameClient.segmentMoveTo(190, 340);
        gameClient.clickNPC(2);
        System.out.println("点周瑜");
        gameClient.simpleSleep();
        System.out.println("神亭岭大战执行完毕");
    }
}
