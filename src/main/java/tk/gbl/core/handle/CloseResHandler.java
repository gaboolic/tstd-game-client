package tk.gbl.core.handle;

import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.util.ErrorCodeUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.InputStream;

/**
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class CloseResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 02 00 00 0D
        if (data.length != 6) {
            return false;
        }
        int protocol = MultiByteToIntUtil.fromAsc(data[4], data[5]);
        if (protocol <= 73) {
            if(protocol == 19) {
                OutputUtil.output("断线：" + protocol + ":" + ErrorCodeUtil.getErrorMessage(protocol), gameClient, true);
                gameClient.close();
            } else {
                OutputUtil.output("断线：" + protocol + ":" + ErrorCodeUtil.getErrorMessage(protocol), gameClient, true);
                gameClient.clear();
            }
        }
        return false;
    }
}
