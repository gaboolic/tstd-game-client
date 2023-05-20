package tk.gbl.core.handle.goods;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.InputStream;

/**
 * 失去物品
 * <p/>
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class LossItemResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 04 00 17 09 05 32
        if (data[4] == (ResponseProtocolConstant.u1709.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1709.getId() & 0xFF)) {
            int index = data[6];
            int count = data[7];
            BagItem bagItem = gameClient.getBagItems()[index];
            String itemName = "";
            if (bagItem != null) {
                itemName = ItemIdUtil.getItemName(bagItem.getId());
                bagItem.setCount(bagItem.getCount() - count);
                if (bagItem.getCount() <= 0) {
                    gameClient.getBagItems()[index] = null;
                }
            }
            OutputUtil.output("u1709失去物品 " + itemName + index + " " + count, gameClient);
            return true;
        }

        /**
         * 0           4  5        8
         * F4 44 05 00 17 07 D4 84 05
         * F4 44 05 00 17 07 44 9C 05
         * F4 44 05 00 17 07 64 98 01
         */
        if (data[4] == (ResponseProtocolConstant.u1707.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1707.getId() & 0xFF)) {
            int count = data[8];
            int reduceCount = count;
            int itemId = MultiByteToIntUtil.from(data[6], data[7]);
            BagItem[] bagItems = gameClient.getBagItems();
            if (bagItems != null) {
                for (int i = 1; i <= 25; i++) {
                    BagItem bagItem = bagItems[i];
                    if (bagItem == null) {
                        continue;
                    }
                    if (bagItem.getId() == itemId) {
                        if (bagItem.getCount() >= reduceCount) {
                            bagItem.setCount(bagItem.getCount() - reduceCount);
                            reduceCount = 0;
                        } else {
                            bagItem.setCount(0);
                            reduceCount -= bagItem.getCount();
                        }
                    }
                    if (bagItem.getCount() <= 0) {
                        gameClient.getBagItems()[i] = null;
                    }
                    if (reduceCount <= 0) {
                        break;
                    }
                }
            }
            OutputUtil.output("u1707任务失去物品 " + itemId + "(" + ItemIdUtil.getItemName(itemId) + ")" + count, gameClient);
            return true;
        }
        return false;
    }
}
