package tk.gbl.core.script.work.shimao;

import tk.gbl.bean.BagItem;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:吕凯计守永昌
 * 地区:南中、永昌城
 * 任务条件:需完成“平蛮指掌图”的任务
 * 流程:
 * 1.前往永昌城移动到吕凯下方触发交谈后，进入官府与王伉交谈并答应协助。
 * 2.出官府移动到吕凯下方触发交谈后，答应协助。
 * 3.前往越隽寨门触发交谈后，会与鄂焕、高定、南中兵x5进行战斗(请遁逃)。
 * 4.战斗结束后前往锦带山(260,950)触发交谈后，会与鄂焕、高定、南中兵x5进行战斗(请遁逃)。
 * 5.战斗结束后前往锦带山湖畔(1050,200)后再移动到(450,230)触发交谈，会与南中兵x10(增援：鄂焕、高定)进行战斗。
 * 6.战斗胜利后，答应收集5个“黑松木-朝歌树林的木族人或木族长掉落”、5个“轻染布-到芒旸树林收集蓝天绸缎后给有学合成术的合成(成功机率高) ”、1个“硬厚皮-王屋矿坑宝箱，或是到黎阳树林打狼兵收集碎狼皮给未学合成术的合成”。
 * 7.收集齐全后返回与吕凯交谈获得2个“伪装娃娃”，答应协助。
 * 8.使用伪装娃娃后前往泸津关(左进场景)阶梯触发交谈后，会与朱褒、雍闿、南蛮精兵x8进行战斗。
 * 9.战斗胜利后前往泸津关(右进场景)阶梯触发交谈后，会与朱褒、雍闿(增援：愤怒的朱褒、愤怒的雍闿)进行战斗。
 * 10.战斗胜利后，即可完成任务
 * 任务奖品:经验值若干(0.3%)、[随机取得]真福神x2、大福神x4
 * <p/>
 * Date: 2017/4/26
 * Time: 15:33
 *
 * @author Tian.Dong
 */
public class T2_lvkaijishouyongchangAction extends ScriptAction {

    /**
     * 吕凯计守永昌1=30011=0=622,455=1=8==0
     * 吕凯计守永昌2=30301=1=270,340=0=0==0
     * 吕凯计守永昌3=30011=0=622,455=1=8==0
     * 吕凯计守永昌4=30811=0=750,570=1=3==0
     * 吕凯计守永昌5=30471=0=260,950=1=5==0
     * 吕凯计守永昌6=30473=0=1050,200=1=3==0
     * 吕凯计守永昌7=30473=0=450,230=1=4==0
     * 吕凯计守永昌8=30011=3=650,380=1=8==0
     * 吕凯计守永昌10=30403=0=402,535=1=3==0
     * 吕凯计守永昌11=30401=0=722,535=1=4==0
     */
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始吕凯计守永昌");
        gameClient.moveFar(30011);
        gameClient.segmentMoveTo(622, 455);
        gameClient.move(8);
        System.out.println("前往永昌城移动到吕凯下方触发交谈后");
        gameClient.simpleSleep();

        gameClient.moveFar(30301);
        gameClient.segmentMoveTo(270, 340);
        gameClient.clickNPC(1);
        System.out.println("进入官府与王伉交谈并答应协助");
        gameClient.simpleSleep();

        gameClient.moveFar(30011);
        gameClient.segmentMoveTo(622, 455);
        gameClient.move(8);
        System.out.println("出官府移动到吕凯下方触发交谈后，答应协助。");
        gameClient.simpleSleep();

        //吕凯计守永昌4=30811=0=750,570=1=3==0
        gameClient.moveFar(30811);
        gameClient.segmentMoveTo(750, 570);
        gameClient.move(3);
        System.out.println("前往越隽寨门触发交谈后，会与鄂焕、高定、南中兵x5进行战斗(请遁逃)。");
        gameClient.simpleSleep();

        //吕凯计守永昌5=30471=0=260,950=1=5==0
        gameClient.moveFar(30471);
        gameClient.segmentMoveTo(260, 950);
        gameClient.move(5);
        System.out.println("战斗结束后前往锦带山(260,950)触发交谈后，会与鄂焕、高定、南中兵x5进行战斗(请遁逃)。");
        gameClient.simpleSleep();

        //吕凯计守永昌6=30473=0=1050,200=1=3==0
        //吕凯计守永昌7=30473=0=450,230=1=4==0
        gameClient.moveFar(30473);
        gameClient.segmentMoveTo(1050, 200);
        gameClient.move(3);
        System.out.println("战斗结束后前往锦带山湖畔(1050,200)后");
        gameClient.simpleSleep();
        gameClient.segmentMoveTo(450, 230);
        gameClient.move(4);
        System.out.println("再移动到(450,230)触发交谈，会与南中兵x10(增援：鄂焕、高定)进行战斗");
        gameClient.simpleSleep();

        //吕凯计守永昌8=30011=3=650,380=1=8==0
        gameClient.moveFar(30011);
        gameClient.segmentMoveTo(650, 380);
        gameClient.move(8);
        gameClient.simpleSleep();
        gameClient.move(8);
        gameClient.simpleSleep();
        System.out.println("收集齐全后返回与吕凯交谈获得2个“伪装娃娃”，答应协助。");


        //使用伪装娃娃
        int index = 0;
        for (int i = 1; i < gameClient.getBagItems().length; i++) {
            BagItem bagItem = gameClient.getBagItems()[i];
            if (bagItem == null) {
                continue;
            }
            if (bagItem.getId() == 48075) {
                index = i;
            }
        }
        gameClient.useItem(index, 1);
        System.out.println("使用伪装娃娃,位置" + index);

        /*
         * 8.使用伪装娃娃后前往泸津关(左进场景)阶梯触发交谈后，会与朱褒、雍闿、南蛮精兵x8进行战斗。
         * 吕凯计守永昌10=30403=0=402,535=1=3==0
         */
        gameClient.moveFar(30403);
        gameClient.segmentMoveTo(402, 535);
        gameClient.move(3);
        System.out.println("使用伪装娃娃后前往泸津关(左进场景)阶梯触发交谈后，会与朱褒、雍闿、南蛮精兵x8进行战斗。");
        gameClient.simpleSleep();

        /**
         * 9.战斗胜利后前往泸津关(右进场景)阶梯触发交谈后，会与朱褒、雍闿(增援：愤怒的朱褒、愤怒的雍闿)进行战斗。
         * 吕凯计守永昌11=30401=0=722,535=1=4==0
         */
        gameClient.moveFar(30401);
        gameClient.segmentMoveTo(722, 535);
        gameClient.move(4);
        System.out.println("战斗胜利后前往泸津关(右进场景)阶梯触发交谈后，会与朱褒、雍闿(增援：愤怒的朱褒、愤怒的雍闿)进行战斗。");
        gameClient.simpleSleep();

        System.out.println("完成吕凯计守永昌");
    }
}
