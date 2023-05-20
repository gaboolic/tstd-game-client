package tk.gbl.core.handle.fight;

import tk.gbl.bean.Position;
import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * F4 44 05 00 18 05 32 01 01
 * <p/>
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class U1805ResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 05 00 18 05 32 01 01
        //0  1        4  5        8   9                            19 20                      28
        if (data[4] == (ResponseProtocolConstant.u1805.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1805.getId() & 0xFF)) {
            OutputUtil.output("u1805是什么东西????", gameClient, false);
            if (data[8] == 0x01) {
//                gameClient.eventOk();
            }
            return true;
        }
        return false;
    }
}
