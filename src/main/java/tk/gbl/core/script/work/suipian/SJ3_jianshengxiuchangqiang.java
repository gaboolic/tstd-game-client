package tk.gbl.core.script.work.suipian;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 29232生锈长枪   25435  守关士卒   1382,1200
 * <p/>
 * Date: 2017/6/9
 * Time: 14:41
 *
 * @author Tian.Dong
 */
public class SJ3_jianshengxiuchangqiang extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        int mapId = 25435;
        int itemId = 29232;
        gameClient.waitForLoginSuccess();
        if (gameClient.getItemCount(itemId) > 0) {
            return;
        }
        if (gameClient.getCurrentMapId() == mapId
                && gameClient.getPoint().getX() == 1382 && gameClient.getPoint().getY() == 1200) {

        } else {
            gameClient.moveFar(mapId);
            gameClient.simpleMove(3);
            gameClient.moveFar(mapId);
            gameClient.segmentMoveTo(1700, 230, 2000);
            gameClient.segmentMoveTo(1700, 1200, 2000);
            gameClient.segmentMoveTo(1382, 1200, 2000);
        }
        try {
            gameClient.doDoDoScriptAction("捡生锈长枪=25435=0=1382,1200=2=0=0=29232");
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
