package tk.gbl.core.handle.goods;

import tk.gbl.bean.BagItem;
import tk.gbl.bean.Context;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 得到东西
 * Date: 2017/4/1
 * Time: 17:31
 *
 * @author Tian.Dong
 */
public class PickUpResHandler extends BaseHandler {

    BagItem theItem;
    int itemId;
    int count;

    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 05 00 17 02 02 00 01
        //F4 44 0E 00 17 06 BC 65 01 00 00 00 00 00 00 00 00 00
        //F4 44 0C 00 17 8D 01 18 00 00 00 00 00 00 00 00
        //   1  2  3  4  5                       13 14 15 16 17       20 21 22 23          27 28       31 32 33 34
        if (data[4] == (ResponseProtocolConstant.pickUp.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.pickUp.getId() & 0xFF)) {
            int index = data[6];
            BagItem bagItem = gameClient.getSceneItems()[index];
            gameClient.getSceneItems()[index] = null;
            if (bagItem != null && gameClient.getGameConfig().getNoshowItemIdList().contains(bagItem.getId())) {
                OutputUtil.output("pickup 有人在捡东西:" + (bagItem == null ? "" : bagItem.getId()) + ",index " + index, gameClient,false);
            } else {
                OutputUtil.output("pickup 有人在捡东西:" + (bagItem == null ? "" : bagItem.getId()) + ",index " + index, gameClient);
            }
            return true;
        }

        //F4 44 08 00 17 03 44 9C 5C 01 A4 01
        if (data[4] == (ResponseProtocolConstant.throwAway.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.throwAway.getId() & 0xFF)) {
            int itemId = MultiByteToIntUtil.from(data[6], data[7]);
            int x = MultiByteToIntUtil.from(data[8], data[9]);
            int y = MultiByteToIntUtil.from(data[10], data[11]);
            BagItem[] sceneItems = gameClient.getSceneItems();
            BagItem bagItem = new BagItem();
            bagItem.setId(itemId);
            bagItem.setX(x);
            bagItem.setY(y);
            for (int i = 1; i < sceneItems.length; i++) {
                if (sceneItems[i] == null) {
                    bagItem.setIndex(i);
                    sceneItems[i] = bagItem;
                    break;
                }
            }
            if (gameClient.getGameConfig().getNoshowItemIdList().contains(itemId)) {
                OutputUtil.output("物品出现 " + itemId + "(" + ItemIdUtil.getItemName(itemId) + ")" + "(" + x + "," + y + ")", gameClient, false);
            } else {
                OutputUtil.output("物品出现 " + itemId + "(" + ItemIdUtil.getItemName(itemId) + ")" + "(" + x + "," + y + ")", gameClient);
            }
            gameClient.setGetItemTime(new Date());
            Context context = new Context();
            context.getParam().put("itemId", itemId);
            gameClient.fire("itemAppear", context);
            return true;
        }
        if (data[4] == (ResponseProtocolConstant.u1706.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1706.getId() & 0xFF)) {
            itemId = (data[7] << 8) + data[6];
            count = data[8];
            OutputUtil.output("u1706东西是" + itemId + "," + count + "个", gameClient);
            return true;
        }

        /**
         * F4 44 0E 00 17 08 03 21 29 01 DC 04 6E 00 00 00 00 00
         *
         * F4 44 0E 00 17 08 03 28 5A 01 00 00 00 00 00 00 00 00
         *                   6  7  8  9
         * F4 44 0E 00 17 08 04 05 5A 01 00 00 00 00 00 00 00 00
         */
        if (data[4] == (ResponseProtocolConstant.u1708.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1708.getId() & 0xFF)) {
            itemId = MultiByteToIntUtil.from(data[7], data[8]);
            count = data[9];
            int wastage = data[10];
            theItem = new BagItem();
            theItem.setId(itemId);
            theItem.setCount(count);
            theItem.setWastage(wastage);
            OutputUtil.output("u1708东西是" + itemId + "," + count + "个,损耗"+wastage, gameClient);
            return true;
        }

        /**
         * F4 44 0C 00 17 8D 01 10 00 00 00 00 00 00 00 00
         * F4 44 0C 00 17 8D 01 18 00 00 00 00 00 00 00 00
         * F4 44 39 00 17 8D 01 01 00 00 00 00 00 00 00 00 02 00 00 00 00 00 00 00 00 03 00 00 00 00 00 00 00 00 04 00 00 00 00 00 00 00 00 05 00 00 00 00 00 00 00 00 11 00 00 00 00 00 00 00 00
         * F4 44 39 00 17 8D 01
         * 01 00 00 00 00 00 00 00 00
         * 02 00 00 00 00 00 00 00 00
         * 03 00 00 00 00 00 00 00 00
         * 04 00 00 00 00 00 00 00 00
         * 05 00 00 00 00 00 00 00 00
         * 11 00 00 00 00 00 00 00 00
         得到物品0,位置:1,数量:1当前数量:51
         得到物品F4 44 0D 00 17 8D 08 00 02 00 00 00 00 00 00 00 00
         0,位置:0,数量:8当前数量:1
         */
        if (data[4] == (ResponseProtocolConstant.u178D.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u178D.getId() & 0xFF)) {
            if (data[6] == 0x09) {
                return false;
            }
            if (data[6] != 0x01) {
                return false;
            }
            int bagCount = data[6];
            int bagIndex = data[7];
            if (bagCount == 0 || bagIndex == 0) {
                return false;
            }
            if (itemId == 0) {
                return false;
            }

            BagItem[] bagItems = gameClient.getBagItems();
            if (bagItems == null) {
                bagItems = new BagItem[26];
                gameClient.setBagItems(bagItems);
            }
            BagItem bagItem = bagItems[bagIndex];
            if (bagItem == null || bagItem.getId() != itemId) {
                bagItem = new BagItem();
                bagItem.setId(itemId);
                bagItems[bagIndex] = bagItem;
            }
            if(theItem != null) {
                bagItem.setWastage(theItem.getWastage());
            }
            bagItem.setCount(bagItem.getCount() + count);
            if (gameClient.getGameConfig().getNoshowItemIdList().contains(itemId)) {
                OutputUtil.output("得到物品" + itemId + "(" + ItemIdUtil.getItemName(itemId) + ")" + ",位置:" + bagIndex + ",数量:" + count + "当前数量:" + bagItem.getCount(), gameClient, false);
            } else {
                OutputUtil.output("得到物品" + itemId + "(" + ItemIdUtil.getItemName(itemId) + ")" + ",位置:" + bagIndex + ",数量:" + count + "当前数量:" + bagItem.getCount(), gameClient, true);
            }
            if (gameClient.getGameConfig().isAutoMergeBag()) {
                gameClient.mergeBag();
                gameClient.showBag();
            }
            Integer gainItemCount = gameClient.getGameParam().getGainBagItemCountMap().get(itemId);
            if(gainItemCount == null) {
                gainItemCount = 0;
            }
            gameClient.getGameParam().getGainBagItemCountMap().put(itemId,gainItemCount+1);

            gameClient.setGetItemTime(new Date());
            itemId = 0;
            count = 1;
            return true;
        }
        return false;
    }
}
