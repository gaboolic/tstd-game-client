package tk.gbl.core.handle.login;

import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class RoleInfoResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 77 00 05 03 02 48 00 42 00 00 00 00 00 00 00 06 00 00 00 00 00 06 95 01 00 00 05 00 0A 00 02 01 00 00 56 00 42 00 00 00 00 00 03 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 F4 01 F4 01 F4 01 F4 01 F4 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 C7 36 01 D2 36 01
        //F4 44 77 00 05 03 02 52 00 3E 00 00 00 00 00 00 00 06 00 00 00 00 00 02 17 00 00 00 01 00 02 00 09 00 00 00 52 00 3E 00 00 00 00 00 03 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 F4 01 F4 01 F4 01 F4 01 F4 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 C7 36 01 D2 36 01
        //                     7  8  9  10                                     23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16
        //F4 44 9B 00 05 03 04 74 02 7C 00 01 00 2D 00 00 00 20 00 32 00 00 00 40 52 16 2E 00 08 00 05 00 89 6F 2F 00 A1 02 7C 00 11 00 00 00 08 00 00 00 00 00 00 00 18 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 F4 01 00 00 6E 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 C9 32 01 CA 32 01 CB 32 05 CC 32 01 CD 32 05 CF 32 05 D2 32 01 B1 36 01 B2 36 01 B3 36 01 B4 36 01 B5 36 01 C7 36 01 D2 36 01
        //                                 智力   攻击   防御   敏捷  体质  能量  dj   经验      jn    sx
        //                                  1     45     0     32    50    0
        //F4 44 AA 00 05 03 01 33 05 9A 01 37 01 00 00 00 00 20 00 3D 00 00 00 C8 8A 54 89 0E 00 00 00 00 3B 97 BF 09 33 05 9A 01 F6 FF FF FF 03 00 00 00 94 00 00 00 1B 00 00 00 96 00 00 00 96 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 A2 07 00 00 00 00 00 00 00
        //11 27 01 12 27 01 13 27 01 14 27 0A 15 27 01 16 27 01 17 27 0A 18 27 01 1A 27 0A 1B 27 0A 1C 27 04 1D 27 0A 1F 27 05 F9 2A 01 FA 2A 01 FC 2A 01 B1 36 01 C7 36 01 D2 36 01

        if (data[4] == (ResponseProtocolConstant.u0503.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0503.getId() & 0xFF)) {
            int type = data[6];
            int hp = MultiByteToIntUtil.from(data[7], data[8]);
            int mp = MultiByteToIntUtil.from(data[9], data[10]);
            int zl = MultiByteToIntUtil.from(data[11], data[12]);
            int atk = MultiByteToIntUtil.from(data[13], data[14]);
            int def = MultiByteToIntUtil.from(data[15], data[16]);
            int agi = MultiByteToIntUtil.from(data[17], data[18]);
            int hpx = MultiByteToIntUtil.from(data[19], data[20]);
            int spx = MultiByteToIntUtil.from(data[21], data[22]);
            int level = data[23];
            long exp = MultiByteToIntUtil.from(data[24], data[25], data[26], data[27]);
            int jn = data[28];
            int sx = data[30];
            int hpMax = MultiByteToIntUtil.from(data[36], data[37]);
            int mpMax = MultiByteToIntUtil.from(data[38], data[39]);

            RoleInfo roleInfo = gameClient.getRoleInfo();
            roleInfo.setType(type);
            roleInfo.setHp(hp);
            roleInfo.setMp(mp);
            roleInfo.setHpMax(hpMax);
            roleInfo.setMpMax(mpMax);
            roleInfo.setLevel(level);
            roleInfo.setExp(exp);
            roleInfo.setJn(jn);
            roleInfo.setSx(sx);
            roleInfo.setZl(zl);
            roleInfo.setAtk(atk);
            roleInfo.setDef(def);
            roleInfo.setAgi(agi);
            roleInfo.setHpx(hpx);
            roleInfo.setSpx(spx);
            int jnListPos = 117;
            while (jnListPos+1 < data.length) {
                int skillId = MultiByteToIntUtil.from(data[jnListPos], data[jnListPos + 1]);
                roleInfo.getSkillList().add(skillId);
                jnListPos += 3;
            }
            OutputUtil.output("人的血量蓝量" + "属性" + type + "等级" + level + ",经验" + exp
                    + "技能/属性点" + jn + "/" + sx
                    + ",血" + hp + "/" + hpMax + ",蓝" + mp + "/" + mpMax, gameClient,true);
            OutputUtil.output("人的技能列表:" + roleInfo.getSkillList(),gameClient);
            if (sx > 0) {
                if (gameClient.getPassword().equals("123456")) {
                    int requireSxCount = 50 - hpx;
                    int addCount = Math.min(sx, requireSxCount);
                    gameClient.addShuxing(0x1F, addCount);
                }
            }
            return true;
        }


        //F4 44 0A 00 1A 04 7A 26 54 00 00 00 00 00
        if (data[4] == (ResponseProtocolConstant.u1A04.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1A04.getId() & 0xFF)) {
            long money = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            gameClient.getRoleInfo().setMoney(money);
            OutputUtil.output("金币数量" + money, gameClient);
            gameClient.fire("loginSuccess");
            return true;
        }

        //F4 44 06 00 1A 01 64 00 00 00
        if (data[4] == (ResponseProtocolConstant.u1A01.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1A01.getId() & 0xFF)) {
            long money = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            gameClient.getRoleInfo().setMoney(gameClient.getRoleInfo().getMoney() + money);
            if (money > 0) {
                OutputUtil.output("得到金币" + money + ",当前金币" + gameClient.getRoleInfo().getMoney(), gameClient);
            }
            return true;
        }

        //F4 44 06 00 1A 02 98 0D 00 00
        if (data[4] == (ResponseProtocolConstant.u1A02.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1A02.getId() & 0xFF)) {
            long money = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            gameClient.getRoleInfo().setMoney(gameClient.getRoleInfo().getMoney() - money);
            if (money > 0) {
                OutputUtil.output("失去金币" + money + ",当前金币" + gameClient.getRoleInfo().getMoney(), gameClient);
            }
            return true;
        }
        return false;
    }


}
