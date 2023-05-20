package tk.gbl.core.handle.goods;

import tk.gbl.bean.BagItem;
import tk.gbl.bean.Goods;
import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.EquipType;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.NpcIdUtil;
import tk.gbl.util.OutputUtil;

import java.io.InputStream;

/**
 * Date: 2017/6/4
 * Time: 14:06
 *
 * @author Tian.Dong
 */
public class EquipResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) {
        if (!checkData(data)) {
            return false;
        }
        //0                 6  7  8
        //F4 44 04 00 17 10 02 01
        //F4 44 04 00 17 10 02 08
        //卸下装备
        if (data[4] == (ResponseProtocolConstant.u1710.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1710.getId() & 0xFF)) {
            int equipType = data[6];
            int bagIndex = data[7];

            BagItem bagItem = gameClient.getEquipItems()[equipType];
            gameClient.getBagItems()[bagIndex] = bagItem;
            gameClient.getEquipItems()[equipType] = null;
            OutputUtil.output("卸下装备 " + equipType + "到背包格子" + bagIndex + "," + bagItem.getId() + "(" + ItemIdUtil.getItemName(bagItem.getId()) + ")", gameClient, true);
            return true;
        }

        //0                 6  7  8
        //F4 44 05 00 17 16 02 01 0F
        //宠物卸下装备
        if (data[4] == (ResponseProtocolConstant.u1716.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1716.getId() & 0xFF)) {
            int petIndex = data[6];
            int equipType = data[7];
            int bagIndex = data[8];

            RoleInfo pet = gameClient.getPetList()[petIndex];
            BagItem bagItem = pet.getEquipItems()[equipType];//宠物装备
            gameClient.getBagItems()[bagIndex] = bagItem;//背包=宠物装备
            pet.getEquipItems()[equipType] = null;//宠物装备=null
            OutputUtil.output("宠物" + petIndex + "(" + NpcIdUtil.getName(pet.getId()) + ")" + "卸下装备 " + equipType + "到背包格子" + bagIndex + "," + bagItem.getId() + "(" + ItemIdUtil.getItemName(bagItem.getId()) + ")", gameClient, true);
            return true;
        }
        //0  1              6
        //F4 44 03 00 17 11 08
        //装备物品
        if (data[4] == (ResponseProtocolConstant.u1711.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1711.getId() & 0xFF)) {
            int bagIndex = data[6];
            BagItem bagItem = gameClient.getBagItems()[bagIndex];
            Goods goods = ItemIdUtil.getGoods(bagItem.getId());
            int equipType = 0;
            if (goods.getEquipType() != null) {
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
            BagItem oldEquipItem = gameClient.getEquipItems()[equipType];
            gameClient.getEquipItems()[equipType] = bagItem;
            gameClient.getBagItems()[bagIndex] = null;
            if (oldEquipItem != null) {
                gameClient.getBagItems()[bagIndex] = oldEquipItem;
            }
            OutputUtil.output("装备物品 " + bagIndex + " " + bagItem.getId() + "(" + ItemIdUtil.getItemName(bagItem.getId()) + ")", gameClient, true);
            return true;
        }


        //0  1              6
        //F4 44 04 00 17 17 02 03
        //F4 44 04 00 17 17 02 05
        //宠物装备物品
        if (data[4] == (ResponseProtocolConstant.u1717.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1717.getId() & 0xFF)) {
            int petIndex = data[6];
            int bagIndex = data[7];
            BagItem bagItem = gameClient.getBagItems()[bagIndex];
            Goods goods = ItemIdUtil.getGoods(bagItem.getId());
            int equipType = 0;
            if (goods.getEquipType() != null) {
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
            RoleInfo pet = gameClient.getPetList()[petIndex];
            BagItem oldEquipItem = pet.getEquipItems()[equipType];
            pet.getEquipItems()[equipType] = bagItem;
            gameClient.getBagItems()[bagIndex] = null;
            if (oldEquipItem != null) {
                gameClient.getBagItems()[bagIndex] = oldEquipItem;
            }
            OutputUtil.output("宠物" + petIndex + "(" + NpcIdUtil.getName(pet.getId()) + ")" + "装备物品 " + bagIndex + " " + bagItem.getId() + "(" + ItemIdUtil.getItemName(bagItem.getId()) + ")", gameClient, true);
            return true;
        }
        return false;
    }
}
