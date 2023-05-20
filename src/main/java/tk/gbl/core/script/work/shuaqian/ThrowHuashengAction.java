package tk.gbl.core.script.work.shuaqian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.util.Random;

/**
 * Date: 2017/4/6
 * Time: 14:29
 *
 * @author Tian.Dong
 */
public class ThrowHuashengAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setAutoMergeBag(false);
        gameClient.getGameConfig().setAutoMergeBag(false);
        gameClient.moveFar(12064);
        while (gameClient.getCurrentMapId() != 12064) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameClient.segmentMoveTo(1350, 80);//邺城广场 大毛

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OutputUtil.output("开始扔东西", gameClient, true);
        int count = 0;
        while (true) {
            gameClient.move(8);
            gameClient.eventOk();
            gameClient.eventOk();
            gameClient.eventOk();
            gameClient.eventOk();
            gameClient.eventOk();
            gameClient.throwAway(1, 1);
            count++;
            gameClient.sleep(5);

            if (count % 10000 == 0) {
                OutputUtil.output("扔了" + count + "个", gameClient, true);
            }
        }
    }
}