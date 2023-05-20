package tk.gbl.core.listener;

import tk.gbl.bean.Context;
import tk.gbl.core.GameClient;
import tk.gbl.util.OutputUtil;
import tk.gbl.util.image.Output;

import java.io.IOException;
import java.util.Date;

/**
 * Date: 2017/4/6
 * Time: 17:48
 *
 * @author Tian.Dong
 */
public class FightStartListener extends BaseListener {
    @Override
    public void doAction(GameClient gameClient, Context context) throws IOException {
        if (gameClient.getMoveIndex() != 0) {
            OutputUtil.output("进入战斗 " + gameClient.getCurrentMapId() + ",moveIndex " + gameClient.getMoveIndex() + ",坐标:" + gameClient.getPoint(), gameClient, true);
        } else {
            OutputUtil.output("进入战斗 " + gameClient.getCurrentMapId() + ",坐标:" + gameClient.getPoint(), gameClient, true);
        }
        gameClient.getGameParam().setInFight(true);
        gameClient.setFightTime(new Date());
        gameClient.setFighting(true);
        if (gameClient.getGameConfig().isFightReconnect()) {
            OutputUtil.output("遇怪重连!", gameClient);
            gameClient.clear();
            return;
        }
        gameClient.getFightLock().lock();
        OutputUtil.output("进入战斗-锁", gameClient, gameClient.isConsoleOutput());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.refreshMainFrame("战斗开始");
    }
}
