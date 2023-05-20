package tk.gbl.core.listener;

import tk.gbl.bean.Context;
import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.fightai.QingliuAi;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Date: 2017/4/6
 * Time: 17:48
 *
 * @author Tian.Dong
 */
public class FightEndListener extends BaseListener {
    @Override
    public void doAction(GameClient gameClient, Context context) throws IOException {
        //升级模式 强制结束战斗
        if (gameClient.getTeamLeaderId() == gameClient.getUserId() || !gameClient.isJoinTeam()) {
            if (gameClient.getGameConfig().getGameConfigType().equals(GameConfigType.gainLevel)) {
                gameClient.u1021();
            } else {
                gameClient.eventOk();
            }
        }
        next();
        RoleInfo[][] roleArray = gameClient.getFightInfo().getRoleArray();
        for (int i = 0; i < roleArray.length; i++) {
            for (int j = 0; j < roleArray[i].length; j++) {
                roleArray[i][j] = null;
            }
        }
        gameClient.getFightInfo().setRoundIndex(1);
        //清流 使用物品
        gameClient.autoUseAndThrow();
        if (gameClient.getTeamLeaderId() == gameClient.getUserId()) {
            //开始清流判断
            QingliuAi.qingliu(gameClient);
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.setFightTime(new Date());
        gameClient.setFighting(false);
        gameClient.getFightLock().unlock();
        OutputUtil.output("战斗结束释放锁", gameClient);
        gameClient.refreshMainFrame("战斗结束");
    }


}
