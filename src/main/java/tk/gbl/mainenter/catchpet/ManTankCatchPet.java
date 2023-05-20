package tk.gbl.mainenter.catchpet;

import tk.gbl.core.GameClient;
import tk.gbl.core.script.catchpet.CatchPetAction;

import java.io.IOException;

/**
 * Date: 2017/4/19
 * Time: 10:41
 *
 * @author Tian.Dong
 */
public class ManTankCatchPet {
    public static void main(String[] args) throws IOException {
        final GameClient gameClient = new GameClient();
        gameClient.setUsername("WP00114018");
        gameClient.setPassword("23467");
        gameClient.init();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.waitForLoginSuccess();
        System.out.println("***当前地图："+gameClient.getCurrentMapId());

        CatchPetAction catchPetAction = new CatchPetAction();
        catchPetAction.setClickNpc(true);
        catchPetAction.setMapId(15415);
        catchPetAction.setNpcIndex(6);
        catchPetAction.setNpcId(14342);
        catchPetAction.setX(222);
        catchPetAction.setY(275);
        gameClient.doScriptAction(catchPetAction);

        System.out.println("完成");
    }
}
