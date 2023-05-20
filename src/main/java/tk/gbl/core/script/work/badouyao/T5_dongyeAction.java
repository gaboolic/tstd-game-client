package tk.gbl.core.script.work.badouyao;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 东冶平定战
 * 1.前去会稽城，在城门口见到虞翻，与他交谈，答应协助。
 * 2.前往余姚沼泽，在第二层左方会遇见吕合与秦狼在驻守粮车，与吕合交谈，会与吕合、秦狼、黄龙罗、周勃进入战斗。
 * 3.胜利后再到军帐内与全柔交谈，会与周昂、周隅、周昕、全柔进入战斗，胜利后得【王朗令牌】。
 * 4.在前往会稽树林，进入后与王朗交谈，会与王朗、严白虎、董袭、贺齐、陈宝、芮良进入战斗。
 * 5.得胜后，前往东治村，在右边的小屋内，与王朗交谈，会与商升、何雄、詹强、张雅进入战斗，胜利后得即可完成任务。吕范会出现，请玩家回建业城。
 * 任务条件：须完成吴郡平定战
 * 地区：扬州、建业城
 * 获得物品：随机获得：演孔图、鬼谷子兵法、伤寒杂病论(知+10、防+1、Lv.40)、紫云长袍、翔鹤长衫
 * <p/>
 * Date: 2017/4/26
 * Time: 15:54
 *
 * @author Tian.Dong
 */
public class T5_dongyeAction extends ScriptAction {

    /**
     * 5东冶平定战 - 虞翻=18021=7=742,675=0=0==0
     * 5东冶平定战 - 吕合=18862=5=440,980=0=0==0
     * 5东冶平定战 - 全柔=18863=1=522,355=0=0==0
     * 5东冶平定战 - 王朗1=18891=4=202,1095=0=0==0
     * 5东冶平定战 - 王朗2=18176=1=470,340=0=0==0
     */
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始东冶平定战 ");
        //前去会稽城，在城门口见到虞翻，与他交谈，答应协助。
        //5东冶平定战 - 虞翻=18021=7=742,675=0=0==0
        gameClient.moveFar(18021);
        System.out.println("来到18021会稽");
        gameClient.segmentMoveTo(742,675);
        gameClient.clickNPC(7);
        System.out.println("点虞翻");
        gameClient.simpleSleep();

        //2.前往余姚沼泽，在第二层左方会遇见吕合与秦狼在驻守粮车，与吕合交谈，会与吕合、秦狼、黄龙罗、周勃进入战斗。
        //5东冶平定战 - 吕合=18862=5=440,980=0=0==0
        gameClient.moveFar(18862);
        System.out.println("来到18862余姚沼泽");
        gameClient.segmentMoveTo(522,995);
        gameClient.clickNPC(5);
        System.out.println("点吕合");
        gameClient.simpleSleep();

        //3胜利后再到军帐内与全柔交谈，会与周昂、周隅、周昕、全柔进入战斗，胜利后得【王朗令牌】。
        //5东冶平定战 - 全柔=18863=1=522,355=0=0==0
        gameClient.moveFar(18863);
        System.out.println("来到18863找全柔");
        gameClient.segmentMoveTo(522,355);
        gameClient.clickNPC(1);
        System.out.println("点全柔");
        gameClient.simpleSleep();

        //4.在前往会稽树林，进入后与王朗交谈，会与王朗、严白虎、董袭、贺齐、陈宝、芮良进入战斗。
        //5东冶平定战 - 王朗1=18891=4=202,1095=0=0==0
        gameClient.moveFar(18891);
        System.out.println("来到18891会稽树林");
        gameClient.segmentMoveTo(202,1095);
        gameClient.clickNPC(4);
        System.out.println("点王朗");
        gameClient.simpleSleep();

        //5.得胜后，前往东治村，在右边的小屋内，与王朗交谈，会与商升、何雄、詹强、张雅进入战斗，胜利后得即可完成任务。吕范会出现，请玩家回建业城。
        //5东冶平定战 - 王朗2=18176=1=470,340=0=0==0
        gameClient.moveFar(18176);
        System.out.println("来到18176东治村小屋");
        gameClient.segmentMoveTo(470,340);
        gameClient.clickNPC(1);
        System.out.println("点王朗");
        gameClient.simpleSleep();
        System.out.println("东冶平定战完成");
    }
}
