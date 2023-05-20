package tk.gbl.core.script.work.badouyao;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 西塞山大战
 * 1.在建业官府与孙权交谈，答应前往探查。
 * 2.前往庐江水寨，在洞内见到黄猗，与他交谈，会与黄猗、袁、袁曜进入战斗。
 * 3.胜利后前往柴桑寨第二层，与刘勋交谈，会与刘勋、刘威、刘偕、僮芝进入战斗。
 * 4.胜利后前往西塞山，与刘威交谈，会与吕公、张硕、刘偕、刘威、韩晞、黄射、刘盘、刘虎进入战斗。
 * 5.胜利后进入山顶，进入后网右上走，与黄祖交谈，会与黄祖、甘宁、蔡瑁、韩晞、黄射、刘盘、刘虎、刘勋、陈生、张虎、王威进入战斗，胜利后即可完成任务。
 * 任务条件：须完成东冶平定战
 * 地区：扬州、建业城
 * 获得物品：随机获得：背包巴兑换卷、恶魔兑换卷、心巴豆妖兑换卷
 * <p/>
 * Date: 2017/4/26
 * Time: 15:54
 *
 * @author Tian.Dong
 */
public class T6_xisaishanAction extends ScriptAction {

    /**
     * 6西塞山大战 - 孙权=18301=8=582,395=0=0==0
     * 6西塞山大战 - 黄猗=15842=2=430,290=0=0==0
     * 6西塞山大战 - 刘勋=18816=8=570,560=0=0==0
     * 6西塞山大战 - 切换=18816=0=570,560=1=3==0
     * 6西塞山大战 - 刘威=18422=1=490,780=0=0==0
     * 6西塞山大战 - 黄祖=18423=9=2522,495=0=0==0
     */
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始西塞山大战");
        //1.在建业官府与孙权交谈，答应前往探查。
        //6西塞山大战 - 孙权=18301=8=582,395=0=0==0
        gameClient.moveFar(18301);
        System.out.println("来到18301建业官府");
        gameClient.segmentMoveTo(582, 395);
        gameClient.clickNPC(8);
        System.out.println("点孙权");
        gameClient.simpleSleep();

        /**
         * 2.前往庐江水寨，在洞内见到黄猗，与他交谈，会与黄猗、袁、袁曜进入战斗。
         * 6西塞山大战 - 黄猗=15842=2=430,290=0=0==0
         */
        gameClient.moveFar(15842);
        System.out.println("来到15842庐江水寨");
        gameClient.segmentMoveTo(430, 290);
        gameClient.clickNPC(2);
        System.out.println("点黄猗");
        gameClient.simpleSleep();

        /**
         * 3.胜利后前往柴桑寨第二层，与刘勋交谈，会与刘勋、刘威、刘偕、僮芝进入战斗。
         * 6西塞山大战 - 刘勋=18816=8=570,560=0=0==0
         */
        gameClient.moveFar(18816);
        System.out.println("来到18816柴桑寨第二层");
        gameClient.segmentMoveTo(570, 560);
        gameClient.clickNPC(8);
        System.out.println("点刘勋");
        gameClient.simpleSleep();

        /**
         * 4.胜利后前往西塞山，与刘威交谈，会与吕公、张硕、刘偕、刘威、韩晞、黄射、刘盘、刘虎进入战斗。
         * 6西塞山大战 - 刘威=18422=1=490,780=0=0==0
         */
        gameClient.moveFar(18422);
        System.out.println("来到18422西塞山");
        gameClient.segmentMoveTo(490, 780);
        gameClient.clickNPC(1);
        System.out.println("点刘威");
        gameClient.simpleSleep();

        /**
         *5.胜利后进入山顶，进入后网右上走，与黄祖交谈，会与黄祖、甘宁、蔡瑁、韩晞、黄射、刘盘、刘虎、刘勋、陈生、张虎、王威进入战斗，胜利后即可完成任务。
         *6西塞山大战 - 黄祖=18423=9=2522,495=0=0==0
         */
        gameClient.moveFar(18423);
        System.out.println("来到18423西塞山顶");
        gameClient.segmentMoveTo(2522, 495);
        gameClient.clickNPC(9);
        System.out.println("点黄祖");
        gameClient.simpleSleep();

        System.out.println("西塞山大战执行完毕");
    }
}
