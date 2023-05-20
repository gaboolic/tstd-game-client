package tk.gbl.core.script.work.suipian;

import tk.gbl.bean.BagItem;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 火把 11分钟刷新2个
 * 残皮甲 11分钟
 * 生锈长矛 6分钟
 * <p/>
 * 29232生锈长枪   25435  守关士卒   1362,1295 1344,1316
 * 29233残皮甲     25435  守关士卒   242,695
 * 29235火把       25414  猛羌将     162,375
 * <p/>
 * Date: 2017/6/9
 * Time: 14:41
 *
 * @author Tian.Dong
 */
public class SJ1_jianhuoba extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        int mapId = 25414;
        int itemId = 29235;
        gameClient.waitForLoginSuccess();
        if (gameClient.getItemCount(itemId) > 0) {
            return;
        }
        gameClient.moveFar(mapId);
        gameClient.simpleMove(1);
        gameClient.moveFar(mapId);
        try {
            gameClient.doDoDoScriptAction("捡火把=25414=0=162,375=2=0=0=29235");
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameClient.waitForLoginSuccess();
        while (gameClient.getItemCount(itemId) < 1) {
            gameClient.waitForLoginSuccess();
            gameClient.sleep(2000);
            if (gameClient.getCurrentMapId() != mapId) {
                break;
            }
            for (int i = 0; i < gameClient.getSceneItems().length; i++) {
                BagItem sceneItem = gameClient.getSceneItems()[i];
                if (sceneItem == null) {
                    continue;
                }
                if (sceneItem.getId() == itemId) {
                    gameClient.pickUp(i);
                    break;
                }
            }
        }
    }

    private void singleFlow(GameClient gameClient) throws IOException {

    }
}
