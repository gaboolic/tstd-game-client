package tk.gbl.core.handle.login;

import tk.gbl.bean.BagItem;
import tk.gbl.bean.RoleInfo;
import tk.gbl.config.GameConfig;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.NpcIdUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * 宠物信息
 * <p/>
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class PetInfoResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }

        //0                 6
        //F4 44 6D 00 0F 08 01 DC B0 06 00 00 00 01 47 02 C3 00 2F 00 86 00 44 00 4C 00 43 00 2A 00 00 3C 01 00 00 08 A8 67 A1 45 A4 6A B3 EC 01 01 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
        //F4 44 D8 00 0F 08 01 DC B0 06 00 00 00 01 47 02 C3 00 2F 00 86 00 44 00 4C 00 43 00 2A 00 00 3C 01 00 00 08 A8 67 A1 45 A4 6A B3 EC 01 01 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 DB B0 06 00 00 00 01 11 02 D3 00 89 00 27 00 40 00 4A 00 3A 00 32 00 00 3C 01 00 00 08 A8 67 A1 45 A4 70 B3 EC 01 01 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
        if (data[4] == (ResponseProtocolConstant.u0F08.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0F08.getId() & 0xFF)) {
            OutputUtil.output("u0F08所有宠物信息", gameClient);
            int pos = 6;
            while (pos < data.length) {
                int index1 = pos;
                int length = data[pos +29];
                parse(data, index1, gameClient);
                pos += length + 99;
            }
            for (int i = 1; i < gameClient.getPetList().length; i++) {
                RoleInfo pet = gameClient.getPetList()[i];
                if (pet != null) {
                    OutputUtil.output(i + " " + pet, gameClient);
                }
            }
            if (gameClient.getPetList() != null) {
                for (RoleInfo pet : gameClient.getPetList()) {
                    if (pet == null) {
                        continue;
                    }
                    if (gameClient.getGameConfig().getHorseIdList().contains(pet.getId())) {
                        OutputUtil.output("!有马" + pet.getId(), gameClient);
                        gameClient.shangma(pet.getId());
                    }
                }
            }
            return true;
        }


        //0  1        4  5  6              11 12
        //F4 44 0C 00 0F 01 6B C3 01 00 03 5D 46 00 00 01
        //                  115563

        //F4 44 0C 00 0F 01 C3 BA 00 00 02 6B 45 00 00 01
        //得到宠物 位置2     47811
        //F4 44 0C 00 0F 01 C3 BA 00 00 02 6B 45 00 00 01

        //失去宠物           47812
        //F4 44 0C 00 0F 01 C4 BA 00 00 04 6B 45 00 00 01
        //
        //F4 44 0C 00 0F 01 ED E3 00 00 02 E6 B0 00 00 01
        if (data[4] == (ResponseProtocolConstant.u0F01.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0F01.getId() & 0xFF)) {
            long userId = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            int petIndex = data[10];
            int npcId = MultiByteToIntUtil.from(data[11], data[12]);
            OutputUtil.output("u0F01  " + userId + "得到" + +npcId + "(" + NpcIdUtil.getName(npcId) + ")", gameClient, true);
            if (userId == gameClient.getUserId()) {
                RoleInfo pet = new RoleInfo();
                pet.setId(npcId);
                pet.setIndex(petIndex);
                gameClient.getPetList()[petIndex] = pet;
                OutputUtil.output("u0F01得到宠物 " + npcId + "(" + NpcIdUtil.getName(npcId) + ")" + ",位置" + petIndex, gameClient, true);
            }
            return true;
        }

        //F4 44 07 00 0F 02 6F A4 00 00 01
        if (data[4] == (ResponseProtocolConstant.u0F02.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0F02.getId() & 0xFF)) {
            long userId = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            int petIndex = data[10];
            OutputUtil.output("u0F02  " + userId + "失去宠物，序号" + petIndex, gameClient, true);
            if (userId == gameClient.getUserId()) {
                RoleInfo oldPet = gameClient.getPetList()[petIndex];
                gameClient.getPetList()[petIndex] = null;
                if (gameClient.getPet() != null && gameClient.getPet().getId() == oldPet.getId()) {
                    gameClient.setPet(null);
                }
                OutputUtil.output("u0F02失去宠物 " + oldPet.getId() + "(" + NpcIdUtil.getName(oldPet.getId()) + ")" + ",位置" + petIndex, gameClient, true);
            }
            return true;
        }

        //F4 44 02 00 13 02
        if (data[4] == (ResponseProtocolConstant.u1302.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1302.getId() & 0xFF)) {
            OutputUtil.output("u1302武将休息 ", gameClient, true);
            gameClient.setPet(null);
            return true;
        }

        //F4 44 06 00 13 04 DC B0 00 00
        if (data[4] == (ResponseProtocolConstant.u1304.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1304.getId() & 0xFF)) {
            OutputUtil.output("u1304 ", gameClient);
            long id = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            OutputUtil.output("出战武将" + id, gameClient, true);
            for (RoleInfo pet : gameClient.getPetList()) {
                if (pet == null) {
                    continue;
                }
                if (pet.getId() == id) {
                    gameClient.setPet(pet);
                }
            }
            if (gameClient.getPet() == null) {
                RoleInfo pet = new RoleInfo();
                pet.setId(id);
                gameClient.setPet(pet);
            }
            return true;
        }

        //F4 44 06 00 13 01 DA 3B 00 00
        if (data[4] == (ResponseProtocolConstant.u1301.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1301.getId() & 0xFF)) {
            OutputUtil.output("u1301 ", gameClient);
            long id = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            OutputUtil.output("出战武将" + id, gameClient, true);
            for (RoleInfo pet : gameClient.getPetList()) {
                if (pet == null) {
                    continue;
                }
                if (pet.getId() == id) {
                    gameClient.setPet(pet);
                }
            }
            if (gameClient.getPet() == null) {
                RoleInfo pet = new RoleInfo();
                pet.setId(id);
                gameClient.setPet(pet);
            }
            return true;
        }
        return false;
    }

    public int indexOf(int[] data, int number, int start) {
        for (int i = start + 90; i < Math.min(data.length, i + 115); i++) {
            if (data[i] == number) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 0                    7  8  9  10 11 12 13 14    16    18    20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36
     * 02 DA 3B 18 62 11 00 32 01 00 CE 00 16 00 62 00 39 00 66 00 37 00 12 00 00 63 01 16 00 04 A8 F5 AC 65 01 01 01
     * 21 4E 05 00 00 00 00 00 00 00
     * 00 00 00 00 00 00 00 00 00 00
     * 12 27 06 00 00 00 00 00 00 00
     * 09 52 07 00 00 00 00 00 00 00
     * 82 57 07 00 00 00 00 00 00 00
     * 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
     */
    public void parse(int[] data, int pos, GameClient gameClient) {
        int index = data[pos];
        int id = MultiByteToIntUtil.from(data[pos + 1], data[pos + 2]);
        long exp = MultiByteToIntUtil.from(data[pos + 3], data[pos + 4], data[pos + 5], data[pos + 6]);
        int level = data[pos + 7];
        int hp = MultiByteToIntUtil.from(data[pos + 8], data[pos + 9]);
        int mp = MultiByteToIntUtil.from(data[pos + 10], data[pos + 11]);
        int zl = MultiByteToIntUtil.from(data[pos + 12], data[pos + 13]);
        int atk = MultiByteToIntUtil.from(data[pos + 14], data[pos + 15]);
        int def = MultiByteToIntUtil.from(data[pos + 16], data[pos + 17]);
        int agi = MultiByteToIntUtil.from(data[pos + 18], data[pos + 19]);
        int hpx = MultiByteToIntUtil.from(data[pos + 20], data[pos + 21]);
        int spx = MultiByteToIntUtil.from(data[pos + 22], data[pos + 23]);
        int length = data[pos+29];
        RoleInfo pet = new RoleInfo();
        pet.setId(id);
        pet.setExp(exp);
        pet.setLevel(level);
        pet.setHp(hp);
        pet.setMp(mp);
        pet.setZl(zl);
        pet.setAtk(atk);
        pet.setDef(def);
        pet.setAgi(agi);
        pet.setHpx(hpx);
        pet.setSpx(spx);
        pet.setIndex(index);
        for (int i = 1; i <= 6; i++) {
            int equipPos = pos + 33 + length + (i - 1) * 10;
            int itemId = MultiByteToIntUtil.from(data[equipPos],data[equipPos+1]);
            if(itemId == 0) {
                continue;
            }
            int wastage = data[equipPos+2];
            BagItem equipItem = new BagItem();
            equipItem.setId(itemId);
            equipItem.setWastage(wastage);
            equipItem.setCount(1);
            pet.getEquipItems()[i] = equipItem;
        }
        gameClient.getPetList()[index] = pet;
    }
}
