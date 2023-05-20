import org.junit.Test;
import tk.gbl.core.handle.TotalHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.HexStringToBytesUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/4/4
 * Time: 20:25
 *
 * @author Tian.Dong
 */
public class ZhandouTest {
    /**
     * 简雍
     * <p/>
     * F4 44 0A 00 32 01 03 02 03 02 69 42 08 D4
     * ô  D        2                 i  B     Ô
     * F4 44 0A 00 32 01 03 02 03 02 69 42 09 E8
     * ô  D        2                 i  B     è
     * F4 44 0A 00 32 01 03 02 00 02 10 27 76 C3
     */
    @Test
    public void testPK1() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A4 AD A6 AF AE AA 82 AD AD AC AD ");//PK 1
        reqList.add("59 E9 A7 AD 9F AC AE AF AE AF C4 EF A5 79 ");//防御
        reqList.add("59 E9 A7 AD 9F AC AE AF AE AF C4 EF A4 45 ");//防御
        reqList.add("59 E9 A7 AD 9F AC AE AF AD AF BD 8A DB 6E ");//肉搏

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testPK(){
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A4 AD A6 AF AE AA 82 AD AD AC AD ");//PK 1
        reqList.add("59 E9 A4 AD A6 AF AE 25 86 AD AD AC AD ");//PK jj
        reqList.add("59 E9 A4 AD A6 AF AE 4B 9B AD AD AE AD");//PK cg

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 稻草人
     * F4 44 0A 00 32 01 03 02 00 02 10 27 29 ED
     * ô  D        2                    '  )  í
     * F4 44 0A 00 32 01 03 02 00 02 10 27 00 0E
     * ô  D        2                    '
     * F4 44 0A 00 32 01 03 02 00 02 10 27 18 64
     * ô  D        2                    '     d
     * F4 44 0A 00 32 01 03 02 00 02 10 27 76 C3
     * ô  D        2                    '  v  Ã
     * <p/>
     * F4 44 0A 00 32 01 03 02 02 02 CB 32 20 7F 闪躲13003
     * ô  D        2      谁    宠物  Ë  2     
     * F4 44 0A 00 32 01 02 02 02 02 69 42 48 4C 防御17001
     * ô  D        2            宠物  i  B  H  L
     * F4 44 0A 00 32 01 03 02 03 02 51 46 6A 3F
     * ô  D        2            自己  Q  F  j  ?
     * F4 44 0A 00 32 01 02 02 02 02 51 46 19 E8
     * ô  D        2            宠物  Q  F     è
     */
    @Test
    public void testDaocaoren() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD B9 AC AC AD ");//点击稻草人
        reqList.add("59 E9 A7 AD 9F AC AE AF AD AF BD 8A 84 40 ");//肉搏
        reqList.add("59 E9 A7 AD 9F AC AE AF AD AF BD 8A AD A3 ");//肉搏
        reqList.add("59 E9 A7 AD 9F AC AE AF AD AF BD 8A B5 C9 ");//肉搏
        reqList.add("59 E9 A7 AD 9F AC AE AF AD AF BD 8A DB 6E ");//肉搏


        reqList.add("59 E9 A7 AD 9F AC AE AF AF AF 66 9F 8D D2 ");//闪躲宠物
        reqList.add("59 E9 A7 AD 9F AC AF AF AF AF C4 EF E5 E1 ");//防御宠物

        reqList.add("59 E9 A7 AD 9F AC AE AF AE AF FC EB C7 92 ");//逃跑
        reqList.add("59 E9 A7 AD 9F AC AF AF AF AF FC EB B4 45 ");//逃跑

        reqList.add("59 E9 A9 AD 98 A8 AF AF 59 E9 A9 AD A6 AC AF AF 59 E9 A5 AD A6 AD 0D 17 AC AD AD AD 59 E9 A8 AD A6 AC AE AF AD ");//res
        reqList.add("59 E9 AF AD B9 AB ");//肉搏
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 3个稻草人
     * F4 44 0A 00 32 01 03 02 00 01 10 27 22 51
     * ô  D        2                    '  "  Q
     * F4 44 0A 00 32 01 02 02 00 02 10 27 47 CA
     * 肉搏
     * F4 44 0A 00 32 01 03 02 03 02 69 42 10 5E
     * ô  D        2            自己  防御     ^
     * F4 44 0A 00 32 01 02 02 00 03 10 27 26 EE
     */
    @Test
    public void test3GeDaocaoren() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD B9 AC AE AD ");//点击稻草人
        reqList.add("59 E9 AF AD AB AF 59 E9 B1 AD A6 57 C5 AE AC AF 0D 17 AC AD AD AD AD AD AD AD AE AF 0C AF D1 AD 92 AD D1 AD ED A9 59 E9 A8 AD A6 A7 AC AD AD 59 E9 B7 AD A6 A8 A8 A9 FE EB AD AD AF AD 0D 17 AC AD AF AF 24 AD E0 AD 24 AD E0 AD AC AC 59 E9 B7 AD A6 A8 AC AA 51 F8 AD AD AE AD AD AD AD AD AD AC BF AD EE AD BF AD EE AD AC AD 59 E9 B7 AD A6 ");//位置信息
        reqList.add("59 E9 A7 AD 9F AC AE AF AD AC BD 8A 8F FC ");//人肉搏
        reqList.add("59 E9 A7 AD 9F AC AF AF AD AF BD 8A EA 67 ");//宠物肉搏
        reqList.add("59 E9 A1 AD A5 AC 93 AC BE AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC 92 AC 75 C4 82 AD AD AD AD AD 59 E9 A1 AD A5 AC 9F AC AD AD AD AD AD AD AD AD 59 E9 A9 AD 98 AE AD AC 59 E9 A1 AD A5 AC 93 AC BE AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC 92 AC 74 C4 82 AD AD AD AD AD 59 E9 A1 AD A5 AC 9F AC AD AD AD AD AD AD AD AD 59 E9 AF AD 99 AC ");//怪物信息

        reqList.add("59 E9 A7 AD 9F AC AE AF AE AF C4 EF BD F3 ");//防御
        reqList.add("59 E9 A7 AD 9F AC AF AF AD AE BD 8A 8B 43 ");//肉搏
        reqList.add("59 E9 AF AD B9 AB ");//结束
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 1个人打3个稻草人
     * F4 44 0A 00 32 01 03 02 00 03 10 27 4A AB
     * ô  D        2              左1 技能  J  «
     * F4 44 0A 00 32 01 03 02 00 02 10 27 38 E2   肉搏10000
     * ô  D        2                    '  8  â
     * F4 44 0A 00 32 01 03 02 00 01 10 27 56 F5
     * <p/>
     * F4 44 0A 00 32 01 03 02 03 02 69 42 2B 62   防御17001
     * ô  D        2                 i  B  +  b
     * F4 44 0A 00 32 01 03 02 03 02 69 42 26 95
     * ô  D        2                 i  B  &  
     */
    @Test
    public void test1da3GeDaocaoren() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD B9 AC AE AD ");//点击稻草人
        reqList.add("59 E9 A7 AD 9F AC AE AF AD AE BD 8A E7 06 ");//肉搏左1
        reqList.add("59 E9 A7 AD 9F AC AE AF AD AF BD 8A 95 4F ");//肉搏中间
        reqList.add("59 E9 A7 AD 9F AC AE AF AD AC BD 8A FB 58 ");//肉搏右边

        reqList.add("59 E9 A7 AD 9F AC AE AF AE AF C4 EF 86 CF ");//防御
        reqList.add("59 E9 A7 AD 9F AC AE AF AE AF C4 EF 8B 38 ");//防御
        reqList.add("59 E9 AF AD B9 AB ");//结束

        reqList.add("59 E9 BE AD 9F AC A2 AD AF AF BD 8A AC AC AD AF AC AD AC B4 12 AD AC ");

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testChuzhanXiuxi() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AB AD BE AC FE EB AD AD ");//出战黄骠马 18003
        reqList.add("59 E9 AF AD BE AF ");//休息黄骠马

        reqList.add("59 E9 AB AD BE AC 56 83 AD AD ");//出战赵云 12027
        reqList.add("59 E9 AF AD BE AF ");//休息赵云

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testZhandoujieshuRes() {
        List<String> reqList = new ArrayList<>();
        //战斗结束
        reqList.add("59 E9 A1 AD A5 AC 93 AC BE AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC 92 AC 7B C4 82 AD AD AD AD AD 59 E9 A1 AD A5 AC 9F AC AD AD AD AD AD AD AD AD 59 E9 A9 AD 98 AE AD AF 59 E9 A9 AD A6 AC AF AF 59 E9 A5 AD A6 AD 0D 17 AC AD AD AD 59 E9 A8 AD A6 AC AE AF AD ");
        reqList.add("59 E9 A1 AD A5 AC 93 AC BE AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC 92 AC 7A C4 82 AD AD AD AD AD 59 E9 A1 AD A5 AC 9F AC AD AD AD AD AD AD AD AD 59 E9 A2 AD A5 AF A9 AF AD 89 AC A5 AD AD AD AD AD AD AD 59 E9 A9 AD A6 AC AF AF 59 E9 A5 AD A6 AD 0D 17 AC AD AD AD 59 E9 A8 AD A6 AC AE AF AD ");

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }

    }


    @Test
    public void testAAA() {
        List<String> reqList = new ArrayList<>();

        reqList.add("59 E9 AF AD B9 A5 ");
        reqList.add("59 E9 AF AD 99 AC ");
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }


    @Test
    public void testBeidou() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A7 AD 9F AC AF AF AD AF BD 8A AA C7 ");
        reqList.add("59 E9 AB AD BD AC AC AD AD AD ");
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 3108919
     * 17 - 4B 1A 2E
     * 22 - BD 71 24
     *
     * F4 44 0F 00 08 02 04 03 00 24 01 BD 71 24 00 00 00 00 00
     * F4 44 0C 00 08 01 24 01 4B 1A 2E 00 00 00 00 00
     */
    //@Test
    public void testJingyan() throws IOException {
        List<String> reqList = new ArrayList<>();
        reqList.add("");
        reqList.add("");
        reqList.add("");
        reqList.add("");
        reqList.add("");
        reqList.add("");
        reqList.add("");
//        reqList.add("59 E9 A7 AD 9F AC AE AF AD AF BD 8A AD AD ");
//        reqList.add("59 E9 A7 AD 9F AC AF AF AD AF BD 8A AD AD ");
        reqList.add("59 E9 A9 AD 98 A8 AE AF ");
        reqList.add("59 E9 A9 AD 98 A8 AF AF 59 E9 BE AD 9F AC A2 AD AF AF BD 8A AC AC AD AF AC AD AC B4 5F AD AC ");
        reqList.add("59 E9 A0 AD A1 AF 1D AD AD 4D 83 63 A9 E3 AF AC AD 59 E9 A5 AD A8 AD AF 1D AD AD B7 E0 ");
        reqList.add("59 E9 AA AD EC AE AF 1D AD AD AC 59 E9 A7 AD A6 A9 AF AF 1D AD AD AD AD AC ");
        reqList.add("59 E9 BE AD 9F AC A2 AD AE AF BD 8A AC AC AD A9 AC AD AC B4 1D AD AC ");
        reqList.add("59 E9 98 AD AE 10 EE AD AD AF AF 65 AD AD B4 C8 71 AE BC AF AD AB AD 11 45 DB B7 B1 02 D0 B7 A8 5F FD 03 E1 C9 EA 14 FE 3B FA AD AD AD AD AD C8 AD AD E8 DF C8 D9 DF C4 CC 59 E9 BD AD A8 AD 10 EE AD AD 5F FD 03 E1 C9 EA 14 FE 3B FA ");
        reqList.add("59 E9 AB AD AC AC 10 EE AD AD ");
        reqList.add("59 E9 9B AD AE 39 E2 AD AD AF AF 65 AD AD B4 C8 71 AE BC AF AD AB AD 81 AD DA B7 B1 02 D0 B7 A8 BA FC 03 E1 C9 EA 14 FE 0C FA AD AD AD AD AD C8 AD AD FB C4 CE D9 C2 DF C4 CC 59 E9 BD AD A8 AD 39 E2 AD AD BA FC 03 E1 C9 EA 14 FE 0C FA ");
        reqList.add("59 E9 AB AD AC AC 39 E2 AD AD ");
        reqList.add("59 E9 A1 AD A5 AC 93 AC BE AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC 92 AC B9 DD 82 AD AD AD AD AD 59 E9 A1 AD A5 AC 9F AC AD AD AD AD A9 AD AD AD 59 E9 A9 AD 98 AE AD A9 59 E9 A1 AD A5 AC 93 AC BE AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC 92 AC 9A DD 82 AD AD AD AD AD 59 E9 A1 AD A5 AC 9F AC AD AD AD AD A9 AD AD AD 59 E9 A1 AD A5 AC 89 AC E6 B7 83 AD AD AD AD AD 59 E9 A2 AD A5 AF A9 AE AD 89 AC 10 DC 89 AD AD AD AD AD 59 E9 A4 AD B5 A5 0D 17 AC AD AF AD AD 59 E9 A9 AD A6 AC AF AF 59 E9 A5 AD A6 AD 0D 17 AC AD AD AD 59 E9 A8 AD A6 AC AE AF AD ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 A8 AD B5 A8 FF AD AC 59 E9 AF AD B9 A0 ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD B9 AA 59 E9 A0 AD A1 0D 17 AC AD 55 87 6F AC 63 A9 B0 AD ");
        reqList.add("59 E9 AF AD BA 9D ");
        reqList.add("59 E9 AF AD A1 AC ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 84 AD BB A9 AC AD AD AD B3 AD FD AD AD AD AD AD AD AF AD AD AD 53 AB 61 AB AD AD AD AD AD AE AD AD AD F3 AB F5 AF AD AD AD AD AD 59 E9 AA AD EC AE 0D 17 AC AD AC 59 E9 AF AD A8 A9 59 E9 AF AD B9 A5 ");
        reqList.add("59 E9 AB AD AC AC 68 11 AC AD ");
        reqList.add("59 E9 98 AD AE 10 EE AD AD AF AF 65 AD AD B4 C8 71 AE BC AF AD AB AD 11 45 DB B7 B1 02 D0 B7 A8 5F FD 03 E1 C9 EA 14 FE 3B FA AD AD AD AD AD C8 AD AD E8 DF C8 D9 DF C4 CC 59 E9 BD AD A8 AD 10 EE AD AD 5F FD 03 E1 C9 EA 14 FE 3B FA ");
        reqList.add("59 E9 AB AD AC AC 10 EE AD AD ");
        reqList.add("59 E9 9D AD AE 9D 1C AC AD AF AE A4 AD AD 4C 83 90 A8 34 AE AD AE AD B9 8A 2B B7 B5 6B 37 96 AC 94 E7 AD AD AD AD AD C8 AD AD 00 15 1D CC 01 D4 00 71 03 DA 59 E9 A5 AD A8 AD 9D 1C AC AD 94 E7 ");
        reqList.add("59 E9 9B AD AE 39 E2 AD AD AF AF 65 AD AD B4 C8 71 AE BC AF AD AB AD 81 AD DA B7 B1 02 D0 B7 A8 BA FC 03 E1 C9 EA 14 FE 0C FA AD AD AD AD AD C8 AD AD FB C4 CE D9 C2 DF C4 CC 59 E9 BD AD A8 AD 39 E2 AD AD BA FC 03 E1 C9 EA 14 FE 0C FA ");
        reqList.add("59 E9 AB AD AC AC 39 E2 AD AD 59 E9 A0 AD A1 E2 82 AC AD 55 87 13 A7 8B AA AF AD 59 E9 A7 AD A8 AD E2 82 AC AD C8 E7 2F 93 59 E9 A0 AD A1 23 4A AD AD 55 87 13 A7 8B AA AF AD 59 E9 BD AD A8 AD 23 4A AD AD 7D E2 45 E6 5B 85 15 FE 1F F4 59 E9 A0 AD A1 20 4A AD AD 55 87 13 A7 8B AA AF AD 59 E9 BF AD A8 AD 20 4A AD AD 7C E2 45 E6 F1 EA 48 F9 60 F5 9E F6 59 E9 A0 AD A1 3F 82 AC AD 55 87 13 A7 8B AA AF AD 59 E9 BD AD A8 AD 3F 82 AC AD C8 58 CB 58 98 84 CA 58 1E F4 59 E9 A0 AD A1 3E 82 AC AD 55 87 13 A7 8B AA AF AD 59 E9 BF AD A8 AD 3E 82 AC AD 7C E2 45 E6 B6 EA BD F8 0C FA 9B F6 59 E9 AA AD EC AE 20 4A AD AD AC 59 E9 AA AD EC AE 23 4A AD AD AC 59 E9 AA AD EC AE 23 4A AD AD AC 59 E9 B8 AD 8A A4 3E 82 AC AD E0 AC AD AD A7 1F EA 17 46 09 7C 09 F8 0C 1D 59 E9 AA AD EC AE 3E 82 AC AD AC 59 E9 B8 AD 8A A4 3E 82 AC AD E0 AC AD AD A7 1F EA 17 46 09 7C 09 F8 0C 1D 59 E9 AA AD EC AE 3E 82 AC AD AC ");
        reqList.add("59 E9 B8 AD 8A A4 3F 82 AC AD E0 AC AD AD A7 1F EA 17 46 09 7C 09 F8 0C 1D 59 E9 AA AD EC AE 3F 82 AC AD AC 59 E9 B8 AD 8A A4 3F 82 AC AD E0 AC AD AD A7 1F EA 17 46 09 7C 09 F8 0C 1D 59 E9 AA AD EC AE 3F 82 AC AD AC 59 E9 AA AD EC AE 23 4A AD AD AC 59 E9 B8 AD 8A A4 3F 82 AC AD E0 AC AD AD A7 1F EA 17 46 09 7C 09 F8 0C 1D 59 E9 AA AD EC AE 3F 82 AC AD AC 59 E9 AA AD EC AE 20 4A AD AD AC ");
        reqList.add("59 E9 B8 AD 8A A4 3E 82 AC AD E0 AC AD AD A7 1F EA 17 46 09 7C 09 F8 0C 1D 59 E9 AA AD EC AE 3E 82 AC AD AC 59 E9 BA AD A0 AB E2 82 AC AD A9 23 4A AD AD 20 4A AD AD 3F 82 AC AD 3E 82 AC AD 59 E9 AB AD A0 A6 23 4A AD AD 59 E9 AA AD EC AE E2 82 AC AD AC 59 E9 A0 AD A1 E2 82 AC AD 4D 83 17 A9 C7 A4 AC AD 59 E9 A0 AD A1 23 4A AD AD 4D 83 17 A9 C7 A4 AC AD 59 E9 A0 AD A1 20 4A AD AD 4D 83 17 A9 C7 A4 AC AD 59 E9 A0 AD A1 3F 82 AC AD 4D 83 17 A9 C7 A4 AC AD 59 E9 A0 AD A1 3E 82 AC AD 4D 83 17 A9 C7 A4 AC AD ");
        for (String str : reqList) {
            if (str.length() == 0) {
                continue;
            }
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
            byte[] bytes = HexStringToBytesUtil.hexStringToBytes(str);
            int[] data = ByteArrayToIntArrayUtil.xorAndTransform(bytes, bytes.length);
            new TotalHandler().newHandle(0, data, null, null);
        }
    }

    @Test
    public void testJingyan2() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AB AD AC AC 0B F6 AC AD");
        reqList.add("59 E9 AA AD BA DE AC C9 AD AD AD 59 E9 A2 AD A5 AF A9 AC AD 89 AC 97 A5 AD AD AD AD AD AD 59 E9 A9 AD BA A4 AE AC 59 E9 AF AD BA A2 ");
        reqList.add("59 E9 99 AD AE 0B F6 AC AD AF AF B3 AD AD C4 95 77 AF ED AC AD A9 AD A4 47 DB B7 71 CB 40 A8 AF C8 E7 21 F7 AD AD AD AD AD C8 AD AD 0A 48 1E 7D E4 E9 97 95 95 94 94 95 59 E9 A7 AD A8 AD 0B F6 AC AD C8 E7 21 F7");
        for (String str : reqList) {
            if (str.length() == 0) {
                continue;
            }
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 106+100=206
     */
    @Test
    public void testJingyan106_100_206() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AB AD AC AC 10 EE AD AD");
        reqList.add("59 E9 AA AD BA DE AC C9 AD AD AD 59 E9 A2 AD A5 AF A9 AC AD 89 AC 63 AD AD AD AD AD AD AD 59 E9 A2 AD A5 AF A9 AC AD 8E AC A9 AD AD AD AD AD AD AD 59 E9 A2 AD A5 AF A9 AC AD B2 AC A2 AD AD AD AD AD AD AD 59 E9 A2 AD A5 AF A9 AC AD ED AC 92 AD AD AD AD AD AD AD 59 E9 A2 AD A5 AF A9 AC AD B4 AC 0E AD AD AD AD AD AD AD 59 E9 A2 AD A5 AF ");
        reqList.add("59 E9 9B AD AE 39 E2 AD AD AF AF 65 AD AD B4 C8 71 AE BC AF AD AB AD 81 AD DA B7 B1 02 D0 B7 A8 BA FC 03 E1 C9 EA 14 FE 0C FA AD AD AD AD AD C8 AD AD FB C4 CE D9 C2 DF C4 CC 59 E9 BD AD A8 AD 39 E2 AD AD BA FC 03 E1 C9 EA 14 FE 0C FA ");
        reqList.add("59 E9 AB AD AC AC 39 E2 AD AD ");
        for (String str : reqList) {
            if (str.length() == 0) {
                continue;
            }
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void test206_100_306(){
        String string = "59 E9 AB AD AC AC 10 EE AD AD \n" +
                "59 E9 AA AD BA DE AC C9 AD AD AD 59 E9 A2 AD A5 AF A9 AC AD 89 AC 9F AC AD AD AD AD AD AD 59 E9 A2 AD A5 AF A9 AC AD 8E AC A8 AD AD AD AD AD AD AD 59 E9 A2 AD A5 AF A9 AC AD B0 AC AB AD AD AD AD AD AD AD 59 E9 A2 AD A5 AF A9 AC AD ED AC ED AD AD AD AD AD AD AD 59 E9 A2 AD A5 AF A9 AC AD B4 AC 05 AD AD AD AD AD AD AD 59 E9 A2 AD A5 AF \n" +
                "59 E9 A5 AD A8 AF 7A 35 AC AD BC 8A 59 E9 A5 AD A8 AF 75 35 AC AD BC 8A 59 E9 A5 AD A8 AF 0A 82 AC AD BC 8A \n" +
                "59 E9 A5 AD A8 AF 74 35 AC AD BC 8A 59 E9 A5 AD A8 AF 77 35 AC AD BC 8A \n" +
                "59 E9 9B AD AE 39 E2 AD AD AF AF 65 AD AD B4 C8 71 AE BC AF AD AB AD 81 AD DA B7 B1 02 D0 B7 A8 BA FC 03 E1 C9 EA 14 FE 0C FA AD AD AD AD AD C8 AD AD FB C4 CE D9 C2 DF C4 CC 59 E9 BD AD A8 AD 39 E2 AD AD BA FC 03 E1 C9 EA 14 FE 0C FA 59 E9 AB AD AC AC 39 E2 AD AD \n" +
                "59 E9 A4 AD B5 A5 6F 9D AD AD AF AD AD 59 E9 A4 AD B5 A5 3D D1 AD AD AF AD AD 59 E9 A4 AD B5 A5 64 02 AD AD AF AD AD 59 E9 A4 AD B5 A5 67 02 AD AD AF AD AD 59 E9 A4 AD B5 A5 66 02 AD AD AF AD AD \n";
        String[] strings = string.split("\n");
        for (String str : strings) {
            if (str.length() == 0) {
                continue;
            }
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testDaocaorenFlow(){
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AF AD BD 8C ");
        reqList.add("59 E9 AE AD 8D AF A7 59 E9 AE AD 8D AF 97 59 E9 AF AD BD 8C 59 E9 A9 AD B9 AC AC AD ");
        reqList.add("59 E9 AF AD BD 8C ");
        reqList.add("59 E9 AE AD 8D AF A7 59 E9 AE AD 8D AF 97 59 E9 AF AD BD 8C 59 E9 A9 AD B9 AC AC AD ");
        for (String str : reqList) {
            if (str.length() == 0) {
                continue;
            }
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testSongyong(){
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AF AD BD 8C ");
        reqList.add("59 E9 AE AD 8D AF A7 59 E9 AE AD 8D AF 97 59 E9 AF AD BD 8C 59 E9 A9 AD B9 AC AF AD 59 E9 AE AD B9 A4 B3 59 E9 AF AD B9 AB ");
        reqList.add("59 E9 A7 AD 9F AC AE AF AD AF BD 8A AD AD ");
        reqList.add("59 E9 A7 AD 9F AC AE AC AD AF BD 8A AD AD ");
        reqList.add("59 E9 A7 AD 9F AC AF AC AD AF BD 8A AD AD ");
        reqList.add("59 E9 AF AD BD 8C ");
        reqList.add("59 E9 AE AD 8D AF A7 59 E9 AE AD 8D AF 97 59 E9 AF AD BD 8C 59 E9 A9 AD B9 AC AF AD 59 E9 AE AD B9 A4 B3 59 E9 AF AD B9 AB ");
        for (String str : reqList) {
            if (str.length() == 0) {
                continue;
            }
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testJieqiao(){
        String str = "59 E9 A4 AD AB AC A9 BF A9 2E A9 66 C0 \n" +
                "59 E9 A9 AD B9 A9 A3 AD \n" +
                "59 E9 AF AD B9 A5 \n" +//res
                "59 E9 A9 AD B9 A5 BD AD \n" +
                "59 E9 A7 AD 9F AC AE AF AD AC 4E 83 99 72 \n" +
                "59 E9 A7 AD 9F AC AF AF AD AE 4E 83 BC 79 \n" +
                "59 E9 A7 AD 9F AC AE AF AD AE 4E 83 9B 7F \n" +
                "59 E9 A7 AD 9F AC AF AF AD AE 4E 83 9C D6 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD BA 9D \n" +
                "59 E9 AF AD A1 AC \n" +
                "59 E9 AF AD B9 AB \n";
        System.out.println(ShowUtil.show2(str));
    }

    @Test
    public void testHuguan(){
        String str = "59 E9 A4 AD AB AC AF 0F AD D6 AA 66 15  \n" +
                "59 E9 A9 AD B9 A5 A6 AD \n" +
                "59 E9 A7 AD 9F AC AE AF AD AC 4E 83 A3 A6 \n" +
                "59 E9 A7 AD 9F AC AF AF AD AE 4E 83 AD 5C \n" +
                "59 E9 A7 AD 9F AC AE AF AD AE 4E 83 84 03 \n" +
                "59 E9 A7 AD 9F AC AF AF AD AE BD 8A A5 94 \n" +
                "59 E9 A7 AD 9F AC AE AF AD AE BD 8A B3 5C \n" +
                "59 E9 A7 AD 9F AC AF AF AD AE BD 8A 84 41 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 A8 AD B5 A8 9F AC AC 59 E9 AF AD B9 A0 \n"+
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD BA 9D \n" +
                "59 E9 AF AD A1 AC \n" +
                "59 E9 AF AD B9 AB \n" +

                "59 E9 A4 AD AB AC AD 2F AF 76 AC 66 A8 \n" +
                "59 E9 A9 AD B9 A5 A9 AD \n";
        System.out.println(ShowUtil.show2(str));
    }

    @Test
    public void testHuguanBanlv(){
      String str2 = "59 E9 A9 AD B9 A5 AC AD \n" +
              "59 E9 AF AD BA 9D \n" +
              "59 E9 AF AD A1 AC \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 A9 AD B9 A5 A6 AD \n" +
              "59 E9 A7 AD 9F AC AE AF AD AC 4E 83 AD AD \n" +
              "59 E9 A7 AD 9F AC AE AF AD AE 4E 83 AD AD \n" +
              "59 E9 A7 AD 9F AC AE AF AD AE 4E 83 AD AD \n" +
              "59 E9 A1 AD A5 AC 93 AC 87 AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC 92 AC BD 99 90 F7 AD AD AD AD 59 E9 A1 AD A5 AC 9F AC AD AD AD AD AC AD AD AD 59 E9 A5 AD A6 AD 6C 17 AD AD AD AD 59 E9 A8 AD A6 AC AE AF AD 59 E9 A1 AD A5 AC B4 AC 18 A2 AD AD AD AD AD AD 59 E9 A1 AD A5 AC B7 AC C5 AC AD AD AD AD AD AD \n"+
              "59 E9 AF AD B9 AB \n" +
              "59 E9 A8 AD B5 A8 9F AC AC 59 E9 AF AD B9 A0  \n"+ //res
              "59 E9 AF AD B9 AB \n" +
              "59 E9 AF AD BA 9D \n" +
              "59 E9 AF AD A1 AC \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 A9 AD B9 A5 AF AD \n" +
              "59 E9 A7 AD 9F AC AE AF AD AC 4E 83 AD AD \n" +
              "59 E9 A7 AD 9F AC AE AF AD AE 4E 83 AD AD \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 AF AD BA 9D \n" +
              "59 E9 AF AD A1 AC \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 A4 AD AB AC A9 D7 AD 92 AF AD AD \n" +
              "59 E9 AE AD 8D AF A7 59 E9 AE AD 8D AF 97 59 E9 AF AD BD 8C 59 E9 A9 AD B9 AC AE AD \n" +
              "59 E9 A7 AD 9F AC AE AF AD AF 4E 83 AD AD \n" +
              "59 E9 AF AD BD 8C ";
        String str = "59 E9 A9 AD B9 A5 A6 AD \n" +
                "59 E9 A7 AD 9F AC AE AF AD AC 4E 83 AD AD \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 A8 AD B5 A8 9F AC AC 59 E9 AF AD B9 A0 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AA 59 E9 A0 AD A1 6C 17 AD AD 22 9D D3 A9 87 AE A2 AD \n" +
                "59 E9 AF AD BA 9D \n" +
                "59 E9 AF AD A1 AC \n" +
                "59 E9 AF AD B9 AB ";
        System.out.println(ShowUtil.show2(str));
        System.out.println();
        System.out.println(ShowUtil.show2(str2));
    }

    @Test
    public void testQingzhouToKuashengmigong(){
        String str = "59 E9 A9 AD B9 A5 A5 AD \n" +
                "59 E9 A7 AD 9F AC AE AF AD AC AF 82 AD AD \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 A8 AD B5 A8 C0 AD AC 59 E9 AF AD B9 A0 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AA 59 E9 A0 AD A1 6C 17 AD AD 3E 91 13 AD F3 AC A6 AD \n" +
                "59 E9 AF AD BA 9D \n" +
                "59 E9 AF AD A1 AC \n" +
                "59 E9 AF AD B9 AB ";
        System.out.println(ShowUtil.show2(str));
    }
}
