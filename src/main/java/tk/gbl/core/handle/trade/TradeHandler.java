package tk.gbl.core.handle.trade;

import tk.gbl.bean.Context;
import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * 接收别人发起的交易处理
 * <p/>
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class TradeHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 06 00 19 0A 5D C1 01 00
        if (data[4] == (ResponseProtocolConstant.tradePetRequest.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.tradePetRequest.getId() & 0xFF)) {
            long userId = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            OutputUtil.output(userId + "tradePetRequest ", gameClient);
            Context context = new Context();
            context.setFromUserId(userId);
            gameClient.fire("tradePetRequest", context);
            return true;
        }

        //F4 44 06 00 19 01 5D C1 01 00
        if (data[4] == (ResponseProtocolConstant.tradeRequest.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.tradeRequest.getId() & 0xFF)) {
            long userId = MultiByteToIntUtil.from(data[6], data[7], data[8], data[9]);
            OutputUtil.output(userId + "发起交易请求 ",gameClient);
            Context context = new Context();
            context.setFromUserId(userId);
            gameClient.fire("tradeRequest", context);
            return true;
        }

        //F4 44 1C 00 19 03 00 00 00 00 05 5A 01 00 00 00 00 00 00 00 00 00 2B 01 00 00 00 00 00 00 00 00
        if (data[4] == (ResponseProtocolConstant.tradeConfirm.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.tradeConfirm.getId() & 0xFF)) {
            OutputUtil.output("交易确认 ",gameClient);
            return true;
        }

        //F4 44 03 00 19 02 03 交易完成
        //F4 44 03 00 19 02 04 交易取消
        if (data[4] == (ResponseProtocolConstant.tradeDone.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.tradeDone.getId() & 0xFF)) {
            if(data[6] == 0x04) {
                OutputUtil.output("交易完成 ", gameClient);
            } else {
                OutputUtil.output("交易取消 ", gameClient);
            }
            return true;
        }
        return false;
    }


}
