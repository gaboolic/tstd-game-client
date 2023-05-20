package tk.gbl.core.script.work.shimao;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 平蛮指掌图
 * <p/>
 * 任务名称:平蛮指掌图
 * 地区:南中、永昌城
 * 任务条件:Lv25
 * 流程:
 * 1.与永昌城的呂凱交谈后，答应协助(呂凱加入队伍)。
 * 2.前往锦带山脚(1000,500)触发剧情后，会与禿龙野人进行战斗。
 * 3.战斗结束后答应收集「石田螺」2個(锦帶山脚周边)，收集齐全再移动到(1000,500)触发剧情。
 * 4.前往夾山谷小径(700,350)触发交谈后，会与豚蛮酋長、面蛮酋長、豚蛮卒x2、面蛮x2进行战斗。
 * 5.战斗结束后前往毒池與孟麟交谈，会与朵思大王进行战斗。
 * 6.战斗结束后前往盘蛇谷小径(600,900)触发剧情后，會与深山蛮猴x2、深山野猴x6进行战斗。
 * 7.战斗结束后，前往乌戈石道(2250,300)触发后选择『呃..是「无稽之談」?』。
 * 8.回答後再移动到该触发点与耶塔交谈，取得【乌戈情报】后返回永昌城与呂凱交談，即可完成任務。
 * 任务奖品:经验值若干(0.8%)、[隨机取得]倚天剑(攻+30、敏+3,Lv120)、雪莲还魂丹
 * 备注:耶塔位置：由乌戈国土安上方的洞口进入乌戈石道，移动到上述的座標即可。
 * <p/>
 * Date: 2017/4/26
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class T1_PingmanzhizhangtuAction extends ScriptAction {

    /**
     * 平蛮指掌图1=30011=3=650,380=0=0==0
     * 平蛮指掌图2=30011=3=650,380=0=0==0
     * 平蛮指掌图3=30471=0=1000,500=1=3==0
     * 平蛮指掌图6=30471=0=1000,500=1=3==0
     * 平蛮指掌图7=30467=0=700,350=1=3==0
     * 平蛮指掌图8=30431=1=750,550=0=0==0
     * 平蛮指掌图9=30481=0=600,900=1=3==0
     * 平蛮指掌图10=30551=0=2250,300=1=3=3=0
     * 平蛮指掌图11=30551=0=2250,300=1=3==0
     * 平蛮指掌图12=30011=3=650,380=0=0==0
     */
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始平蛮执掌图");
        gameClient.moveFar(30011);
        System.out.println("来到30011");
        gameClient.segmentMoveTo(650,380);
        gameClient.clickNPC(3);
        System.out.println("点击呂凱");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.clickNPC(3);
        System.out.println("点击呂凱");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gameClient.moveFar(30471);
        System.out.println("来到30471锦带山脚");
        gameClient.segmentMoveTo(1000, 500);
        System.out.println("来到1000, 500");
        gameClient.move(3);
        System.out.println("触发剧情1");
        gameClient.simpleSleep();
        gameClient.move(3);
        System.out.println("收集齐全,触发剧情2");
        gameClient.simpleSleep();

        gameClient.moveFar(30467);
        System.out.println("来到30467夾山谷小径");
        gameClient.segmentMoveTo(700,350);
        System.out.println("来到700,350");
        gameClient.move(3);
        System.out.println("触发剧情发生战斗");
        gameClient.simpleSleep();

        gameClient.moveFar(30431);
        System.out.println("来到30431毒池");
        gameClient.segmentMoveTo(750,550);
        gameClient.clickNPC(1);
        System.out.println("点孟麟 打朵思大王");
        gameClient.simpleSleep();

        //战斗结束后前往盘蛇谷小径(600,900)触发剧情后，會与深山蛮猴x2、深山野猴x6进行战斗。
        //平蛮指掌图9=30481=0=600,900=1=3==0
        gameClient.moveFar(30481);
        System.out.println("来到30481盘蛇谷小径");
        gameClient.segmentMoveTo(600,900);
        System.out.println("来到600,900");
        gameClient.move(3);
        System.out.println("触发剧情发生战斗");
        gameClient.simpleSleep();

        /**
         * 7.战斗结束后，前往乌戈石道(2250,300)触发后选择『呃..是「无稽之談」?』。
         * 8.回答後再移动到该触发点与耶塔交谈，取得【乌戈情报】后返回永昌城与呂凱交談，即可完成任務。
         * 任务奖品:经验值若干(0.8%)、[隨机取得]倚天剑(攻+30、敏+3,Lv120)、雪莲还魂丹
         * 备注:耶塔位置：由乌戈国土安上方的洞口进入乌戈石道，移动到上述的座標即可。
         *
         * 平蛮指掌图10=30551=0=2250,300=1=3=3=0
         * 平蛮指掌图11=30551=0=2250,300=1=3==0
         * 平蛮指掌图12=30011=3=650,380=0=0==0
         */
        gameClient.moveFar(30551);
        System.out.println("来到30551乌戈石道");
        gameClient.segmentMoveTo(2250, 300);
        System.out.println("来到2250,300");
        gameClient.move(3);
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(3);
        System.out.println("触发剧情选择『呃..是「无稽之談」");
        gameClient.simpleSleep();
        gameClient.move(3);
        System.out.println("再次触发剧情取得【乌戈情报】");
        gameClient.simpleSleep();

        gameClient.moveFar(30011);
        System.out.println("回到30011永昌城");
        gameClient.segmentMoveTo(650,380);
        gameClient.clickNPC(3);
        System.out.println("点击吕凯");
        gameClient.simpleSleep();
        System.out.println("完成平蛮指掌图");
    }
}
