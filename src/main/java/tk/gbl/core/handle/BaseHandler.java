package tk.gbl.core.handle;

import tk.gbl.core.GameClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2017/3/29
 * Time: 16:18
 *
 * @author Tian.Dong
 */
public abstract class BaseHandler {
    public abstract boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException;

    protected boolean checkData(int[] data) {
        if (data.length <= 2) {
            return false;
        }
        if (data[0] == 0xF4
                && data[1] == 0x44) {
            return true;
        }
        return false;
    }
}
