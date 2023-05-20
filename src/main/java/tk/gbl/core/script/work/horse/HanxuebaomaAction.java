package tk.gbl.core.script.work.horse;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:錦馬超,出擊!     查看前提
 * 地区:并州、上黨城
 * 任务条件:Lv.25
 * 流程:
 * 1.到并州上黨城與賈逵交談，答應協助。
 * 2.到臨汾樹林與西涼刀兵交談，答應協助。
 * 3.再到解良村茶舖與阿通伯交談，答應協助馬超。
 * 4.前往屯留樹林第二層與獅甲士交談，會與鄧昇、王琰、夏昭、師甲士x5進入戰鬥。
 * 5.勝利後，前往河東樹林與長刀兵交談，選擇上前助陣，會與張晟、高柔、張琰、范先、長刀兵x4進入戰鬥。
 * 6.勝利後，再往左下角與鐵冑兵交談，會與衛固、高幹、祝奧、郭援、鐵冑兵x4進入戰鬥。
 * 7.勝利後即可完成任務。
 * 任务奖品:隨機獲得：名駒x1、駿馬x1、汗血寶馬
 * 备注:無
 * <p/>
 * Date: 2017/4/26
 * Time: 15:40
 *
 * @author Tian.Dong
 */
public class HanxuebaomaAction extends ScriptAction {

    /**
     * 贾逵 - 20132=20132=1=470,300=0=0=1=0
     * 西凉刀兵 - 20861=20861=11=510,420=0=0=1=0
     * 阿通伯 - 20281=20281=3=550,500=0=0=1=0
     * 狮甲士 - 20812=20812=8=2130,400=0=0=1=0
     * 长刀兵 - 20831=20831=9=3430,540=0=0=1=0
     * 铁胄兵 - 20831=20831=16=310,1040=0=0=1=0
     */
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始汗血宝马");
        //1.到并州上黨城與賈逵交談，答應協助。
        //贾逵 - 20132=20132=1=470,300=0=0=1=0
        gameClient.moveFar(20132);
        System.out.println("来到20132上党");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.segmentMoveTo(470, 300);
        gameClient.clickNPC(1);
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);
        System.out.println("点賈逵");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //2 到臨汾樹林與西涼刀兵交談，答應協助。
        //西凉刀兵 - 20861=20861=11=510,420=0=0=1=0
        gameClient.moveFar(20861);
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);
        System.out.println("来到20861臨汾樹林");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.segmentMoveTo(510,420);
        gameClient.clickNPC(11);
        System.out.println("点西凉刀兵");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //3 再到解良村茶舖與阿通伯交談，答應協助馬超。
        //阿通伯 - 20281=20281=3=550,500=0=0=1=0
        gameClient.moveFar(20281);
        System.out.println("来到20281解良村茶舖");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.segmentMoveTo(550, 500);
        gameClient.clickNPC(3);
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);
        System.out.println("点阿通伯");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //4.前往屯留樹林第二層與獅甲士交談，會與鄧昇、王琰、夏昭、師甲士x5進入戰鬥。
        //狮甲士 - 20812=20812=8=2130,400=0=0=1=0
        gameClient.moveFar(20812);
        System.out.println("来到20812屯留樹林第二層");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.segmentMoveTo(2130, 400);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.clickNPC(8);
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);
        System.out.println("点獅甲士");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (gameClient.isFighting()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //5.勝利後，前往河東樹林與長刀兵交談，選擇上前助陣，會與張晟、高柔、張琰、范先、長刀兵x4進入戰鬥。
        //长刀兵 - 20831=20831=9=3430,540=0=0=1=0
        gameClient.moveFar(20831);
        System.out.println("来到20831河東樹林");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.segmentMoveTo(3430, 540);
        gameClient.clickNPC(9);
        System.out.println("点長刀兵");
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (gameClient.isFighting()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //6.勝利後，再往左下角與鐵冑兵交談，會與衛固、高幹、祝奧、郭援、鐵冑兵x4進入戰鬥。
        //铁胄兵 - 20831=20831=16=310,1040=0=0=1=0
        gameClient.moveFar(20831);
        System.out.println("来到20831");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.segmentMoveTo(340, 1040);
        gameClient.clickNPC(16);
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);
        System.out.println("点鐵冑兵");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (gameClient.isFighting()) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("完毕");
        gameClient.moveFar(12001);
        System.out.println("回到涿郡");
    }
}
