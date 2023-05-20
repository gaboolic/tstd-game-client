package tk.gbl.core.script.work.shimao;

import tk.gbl.constant.EquipType;
import tk.gbl.constant.ItemIdType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:诸葛亮点兵征南蛮
 * 地区:益州、成都城
 * 任务条件:需完成“吕凯计守永昌”的任务
 * 流程:
 * 1.进入丞相府的相府内堂(25142)与诸葛亮交谈，再进入成都大殿(25304)与刘禅交谈后，答应协助。
 * 2.前往南中朱提树林(30413)军帐前(630,620)与魏延交谈后，答应协助。
 * 3.移动到(1600,1300)触发交谈后，会与鄂焕进行战斗(需维持战斗超过五回合)。
 * 4.战斗结束后移动到右下传点附近触发交谈，会与鄂焕、南中兵x2进行战斗(需三回合内完成)。
 * 5.战斗胜利后返回到军帐前触发交谈，再移动到右下传点附近触发，会与南中兵x10(增援：南中兵x10)进行战斗。
 * 6.战斗胜利返回朱提树林(30413)军帐前触发交谈后，再于该处随机遭遇战斗将可疑蛮兵网罗起来。
 * 7.网罗成功后返回军帐前触发交谈，答应协助(取得“南中皮甲”并装备)。
 * 8.前往槃江雨林30831左上方触发交谈后，会与雍闿进行战斗。
 * 9.战斗胜利后将“南中皮甲”卸下返回朱提树林30413军帐前触发交谈，答应协助(装备“南中皮甲”)。
 * 10.前往牂牁寨门30801触发交谈后，会与朱褒、牂牁壮士x9进行战斗。
 * 11.战斗胜利后将“南中皮甲”卸下返回朱提树林军帐前触发交谈，即可完成任务。
 * 任务奖品:晞星羽毛(?+10)
 * <p/>
 * Date: 2017/4/26
 * Time: 15:38
 *
 * @author Tian.Dong
 */
public class T3_2manbinghou_zhugeliangdianbingzhengnanmanAction extends ScriptAction {

    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始诸葛亮点兵征南蛮-可疑蛮兵后");
        /**
         * 6.战斗胜利返回朱提树林军帐前触发交谈后，再于该处随机遭遇战斗将可疑蛮兵网罗起来。
         * 诸葛亮点兵征南蛮8=30413=0=630,620=1=3==0
         */
        System.out.println("6.战斗胜利返回朱提树林军帐前触发交谈后，再于该处随机遭遇战斗将可疑蛮兵网罗起来。");
        gameClient.segmentMoveTo(630, 620);
        gameClient.move(3);
        gameClient.simpleSleep();

         /* 7.网罗成功后返回军帐前触发交谈，答应协助(取得“南中皮甲”并装备)。
         * 诸葛亮点兵征南蛮9=30413=0=630,320=1=3==0
         */
        System.out.println("7.网罗成功后返回军帐前触发交谈，答应协助(取得“南中皮甲”并装备)。");
        gameClient.segmentMoveTo(630, 620);
        gameClient.move(3);
        gameClient.simpleSleep();
        //装备南中皮甲
        gameClient.equipItemWithId(ItemIdType.nanzhongpijia.getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("已经装备南中皮甲");

        /**
         * 8.前往槃江雨林30831左上方触发交谈后，会与雍闿进行战斗。
         * 诸葛亮点兵征南蛮10=30831=0=402,475=1=3==0
         * 9.战斗胜利后将“南中皮甲”卸下返回朱提树林30413军帐前触发交谈，答应协助(装备“南中皮甲”)。
         * 诸葛亮点兵征南蛮11=30413=0=630,620=1=3==0
         */
        System.out.println("8.前往槃江雨林30831左上方触发交谈后，会与雍闿进行战斗。");
        gameClient.moveFar(30831);
        gameClient.segmentMoveTo(402, 475);
        gameClient.move(3);
        gameClient.simpleSleep();

        //战斗胜利后将“南中皮甲”卸下
        gameClient.unequipItem(EquipType.Body.getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("卸下南中皮甲");
        System.out.println("9.战斗胜利后将“南中皮甲”卸下返回朱提树林30413军帐前触发交谈，答应协助(装备“南中皮甲”)。");

        gameClient.moveFar(30413);
        gameClient.segmentMoveTo(630,620);
        gameClient.move(3);
        gameClient.simpleSleep();

        //装备南中皮甲
        gameClient.equipItemWithId(ItemIdType.nanzhongpijia.getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("装备上南中皮甲，准备去牂牁寨");


        /** 10.前往牂牁寨门30801触发交谈后，会与朱褒、牂牁壮士x9进行战斗。
         * 诸葛亮点兵征南蛮12=30801=0=402,455=1=3==0
         * 战斗胜利后将“南中皮甲”卸下返回朱提树林军帐前触发交谈，即可完成任务。
         * 诸葛亮点兵征南蛮13=30413=0=630,620=1=3==0
         */
        System.out.println("10.前往牂牁寨门30801触发交谈后，会与朱褒、牂牁壮士x9进行战斗。");
        gameClient.moveFar(30801);
        gameClient.segmentMoveTo(402,455);
        gameClient.move(3);
        gameClient.simpleSleep();
        //战斗胜利后将“南中皮甲”卸下
        gameClient.unequipItem(EquipType.Body.getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("卸下南中皮甲");

        System.out.println("战斗胜利后将“南中皮甲”卸下返回朱提树林军帐前触发交谈，即可完成任务。");

        gameClient.moveFar(30413);
        gameClient.segmentMoveTo(630,620);
        gameClient.move(3);
        gameClient.simpleSleep();

        System.out.println("完成诸葛亮点兵征南蛮-可疑蛮兵后");
    }
}
