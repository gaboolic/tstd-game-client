package tk.gbl.core.script.work.badouyao;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 濡须收复战
 * 1.前往寿春城，进入城内与寿春交谈。
 * 2.在前往寿春街道里面与袁曜交谈。
 * 3.在回到卢江寨与孙策交谈。
 * 4.前往濡须寨，通过右上方的濡须钟乳石洞，进入濡须树林，在树林的右上方与卢江兵交谈，会与陆康、陆骏、刘宠、骆俊进入战斗。
 * 5.胜利后即可完成任务。
 * 任务条件：须完成匡亭之战任务
 * 地区：扬州、寿春城
 * 获得物品：行军帐x5
 * <p/>
 * Date: 2017/4/26
 * Time: 15:52
 *
 * @author Tian.Dong
 */
public class T2_ruxuAction extends ScriptAction {

    /**
     * 2濡须收复战 - 寿春兵=15021=9=702,695=0=0==0
     2濡须收复战 - 袁曜=15024=1=1062,515=0=0==0
     2濡须收复战 - 孙策=15847=2=370,400=0=0==0
     2濡须收复战 - 卢江兵=15455=6=1230,560=0=0==0
     */
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始濡须收复战");
        gameClient.moveFar(15021);
        System.out.println("来到15021寿春");
        gameClient.segmentMoveTo(702,695);
        gameClient.clickNPC(9);
        System.out.println("点寿春兵");
        gameClient.simpleSleep();

        gameClient.moveFar(15024);
        System.out.println("来到15024寿春街道");
        gameClient.segmentMoveTo(1062,515);
        gameClient.clickNPC(1);
        System.out.println("点袁曜");
        gameClient.simpleSleep();

        gameClient.moveFar(15847);
        System.out.println("来到15847庐江寨");
        gameClient.segmentMoveTo(370,400);
        gameClient.clickNPC(2);
        System.out.println("点孙策");
        gameClient.simpleSleep();

        /**
         * 2濡须收复战 - 卢江兵=15455=6=1230,560=0=0==0
         */
        gameClient.moveFar(15455);
        System.out.println("来到15455濡须树林");
        gameClient.segmentMoveTo(1230,560);
        gameClient.clickNPC(6);
        System.out.println("点卢江兵");
        gameClient.simpleSleep();
        gameClient.clickNPC(6);
        System.out.println("点卢江兵");
        gameClient.simpleSleep();
        System.out.println("濡须收复战结束");
    }
}
