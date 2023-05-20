package tk.gbl.core.handle.move;

import tk.gbl.bean.Point;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * F4 44 0D 00 0C 76 A2 00 00 E9 4E 9A 01 9A 01 02 00
 * Date: 2017/6/4
 * Time: 11:42
 *
 * @author Tian.Dong
 */
public class U0CResHandler  extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 0D 00 0C 76 A2 00 00 E9 4E 9A 01 9A 01 02 00
        //6B 2F                      地图
        //0  1  2  3  4  5  6  7  8        11 12 13
        if (data[4] == ResponseProtocolConstant.u0C.getId()) {
            long userId = MultiByteToIntUtil.from(data[5], data[6], data[7], data[8]);
            int currentMapId = MultiByteToIntUtil.from(data[9], data[10]);
            if (currentMapId != 0 && userId == gameClient.getUserId()) {
                gameClient.setCurrentMapId(currentMapId);
                int x = MultiByteToIntUtil.from(data[11], data[12]);
                int y = MultiByteToIntUtil.from(data[13], data[14]);
                gameClient.setPoint(new Point(x, y));
                OutputUtil.output("u0C " + userId + "进入地图" + currentMapId, gameClient, false);
                if (gameClient.getMoveLock().tryLock()) {
                    gameClient.getMoveLock().unlock();
                }
                synchronized (gameClient.getMoveFarObj()) {
                    gameClient.getMoveFarObj().notify();
                }
            }
            return true;
        }
        return false;
    }
}
