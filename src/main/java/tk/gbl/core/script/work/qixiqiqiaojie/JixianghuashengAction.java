package tk.gbl.core.script.work.qixiqiqiaojie;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 3.将小香瓜拿给小雪再到邺城广场粮车旁与大毛交谈后，取得“吉祥花生”。
 * <p/>
 * Date: 2017/6/14
 * Time: 18:49
 *
 * @author Tian.Dong
 */
public class JixianghuashengAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
//        gameClient.moveFar(12065);
//        gameClient.segmentMoveTo(630, 140);
//        gameClient.clickNPC(2);
//        gameClient.simpleSleep();

        gameClient.moveFar(12064);
        gameClient.segmentMoveTo(1350, 80);
        gameClient.move(8);
        gameClient.simpleSleep();
    }
}
