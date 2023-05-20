package tk.gbl.core.script;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 学习侦察术
 *
 * Date: 2017/4/4
 * Time: 18:22
 *
 * @author Tian.Dong
 */
public class ZhenchashuAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.simpleMove(1);
        gameClient.simpleMove(4);
        gameClient.moveTo(502, 315); //简雍
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.clickNPC(1);
        gameClient.eventOk();
        gameClient.eventOk();

        gameClient.choose(0x1E);//是
        gameClient.eventOk();
        gameClient.choose(0x20);//选第三个
        gameClient.eventOk();
        gameClient.eventOk();
        gameClient.choose(0x20);//选第三个
        gameClient.eventOk();
        gameClient.eventOk();
        gameClient.choose(0x1F);//选第2个
        gameClient.eventOk();
        gameClient.eventOk();
        gameClient.eventOk();
        gameClient.eventOk();
        gameClient.eventOk();

        gameClient.simpleMove(1);
        gameClient.simpleMove(2);
    }
}
