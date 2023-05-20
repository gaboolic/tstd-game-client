package tk.gbl.core.handle.login;

import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.OutputUtil;

import java.io.InputStream;

/**
 * Date: 2017/3/29
 * Time: 18:11
 *
 * @author Tian.Dong
 */
public class ConnectResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) {
        if (data.length == 8 && checkData(data)) {
            //F4 44 04 00 01 09 65 00
            if (data[2] == 0x04
                    && data[3] == 0x00
                    && data[4] == 0x01
                    && data[5] == 0x09
                    && data[6] == 0x65
                    && data[7] == 0x00) {
                OutputUtil.output("connect success!", gameClient);
                return true;
            }
        }
        return false;
    }


}
