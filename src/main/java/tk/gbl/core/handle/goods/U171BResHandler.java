package tk.gbl.core.handle.goods;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.EquipType;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Date: 2017/6/24
 * Time: 16:32
 *
 * @author Tian.Dong
 */
public class U171BResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //0  1              6  7
        //F4 44 04 00 17 1B 03 02
        if (data[4] == (ResponseProtocolConstant.u171B.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u171B.getId() & 0xFF)) {
            int equipType = data[6];
            int wastage = data[7];
            BagItem equipItem = gameClient.getEquipItems()[equipType];

            if (equipItem != null) {
                equipItem.setWastage(wastage);
                OutputUtil.output("装备" + EquipType.getByCode(equipType) + " "
                        + equipItem.getId() + "(" + ItemIdUtil.getItemName(equipItem.getId()) + ")"
                        + "损耗" + wastage
                        , gameClient, true);
            } else {
                OutputUtil.output("装备" + EquipType.getByCode(equipType) + " "
                        + "损耗" + wastage
                        , gameClient, true);
            }
            if (wastage > 240) {
                OutputUtil.output("装备损耗超过240断线", gameClient, true);
                gameClient.close();
            }
            return true;
        }
        return false;
    }
}
