package tk.gbl.core.script.work.suipian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 29233残皮甲     25435  守关士卒   242,695
 * Date: 2017/6/9
 * Time: 14:41
 *
 * @author Tian.Dong
 */
public class SJ2_jiancanpijia extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        int mapId = 25435;
        int itemId = 29233;
        gameClient.waitForLoginSuccess();
        if (gameClient.getItemCount(itemId) > 0) {
            return;
        }
        if (gameClient.getCurrentMapId() == mapId && gameClient.getPoint().getX() == 242 && gameClient.getPoint().getY() == 695) {

        } else {
            gameClient.moveFar(mapId);
            gameClient.simpleMove(2);
            gameClient.moveFar(mapId);
            gameClient.segmentMoveTo(230, 250, 2000);
            gameClient.segmentMoveTo(242, 695, 2000);
        }

        try {
            gameClient.doDoDoScriptAction("捡残皮甲=25435=0=242,695=2=0=0=29233");
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameClient.waitForLoginSuccess();
        try {
            while (gameClient.getItemCount(itemId) < 1) {
                gameClient.waitForLoginSuccess();
                gameClient.sleep(1000);
                if (gameClient.getCurrentMapId() != mapId) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
