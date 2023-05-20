package tk.gbl.core.handle.goods;

import tk.gbl.MainTank;
import tk.gbl.bean.BagItem;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.InputStream;
import java.util.Date;

/**
 * 场景里的物品
 * <p/>
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class U1704ResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) {
        if (!checkData(data)) {
            return false;
        }
        //0  1              6  7     9  10
        //F4 44 18 00 17 04 03 01 00 88 69 00 00 9A 02 D1 00 03 02 00 B1 66 00 00 CA 02 E9 00
        if (data[4] == (ResponseProtocolConstant.u1704.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1704.getId() & 0xFF)) {
            BagItem[] sceneItems = gameClient.getSceneItems();
            for(int i=0;i<sceneItems.length;i++) {
                sceneItems[i] = null;
            }
            int pos = 6;
            while (pos < data.length) {
                int index = data[pos + 1];
                int itemId = MultiByteToIntUtil.from(data[pos + 3], data[pos + 4]);
                int x = MultiByteToIntUtil.from(data[pos + 7], data[pos + 8]);
                int y = MultiByteToIntUtil.from(data[pos + 9], data[pos + 10]);
                BagItem bagItem = new BagItem();
                bagItem.setId(itemId);
                bagItem.setX(x);
                bagItem.setY(y);
                sceneItems[index] = bagItem;
                String str = "场景里的物品%d %d(%s) %d,%d";
                if(gameClient.getGameConfig().getNoshowItemIdList().contains(itemId)) {
                    OutputUtil.output(String.format(str, index, itemId, ItemIdUtil.getItemName(itemId), x, y), gameClient,false);
                } else {
                    OutputUtil.output(String.format(str, index, itemId, ItemIdUtil.getItemName(itemId), x, y), gameClient);
                }
                pos += 11;
            }
            gameClient.setGetItemTime(new Date());
            return true;
        }
        return false;
    }
}
