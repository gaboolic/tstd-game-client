package tk.gbl.core.handle.chat;

import tk.gbl.bean.Context;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2017/6/16
 * Time: 17:57
 *
 * @author Tian.Dong
 */
public class ServerChatHandler extends BaseHandler {
    /**
     * F4 44 6A 00 17 39 4D C2 F9 B8 67 AC A1 B0 CA B6 7D A9 6C C5 6F A1 49 A7 59 A4 E9 B0 5F A6 DC 32 30 31 37 2F 36 2F 31 36 20 20 32 33 3A 35 39 3A 35 39 A6 62 BD 6D A5 5C B0 CF A5 B4 A9 C7 A9 D2 C0 F2 B1 6F AA BA B8 67 C5 E7 AD C8 BC 57 A5 5B A6 DC 35 30 30 25 A1 41 A4 6A AE 61 A7 56 A4 4F B5 A5 AF C5 A4 40 AA BD BD C4 A7 61 A1 49
     */
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //0                 6           10
        //F4 44 0C 00 02 02 5D C1 01 00 31 32 33 34 35 36
        int protocol = MultiByteToIntUtil.fromAsc(data[4], data[5]);
        if (protocol == ResponseProtocolConstant.u1739.getId()
                || protocol == ResponseProtocolConstant.uC706.getId()) {
            int[] temp = new int[data.length - 10];
            System.arraycopy(data, 10, temp, 0, data.length - 10);
            byte[] bytes = ByteArrayToIntArrayUtil.transform(temp, temp.length);
            String chatString = new String(bytes, "big5");
            OutputUtil.output("服务器公告:" + chatString, gameClient, true);
            return true;
        }
        return false;
    }
}
