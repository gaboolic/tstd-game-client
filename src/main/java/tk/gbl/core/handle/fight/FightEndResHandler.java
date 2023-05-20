package tk.gbl.core.handle.fight;

import tk.gbl.bean.Position;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * 战斗结束
 * <p/>
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class FightEndResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        /**
         * 0                 6  7
         * F4 44 05 00 0B 01 03 02 00
         * F4 44 04 00 0B 01 02 01
         */
        if (data[4] == (ResponseProtocolConstant.u0B01.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0B01.getId() & 0xFF)) {
            OutputUtil.output("战斗结束!" + ShowUtil.showOrigin(data), gameClient, false);
            Position position = new Position(data[6], data[7]);
            if (gameClient.getPosition().equals(position)) {
                OutputUtil.output("自己战斗结束?u0B01 ", gameClient);
                gameClient.fire("fightEnd");
            }
            return true;
        }
        return false;
    }


}
