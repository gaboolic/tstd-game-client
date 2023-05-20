package tk.gbl.core.handle;

import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
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
public class U0B00ResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        /**
         * F4 44 08 00 0B 00 45 BE 01 00 00 00
         * 0  1  2  3        6  7  8  9
         */
        if (data[4] == (ResponseProtocolConstant.u0B00.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0B00.getId() & 0xFF)) {
            long userId = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            OutputUtil.output("u0B00 " + userId + " ", gameClient, false);
            return true;
        }
        return false;
    }


}
