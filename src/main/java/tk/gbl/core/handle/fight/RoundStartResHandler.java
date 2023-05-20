package tk.gbl.core.handle.fight;

import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class RoundStartResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 04 00 35 03 00 02
        if (data[4] == (ResponseProtocolConstant.u3401.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u3401.getId() & 0xFF)) {
            OutputUtil.output("回合开始?3401 ", gameClient);
            gameClient.fire("roundStart");
            return true;
        }
        return false;
    }


}
