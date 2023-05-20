package tk.gbl.core.handle.fight;

import tk.gbl.bean.Position;
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
public class FightRoleInfoHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 1C 00 0B FA 68 01 01 02 45 BE 01 00 00 00 00 00 00 00 03 02 51 00 3D 00 51 00 3D 00 01 03
        //                                  ID                        位置            血51   蓝3D
        //F4 44 1C 00 0B FA 68 01 01 02 45 BE 01 00 00 00 00 00 00 00 03 02 53 00 3F 00 40 00 3F 00 03 03
        //0              5         8  9 10             15             20    22       25       28 29 30 31
        //F4 44 34 00 0B FA 68 01 05 02 5E C1 01 00 00 00 00 00 00 00 03 01 54 00 40 00 42 00 40 00 04 02 01 02 5D C1 01 00 00 00 00 00 00 00 03 02 55 00 41 00 51 00 41 00 05 02
        //F4 44 34 00 0B FA 68
        // 05 02 5E C1 01 00 00 00 00 00 00 00 03 01 54 00 40 00 42 00 40 00 04 02
        // 01 02 5D C1 01 00 00 00 00 00 00 00 03 02 55 00 41 00 51 00 41 00 05 02
        //                         8
        //F4 44 34 00 0B FA 68 01 05 02 5E C1 01 00 00 00 00 00 00 00 03 01 56 00 42 00 05 00 42 00 06 02
        //                        01 02 5D C1 01 00 00 00 00 00 00 00 03 02 56 00 42 00 4D 00 42 00 06 02
        if (data[4] == (ResponseProtocolConstant.u0BFA.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0BFA.getId() & 0xFF)) {
            int pos = 8;
            while (true) {
                long id = MultiByteToIntUtil.from(data[pos + 2], data[pos + 3], data[pos + 4], data[pos + 5]);
                int row = data[pos + 12];
                int column = data[pos + 13];
                Position position = new Position(row, column);
                String positionStr = "(" + data[pos + 12] + "," + data[pos + 13] + ")";
                int allHP = MultiByteToIntUtil.from(data[pos + 14], data[pos + 15]);
                int allMP = MultiByteToIntUtil.from(data[pos + 16], data[pos + 17]);
                int hp = MultiByteToIntUtil.from(data[pos + 18], data[pos + 19]);
                int mp = MultiByteToIntUtil.from(data[pos + 20], data[pos + 21]);
                int level = data[pos+22];
                int type = data[pos + 23];
                RoleInfo roleInfo = new RoleInfo();
                roleInfo.setId(id);
                roleInfo.setHp(hp);
                roleInfo.setHpMax(allHP);
                roleInfo.setMp(mp);
                roleInfo.setMpMax(allMP);
                roleInfo.setPosition(position);
                roleInfo.setLevel(level);
                roleInfo.setType(type);
                gameClient.getFightInfo().getRoleArray()[row][column] = roleInfo;
                if (id == gameClient.getUserId()) {
                    gameClient.getRoleInfo().setHp(hp);
                    gameClient.getRoleInfo().setHpMax(allHP);
                    gameClient.getRoleInfo().setMp(mp);
                    gameClient.getRoleInfo().setMpMax(allMP);
                }
                OutputUtil.output("我方信息:" + id + "位置: " + positionStr + " " + hp + "/" + allHP + "---" + mp + "/" + allMP, gameClient);
                gameClient.refreshMainFrame();
                pos += 24;
                if (pos >= data.length) {
                    break;
                }
            }
            int row = data[20];
            int column = data[21];
            Position position = new Position(row, column);
            gameClient.setPosition(position);
            gameClient.fire("fightStart");
            return true;
        }
        return false;
    }


}
