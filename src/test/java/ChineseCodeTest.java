import org.junit.Test;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.HexStringToBytesUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/3/27
 * Time: 18:02
 *
 * @author Tian.Dong
 */
public class ChineseCodeTest {

    @Test
    public void test111() throws UnsupportedEncodingException {
        String str1 = "F4 44 51 00 10 01 3B AE 5D BB E0 A4 CE AE 4C AB 4A A9 7C A9 4D B1 69 B4 AF A6 55 A6 DB A6 B3 A4 47 AD D3 A5 A4 B6 C0 A5 5D 2C A9 D2 A5 48 A5 48 A4 57 A6 B3 B4 58 AD D3 A5 A4 B6 C0 A5 5D A9 4F 3F 3A 04 A5 7C AD D3 04 A4 AD AD D3 04 A4 4B AD D3 A4 BB AD D3 ";
        String str2 = "F4 44 51 00 10 01 3B AE 5D BB E0 A4 CE AE 4C AB 4A A9 7C A9 4D B1 69 B4 AF A6 55 A6 DB A6 B3 A4 47 AD D3 A5 A4 B6 C0 A5 5D 2C A9 D2 A5 48 A5 48 A4 57 A6 B3 B4 58 AD D3 A5 A4 B6 C0 A5 5D A9 4F 3F 3A 04 A4 BB AD D3 04 A5 7C AD D3 04 A4 AD AD D3 A4 4B AD D3";
        String str3 = "F4 44 51 00 10 01 3B AE 5D BB E0 A4 CE AE 4C AB 4A A9 7C A9 4D B1 69 B4 AF A6 55 A6 DB A6 B3 A4 47 AD D3 A5 A4 B6 C0 A5 5D 2C A9 D2 A5 48 A5 48 A4 57 A6 B3 B4 58 AD D3 A5 A4 B6 C0 A5 5D A9 4F 3F 3A 04 A5 7C AD D3 04 A4 AD AD D3 04 A4 BB AD D3 A4 4B AD D3";
        List<String> stringList = new ArrayList<>();
        stringList.add(str1);
        stringList.add(str2);
        stringList.add(str3);
        for(String str :stringList) {
            System.out.println(str);
            System.out.println(ShowUtil.showCharOrigin(str));
            byte[] bytes = HexStringToBytesUtil.hexStringToBytes(str);
            byte[] temp = new byte[bytes.length - 7];
            System.arraycopy(bytes, 7, temp, 0, bytes.length - 7);
            System.out.println(new String(temp, "big5"));
        }
    }

    @Test
    public void test() throws UnsupportedEncodingException {
        //1C AF 7D 1A 3E AE 7D
        byte[] bytes = new byte[8];
        //bytes[0] = 0x1C;
        bytes[0] = (byte) 0xAF;
        bytes[1] = 0x7D;
        bytes[2] = 0x1A;
        bytes[3] = 0x3E;
        bytes[4] = (byte) 0xAE;
        bytes[5] = 0x7D;
        bytes[6] = 0x1A;
        System.out.println(new String(bytes, "big5"));

        //BF DF
        byte[] bytes1 = new byte[2];
        bytes1[0] = (byte) 0xBF;
        bytes1[1] = (byte) 0xDF;
        System.out.println(new String(bytes1, "big5"));
    }


    //@Test
    public void testBianma() throws Exception {
        char a = '啊';
        System.out.println((int) a);

        File file = new File("C:\\Users\\tian\\Desktop\\啊.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        int by = fileInputStream.read();
        while (by != -1) {
            System.out.println(Integer.toHexString(by));
            by = fileInputStream.read();
        }
        System.out.println();

        //14 65 09 C7 12 72
        String str = "嗷大猫";
        byte[] bytes = str.getBytes("BIG5");
        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i] & 0xFF;
            System.out.println(Integer.toHexString(b));
        }
        System.out.println(string2ASCII("嗷大猫"));
    }

    // 汉字转换成区位码
    public static String string2ASCII(String chinese) {
        byte[] bs;
        String s = "";
        try {
            bs = chinese.getBytes();

            for (int i = 0; i < bs.length; i++) {
                int a = bs[i] & 0xFF;
                int q = (a - 0x80 - 0x20);
                s = s + Integer.toHexString(q + 0xA0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
