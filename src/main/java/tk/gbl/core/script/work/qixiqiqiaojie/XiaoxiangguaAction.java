package tk.gbl.core.script.work.qixiqiqiaojie;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 1.到邺城巷道与小雪交谈后，答应协助。
 * 2.到邺城广场与小绿豆交谈后，取得“小香瓜”。
 * <p/>
 * Date: 2017/6/14
 * Time: 18:49
 *
 * @author Tian.Dong
 */
public class XiaoxiangguaAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.moveFar(12065);
        gameClient.segmentMoveTo(630,140);
        gameClient.clickNPC(2);
        gameClient.simpleSleep();

        gameClient.moveFar(12064);
        gameClient.segmentMoveTo(1010,1160);
        gameClient.clickNPC(43);
        gameClient.simpleSleep();
    }
}
