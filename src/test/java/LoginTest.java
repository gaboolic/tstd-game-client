import org.junit.Test;
import tk.gbl.show.ShowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/3/27
 * Time: 15:23
 *
 * @author Tian.Dong
 */
public class LoginTest {
    public static byte[] int2Bytes(int num) {
        byte[] byteNum = new byte[4];
        for (int ix = 0; ix < 4; ++ix) {
            int offset = 32 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    @Test
    public void testLongToByte() {
        int number = 0x05F5E0FF;
        byte[] bytes = int2Bytes(number);
        System.out.println(number >> 24 & 0xFF);
        System.out.println(number >> 16 & 0xFF);
        System.out.println(number >> 8 & 0xFF);
        System.out.println(number >> 0 & 0xFF);
    }

    @Test
    public void test(){
      System.out.println(("F4 44 00 10 01 06 A0 BA 01 00 77 70 D1 00 35 35 36 36 38 38 F4 44 0B 00 41 01 00 00 00 00 00 00 00 00 00"));
      System.out.println(ShowUtil.showCharOrigin("F4 44 00 10 01 06 A0 BA 01 00 77 70 D1 00 35 35 36 36 38 38 F4 44 0B 00 41 01 00 00 00 00 00 00 00 00 00"));
    }

    @Test
    public void testLoginReq() {
        String loginReq1 = "59 E9 BD AD AC AB AA 1F AC AD DA C3 7C AD 9B 9B 9B 9B 9B 9B 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD ";
        System.out.println(loginReq1);
        System.out.println(ShowUtil.show(loginReq1));
        System.out.println(ShowUtil.showChar(loginReq1));

        String loginReqStr5F5E0FF = "59 E9 BD AD AC AB 52 4D 58 A8 DA C3 7C AD 9B 9B 9B 9B 9B 9B 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD ";
        System.out.println(ShowUtil.show(loginReqStr5F5E0FF));
        System.out.println(ShowUtil.showChar(loginReqStr5F5E0FF));
    }

    //成功
    //F4 44 02 00 14 08 F4 44 05 00 18 05 02 00 00 F4 44 05 00 18 05 31 01 00 F4 44 05 00 18 05 62 01 00

    //F4 44 02 00 01 06 F4 44 02 00 00 12 //错误密码
    //F4 44 03 00 01 03 00 F4 44 02 00 00 12  //没角色
    //F4 44 02 00 47 01
    @Test
    public void testLoginRes() {
        String loginResStrwrongPassword = "59 E9 AF AD AC AB 59 E9 AF AD AD BF";
        String loginReswrongPassword = ShowUtil.show(loginResStrwrongPassword);
        String loginResCharwrongPassword = ShowUtil.showChar(loginResStrwrongPassword);

        System.out.println(loginResStrwrongPassword);
        System.out.println(loginReswrongPassword);
        System.out.println(loginResCharwrongPassword);

        String loginResStrnojuese = "59 E9 AE AD AC AE AD 59 E9 AF AD AD BF";
        String loginResnojuese = ShowUtil.show(loginResStrnojuese);
        String loginResCharnojuese = ShowUtil.showChar(loginResStrnojuese);

        System.out.println(loginResStrnojuese);
        System.out.println(loginResnojuese);
        System.out.println(loginResCharnojuese);

        String loginResStrnojuese2 = "59 E9 AF AD EA AC ";
        String loginResnojuese2 = ShowUtil.show(loginResStrnojuese2);
        String loginResCharnojuese2 = ShowUtil.showChar(loginResStrnojuese2);

        System.out.println(loginResStrnojuese2);
        System.out.println(loginResnojuese2);
        System.out.println(loginResCharnojuese2);

        //重复登录断线
        String loginResRepeat = "F4 44 02 00 00 13 F4 44 02 00 00 12 ";
    }

    @Test
    public void testLoginRes2(){
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AE AD AC AE AD 59 E9 AF AD AD BF ");//吞食伴侣

        reqList.add("59 E9 AE AD AC AE AD ");//游戏
        reqList.add("59 E9 AF AD EA AC ");//游戏

        for(String str:reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
        //程序 F4 44 03 00 01 03 00 F4 44 02 00 00 12
    }


    /**
     * F4 44 10 00 01 06 A0 BA 01 00 77 70 D1 00 35 35 36 36 38 38
     *
     * F4 44 10 00 01 06 A0 BA 01 00 77 70 D1 00 35 35 36 36 38 38 F4 44 0B 00 41 01 00 00 00 00 00 00 00 00 00
     * ô  D                 º        w  p  Ñ     5  5  6  6  8  8  ô  D        A
     * F4 44 02 00 14 08 F4 44 05 00 18 05 02 00 00 F4 44 05 00 18 05 31 01 00 F4 44 05 00 18 05 62 01 00 F4 44 05 00 18 05 91 01 00 F4 44 05 00 18 05 92 01 00 F4 44 05 00 18 05 71 03 00 F4 44 0C 00 08 01 42 01 00 00 00 00 00 00 00 00 F4 44 0C 00 08 01 43 01 00 00 00 00 00 00 00 00 F4 44 0A 00 4E 01 00 00 00 00 00 00 00 00 F4 44 03 00 14 21
     * ô  D              ô  D                       ô  D              1        ô  D              b        ô  D                      ô  D                      ô  D              q        ô  D              B                             ô  D              C                             ô  D        N                             ô  D           !
     */
    @Test
    public void testLoginResSuccess() {
        String loginReq = "59 E9 BD AD AC AB 0D 17 AC AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD ";
        System.out.println(ShowUtil.show(loginReq));
        System.out.println(ShowUtil.showChar(loginReq));

        String loginResSuccess = "59 E9 AF AD B9 A5 59 E9 A8 AD B5 A8 AF AD AD 59 E9 A8 AD B5 A8 9C AC AD 59 E9 A8 AD B5 A8 CF AC AD 59 E9 A8 AD B5 A8 3C AC AD 59 E9 A8 AD B5 A8 3F AC AD 59 E9 A8 AD B5 A8 DC AE AD 59 E9 A1 AD A5 AC EF AC AD AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC EE AC AD AD AD AD AD AD AD AD 59 E9 A7 AD E3 AC AD AD AD AD AD AD AD AD 59 E9 AE AD B9 8C ";
        System.out.println(ShowUtil.show(loginResSuccess));
        System.out.println(ShowUtil.showChar(loginResSuccess));
    }

    public static void main(String[] args) {
        String loginReqStr = "59 E9 BD AD AC AB AC AD AD AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        String loginReq = ShowUtil.show(loginReqStr);
        String loginReqChar = ShowUtil.showChar(loginReqStr);

        System.out.println(loginReqStr);
        System.out.println(loginReq);
        System.out.println(loginReqChar);
        System.out.println();

        /*String loginReqStr2 = "59 E9 BD AD AC AB AF AD AD AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        String loginReq2 = ShowUtil.show(loginReqStr2);
        String loginReqChar2 = ShowUtil.showChar(loginReqStr2);

        System.out.println(loginReqStr2);
        System.out.println(loginReq2);
        System.out.println(loginReqChar2);
        System.out.println();
        String loginReqStr4 = "59 E9 BD AD AC AB A9 AD AD AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        String loginReq4 = ShowUtil.show(loginReqStr4);
        String loginReqChar4 = ShowUtil.showChar(loginReqStr4);

        System.out.println(loginReqStr4);
        System.out.println(loginReq4);
        System.out.println(loginReqChar4);
        System.out.println();

        String loginReqStr8 = "59 E9 BD AD AC AB A5 AD AD AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD ";
        String loginReq8 = ShowUtil.show(loginReqStr8);
        String loginReqChar8 = ShowUtil.showChar(loginReqStr8);

        System.out.println(loginReqStr8);
        System.out.println(loginReq8);
        System.out.println(loginReqChar8);
        System.out.println();

        String loginReqStr16 = "59 E9 BD AD AC AB BD AD AD AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        String loginReq16 = ShowUtil.show(loginReqStr16);
        String loginReqChar16 = ShowUtil.showChar(loginReqStr16);

        System.out.println(loginReqStr16);
        System.out.println(loginReq16);
        System.out.println(loginReqChar16);
        System.out.println();

        String loginReqStr32 = "59 E9 BD AD AC AB 8D AD AD AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        String loginReq32 = ShowUtil.show(loginReqStr32);
        String loginReqChar32 = ShowUtil.showChar(loginReqStr32);

        System.out.println(loginReqStr32);
        System.out.println(loginReq32);
        System.out.println(loginReqChar32);
        System.out.println();*/

        String loginReqStr64 = "59 E9 BD AD AC AB ED AD AD AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        String loginReq64 = ShowUtil.show(loginReqStr64);
        String loginReqChar64 = ShowUtil.showChar(loginReqStr64);

        System.out.println(loginReqStr64);
        System.out.println(loginReq64);
        System.out.println(loginReqChar64);
        System.out.println();

        String loginReqStr128 = "59 E9 BD AD AC AB 2D AD AD AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        String loginReq128 = ShowUtil.show(loginReqStr128);
        String loginReqChar128 = ShowUtil.showChar(loginReqStr128);

        System.out.println(loginReqStr128);
        System.out.println(loginReq128);
        System.out.println(loginReqChar128);
        System.out.println();


        String loginReqStr255 = "59 E9 BD AD AC AB 52 AD AD AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        String loginReq255 = ShowUtil.show(loginReqStr255);
        String loginReqChar255 = ShowUtil.showChar(loginReqStr255);

        System.out.println(loginReqStr255);
        System.out.println(loginReq255);
        System.out.println(loginReqChar255);
        System.out.println();

        /**
         59 E9 BD AD AC AB AD AC AD AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD
         F4 44 10 00 01 06 00 01 00 00 77 70 D1 00 35 35 36 36 38 38 F4 44 0B 00 41 01 00 00 00 00 00 00 00 00 00
         ô  D                      w  p  Ñ     5  5  6  6  8  8  ô  D       A                             
         */
        String loginReqStr256 = "59 E9 BD AD AC AB AD AC AD AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        String loginReq256 = ShowUtil.show(loginReqStr256);
        String loginReqChar256 = ShowUtil.showChar(loginReqStr256);

        System.out.println(loginReqStr256);
        System.out.println(loginReq256);
        System.out.println(loginReqChar256);
        System.out.println();

        /**
         59 E9 BD AD AC AB AD AD A9 AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD
         F4 44 10 00 01 06 00 00 04 00 77 70 D1 00 35 35 36 36 38 38 F4 44 0B 00 41 01 00 00 00 00 00 00 00 00 00
         ô  D                      w  p  Ñ     5  5  6  6  8  8  ô  D       A                             
         */
        String loginReqStr262144 = "59 E9 BD AD AC AB AD AD A9 AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        String loginReq262144 = ShowUtil.show(loginReqStr262144);
        String loginReqChar262144 = ShowUtil.showChar(loginReqStr262144);

        System.out.println(loginReqStr262144);
        System.out.println(loginReq262144);
        System.out.println(loginReqChar262144);
        System.out.println();

        //59 E9 A0 AD AC AE ED 9D 8D BD DA C3 7C AD 9C 9C 9C 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD
        String loginReqStr10203040 = "59 E9 A0 AD AC AE ED 9D 8D BD DA C3 7C AD 9C 9C 9C 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        System.out.println(ShowUtil.show(loginReqStr10203040));
        System.out.println(ShowUtil.showChar(loginReqStr10203040));

        long i = 4294967295L;
        System.out.println(Long.toHexString(i));
    }

    @Test
    public void testLoginResabcdefg1234(){
        List<String> resList = new ArrayList<>();
        resList.add("F4 44 CE 00 17 05 01 C6 B5 01 00 00 00 00 00 00 00 00 02 58 67 0A 00 00 00 00 00 00 00 00 03 BC 65 32 00 00 00 00 00 00 00 00 04 BC 65 32 00 00 00 00 00 00 00 00 07 BC 65 32 00 00 00 00 00 00 00 00 08 BC 65 32 00 00 00 00 00 00 00 00 09 BC 65 32 00 00 00 00 00 00 00 00 0A BC 65 32 00 00 00 00 00 00 00 00 0C BC 65 2D 00 00 00 00 00 00 00 00 0D BC 65 32 00 00 00 00 00 00 00 00 0E BC 65 32 00 00 00 00 00 00 00 00 0F BC 65 32 00 00 00 00 00 00 00 00 11 BC 65 32 00 00 00 00 00 00 00 00 12 BC 65 32 00 00 00 00 00 00 00 00 13 BC 65 32 00 00 00 00 00 00 00 00 14 BC 65 32 00 00 00 00 00 00 00 00 16 BC 65 32 00 00 00 00 00 00 00 00 F4 44 9C 00 17 8D 01 01 00 00 00 00 00 00 00 00 02 00 00 00 00 00 00 00 00 03 00 00 00 00 00 00 00 00 04 00 00 00 00 00 00 00 00 07 00 00 00 00 00 00 00 00 08 00 00 00 00 00 00 00 00 09 00 00 00 00 00 00 00 00 0A 00 00 00 00 00 00 00 00 0C 00 00 00 00 00 00 00 00 0D 00 00 00 00 00 00 00 00 0E 00 00 00 00 00 00 00 00 0F 00 00 00 00 00 00 00 00 11 00 00 00 00 00 00 00 00 12 00 00 00 00 00 00 00 00 13 00 00 00 00 00 00 00 00 14 00 00 00 00 00 00 00 00 16 00 00 00 00 00 00 00 00 F4 44 0C 00 17 0B 39 4A 04 00 00 00 00 00 00 00 F4 44 0D 00 17 8D 08 00 02 00 00 00 00 00 00 00 00 F4 44 06 00 13 04 DC B0 00 00 F4 44 11 00 18 07 02 00 40 03 00 04 70 00 08 76 00 02 A6 00 80 F4 44 0A 00 1A 04 F0 0A 00 00 00 00 00 00 F4 44 29 01 29 05 01 03 00 08 AF AB A4 A7 B8 74 B0 EC 94 43 00 00 B5 42 00 00 F6 E6 00 00 6E 9D 01 00 7A 37 00 00 CA 3C 00 00 6D 79 00 00 C7 3B 00 00 1F 03 00 00 08 B4 CB C3 B9 B8 55 B9 B3 A2 06 00 00 02 01 00 08 AF AB A4 A7 B8 74 B0 EC 94 43 00 00 B5 42 00 00 F6 E6 00 00 6E 9D 01 00 7A 37 00 00 CA 3C 00 00 6D 79 00 00 C7 3B 00 00 1F 03 00 00 08 B4 CB C3 B9 B8 55 B9 B3 A2 06 00 00 03 01 00 08 AF AB A4 A7 B8 74 B0 EC 94 43 00 00 B5 42 00 00 F6 E6 00 00 6E 9D 01 00 7A 37 00 00 CA 3C 00 00 6D 79 00 00 C7 3B 00 00 1F 03 00 00 08 B4 CB C3 B9 B8 55 B9 B3 A2 06 00 00 04 01 00 08 AF AB A4 A7 B8 74 B0 EC 94 43 00 00 B5 42 00 00 F6 E6 00 00 6E 9D 01 00 7A 37 00 00 CA 3C 00 00 6D 79 00 00 C7 3B 00 00 1F 03 00 00 08 B4 CB C3 B9 B8 55 B9 B3 A2 06 00 00 05 01 00 06 B4 63 A4 48 A8 A6 8B 3E 00 00 4D BA 01 00 5C B5 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 65 07 00 00 00 00 00 00 00 F4 44 DF 00 16 04 01 00 00 00 A6 04 24 04 00 00 00 00 00 02 00 00 00 B6 03 58 02 00 00 00 00 00 03 00 00 00 36 01 E4 02 00 00 00 00 00 04 00 00 00 EA 01 D4 03 00 00 00 00 00 05 00 00 00 BE 00 20 03 00 00 00 00 00 06 00 00 00 5A 00 34 03 00 00 00 00 00 07 00 00 00 96 00 34 03 00 00 00 00 00 08 00 00 00 AE 01 DC 00 00 00 00 00 00 09 00 00 00 36 06 30 02 00 00 00 00 00 0A 00 00 00 0A 05 A8 02 00 00 00 00 00 0B 00 00 00 2E 04 50 00 00 00 00 00 00 0C 00 00 00 46 00 A4 01 00 00 00 00 00 0D 00 00 00 22 01 70 03 00 00 00 00 00 0E 00 00 00 82 00 98 03 00 00 00 00 00 0F 00 00 00 AA 05 44 02 00 00 00 00 00 10 00 00 00 E6 05 58 02 00 00 00 00 00 11 00 00 00 1A 04 48 03 02 00 00 00 00 F4 44 18 00 17 04 03 01 00 27 A8 00 00 E6 01 E1 00 03 02 00 09 A8 00 00 01 03 AC 01 F4 44 07 00 41 03 44 BE 01 00 01 F4 44 02 00 05 04 F4 44 02 00 0F 0A F4 44 20 00 02 0B 00 00 00 00 A1 6D A7 5D AD B9 A4 D1 A6 61 4F 6E 6C 69 6E 65 B0 EA A5 4B B5 4C C2 F9 A1 6E F4 44 34 00 02 0B 00 00 00 00 A9 D2 A6 B3 B8 EA AE C6 A4 F9 A4 BA AE 65 A7 4B B6 4F A5 F4 AA B1 A1 49 A5 BC A8 D3 A7 F3 A6 68 A4 BA AE 65 B5 A5 A7 41 C5 E9 C5 E7 A1 49 F4 44 3C 00 02 0B 00 00 00 00 A8 67 A8 74 AA 5A B1 4E C5 E5 A4 D1 B5 6E B3 F5 A1 49 A5 FE B7 73 C5 51 AE F0 B3 79 AB AC A1 49 AA FE B1 61 AD 5E B6 AF A7 DE AF E0 C1 7C A5 40 B5 4C C2 F9 A1 49 F4 44 38 00 02 0B 00 00 00 00 A5 FE B7 73 AC A1 B0 CA A1 41 A7 F3 A6 68 BC FA AB 7E A1 41 A7 F3 A5 69 C0 F2 B1 6F A7 5D AD B9 A5 76 A4 57 AD BA A6 B8 A5 58 B2 7B AA 5A B1 4E A1 49 F4 44 26 00 02 0B 00 00 00 00 A5 FE B3 A1 A6 61 B9 CF A7 4B B6 4F C2 F4 A1 41 BA EB B1 6D AC A1 B0 CA A4 D1 A4 D1 A6 B3 A1 49 F4 44 3D 00 02 0B 00 00 00 00 BD D0 C0 48 AE C9 AF 64 B7 4E A9 78 BA F4 B3 CC B7 73 A4 BD A7 69 A1 47 68 74 74 70 3A 2F 2F 74 73 68 61 6F 2E 6F 6E 6C 69 6E 65 2D 67 61 6D 65 2E 63 6F 6D 2E 63 6E F4 44 0E 00 02 0B 00 00 00 00 A1 69 AC A1 B0 CA A1 6A F4 44 79 00 02 0B 00 00 00 00 31 A1 42 A5 FE AA 41 B6 7D B1 D2 A8 E2 AD BF B8 67 C5 E7 AC A1 B0 CA A1 5D 32 30 31 37 30 32 32 34 7E 32 30 31 37 30 32 32 36 A1 5E A1 42 A1 5D 32 30 31 37 30 33 30 31 7E 32 30 31 37 30 33 30 35 A1 5E A1 42 A1 5D 32 30 31 37 30 33 30 38 7E 32 30 31 37 30 33 31 32 A1 5E A1 42 A1 5D 32 30 31 37 30 33 31 35 7E 32 30 31 37 30 33 31 36 A1 5E A1 43 F4 44 62 00 02 0B 00 00 00 00 32 A1 42 A5 FE AA 41 B6 7D B1 D2 A4 AD AD BF B8 67 C5 E7 AC A1 B0 CA A1 5D 32 30 31 37 30 32 32 37 7E 32 30 31 37 30 32 32 38 A1 5E A1 42 A1 5D 32 30 31 37 30 33 30 36 7E 32 30 31 37 30 33 30 37 A1 5E A1 42 A1 5D 32 30 31 37 30 33 31 33 7E 32 30 31 37 30 33 31 34 A1 5E A1 43 F4 44 46 00 02 0B 00 00 00 00 A9 D2 A6 B3 A4 BA AE 65 B8 D4 B1 A1 BD D0 B0 D1 A6 D2 A9 78 A4 E8 BA F4 AD B6 BB A1 A9 FA A1 41 B9 43 C0 B8 A4 BA AE 65 A5 48 BD 75 A4 57 B9 EA BB DA AA AC AA 70 AC B0 AD E3 A1 43 23 65 6E 64 F4 44 06 01 27 1F 01 00 00 00 02 14 00 00 00 0A 70 00 00 00 01 1E 00 00 00 06 A8 00 00 00 0D 2B 00 00 00 02 32 00 00 00 08 A1 03 00 00 01 E0 00 00");

        for(String str:resList) {
            System.out.println(ShowUtil.showOrigin(str));
            System.out.println(ShowUtil.showCharOrigin(str));
        }
    }

    @Test
    public void testLoginFlow(){
        String str = "59 E9 AC AD AD  \n" +
                "59 E9 BD AD AC AB 68 17 AD AD DA DD 7C AD 9C 94 95 99 9C 9D 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD \n" +
                "59 E9 AB AD A2 A9 24 EB AD AD  \n" +
                "59 E9 AF AD EF AE \n" ;
        System.out.println(ShowUtil.show2(str));
    }
}
