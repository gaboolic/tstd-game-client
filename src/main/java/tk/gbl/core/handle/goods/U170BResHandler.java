package tk.gbl.core.handle.goods;

import tk.gbl.bean.BagItem;
import tk.gbl.bean.Goods;
import tk.gbl.constant.EquipType;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;
import tk.gbl.util.image.Output;

import java.io.InputStream;

/**
 * 当前身上的装备
 * <p/>
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class U170BResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) {
        if (!checkData(data)) {
            return false;
        }
        //0                 6  7  8
        //F4 44 16 00 17 0B 39 4A 07 00 00 00 00 00 00 00 13 5A 00 00 00 00 00 00 00 00
        //                   白皮甲                         火箭
        //F4 44 3E 00 17 0B E3 4F 00 00 00 00 00 00 00 00 F9 4B 00 00 00 00 00 00 00 00 1C 47 01 01 6E 00 00 00 00 00 80 54 00 00 00 00 00 00 00 00 B7 57 00 00 00 00 00 00 00 00 10 5B 00 00 00 00 00 00 00 00
        //0                 娥皇头冠                       女英彩袍                       镶玉扇                         女英水袖                       娥皇云履                       龙神青鳞
        //0                  白皮甲 耗损
        //F4 44 0C 00 17 0B 39 4A 1B 00 00 00 00 00 00 00
        //F4 44 08 00 05 00 E9 5B 01 00 39 4A
        if (data[4] == (ResponseProtocolConstant.u170B.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u170B.getId() & 0xFF)) {
            int pos = 6;
            while (pos < data.length) {
                int itemId = MultiByteToIntUtil.from(data[pos], data[pos + 1]);
                int wastage = data[pos + 2];
                Goods goods = ItemIdUtil.getGoods(itemId);
                int equipType = 0;
                if (goods == null) {
                    OutputUtil.output("找不到物品，物品id " + itemId, gameClient, true);
                } else {
                    int equipTypeIndex = 1;
                    for(int i=1;i<=6;i++) {
                        if(gameClient.getEquipItems()[i] == null) {
                            equipTypeIndex = i;
                            break;
                        }
                    }
                    equipType = equipTypeIndex;
                }
                if (goods != null && goods.getEquipType() != null) {
                    if (goods.getEquipType().equals("Weapon")) {
                        equipType = EquipType.Weapon.getId();
                    } else if (goods.getEquipType().equals("Body")) {
                        equipType = EquipType.Body.getId();
                    } else if (goods.getEquipType().equals("Head")) {
                        equipType = EquipType.Head.getId();
                    } else if (goods.getEquipType().equals("Foot")) {
                        equipType = EquipType.Foot.getId();
                    } else if (goods.getEquipType().equals("Wrist")) {
                        equipType = EquipType.Wrist.getId();
                    } else if (goods.getEquipType().equals("Special")) {
                        equipType = EquipType.Special.getId();
                    }
                }
                BagItem bagItem = new BagItem();
                bagItem.setId(itemId);
                bagItem.setWastage(wastage);
                gameClient.getEquipItems()[equipType] = bagItem;
                pos += 10;
            }
            BagItem[] bagItems = gameClient.getEquipItems();
            StringBuilder bagString = new StringBuilder();
            for (int i = 1; i <= 6; i++) {
                BagItem bagItem = bagItems[i];
                if (bagItem == null) {
                    continue;
                }
                bagString.append(i + " " + bagItems[i].getId() + "(" + ItemIdUtil.getItemName(bagItems[i].getId()) + ") " + "耗损" + bagItems[i].getWastage() + ";");
            }
            OutputUtil.output("装备加载完毕:" + bagString.toString(), gameClient, true);
            return true;
        }
        return false;
    }
}
