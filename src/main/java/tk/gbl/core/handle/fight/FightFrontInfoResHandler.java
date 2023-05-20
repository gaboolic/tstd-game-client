package tk.gbl.core.handle.fight;

import tk.gbl.bean.Position;
import tk.gbl.bean.RoleInfo;
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
 * 战场信息
 * <p/>
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class FightFrontInfoResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 1A 00 0B 05 05 02 C2 BA 00 00 00 00 00 00 00 00 03 04 20 14 68 01 20 14 68 01 C8 03
        //
        //621 26D    212  D4 2
        //F4 44 1A 00 0B 05 05 04 DC B0 00 00 02 00 45 BE 01 00 02 02 6D 02 D4 00 6D 02 D4 00 02 04
        //                                                       位置   血    蓝
        //F4 44 1A 00 0B 05 01 07 FC 55 00 00 03 00 00 00 00 00 00 02 12 00 43 00 12 00 43 00 01 00
        //F4 44 1A 00 0B 05 01 07 FC 55 00 00 02 00 00 00 00 00 00 02 12 00 43 00 12 00 43 00 01 00
        //            4  5                                      18 19
        //福神衰神                  NPC
        //F4 44 1A 00 0B 05 07 07 C9 3A 00 00 04 00 00 00 00 00 00 02 DC 54 74 08 DC 54 74 08 C8 04
        //北斗星君                 15049
        //F4 44 1A 00 0B 05 05 02 5E C1 01 00 00 00 00 00 00 00 03 01 56 00 42 00 01 00 42 00 06 02
        //F4 44 1A 00 0B 05 05 02 5F C1 01 00 00 00 00 00 00 00 03 03 55 00 41 00 55 00 41 00 05 02
        //F4 44 1A 00 0B 05 01 07 FC 55 00 00 04 00 00 00 00 00 00 02 12 00 43 00 12 00 43 00 01 00
        //0  1        4  5        8   9                            19 20                      28
        if (data[4] == (ResponseProtocolConstant.u0B05.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0B05.getId() & 0xFF)) {
            long npcId = MultiByteToIntUtil.from(data[8], data[9], data[10], data[11]);
            long userId = MultiByteToIntUtil.from(data[14], data[15], data[16], data[17]);
            int row = data[18];
            int column = data[19];
            Position position = new Position(row, column);
            int hpMax = MultiByteToIntUtil.from(data[20], data[21]);
            int mpMax = MultiByteToIntUtil.from(data[22], data[23]);
            int hp = MultiByteToIntUtil.from(data[24], data[25]);
            int mp = MultiByteToIntUtil.from(data[26], data[27]);
            int level = data[28];
            int type = data[29];
            if (userId == 0) {
                if (row == 2 || row == 3) {
                    OutputUtil.output("友" + npcId + " " + type + " 位置" + row + " " + column + "hp:" + hp + "/" + hpMax + ",mp:" + mp + "/" + mpMax + ",等级" + level, gameClient);
                } else {
                    OutputUtil.output("怪" + npcId + "(" + NpcIdUtil.getName(npcId) + type + ")" + "位置" + row + " " + column + "hp:" + hp + "/" + hpMax + ",mp:" + mp + "/" + mpMax + ",等级" + level, gameClient);
                }
            } else {
                OutputUtil.output("宠" + npcId + "(" + NpcIdUtil.getName(npcId) + type + ")" + "位置" + row + " " + column + "hp:" + hp + "/" + hpMax + ",mp:" + mp + "/" + mpMax + ",等级" + level, gameClient);
            }
            RoleInfo roleInfo = new RoleInfo();
            roleInfo.setId(npcId);
            roleInfo.setHp(hp);
            roleInfo.setHpMax(hpMax);
            roleInfo.setMp(mp);
            roleInfo.setMpMax(mpMax);
            roleInfo.setLevel(level);
            roleInfo.setType(type);
            roleInfo.setPosition(position);
            gameClient.getFightInfo().getRoleArray()[row][column] = roleInfo;
            if (npcId == 15049) {
                OutputUtil.output("北斗星君问问题来了", gameClient);
            }
            return true;
        }
        return false;
    }
}
