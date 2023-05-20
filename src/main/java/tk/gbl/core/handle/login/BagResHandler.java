package tk.gbl.core.handle.login;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2017/4/4
 * Time: 11:07
 *
 * @author Tian.Dong
 */
public class BagResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        int bagResIndex = 0;
        if ((data[4] << 8) + data[5] != ResponseProtocolConstant.bag.getId()) {
            return false;
        }
        OutputUtil.output("BagRes::", gameClient);
        int bagByteCount = (data[bagResIndex + 3] << 8) + data[bagResIndex + 2];
        //12 byte代表一个背包格子
        BagItem[] bagItems = new BagItem[26];
        gameClient.setBagItems(bagItems);
        for (int i = bagResIndex + 6; i < bagResIndex + 6 + bagByteCount - 2; i += 12) {
            if (i >= data.length) {
                break;
            }
            int bagIndex = data[i];
            if (bagIndex > 25) {
                break;
            }
            BagItem bagItem = new BagItem();
            int id = (data[i + 2] << 8) + (data[i + 1]);
            bagItem.setId(id);
            bagItem.setCount(data[i + 3]);
            bagItem.setWastage(data[i + 4]);
            bagItems[bagIndex] = bagItem;
        }
        StringBuilder bagString = new StringBuilder();
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            bagString.append(i + " " + bagItems[i].getId() + "(" + ItemIdUtil.getItemName(bagItems[i].getId()) + ") " + bagItems[i].getCount() + "耗损" + bagItems[i].getWastage() + ";");
        }
        OutputUtil.output("物品加载完毕:" + bagString.toString(), gameClient, true);
        return true;
    }
}
