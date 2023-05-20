package tk.gbl.core.handle.goods;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * 钱庄
 * Date: 2017/5/24
 * Time: 8:10
 *
 * @author Tian.Dong
 */
public class StoreBagResHandler extends BaseHandler {

    //                  6
    //F4 44 F2 00 1E 01 01 D4 84 32 00 00 00 00 00 00 00 00 02 D4 84 32 00 00 00 00 00 00 00 00 03 D4 84 32 00 00 00 00 00 00 00 00 04 D4 84 32 00 00 00 00 00 00 00 00 05 D4 84 32 00 00 00 00 00 00 00 00 06 D4 84 32 00 00 00 00 00 00 00 00 07 D4 84 32 00 00 00 00 00 00 00 00 08 D4 84 32 00 00 00 00 00 00 00 00 09 D4 84 32 00 00 00 00 00 00 00 00 0A D4 84 32 00 00 00 00 00 00 00 00 0B D4 84 32 00 00 00 00 00 00 00 00 0C D4 84 32 00 00 00 00 00 00 00 00 0D D4 84 32 00 00 00 00 00 00 00 00 0E D4 84 32 00 00 00 00 00 00 00 00 0F D4 84 32 00 00 00 00 00 00 00 00 10 D4 84 32 00 00 00 00 00 00 00 00 11 D4 84 32 00 00 00 00 00 00 00 00 12 D4 84 32 00 00 00 00 00 00 00 00 13 D4 84 32 00 00 00 00 00 00 00 00 14 D4 84 32 00 00 00 00 00 00 00 00
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        if (data[4] == (ResponseProtocolConstant.u1E01.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1E01.getId() & 0xFF)) {

            int bagResIndex = 0;
            int bagByteCount = (data[bagResIndex + 3] << 8) + data[bagResIndex + 2];
            //12 byte代表一个背包格子
            BagItem[] bagItems = new BagItem[50];
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
                bagItems[bagIndex] = bagItem;
            }
            StringBuilder bagString = new StringBuilder();
            for (int i = 1; i <= 25; i++) {
                BagItem bagItem = bagItems[i];
                if (bagItem == null) {
                    continue;
                }
                bagString.append(i + " " + bagItems[i].getId() + "(" + ItemIdUtil.getItemName(bagItems[i].getId()) + ") " + bagItems[i].getCount() + ";");
            }
            OutputUtil.output("物品加载完毕:" + bagString.toString(), gameClient);
            return true;
        }
        return false;
    }
}
