package tk.gbl.core.handle;

import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.show.ShowUtil;

import java.io.InputStream;

/**
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class U0101ResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) {
        if (!checkData(data)) {
            return false;
        }
        /**
         *F4 44 06 00 01 01 D4 BB 01 00 
         *F4 44 06 00 01 01 E2 B9 01 00
         *F4 44 06 00 01 01 B7 B9 01 00
         *F4 44 06 00 01 01 52 BA 01 00
         */
        if (data[4] == (ResponseProtocolConstant.u0101.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0101.getId() & 0xFF)) {
            return true;
        }
        return false;
    }


}
