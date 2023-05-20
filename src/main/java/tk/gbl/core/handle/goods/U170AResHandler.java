package tk.gbl.core.handle.goods;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.OutputUtil;

import java.io.InputStream;

/**
 * 拆分物品
 * <p/>
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class U170AResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) {
        if (!checkData(data)) {
            return false;
        }
        //                  6  7  8
        //F4 44 05 00 17 0A 0A 07 03
        if (data[4] == (ResponseProtocolConstant.u170A.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u170A.getId() & 0xFF)) {
            int fromIndex = data[6];
            int count = data[7];
            int toIndex = data[8];
            BagItem fromBagItem = gameClient.getBagItems()[fromIndex];
            BagItem toBagItem = gameClient.getBagItems()[toIndex];
            if (fromBagItem != null) {
                fromBagItem.setCount(fromBagItem.getCount() - count);
                if (fromBagItem.getCount() <= 0) {
                    gameClient.getBagItems()[fromIndex] = null;
                }
                if (toBagItem == null) {
                    toBagItem = new BagItem();
                    gameClient.getBagItems()[toIndex] = toBagItem;
                    toBagItem.setId(fromBagItem.getId());
                    toBagItem.setCount(count);
                } else {
                    toBagItem.setCount(toBagItem.getCount() + count);
                }
            }
            OutputUtil.output("U170A拆分物品" + fromIndex + "->" + toIndex + ":" + count, gameClient);
            return true;
        }
        return false;
    }
}
