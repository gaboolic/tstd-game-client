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
public class U1808ResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) {
        if (!checkData(data)) {
            return false;
        }
        /**
         *没匹配到处理器F4 44 09 00 18 08 1A 2E 01 00 02 00 01
         没匹配到处理器F4 44 09 00 18 08 8E E7 00 00 02 00 01
         没匹配到处理器F4 44 09 00 18 08 8D E7 00 00 02 00 01
         没匹配到处理器F4 44 09 00 18 08 92 2F 01 00 02 00 01
         没匹配到处理器F4 44 09 00 18 08 93 2F 01 00 02 00 0
         */
        if (data[4] == (ResponseProtocolConstant.u1808.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1808.getId() & 0xFF)) {
            return true;
        }
        return false;
    }


}
