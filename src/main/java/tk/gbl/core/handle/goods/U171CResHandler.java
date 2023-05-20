package tk.gbl.core.handle.goods;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.EquipType;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2017/6/24
 * Time: 16:32
 *
 * @author Tian.Dong
 */
public class U171CResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //0  1              6  7
        //F4 44 05 00 17 1C 02 04 05
        //F4 44 05 00 17 1C 01 02 29
        if (data[4] == (ResponseProtocolConstant.u171C.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u171C.getId() & 0xFF)) {
            int petIndex = data[6];
            int equipType = data[7];
            int wastage = data[8];
            BagItem equipItem = gameClient.getPetList()[petIndex].getEquipItems()[equipType];

            if (equipItem != null) {
                equipItem.setWastage(wastage);
                OutputUtil.output("武将装备" + EquipType.getByCode(equipType) + " "
                        + equipItem.getId() + "(" + ItemIdUtil.getItemName(equipItem.getId()) + ")"
                        + "损耗" + wastage
                        , gameClient, true);
            } else {
                OutputUtil.output("武将装备" + EquipType.getByCode(equipType) + " "
                        + "损耗" + wastage
                        , gameClient, true);
            }
            if (wastage > 240) {
                OutputUtil.output("武将装备损耗超过240断线", gameClient, true);
                gameClient.close();
            }
            return true;
        }
        return false;
    }
}
