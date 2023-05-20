package tk.gbl.core.handle.move;

import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;
import tk.gbl.util.PathFindingUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2017/4/14
 * Time: 10:33
 *
 * @author Tian.Dong
 */
public class MoveHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        /**
         * F4 44 06 00 14 22 08 32 E3 2E
         */
        if (data[4] == (ResponseProtocolConstant.move.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.move.getId() & 0xFF)) {
            int fromMapId = MultiByteToIntUtil.from(data[6], data[7]);
            int toMapId = MultiByteToIntUtil.from(data[8], data[9]);
            gameClient.setCurrentMapId(toMapId);
            OutputUtil.output("u1422 move 从" + fromMapId + "进入地图" + toMapId + "(" + PathFindingUtil.getMapName(toMapId) + ")", gameClient);
            if (gameClient.getMoveLock().tryLock()) {
                gameClient.getMoveLock().unlock();
            }
            synchronized (gameClient.getMoveFarObj()) {
                gameClient.getMoveFarObj().notify();
            }
            return true;
        }

        /**
         * 0  1  2  3  4  5           9  10
         * F4 44 0D 00 0C 5D C1 01 00 E3 2E BA 04 96 00 01 00
         *
         * F4 44 0D 00 0C 60 C1 01 00 E3 2E BA 04 3E 03 05 00
         *
         * F4 44 0D 00 0C A0 BA 01 00 A4 4E 5E 01 9A 01 08 00
         */
        int protocol = data[4];
        if (protocol == (ResponseProtocolConstant.moveMapEnter.getId()) && data.length > 8) {
            gameClient.setMoveIndex(0);
            long userId = MultiByteToIntUtil.from(data[5], data[6], data[7], data[8]);
            int mapId = MultiByteToIntUtil.from(data[9], data[10]);
            if (userId != gameClient.getUserId()) {
                return true;
            }
            int x = MultiByteToIntUtil.from(data[11], data[12]);
            int y = MultiByteToIntUtil.from(data[13], data[14]);
            OutputUtil.output("moveMapEnter move 进入地图" + mapId + "(" + PathFindingUtil.getMapName(mapId) + ")" + ",坐标" + x + "," + y, gameClient);
            gameClient.setCurrentMapId(mapId);
            gameClient.getPoint().setX(x);
            gameClient.getPoint().setY(y);
            if (gameClient.getTeamLeaderId() == gameClient.getUserId() || !gameClient.isJoinTeam()) {
                gameClient.moveAfter();
            }
            if (gameClient.getMoveLock().tryLock()) {
                gameClient.getMoveLock().unlock();
            }
            synchronized (gameClient.getMoveFarObj()) {
                gameClient.getMoveFarObj().notify();
            }
            gameClient.refreshMainFrame("进入地图" + mapId);
            return true;
        }
        return false;
    }
}
