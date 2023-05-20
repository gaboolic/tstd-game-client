package tk.gbl.core.listener;

import tk.gbl.bean.Context;
import tk.gbl.core.GameClient;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.util.Map;

/**
 * Date: 2017/4/6
 * Time: 17:48
 *
 * @author Tian.Dong
 */
public class TradePetListener extends BaseListener {
    @Override
    public void doAction(GameClient gameClient, Context context) throws IOException {
        if (!gameClient.getGameConfig().isAutoTradePet()) {
            OutputUtil.output("交易宠物拒绝", gameClient, true);
        }
        gameClient.tradePet(0);
        gameClient.tradePetConfirm();
    }
}
