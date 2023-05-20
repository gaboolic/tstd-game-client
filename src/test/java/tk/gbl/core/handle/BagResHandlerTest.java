package tk.gbl.core.handle;

import org.junit.Test;
import tk.gbl.bean.Position;
import tk.gbl.core.GameClient;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.HexStringToBytesUtil;

import java.io.IOException;

/**
 * F4 44 DA 00 17 05 01 0C 7D 04 00 00 00 00 00 00 00 00 02 19 4D 01 00 00 00 00 00 00 00 00 03 39 B5 01 00 00 00 00 00 00 00 00 04 BA 65 02 00 00 00 00 00 00 00 00 05 BA 65 32 00 00 00 00 00 00 00 00 06 54 B4 01 00 00 00 00 00 00 00 00 07 30 66 01 00 00 00 00 00 00 00 00 08 0A 5A 01 00 00 00 00 00 00 00 00 09 D4 B3 05 00 00 00 00 00 00 00 00 0A 05 5A 01 00 00 00 00 00 00 00 00 0B C1 36 01 00 00 00 00 00 00 00 00 0C 48 49 01 00 00 00 00 00 00 00 00 0D 30 66 32 00 00 00 00 00 00 00 00 0E 30 66 32 00 00 00 00 00 00 00 00 0F BA 65 32 00 00 00 00 00 00 00 00 10 30 66 32 00 00 00 00 00 00 00 00 11 BA 65 32 00 00 00 00 00 00 00 00 12 1F 79 01 00 00 00 00 00 00 00 00
 [2017-05-14 13:20:26] BagRes::
 [2017-05-14 13:20:26] F4 44 A5 00 17 8D 01 01 00 00 00 00 00 00 00 00 02 00 00 00 00 00 00 00 00 03 00 00 00 00 00 00 00 00 04 00 00 00 00 00 00 00 00 05 00 00 00 00 00 00 00 00 06 00 00 00 00 00 00 00 00 07 00 00 00 00 00 00 00 00 08 00 00 00 00 00 00 00 00 09 00 00 00 00 00 00 00 00 0A 00 00 00 00 00 00 00 00 0B 00 00 00 00 00 00 00 00 0C 00 00 00 00 00 00 00 00 0D 00 00 00 00 00 00 00 00 0E 00 00 00 00 00 00 00 00 0F 00 00 00 00 00 00 00 00 10 00 00 00 00 00 00 00 00 11 00 00 00 00 00 00 00 00 12 00 00 00 00 00 00 00 00
 [2017-05-14 13:20:26] 得到物品31022(恶魔兑换券),位置:1,数量:1当前数量:5
 [2017-05-14 13:20:26] F4 44 0C 00 17 0B 39 4A 01 00 00 00 00 00 00 00
 [2017-05-14 13:20:26] 115575req:F4 44 0B 00 41 01 43 41 44 42 00 00 00 00 00
 [2017-05-14 13:20:26] 没匹配到处理器
 [2017-05-14 13:20:26] F4 44 0D 00 17 8D 08 00 02 00 00 00 00 00 00 00 00
 [2017-05-14 13:20:26] 得到物品31022(恶魔兑换券),位置:0,数量:8当前数量:1

 * Date: 2017/5/14
 * Time: 13:30
 *
 * @author Tian.Dong
 */
public class BagResHandlerTest {
    @Test
    public void testBagRes() throws IOException {
        String res = "F4 44 DA 00 17 05 01 0C 7D 04 00 00 00 00 00 00 00 00 02 19 4D 01 00 00 00 00 00 00 00 00 03 39 B5 01 00 00 00 00 00 00 00 00 04 BA 65 02 00 00 00 00 00 00 00 00 05 BA 65 32 00 00 00 00 00 00 00 00 06 54 B4 01 00 00 00 00 00 00 00 00 07 30 66 01 00 00 00 00 00 00 00 00 08 0A 5A 01 00 00 00 00 00 00 00 00 09 D4 B3 05 00 00 00 00 00 00 00 00 0A 05 5A 01 00 00 00 00 00 00 00 00 0B C1 36 01 00 00 00 00 00 00 00 00 0C 48 49 01 00 00 00 00 00 00 00 00 0D 30 66 32 00 00 00 00 00 00 00 00 0E 30 66 32 00 00 00 00 00 00 00 00 0F BA 65 32 00 00 00 00 00 00 00 00 10 30 66 32 00 00 00 00 00 00 00 00 11 BA 65 32 00 00 00 00 00 00 00 00 12 1F 79 01 00 00 00 00 00 00 00 00";
//        String res = "59 E9 AA AD 98 BD AF AC AE EE AD 59 E9 A9 AD A6 AC AF AF 59 E9 A5 AD A6 AD 6E 17 AD AD AD AD 59 E9 A8 AD A6 AC AE AF AD 59 E9 A2 AD A5 AF A9 AE AD B4 AC 6C A2 AD AD AD AD AD AD ";
        GameClient gameClient = new GameClient();
        gameClient.setIndex(1);
        gameClient.setPosition(new Position());
        byte[] bytes = HexStringToBytesUtil.hexStringToBytes(res);
        System.out.println(bytes.length);
        int[] data = ByteArrayToIntArrayUtil.transform(bytes, bytes.length);
        TotalHandler totalHandler = new TotalHandler();
        totalHandler.newHandle(0, data, null, gameClient);
    }
}
