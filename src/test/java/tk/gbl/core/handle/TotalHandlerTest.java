package tk.gbl.core.handle;

import org.junit.Test;
import tk.gbl.bean.Position;
import tk.gbl.core.GameClient;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.HexStringToBytesUtil;

import java.io.IOException;

/**
 * Date: 2017/4/6
 * Time: 15:13
 *
 * @author Tian.Dong
 */
public class TotalHandlerTest {
    /**
     * 115038申请入队
     * F4 44 06 00 0D 01 5E C1 01 00
     * <p/>
     * F4 44 0A 00 0D 05 5D C1 01 00 5E C1 01 00
     *
     * @throws IOException
     */
    //@Test
    public void test() throws IOException {
//        String res1 = "59 E9 AB AD A0 AC F3 6C AC AD ";
//        String res1 = "59 E9 A7 AD A0 A8 F0 6C AC AD F3 6C AC AD 59 E9 BD AD A5 AE F3 6C AC AD B4 AC FC AD AD AD AD AD AD AD 59 E9 BD AD A5 AE F3 6C AC AD B2 AC AD AD AD AD AD AD AD AD 59 E9 BD AD A5 AE F3 6C AC AD 7A AC AD AD AD AD AD AD AD AD 59 E9 BD AD A5 AE F3 6C AC AD 62 AC AD AD AD AD AD AD AD AD ";
//        String res1 = "59 E9 AA AD A0 AE AC F0 6C AC AD 59 E9 A7 AD A0 A8 F0 6C AC AD F3 6C AC AD 59 E9 BD AD A5 AE F0 6C AC AD B4 AC FC AD AD AD AD AD AD AD 59 E9 BD AD A5 AE F0 6C AC AD B2 AC AD AD AD AD AD AD AD AD 59 E9 BD AD A5 AE F0 6C AC AD 7A AC AD AD AD AD AD AD AD AD 59 E9 BD AD A5 AE F0 6C AC AD 62 AC AD AD AD AD AD AD AD AD ";
//        String res1 = "59 E9 AB AD A0 AA F3 6C AC AD 59 E9 AB AD A0 A6 F3 6C AC AD ";
//        String res1 = "59 E9 AB AD EF AF AD AD AD AD ";
//        String res1 = "59 E9 AA AD 8D AF 06 09 AC AD A7 59 E9 AA AD 8D AF 06 09 AC AD 97 59 E9 A7 AD A6 A9 AF 06 09 AC AD AD AD AC 59 E9 A7 AD A6 A9 AF 01 09 AC AD AD AD A8 59 E9 A7 AD A6 A9 AF 00 09 AC AD AD AD A8 59 E9 A7 AD A6 A9 AF 02 09 AC AD AD AD A8 59 E9 A7 AD A6 A9 AF 03 09 AC AD AD AD A8 59 E9 AA AD 8D AF E5 6C AC AD A7 59 E9 AA AD 8D AF E5 6C AC AD 97 59 E9 A7 AD A6 A9 AF E5 6C AC AD AD AD AC 59 E9 A7 AD A6 A9 AF E4 6C AC AD AD AD A8 59 E9 A7 AD A6 A9 AF E6 6C AC AD AD AD A8 59 E9 A7 AD A6 A9 AF E7 6C AC AD AD AD A8 59 E9 A7 AD A6 A9 AF E1 6C AC AD AD AD A8 ";

        //扔东西
//        String res1 = "59 E9 A4 AD BA AE 11 C8 BB AB 9D AF AC 59 E9 A4 AD BA AE 11 C8 43 A8 9A AF AC 59 E9 A4 AD BA AE 11 C8 8C AB 86 AF AC 59 E9 A4 AD BA AE 11 C8 A7 AB 8C AF AC 59 E9 A4 AD BA AE 11 C8 B3 AB 8E AF AC 59 E9 A4 AD BA AE 11 C8 49 A8 89 AF AC 59 E9 A4 AD BA AE 11 C8 73 A8 B8 AF AC 59 E9 A4 AD BA AE 11 C8 AB AB 89 AF AC 59 E9 A4 AD BA AE 11 C8 BC AB BA AF AC 59 E9 A4 AD BA AE 11 C8 41 A8 B3 AF AC 59 E9 A4 AD BA AE 11 C8 B9 AB 85 AF AC 59 E9 A4 AD BA AE 11 C8 50 A8 86 AF AC 59 E9 A4 AD BA AE 11 C8 4A A8 84 AF AC 59 E9 A4 AD BA AE 11 C8 45 A8 B7 AF AC 59 E9 A4 AD BA AE 11 C8 63 A8 BF AF AC 59 E9 A4 AD BA AE 11 C8 40 A8 B1 AF AC 59 E9 A4 AD BA AE 11 C8 51 A8 90 AF AC 59 E9 A4 AD BA AE 11 C8 8A AB B0 AF AC 59 E9 A4 AD BA AE 11 C8 5C A8 8B AF AC 59 E9 A4 AD BA AE 11 C8 60 A8 B2 AF AC 59 E9 A4 AD BA AE 11 C8 BA AB BF AF AC 59 E9 A4 AD BA AE 11 C8 8E AB B4 AF AC 59 E9 A4 AD BA AE 11 C8 BB AB 97 AF AC 59 E9 A4 AD BA AE 11 C8 66 A8 B0 AF AC 59 E9 A4 AD BA AE 11 C8 48 A8 85 AF AC 59 E9 A4 AD BA AE 11 C8 49 A8 83 AF AC 59 E9 A4 AD BA AE 11 C8 B3 AB 8E AF AC 59 E9 A4 AD BA AE 11 C8 7C A8 84 AF AC 59 E9 A4 AD BA AE 11 C8 58 A8 84 AF AC 59 E9 A4 AD BA AE 11 C8 5A A8 9B AF AC 59 E9 A4 AD BA AE 11 C8 52 A8 8A AF AC 59 E9 A4 AD BA AE 11 C8 4B A8 8D AF AC 59 E9 A4 AD BA AE 11 C8 A7 AB 84 AF AC 59 E9 A4 AD BA AE 11 C8 73 A8 97 AF AC 59 E9 A4 AD BA AE 11 C8 43 A8 81 AF AC 59 E9 A4 AD BA AE 11 C8 5F A8 86 AF AC 59 E9 A4 AD BA AE 11 C8 71 A8 8E AF AC 59 E9 A4 AD BA AE 11 C8 5A A8 8B AF AC 59 E9 A4 AD BA AE 11 C8 77 A8 B2 AF AC 59 E9 A4 AD BA AE 11 C8 AB AB 8F AF AC 59 E9 A4 AD BA AE 11 C8 B0 AB B5 AF AC 59 E9 A4 AD BA AE 11 C8 56 A8 88 AF AC 59 E9 A4 AD BA AE 11 C8 73 A8 B5 AF AC 59 E9 A4 AD BA AE 11 C8 43 A8 8F AF AC 59 E9 A4 AD BA AE 11 C8 52 A8 89 AF AC 59 E9 A4 AD BA AE 11 C8 4B A8 BE AF AC 59 E9 A4 AD BA AE 11 C8 A3 AB B2 AF AC 59 E9 A4 AD BA AE 11 C8 47 A8 B7 AF AC 59 E9 A4 AD BA AE 11 C8 51 A8 88 AF AC 59 E9 A4 AD BA AE 11 C8 A6 AB B2 AF AC 59 E9 A9 AD BA A4 A9 9F ";
        String res1 = "59 E9 A4 AD BA AE 11 C8 D9 AC 0D AC AC 59 E9 A4 AD BA AE 11 C8 28 AC 36 AC AC 59 E9 A4 AD BA AE 11 C8 CE AC 10 AC AC 59 E9 A4 AD BA AE 11 C8 96 AC 01 AC AC 59 E9 A4 AD BA AE 11 C8 C7 AC 10 AC AC 59 E9 A4 AD BA AE 11 C8 D6 AC 0A AC AC 59 E9 A4 AD BA AE 11 C8 D6 AC 09 AC AC 59 E9 A4 AD BA AE 11 C8 DC AC 1A AC AC 59 E9 A4 AD BA AE 11 C8 E8 AC 6E AC AC 59 E9 A4 AD BA AE 11 C8 C9 AC 15 AC AC 59 E9 A4 AD BA AE 11 C8 E0 AC 1E AC AC 59 E9 A4 AD BA AE 11 C8 C4 AC 05 AC AC 59 E9 A4 AD BA AE 11 C8 FC AC 09 AC AC 59 E9 A4 AD BA AE 11 C8 DC AC 0E AC AC 59 E9 A4 AD BA AE 11 C8 24 AC 1F AC AC 59 E9 A4 AD BA AE 11 C8 E1 AC 31 AC AC 59 E9 A4 AD BA AE 11 C8 D6 AC 31 AC AC 59 E9 A4 AD BA AE 11 C8 ED AC 0F AC AC 59 E9 A4 AD BA AE 11 C8 F2 AC 13 AC AC 59 E9 A4 AD BA AE 11 C8 C4 AC 1F AC AC 59 E9 A4 AD BA AE 11 C8 E3 AC 11 AC AC 59 E9 A4 AD BA AE 11 C8 C0 AC 30 AC AC 59 E9 A4 AD BA AE 11 C8 91 AC 10 AC AC 59 E9 A4 AD BA AE 11 C8 22 AC 31 AC AC 59 E9 A4 AD BA AE 11 C8 C1 AC 11 AC AC 59 E9 A4 AD BA AE 11 C8 EE AC 10 AC AC 59 E9 A4 AD BA AE 11 C8 DB AC 06 AC AC 59 E9 A4 AD BA AE 11 C8 C7 AC 0F AC AC 59 E9 A4 AD BA AE 11 C8 FD AC 33 AC AC 59 E9 A4 AD BA AE 11 C8 C4 AC 03 AC AC 59 E9 A4 AD BA AE 11 C8 DC AC 31 AC AC 59 E9 A4 AD BA AE 11 C8 FC AC 13 AC AC 59 E9 A4 AD BA AE 11 C8 D4 AC 33 AC AC 59 E9 A4 AD BA AE 11 C8 FE AC 1C AC AC 59 E9 A4 AD BA AE 11 C8 DE AC 10 AC AC 59 E9 A4 AD BA AE 11 C8 E8 AC 12 AC AC 59 E9 A4 AD BA AE 11 C8 F9 AC 04 AC AC 59 E9 A4 AD BA AE 11 C8 ED AC 1B AC AC 59 E9 A4 AD BA AE 11 C8 2A AC 0E AC AC 59 E9 A4 AD BA AE 11 C8 FA AC 0A AC AC 59 E9 A4 AD BA AE 11 C8 FD AC 1A AC AC 59 E9 A4 AD BA AE 11 C8 CF AC 04 AC AC 59 E9 A4 AD BA AE 11 C8 DD AC 00 AC AC 59 E9 A4 AD BA AE 11 C8 EC AC 30 AC AC 59 E9 A4 AD BA AE 11 C8 90 AC 17 AC AC 59 E9 A4 AD BA AE 11 C8 D0 AC 16 AC AC 59 E9 A4 AD BA AE 11 C8 EC AC 05 AC AC 59 E9 A4 AD BA AE 11 C8 DF AC 0D AC AC 59 E9 A4 AD BA AE 11 C8 DE AC 14 AC AC 59 E9 A4 AD BA AE 11 C8 FC AC 13 AC AC 59 E9 A9 AD BA A4 A8 9F ";
        byte[] bytes = HexStringToBytesUtil.hexStringToBytes(res1);
        int[] data = ByteArrayToIntArrayUtil.xorAndTransform(bytes, bytes.length);
        System.out.println(ShowUtil.show(res1));
        TotalHandler totalHandler = new TotalHandler();
        totalHandler.newHandle(0, data, null, null);
    }

    /**
     * 18 12     67 43
     * 621 26D    212  D4 2
     * 81 61 1
     * <p/>
     * <p/>
     * F4 44 1A 00 0B 05 05 04 DC B0 00 00 02 00 45 BE 01 00 02 02 6D 02 D4 00 6D 02 D4 00 02 04
     * <p/>
     * 怪物
     * F4 44 1A 00 0B 05 01 07 FC 55 00 00 03 00 00 00 00 00 00 02 12 00 43 00 12 00 43 00 01 00
     */
    //@Test
    public void test2() throws IOException {
        String res1 = "59 E9 AF AD B9 A5 59 E9 AF AD AB AF 59 E9 B1 AD A6 57 C5 AC AC AF E8 13 AC AD AD AD AD AD AD AD AE AF FC AD 90 AD FC AD 90 AD AC AE 59 E9 A8 AD A6 A7 AC AD AD 59 E9 B7 AD A6 A8 A8 A9 71 1D AD AD AF AD E8 13 AC AD AF AF C0 AF 79 AD C0 AF 79 AD AF A9 59 E9 B7 AD A6 A8 AC AA 51 F8 AD AD AE AD AD AD AD AD AD AF BF AD EE AD BF AD EE AD AC AD 59 E9 AF AD B9 A4 ";
//        String res1 = "59 E9 A1 AD A5 AC 93 AC AE AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC 92 AC FF AC AD AD AD AD AD AD 59 E9 A1 AD A5 AC 9F AC AD AD AD AD AD AD AD AD 59 E9 A9 AD 98 AE AD AF 59 E9 A9 AD A6 AC AF AF 59 E9 A5 AD A6 AD E8 13 AC AD AD AD 59 E9 A8 AD A6 AC AE AF AD ";
        System.out.println(ShowUtil.show(res1));
        byte[] bytes = HexStringToBytesUtil.hexStringToBytes(res1);
        System.out.println(bytes.length);
        int[] data = ByteArrayToIntArrayUtil.xorAndTransform(bytes, bytes.length);
        TotalHandler totalHandler = new TotalHandler();
        totalHandler.newHandle(0, data, null, null);
    }

    @Test
    public void testDamage() throws IOException {
        GameClient gameClient = new GameClient();
        gameClient.setIndex(1);
        //String res = "F4 44 69 00 32 01 21 00 02 02 E3 2E 03 03 00 02 01 00 01 19 8F 01 01 00 01 01 00 01 19 8E 01 01 00 03 01 00 01 19 8D 01 01 0F 00 03 00 10 27 01 01 00 02 00 02 01 00 00 00 01 0F 00 03 04 10 27 01 01 00 02 01 00 01 19 01 00 01 0F 00 03 01 10 27 01 01 00 02 01 00 01 19 01 00 01 0F 00 03 03 10 27 01 01 00 02 01 00 01 19 02 00 01";
//        String res = "F4 44 43 00 32 01 3F 00 03 02 00 2F 08 05 00 01 01 00 01 19 D3 02 01 00 00 01 00 01 19 FB 01 01 00 02 01 00 02 19 EF 02 01 DF 00 00 01 00 03 01 00 02 19 F5 01 01 DF 00 00 01 00 04 01 00 02 19 FD 01 01 DF 00 00 01 ";
        //天降
//        String res = "F4 44 3E 00 32 01 3A 00 03 01 16 2B 08 04 03 01 01 00 02 19 00 00 00 1A 00 00 00 02 03 01 00 02 19 00 00 00 1A 74 00 00 03 02 01 01 02 19 00 00 00 1A 00 00 00 03 03 01 00 02 19 00 00 00 1A 54 00 00 ";
        String res = "F4 44 23 00 32 01 1F 00 00 01 11 2B 03 01 00 03 01 00 05 00 00 00 01 DD 00 00 01 DE 00 00 01 DF 00 00 01 E1 00 00 01 ";

        byte[] bytes = HexStringToBytesUtil.hexStringToBytes(res);
        System.out.println(bytes.length);
        int[] data = ByteArrayToIntArrayUtil.transform(bytes, bytes.length);
        TotalHandler totalHandler = new TotalHandler();
        totalHandler.newHandle(0, data, null, gameClient);
    }

    @Test
    public void testRoleInfoList() throws IOException {
//        String res = "F4 44 43 01 0F 08 01 73 46 06 00 00 00 01 C5 00 59 00 0D 00 31 00 19 00 27 00 1D 00 0E 00 00 3E 01 00 00 04 B7 E0 A4 FD 01 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 26 5B 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 48 B5 67 8A 0D 15 C8 C1 0F 10 03 D5 00 B1 00 50 00 74 00 3A 00 3F 00 00 64 01 00 00 0A A8 67 A1 45 A5 FA BC 42 B3 C6 05 0A 05 C1 51 96 05 6F 00 00 00 00 00 2D 4B 05 05 6F 00 00 00 00 00 21 29 0A 05 6F 00 00 00 00 00 06 55 41 05 6F 00 00 00 00 00 A9 59 C3 05 6F 00 00 00 00 00 3D 5A 00 00 00 00 00 00 00 00 05 01 00 00 00 00 04 0D 56 06 00 00 00 01 81 00 43 00 05 00 04 00 05 00 04 00 0C 00 03 00 00 39 01 00 00 0A AD 49 A5 5D A4 DA A8 A7 A7 AF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ";
        String res = "F4 44 A6 01 0F 08 01 DF B0 AC 89 01 0D AB 06 06 79 03 ED 00 28 00 6A 00 7A 00 2F 00 54 00 00 64 01 00 00 08 A8 67 A1 45 B3 AF AE 63 0A 05 0A D1 4F 00 01 6E 00 00 00 00 00 82 E6 60 05 6F 00 00 00 00 00 F6 E6 FA 05 6F 00 00 00 00 00 13 55 00 01 6E 00 00 00 00 00 A1 57 3D 01 6C 00 00 00 00 00 33 5B 00 00 00 00 00 00 00 00 0A 00 00 00 00 00 02 89 46 A6 0F 00 00 0B BE 00 76 00 19 00 22 00 1A 00 2E 00 0F 00 0D 00 00 46 01 0A 00 02 C6 4E 01 01 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 29 5B 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 44 A0 8A 54 89 0E C8 6C 05 DE 02 D4 00 19 00 5C 00 7E 00 30 00 3F 00 00 63 01 00 00 04 B3 A2 B9 C5 0A 0A 0A D1 4F 00 03 6C 00 00 00 00 00 84 E6 5D 03 6C 00 00 00 00 00 F8 E6 B5 03 6C 00 00 00 00 00 12 55 79 03 6C 00 00 00 00 00 B5 59 00 02 69 00 00 00 00 00 7D 5A 00 00 00 00 00 00 00 00 01 00 00 00 00 00 04 0D 56 06 00 00 00 01 81 00 43 00 05 00 04 00 05 00 04 00 0C 00 03 00 00 3C 01 00 00 0A AD 49 A5 5D A4 DA A8 A7 A7 AF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ";
        GameClient gameClient = new GameClient();
        gameClient.setIndex(1);
        byte[] bytes = HexStringToBytesUtil.hexStringToBytes(res);
        System.out.println(bytes.length);
        int[] data = ByteArrayToIntArrayUtil.transform(bytes, bytes.length);
        TotalHandler totalHandler = new TotalHandler();
        totalHandler.newHandle(0, data, null, gameClient);
    }


    @Test
    public void testCatchPet() throws IOException {
        String res = "59 E9 A1 AD A5 AC 9F AC AD AD AD AD AD AD AD AD 59 E9 A2 AD A5 AF A9 AF AD ED AC 91 AD AD AD AD AD AD AD 59 E9 A1 AD A2 AC 6E 17 AD AD AF C6 E8 AD AD AC 59 E9 81 AD AE 9F 82 AC AD AF AC AC AD AD 4E 83 5B AC 42 AC AD AD AD 8D 67 C1 8D 93 03 D0 B7 AC B4 E0 AD AD AD AD AD C8 AD AD 04 FD 06 1E 9C 94 59 E9 A5 AD A8 AD 9F 82 AC AD B4 E0 ";
//        String res = "59 E9 AA AD 98 BD AF AC AE EE AD 59 E9 A9 AD A6 AC AF AF 59 E9 A5 AD A6 AD 6E 17 AD AD AD AD 59 E9 A8 AD A6 AC AE AF AD 59 E9 A2 AD A5 AF A9 AE AD B4 AC 6C A2 AD AD AD AD AD AD ";
        GameClient gameClient = new GameClient();
        gameClient.setIndex(1);
        gameClient.setPosition(new Position());
        byte[] bytes = HexStringToBytesUtil.hexStringToBytes(res);
        System.out.println(bytes.length);
        int[] data = ByteArrayToIntArrayUtil.xorAndTransform(bytes, bytes.length);
        TotalHandler totalHandler = new TotalHandler();
        totalHandler.newHandle(0, data, null, gameClient);
    }

    @Test
    public void testPetList() throws IOException {
        String res = "F4 44 69 00 0F 08 02 DA 3B 98 62 11 00 32 A1 02 CE 00 16 00 62 00 39 00 66 00 37 00 12 00 00 64 01 16 00 04 A8 F5 AC 65 01 01 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ";
        GameClient gameClient = new GameClient();
        gameClient.setIndex(1);
        byte[] bytes = HexStringToBytesUtil.hexStringToBytes(res);
        System.out.println(bytes.length);
        int[] data = ByteArrayToIntArrayUtil.transform(bytes, bytes.length);
        TotalHandler totalHandler = new TotalHandler();
        totalHandler.newHandle(0, data, null, gameClient);
    }

    @Test
    public void testGetItem() throws IOException {
        String res = "F4 44 0C 00 17 8D 01 10 00 00 00 00 00 00 00 00";
        GameClient gameClient = new GameClient();
        gameClient.setIndex(1);
        byte[] bytes = HexStringToBytesUtil.hexStringToBytes(res);
        System.out.println(bytes.length);
        int[] data = ByteArrayToIntArrayUtil.transform(bytes, bytes.length);
        TotalHandler totalHandler = new TotalHandler();
        totalHandler.newHandle(0, data, null, gameClient);
    }
}
