package tk.gbl.core.handle.createrole;

import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2017/4/1
 * Time: 17:31
 *
 * @author Tian.Dong
 */
public class SetPropertiesAndInitResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 02 00 00 12
        //F4 44 02 00 09 01
        if (data[4] == 0x00 && data[5] == 0x12) {
            OutputUtil.output("SetPropertiesAndInitRes 失败", gameClient);
            return true;
        }
        if (data[4] == 0x09 && data[5] == 0x01) {
            OutputUtil.output("SetPropertiesAndInitRes 成功", gameClient);
            gameClient.confirmInit();
            gameClient.setIdCard();
            return true;
        }
        return false;
    }
}
