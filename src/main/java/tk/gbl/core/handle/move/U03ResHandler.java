package tk.gbl.core.handle.move;

import tk.gbl.bean.Point;
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
public class U03ResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 32 00 03 5E C1 01 00 02 02 01 00 00 E3 2E F6 01 EF 01 00 06 00 BC 35 7F 1A 1C AF 7D 1A 01 39 4A 00 00 00 00 00 65 00 00 B9 C8 A4 6A D8 70 31 35 30 33 38 35
        //6B 2F                               地图
        //F4 44 2B 00 03 45 BE 01 00 02 0B 00 6B 2F 5E 01 9A 01 00 06 00 BC 35 7F 1A 1C AF 7D 1A 01 39 4A 00 00 00 00 65 00 00 B9 C8 A4 6A BF DF 31 39
        //0  1  2  3  4  5  6  7  8           12 13
        //F4 44 2D 00 03 45 2F 01 00 02 01 19 00 00 E7 2F F6 01 EF 01 00 00 00 20 CA 6C 20 3E AE 7D 1A 01 19 4D 00 00 00 00 00 65 00 00 B8 AF B4 B4 31 33 34
        //0                                                                                         南中皮甲
        //F4 44 2F 00 03 5E C1 01 00 02 00 00 CD 76 BE 02 2B 02 00 06 00 BC 35 7F 1A 1C AF 7D 1A 01 45 4D 00 00 00 00 65 00 00 B9 C8 A4 6A D8 70 31 35 30 33 38 35
        //0                                                                                         bpj    hjwz
        //F4 44 31 00 03 5E C1 01 00 02 00 00 CD 76 BE 02 2B 02 00 06 00 BC 35 7F 1A 1C AF 7D 1A 02 39 4A 13 5A 00 00 00 00 65 00 00 B9 C8 A4 6A D8 70 31 35 30 33 38 35
        if (data[4] == ResponseProtocolConstant.u03.getId()) {
            long userId = MultiByteToIntUtil.from(data[5], data[6], data[7], data[8]);
            int currentMapId = MultiByteToIntUtil.from(data[12], data[13]);
            if (currentMapId != 0 && userId == gameClient.getUserId()) {
                gameClient.setCurrentMapId(currentMapId);
                int x = MultiByteToIntUtil.from(data[14], data[15]);
                int y = MultiByteToIntUtil.from(data[16], data[17]);
                gameClient.setPoint(new Point(x, y));
                OutputUtil.output("u03 " + userId + "进入地图" + currentMapId, gameClient, false);
                if (gameClient.getMoveLock().tryLock()) {
                    gameClient.getMoveLock().unlock();
                }
            }
            return true;
        }
        return false;
    }
}
