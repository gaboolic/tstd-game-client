package tk.gbl.core.listener;

import tk.gbl.bean.BagItem;
import tk.gbl.bean.Context;
import tk.gbl.config.GameConfig;
import tk.gbl.core.GameClient;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * Date: 2017/4/6
 * Time: 17:48
 *
 * @author Tian.Dong
 */
public class ItemAppearListener extends BaseListener {
    @Override
    public void doAction(GameClient gameClient, Context context) throws IOException {
        int itemId = (int) context.getParam().get("itemId");
        if (!gameClient.getGameConfig().isAutoPickupItem()) {
            OutputUtil.output("不自动捡取物品" + itemId, gameClient, false);
            return;
        }

        if (gameClient.getGameConfig().getPickupItemIdList().contains(itemId)) {
            if (itemId == 29235) {
                int huobaCount = gameClient.getItemCount(29235);
                if (huobaCount > 0) {
                    return;
                }
            }
            BagItem[] sceneItems = gameClient.getSceneItems();
            for (int i = 1; i < sceneItems.length; i++) {
                if (sceneItems[i] == null) {
                    continue;
                }
                if (sceneItems[i].getId() == itemId) {
                    OutputUtil.output("尝试捡取" + itemId + ",序号" + i, gameClient);
                    gameClient.pickUp(i);
                    break;
                }
            }
            int itemCount = gameClient.getItemCount(itemId);
            if (itemCount > 0) {
                return;
            }
            for (int i = sceneItems.length - 1; i >= 0; i--) {
                if (sceneItems[i] == null) {
                    continue;
                }
                if (sceneItems[i].getId() == itemId) {
                    OutputUtil.output("尝试捡取" + itemId + ",序号" + i, gameClient);
                    gameClient.pickUp(i);
                    break;
                }
            }
        }
    }
}
