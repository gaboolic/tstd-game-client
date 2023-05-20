package tk.gbl.core.handle.fight;

import tk.gbl.bean.Position;
import tk.gbl.bean.RoleInfo;
import tk.gbl.bean.SkillSpecial;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.constant.SkillType;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.NpcIdUtil;
import tk.gbl.util.OutputUtil;
import tk.gbl.util.SkillIdUtil;

import java.io.InputStream;

/**
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class FightUseSkillResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) {
        if (!checkData(data)) {
            return false;
        }
        /**
         * F4 44 23 00 32 01 1F 00 00 01 11 2B 03 01 00 03 01 00 05 00 00 00 01 DD 00 00 01 DE 00 00 01 DF 00 00 01 E1 00 00 01
         *
         * F4 44 57 00 32 01
         *                   0F 00 03 04 10 27 01 01 00 02 01 00 01 19 09 00 01
         *                   0F 00 02 02 10 27 01 01 00 02 01 00 01 19 35 01 01
         *                   0F 00 03 01 10 27 01 01 00 02 01 00 01 19 09 00 01
         *                   0F 00 03 03 10 27 01 01 00 02 01 00 01 19 07 00 01
         *                   0F 00 03 00 10 27 01 01 00 02 01 00 01 19 07 00 01
         *
         * F4 44 57 00 32 01 0F 00 03 04 10 27 01 01 00 02 01 00 01 19 05 00 01 0F 00 02 02 10 27 01 01 00 02 01 00 01 19 35 01 01 0F 00 03 03 10 27 01 01 00 02 01 00 01 19 02 00 01 0F 00 03 01 10 27 01 01 00 02 01 00 01 19 04 00 01 0F 00 03 00 10 27 01 01 00 02 01 00 01 19 06 00 01
         *
         * F4 44 13 00 32 01 0F 00 02 02 10 27 01 01 00 02 01 00 01 19 C1 00 01
         * 0           4  5        8  9              14 15 16 17
         * F4 44 13 00 32 01 0F 00 02 02 10 27 01 01 00 02 01 00 01 19 BF 00 01  肉搏10000
         *                          宠物  肉搏        怪物             伤害
         *
         * F4 44 13 00 32 01 33 00 03 02 02 2F 08 05 00 01 01 00 01 19 25 03 01 00 00 01 00 01 19 37 02 01 00 02 01 00 01 19 48 03 01 00 03 01 00 01 19 2D 02 01 00 04 01 00 01 19 35 02 01
         *
         * F4 44 69 00 32 01 0F 00 03 04 10 27 01 01 00 02 01 00 01 19 0C 00 01
         *                   0F 00 03 01 10 27 01 01 00 02 01 00 01 19 0C 00 01
         *                   0F 00 03 00 10 27 01 01 00 02 01 00 01 19 0C 00 01
         *                   21 00 02 02 E3 2E 03 03 00 02 01 00 01 19 8F 01 01 00 01 01 00 01 19 8D 01 01 00 03 01 00 01 19 8E 01 01
         *                   0F 00 03 03 10 27 01 01 00 02 01 00 01 19 0C 00 01
         *
         * 5人合击其中1人火箭
         * F4 44 69 00 32 01
         *                   21 00 02 02 E3 2E 03 03 00 02 01 00 01 19 8F 01 01 00 01 01 00 01 19 8E 01 01 00 03 01 00 01 19 8D 01 01
         *                   0F 00 03 00 10 27 01 01 00 02 00 02 01 00 00 00 01
         *                   0F 00 03 04 10 27 01 01 00 02 01 00 01 19 01 00 01
         *                   0F 00 03 01 10 27 01 01 00 02 01 00 01 19 01 00 01
         *                   0F 00 03 03 10 27 01 01 00 02 01 00 01 19 02 00 01
         */
        if (data[4] == (ResponseProtocolConstant.u3201.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u3201.getId() & 0xFF)) {
            int pos = 6;
            int togetherCount = 0;
            while (pos < data.length) {
                int segLength = MultiByteToIntUtil.from(data[pos], data[pos + 1]);
                String position = data[pos + 2] + " " + data[pos + 3];
                int skillId = MultiByteToIntUtil.from(data[pos + 4], data[pos + 5]);

                int singleDamagePos = pos + 8;
                while (singleDamagePos < pos + (segLength + 2)) {
                    String endPosition = data[singleDamagePos] + " " + data[singleDamagePos + 1];
                    int toRow = data[singleDamagePos];
                    int toColumn = data[singleDamagePos + 1];
                    int damage = MultiByteToIntUtil.from(data[singleDamagePos + 6], data[singleDamagePos + 7]);
                    if (skillId == SkillType.tianjiangganlin.getId()
                            || skillId == SkillType.shujingxixue.getId()
                            || skillId == SkillType.miaoshuihouchun.getId()
                            || skillId == SkillType.quanzhiliao.getId()
                            || skillId == SkillType.fuhuoshu.getId()
                            ) {
                        damage = -damage;
                    }
                    RoleInfo roleInfo = gameClient.getFightInfo().getRoleArray()[toRow][toColumn];
                    int oldHp = roleInfo.getHp();
                    roleInfo.setHp(roleInfo.getHp() - damage);
                    String npcName = NpcIdUtil.getName(roleInfo.getId());
                    if (toRow == 3) {
                        npcName = roleInfo.getId() + "";
                    }
                    StringBuilder fightInfoString = new StringBuilder();
                    fightInfoString.append(position + "攻击" + endPosition + " 技能" + skillId + "(" + SkillIdUtil.getSkillName(skillId) + ")" + ",伤害" + damage + "," + npcName + ",剩余生命" + oldHp + "-" + damage + "=" + (roleInfo.getHp()));

                    boolean special = false;
                    if (singleDamagePos + 9 < pos + (segLength + 2)) {
                        if (data[singleDamagePos + 9] == 0xDD || data[singleDamagePos + 9] == 0xDF || data[singleDamagePos + 9] == 0x1A) {
                            SkillSpecial skillSpecial = new SkillSpecial(skillId);
                            roleInfo.setSkillSpecial(skillSpecial);
                            fightInfoString.append(",技能特效");
                            special = true;
                        }
                    }
                    OutputUtil.output(fightInfoString.toString(), gameClient);
                    gameClient.refreshMainFrame(fightInfoString.toString());
                    if (special) {
                        singleDamagePos += 13;
                    } else {
                        singleDamagePos += 9;
                    }
                    if (singleDamagePos < pos + (segLength + 2)) {
                        if (data[singleDamagePos] == 0xDD
                                || data[singleDamagePos] == 0xDE
                                || data[singleDamagePos] == 0xDF
                                || data[singleDamagePos] == 0xE1
                                ) {
                            singleDamagePos += 4;
                        }
                    }
                    if (singleDamagePos < pos + (segLength + 2)) {
                        if (data[singleDamagePos] == 0xDD
                                || data[singleDamagePos] == 0xDE
                                || data[singleDamagePos] == 0xDF
                                || data[singleDamagePos] == 0xE1
                                ) {
                            singleDamagePos += 4;
                        }
                    }
                    if (singleDamagePos < pos + (segLength + 2)) {
                        if (data[singleDamagePos] == 0xDD
                                || data[singleDamagePos] == 0xDE
                                || data[singleDamagePos] == 0xDF
                                || data[singleDamagePos] == 0xE1
                                ) {
                            singleDamagePos += 4;
                        }
                    }
                    if (singleDamagePos < pos + (segLength + 2)) {
                        if (data[singleDamagePos] == 0xDD
                                || data[singleDamagePos] == 0xDE
                                || data[singleDamagePos] == 0xDF
                                || data[singleDamagePos] == 0xE1
                                ) {
                            singleDamagePos += 4;
                        }
                    }
                    if (gameClient.getPosition().equals(new Position(toRow, toColumn))) {
                        gameClient.getRoleInfo().setHp(roleInfo.getHp());
                    }
                }
                pos += (segLength + 2);
                togetherCount++;
            }
            OutputUtil.output("合击数" + togetherCount, gameClient);
            gameClient.refreshMainFrame("合击数" + togetherCount);
            return true;
        }
        return false;
    }


}
