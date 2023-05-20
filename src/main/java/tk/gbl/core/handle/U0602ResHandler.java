package tk.gbl.core.handle;

import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.show.ShowUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class U0602ResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is,GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 02 00 06 02
        if (data[4] == (ResponseProtocolConstant.u0602.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u0602.getId() & 0xFF)) {
            return true;
        }
        return false;
    }


}
