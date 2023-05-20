package tk.gbl.core.handle;

import org.junit.Test;
import tk.gbl.core.GameClient;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.HexStringToBytesUtil;

import java.io.IOException;

/**
 * Date: 2017/5/15
 * Time: 18:22
 *
 * @author Tian.Dong
 */
public class RoleInfoResHandlerTest {
    @Test
    public void test() throws IOException {
        GameClient gameClient = new GameClient();
        String res = "F4 44 AA 00 05 03 01 33 05 9A 01 37 01 00 00 00 00 20 00 3D 00 00 00 C8 8A 54 89 0E 00 00 00 00 3B 97 BF 09 33 05 9A 01 F6 FF FF FF 03 00 00 00 94 00 00 00 1B 00 00 00 96 00 00 00 96 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 A2 07 00 00 00 00 00 00 00 11 27 01 12 27 01 13 27 01 14 27 0A 15 27 01 16 27 01 17 27 0A 18 27 01 1A 27 0A 1B 27 0A 1C 27 04 1D 27 0A 1F 27 05 F9 2A 01 FA 2A 01 FC 2A 01 B1 36 01 C7 36 01 D2 36 01";
        byte[] bytes = HexStringToBytesUtil.hexStringToBytes(res);
        int[] data = ByteArrayToIntArrayUtil.transform(bytes, bytes.length);
        System.out.println(ShowUtil.show(res));
        TotalHandler totalHandler = new TotalHandler();
        totalHandler.newHandle(0, data, null, gameClient);
        System.out.println();
    }
}
