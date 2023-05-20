package tk.gbl.core.listener;

import tk.gbl.bean.Context;
import tk.gbl.core.GameClient;

import java.io.IOException;
import java.util.Map;

/**
 * Date: 2017/4/6
 * Time: 17:48
 *
 * @author Tian.Dong
 */
public class TradeItemListener extends BaseListener {
    @Override
    public void doAction(GameClient gameClient, Context context) throws IOException {
        gameClient.tradeItem(gameClient.getTradeMoney(), gameClient.getTradeItemIndexList());
        gameClient.tradeConfirm();
        gameClient.getTradeItemIndexList().clear();
    }
}
