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
 * F4 44 0C 00 02 02 5D C1 01 00 31 32 33 34 35 36
 * Date: 2017/5/3
 * Time: 16:29
 *
 * @author Tian.Dong
 */
public class ChatHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //0                 6           10
        //F4 44 0C 00 02 02 5D C1 01 00 31 32 33 34 35 36
        int protocol = MultiByteToIntUtil.fromAsc(data[4], data[5]);
        if (protocol == ResponseProtocolConstant.chat.getId()
                || protocol == ResponseProtocolConstant.chatMi.getId()) {
            long userId = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            int[] temp = new int[data.length - 10];
            System.arraycopy(data, 10, temp, 0, data.length - 10);
            byte[] bytes = ByteArrayToIntArrayUtil.transform(temp, temp.length);
            String chatString = new String(bytes, "big5");
            OutputUtil.output("chat:" + userId + ":" + chatString, gameClient);
            Context context = new Context();
            context.setFromUserId(userId);
            context.getParam().put("content", chatString);
            gameClient.fire("chat", context);
            return true;
        }
        return false;
    }
}

