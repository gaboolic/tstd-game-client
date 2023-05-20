package tk.gbl.core.handle;

import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;
import tk.gbl.util.SkillIdUtil;

import java.io.InputStream;

/**
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class RoleInfoChangeResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) {
        if (!checkData(data)) {
            return false;
        }
        //0  1        4  5  6  7
        //F4 44 0C 00 08 01 3E 01 02 00 00 00 00 00 00 00
        //F4 44 0C 00 08 01 24 01 4B 1A 2E 00 00 00 00 00
        //F4 44 0C 00 08 01 6E 01 01 00 00 00 B1 36 00 00
        //血变成1403
        //F4 44 0C 00 08 01 19 01 7B 05 00 00 00 00 00 00
        //蓝变成668
        //F4 44 0C 00 08 01 1A 01 9C 02 00 00 00 00 00 00
        //技能点25
        //F4 44 0C 00 08 01 25 01 19 00 00 00 00 00 00 00
        //属性点50
        //F4 44 0C 00 08 01 26 01 32 00 00 00 00 00 00 00
        if (data[4] == (ResponseProtocolConstant.u0801.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0801.getId() & 0xFF)) {
            if (data[6] == 0x24 && data[7] == 0x01) {
                long exp = MultiByteToIntUtil.from(data[8], data[9], data[10], data[11]);
                long diffExp = exp - gameClient.getRoleInfo().getExp();
                gameClient.getRoleInfo().setExp(exp);
                OutputUtil.output("人物总经验" + exp + ",得到经验" + diffExp, gameClient, true);
            } else if (MultiByteToIntUtil.fromAsc(data[6], data[7]) == 0x2301) {
                int level = data[8];
                gameClient.getRoleInfo().setLevel(level);
                OutputUtil.output("人物升到" + level + "级", gameClient, true);
            } else if (MultiByteToIntUtil.fromAsc(data[6], data[7]) == 0x2501) {
                int jn = data[8];//技能点25
                gameClient.getRoleInfo().setJn(jn);
                OutputUtil.output("人物技能点" + jn + "", gameClient, true);
            } else if (MultiByteToIntUtil.fromAsc(data[6], data[7]) == 0x2601) {
                int sx = data[8];//属性点50
                gameClient.getRoleInfo().setSx(sx);
                OutputUtil.output("人物属性点" + sx + "", gameClient, true);
            } else if (data[6] == 0x6E && data[7] == 0x01) {
                int skillId = MultiByteToIntUtil.from(data[12], data[13]);
                if (skillId > 0) {
                    gameClient.getRoleInfo().getSkillList().add(skillId);
                    OutputUtil.output("学会技能" + skillId + "(" + SkillIdUtil.getSkillName(skillId) + ")", gameClient, true);
                }
            } else if (data[6] == 0x19 && data[7] == 0x01) {
                int hp = MultiByteToIntUtil.from(data[8], data[9]);
                gameClient.getRoleInfo().setHp(hp);
                OutputUtil.output("人物hp变成" + hp, gameClient);
            } else if (data[6] == 0x1A && data[7] == 0x01) {
                int mp = MultiByteToIntUtil.from(data[8], data[9]);
                gameClient.getRoleInfo().setMp(mp);
                OutputUtil.output("人物mp变成" + mp, gameClient);
            }
            return true;
        }

        //1            4  5  6  7           11
        // F4 44 0F 00 08 02 04 03 00 24 01 BD 71 24 00 00 00 00 00
        /**
         * F4 44 0F 00 08 02 04 01 00 24 01 32 01 00 00 00 00 00 00
         * F4 44 0F 00 08 02 04 01 00 23 01 05 00 00 00 00 00 00 00
         * F4 44 0F 00 08 02 04 01 00 1D 01 06 00 00 00 00 00 00 00
         * F4 44 0F 00 08 02 04 01 00 40 01 40 00 00 00 00 00 00 00
         * F4 44 0F 00 08 02 04 01 00 19 01 A8 00 00 00 00 00 00 00
         *
         * 宠物升级
         * F4 44 0F 00 08 02 04 02 00 23 01 02 00 00 00 00 00 00 00
         */
        if (data[4] == (ResponseProtocolConstant.u0802.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0802.getId() & 0xFF)) {
            int petIndex = data[7];
            RoleInfo pet = gameClient.getPetList()[petIndex];
            if (MultiByteToIntUtil.fromAsc(data[9], data[10]) == 0x2401) {
                long exp = MultiByteToIntUtil.from(data[11], data[12], data[13], data[14]);
                long diffExp = exp - gameClient.getPet().getExp();
                OutputUtil.output("宠物总经验" + exp + ",得到经验" + diffExp, gameClient, true);
                pet.setExp(exp);
            } else if (MultiByteToIntUtil.fromAsc(data[9], data[10]) == 0x2301) {
                int level = data[11];
                OutputUtil.output("宠物升到" + level + "级", gameClient, true);
                pet.setLevel(level);
            } else if (data[9] == 0x19 && data[10] == 0x01) {
                int hp = MultiByteToIntUtil.from(data[11], data[12]);
                OutputUtil.output("宠物hp变成" + hp, gameClient);
                pet.setHp(hp);
            } else if (data[9] == 0x1A && data[10] == 0x01) {
                int mp = MultiByteToIntUtil.from(data[11], data[12]);
                OutputUtil.output("宠物mp变成" + mp, gameClient);
                pet.setMp(mp);
            }
            return true;
        }
        return false;
    }


}
