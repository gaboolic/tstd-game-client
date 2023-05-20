package tk.gbl.core.script;

import tk.gbl.bean.BagItem;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * 从网吧到涿郡广场
 * <p/>
 * Date: 2017/4/4
 * Time: 18:22
 *
 * @author Tian.Dong
 */
public class InitToZJGCAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        OutputUtil.output("开始执行创号去涿郡广场脚本" + gameClient.getCurrentMapId(), gameClient, true);
        //在网吧
        if (gameClient.getCurrentMapId() < 10851) {
            gameClient.moveTrigger(1);
            gameClient.simpleMove(1);
        }
        OutputUtil.output("出了网吧" + gameClient.getCurrentMapId(), gameClient, true);
        OutputUtil.output("开始移动到南斗星君", gameClient, true);
        gameClient.segmentMoveTo(1242, 215);//移动到南斗星君
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);
        gameClient.clickNPC(2);
        gameClient.simpleSleep();

        OutputUtil.output("开始下山去涿郡广场" + gameClient.getCurrentMapId(), gameClient, true);
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);
        gameClient.moveTrigger(2);
        gameClient.simpleMove(2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (gameClient.getCurrentMapId() != 12003) {
            OutputUtil.output("创号脚本执行异常,地图id:" + gameClient.getCurrentMapId(), gameClient, true);
        }
        OutputUtil.output("地图" + gameClient.getCurrentMapId() + " 来到涿郡广场，自动使用物品", gameClient, true);
        gameClient.autoUseAndThrow();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.moveFar(12001);
    }
}
