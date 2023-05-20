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
public class U0500ResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is,GameClient gameClient) {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 0E 00 05 00 0E B1 01 00 17 51 FB 4C CC 54 B4 58
        if (data[4] == (ResponseProtocolConstant.u0500.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0500.getId() & 0xFF)) {
            return true;
        }
        return false;
    }


}
