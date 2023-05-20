import org.junit.Test;
import tk.gbl.show.ShowUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/3/25
 * Time: 9:57
 *
 * @author Tian.Dong
 */
public class ShowUtilTest {


    @Test
    public void testCheckNameNext() {
        /*String setNameResStrNextA = "59 E9 AE AD A4 AF CC ";
        String setNameResNextA = ShowUtil.show(setNameResStrNextA);
        String setNameResCharNextA = ShowUtil.showChar(setNameResStrNextA);

        System.out.println(setNameResStrNextA);
        System.out.println(setNameResNextA);
        System.out.println(setNameResCharNextA);*/
        //B9 C8 A4 6A
        //嗷大猫 B9 C8 A4 6A BF DF
        String setNameResStrNextAodamao = "59 E9 A5 AD A4 AF 14 65 09 C7 12 72 ";
        String setNameResNextAodamao = ShowUtil.show(setNameResStrNextAodamao);
        String setNameResCharNextAodamao = ShowUtil.showChar(setNameResStrNextAodamao);

        System.out.println(setNameResStrNextAodamao);
        System.out.println(setNameResNextAodamao);
        System.out.println(setNameResCharNextAodamao);

        //abcdefg 59 E9 A1 AD A4 AF CC CF CE C9 C8 CB CA 9C 9F 9E
        String setNameResStrNextAbcdefg = "59 E9 A1 AD A4 AF CC CF CE C9 C8 CB CA 9C 9F 9E ";
        String setNameResNextAbcdefg = ShowUtil.show(setNameResStrNextAbcdefg);
        String setNameResCharNextAbcdefg = ShowUtil.showChar(setNameResStrNextAbcdefg);

        System.out.println(setNameResStrNextAbcdefg);
        System.out.println(setNameResNextAbcdefg);
        System.out.println(setNameResCharNextAbcdefg);

        /*
        59 E9 AE AD A4 AE AC 名称重复使用
        * 59 E9 AE AD A4 AE AD 正常
         */
        String setNameResStr = "59 E9 AE AD A4 AE AD";
        String setNameRes = ShowUtil.show(setNameResStr);
        String setNameResChar = ShowUtil.showChar(setNameResStr);

        System.out.println(setNameResStr);
        System.out.println(setNameRes);
        System.out.println(setNameResChar);

        //重复
        String setNameResStrCF = "59 E9 AE AD A4 AE AC";
        String setNameResCF = ShowUtil.show(setNameResStrCF);
        String setNameResCharCF = ShowUtil.showChar(setNameResStrCF);

        System.out.println(setNameResStrCF);
        System.out.println(setNameResCF);
        System.out.println(setNameResCharCF);
    }

    @Test
    public void testSetNameAndInit() {
        //B9 C8 A4 6A BF DF
        String setNameAndInitReqStr = "59 E9 8E AD A4 AC AC AD AD AD B1 02 D0 B7 93 03 D0 B7 AC AC AF AE AD AD AD AB 9C 9F 9E 99 98 9B AB 9C 9F 9E 99 98 9B ";
        String setNameAndInitReq = ShowUtil.show(setNameAndInitReqStr);
        String setNameAndInitReqChar = ShowUtil.showChar(setNameAndInitReqStr);

        System.out.println(setNameAndInitReqStr);
        System.out.println(setNameAndInitReq);
        System.out.println(setNameAndInitReqChar);
    }

    @Test
    public void testSetIdCard() {
        //451324199012135918
        String setIdCardReqStr = "59 E9 B2 AD EA AC AB 14 65 09 C7 12 72 6B AA A1 A0 99 98 9C 9E 9F 99 9C 94 94 9D 9C 9F 9C 9E 98 94 9C 95";
        String setIdCardReqStrReq = ShowUtil.show(setIdCardReqStr);
        String setIdCardReqStrReqChar = ShowUtil.showChar(setIdCardReqStr);

        System.out.println(setIdCardReqStr);
        System.out.println(setIdCardReqStrReq);
        System.out.println(setIdCardReqStrReqChar);

        String resStr1 = "59 E9 81 AD AE 21 6A AD AD AF AE 65 AD AD 4E 83 5B AC 42 AC AD BB AD FC 10 99 8B B1 02 D0 B7 AF CA 58 81 F6 AD AD AD AD AD C8 AF AF 19 CB 18 71 59 E9 A7 AD A8 AD 21 6A AD AD CA 58 81 F6 ";
        System.out.println(ShowUtil.show(resStr1));
        System.out.println(ShowUtil.showChar(resStr1));

        String resStr2 = "59 E9 9C AD AE 77 FF AD AD AC AE 6A AD AD 43 9D FE AD DB AC AD BD AD 6D F0 9E 8B 93 03 D0 B7 A8 7C E2 51 E1 9A 4F 53 59 18 F4 AD AD AD AD AD C8 AF AE 9C 9D 9C 59 E9 BD AD A8 AD 77 FF AD AD 7C E2 51 E1 9A 4F 53 59 18 F4 ";
        System.out.println(ShowUtil.show(resStr2));
        System.out.println(ShowUtil.showChar(resStr2));
    }

    @Test
    public void testChat() {
        /*
        String chatReqStr1 = "59 E9 A5 AD AF AF 14 65 09 C7 12 72 ";
        System.out.println(ShowUtil.show(chatReqStr1));
        System.out.println(ShowUtil.showChar(chatReqStr1));

        String chatReqStr2 = "59 E9 AE AD AF AF CC ";
        System.out.println(ShowUtil.show(chatReqStr2));
        System.out.println(ShowUtil.showChar(chatReqStr2));

        String chatReqStr3 = "59 E9 AE AD AF AF 8C ";
        System.out.println(ShowUtil.show(chatReqStr3));
        System.out.println(ShowUtil.showChar(chatReqStr3));

        System.out.println(ShowUtil.show("59 E9 A5 AD AF AF 0C E4 0C E4 0C E4"));
        System.out.println(ShowUtil.showChar("59 E9 A5 AD AF AF 0C E4 0C E4 0C E4"));
        */

        System.out.println(ShowUtil.show("59 E9 A8 AD AF AF 1D 77 CC"));//啊a
        System.out.println(ShowUtil.showChar("59 E9 A8 AD AF AF 1D 77 CC"));

        System.out.println(ShowUtil.show("59 E9 AE AD AF AF CC "));//a
        System.out.println(ShowUtil.showChar("59 E9 AE AD AF AF CC "));

        System.out.println(ShowUtil.show("59 E9 A9 AD AF AF 1D 77 "));//啊
        System.out.println(ShowUtil.showChar("59 E9 A9 AD AF AF 1D 77 "));

        String chat1Str = "59 E9 AE AD AF AF 9C ";
        String chat2Str = "59 E9 A9 AD AF AF 9C 9F ";
        String chat3Str = "59 E9 A8 AD AF AF 9C 9F 9E ";
        String chat4Str = "59 E9 AB AD AF AF 9C 9F 9E 99 ";
        String chat5Str = "59 E9 AA AD AF AF 9C 9F 9E 99 98";
        String chat6Str = "59 E9 A5 AD AF AF 9C 9F 9E 99 98 9B ";

        System.out.println(ShowUtil.show(chat1Str));
        System.out.println(ShowUtil.showChar(chat1Str));
        System.out.println(ShowUtil.show(chat2Str));
        System.out.println(ShowUtil.showChar(chat2Str));
        System.out.println(ShowUtil.show(chat3Str));
        System.out.println(ShowUtil.showChar(chat3Str));
        System.out.println(ShowUtil.show(chat4Str));
        System.out.println(ShowUtil.showChar(chat4Str));
        System.out.println(ShowUtil.show(chat5Str));
        System.out.println(ShowUtil.showChar(chat5Str));
        System.out.println(ShowUtil.show(chat6Str));
        System.out.println(ShowUtil.showChar(chat6Str));
    }

    @Test
    public void testClickNPC() {
        //59 E9 A9 AD B9 AC AC AD
        String clickNPCStr0 = "59 E9 A9 AD B9 AC AC AD ";
        System.out.println(ShowUtil.show(clickNPCStr0));
        System.out.println(ShowUtil.showChar(clickNPCStr0));

        String clickNPCStr1 = "59 E9 AF AD B9 AB ";//貌似代表结束
//        String clickNPCStr1 = "59 E9 AF AD BA 9D  ";//
        System.out.println(ShowUtil.show(clickNPCStr1));
        System.out.println(ShowUtil.showChar(clickNPCStr1));

        String clickNPCStr2 = "59 E9 A9 AD B9 AC AA AD ";
        System.out.println(ShowUtil.show(clickNPCStr2));
        System.out.println(ShowUtil.showChar(clickNPCStr2));

        String clickNPCStr3 = "59 E9 AF AD B9 AB ";
        System.out.println(ShowUtil.show(clickNPCStr3));
        System.out.println(ShowUtil.showChar(clickNPCStr3));

        String clickNPCStrShoushangShibing = "59 E9 A9 AD B9 AC A5 AD ";
        System.out.println(ShowUtil.show(clickNPCStrShoushangShibing));
        System.out.println(ShowUtil.showChar(clickNPCStrShoushangShibing));

        String clickNPCStrFanmaiji = "59 E9 A9 AD B9 AC AB AD  ";
        System.out.println(ShowUtil.show(clickNPCStrFanmaiji));
        System.out.println(ShowUtil.showChar(clickNPCStrFanmaiji));
    }

    @Test
    public void testMoveTo() {
        //622 595
        //26E 253
        //F4 44 09 00 06 01 00 6E 02 53 02 0A 99
        //F4 44 09 00 06 01 00 xxxxx yyyyy 0A 99
        String moveToStr1 = "59 E9 A4 AD AB AC AD C3 AF FE AF A7 34 ";
        System.out.println(ShowUtil.show(moveToStr1));
        System.out.println(ShowUtil.showChar(moveToStr1));

        String moveToStr2 = "59 E9 A4 AD AB AC A8 7B AE 42 AC A7 68  ";
        System.out.println(ShowUtil.show(moveToStr2));
        System.out.println(ShowUtil.showChar(moveToStr2));

        String moveToStr3 = "59 E9 A4 AD AB AC A9 7F AF AE AF A7 7B  ";
        System.out.println(ShowUtil.show(moveToStr3));
        System.out.println(ShowUtil.showChar(moveToStr3));

        String moveToStr4 = "59 E9 A4 AD AB AC A9 7F AF CA AF A7 C3  ";
        System.out.println(ShowUtil.show(moveToStr4));
        System.out.println(ShowUtil.showChar(moveToStr4));

        String moveToStr5 = "59 E9 AF AD A7 AC ";
        System.out.println(ShowUtil.show(moveToStr5));
        System.out.println(ShowUtil.showChar(moveToStr5));

        String moveToStr6 = "59 E9 A4 AD AB AC AB E3 A9 CA AF A7 B9 ";
        System.out.println(ShowUtil.show(moveToStr6));
        System.out.println(ShowUtil.showChar(moveToStr6));

        String moveToStr7 = "59 E9 A4 AD AB AC A9 1F A9 D1 AF AD AD ";
        System.out.println(ShowUtil.show(moveToStr7));
        System.out.println(ShowUtil.showChar(moveToStr7));

        String moveToStr8 = "59 E9 A4 AD AB AC A9 1F A9 D0 AF AD AD  ";
        System.out.println(ShowUtil.show(moveToStr8));
        System.out.println(ShowUtil.showChar(moveToStr8));

        String moveToStr9 = "59 E9 A4 AD AB AC A9 1F A9 DB AF AD AD ";
        System.out.println(ShowUtil.show(moveToStr9));
        System.out.println(ShowUtil.showChar(moveToStr9));

        String moveToStr10 = "59 E9 A4 AD AB AC A9 1D A9 DB AF AD AD ";//1200 630
        System.out.println(ShowUtil.show(moveToStr10));
        System.out.println(ShowUtil.showChar(moveToStr10));

        String moveToStr11 = "59 E9 A4 AD AB AC A9 45 AE 98 AF AD AD ";//1000 565
        System.out.println(ShowUtil.show(moveToStr11));
        System.out.println(ShowUtil.showChar(moveToStr11));

        String moveToStr12 = "59 E9 A4 AD AB AC A9 8D AE 59 AC AD AD ";//800 500
        System.out.println(ShowUtil.show(moveToStr12));
        System.out.println(ShowUtil.showChar(moveToStr12));

        String moveToStr13 = "59 E9 A4 AD AB AC A9 3D AC 3D AC AD AD  ";//400 400
        System.out.println(ShowUtil.show(moveToStr13));
        System.out.println(ShowUtil.showChar(moveToStr13));
    }

    @Test
    public void testMoveTo2() {
        String moveToStr1 = "59 E9 A4 AD AB AC A9 8B AF 8B AF AD AD ";
        System.out.println(ShowUtil.show(moveToStr1));
        System.out.println(ShowUtil.showChar(moveToStr1));
        String moveToResStr1 = "59 E9 AB AD AC AC BF 4A AD AD ";
        System.out.println(ShowUtil.show(moveToResStr1));
        System.out.println(ShowUtil.showChar(moveToResStr1));

        String moveToStr2 = "59 E9 A4 AD AB AC A9 3D AC 3D AC AD AD  ";
        System.out.println(ShowUtil.show(moveToStr2));
        System.out.println(ShowUtil.showChar(moveToStr2));
        String moveToResStr2 = "59 E9 AB AD AC AC B8 E9 AD AD ";
        System.out.println(ShowUtil.show(moveToResStr2));
        System.out.println(ShowUtil.showChar(moveToResStr2));

        String moveToStr3 = "59 E9 A4 AD AB AC A9 81 AC 81 AC AD AD ";
        System.out.println(ShowUtil.show(moveToStr3));
        System.out.println(ShowUtil.showChar(moveToStr3));
        String moveToResStr3 = "59 E9 AB AD AC AC BF 4A AD AD ";
        System.out.println(ShowUtil.show(moveToResStr3));
        System.out.println(ShowUtil.showChar(moveToResStr3));
    }

    @Test
    public void testMove() {
//        String moveStr = "59 E9 A9 AD B9 A5 A9 AD ";
        String moveStr = "59 E9 A9 AD B9 A5 AC AD ";//邺城城门 12061
        System.out.println(ShowUtil.show(moveStr));
        System.out.println(ShowUtil.showChar(moveStr));

        //12061
        String moveResStr = "59 E9 AF AD B9 AA 59 E9 A0 AD A1 0D 17 AC AD B0 82 B3 A8 CB AE AC AD ";
        System.out.println(ShowUtil.show(moveResStr));
        System.out.println(ShowUtil.showChar(moveResStr));

        String moveStr2 = "59 E9 AF AD BA 9D ";
        System.out.println(ShowUtil.show(moveStr2));
        System.out.println(ShowUtil.showChar(moveStr2));

        String moveStr3 = "59 E9 AF AD A1 AC ";
        System.out.println(ShowUtil.show(moveStr3));
        System.out.println(ShowUtil.showChar(moveStr3));

        String moveStr4 = "59 E9 AF AD B9 AB ";
        System.out.println(ShowUtil.show(moveStr4));
        System.out.println(ShowUtil.showChar(moveStr4));
    }


    /**
     * F4 44 后面一位代表数据长度
     */
    @Test
    public void testFlow() {
        String connectReq = "59 E9 AC AD AD ";
        String connectRes = "59 E9 A9 AD AC A4 C8 AD ";
        String loginReq = "59 E9 BD AD AC AB 0D 17 AC AD DA DD 7C AD 98 98 9B 9B 95 95 ";
        String loginReq2 = "59 E9 B9 AD AC A7 0D 17 AC AD DA DD 7C AD 9C 9F 9E 99 98 9B 9A 95 94 9D ";
        String loginReq3 = "59 E9 BD AD AC AB 0E 17 AC AD DA DD 7C AD 9C 9C 9C 9C 9C 9C 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD";
        String loginRes = "59 E9 AF AD B9 A5 59 E9 A8 AD B5 A8 AF AD AD 59 E9 A8 AD B5 A8 9C AC AD 59 E9 A8 AD B5 A8 CF AC AD 59 E9 A8 AD B5 A8 3C AC AD 59 E9 A8 AD B5 A8 3F AC AD 59 E9 A8 AD B5 A8 DC AE AD 59 E9 A1 AD A5 AC EF AC AD AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC EE AC AD AD AD AD AD AD AD AD 59 E9 A7 AD E3 AC AD AD AD AD AD AD AD AD 59 E9 AE AD B9 8C AD 59 E9 A1 AD A5 AC 7F AC BC AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC 7E AC B6 AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC 7B AC AB AD AD AD AD AD AD AD 59 E9 82 AD AE 0D 17 AC AD AC AD AD 4C 83 67 AD 16 AE AD AD AD B1 02 D0 B7 93 03 D0 B7 A9 9E E3 56 E1 70 FF 54 F8 AD AD AD AD C8 AD AD 14 65 09 C7 12 72 59 E9 9F AD A9 50 14 AC AD AF AF C1 AD AD BE 82 55 AF 36 AD AD AC AD E9 5B DA B7 B1 02 D0 B7 A8 BA FC 56 E1 61 F9 19 F5 FC F7 AD AD AD AD AD C8 AD AD 07 51 6F 68 59 E9 9F AD A9 51 14 AC AD AC AF C0 AD AD BE 82 55 AF 36 AD AD AF AD 41 AE 2B B7 93 03 D0 B7 A8 BA FC 56 E1 61 F9 19 F5 FC F7 AD AD AD AD AD C8 AD AD 07 51 1B 6D 59 E9 9F AD A9 61 9D AD AD AF AE 65 AD AD D7 F2 93 A8 1E AC AD AD AD B1 02 D0 B7 B1 02 D0 B7 AB 7D E2 E5 E0 49 E4 15 FE 0D FA 0B F7 AD AD AD AD AD C8 AD AD 0B CC 59 E9 9D AD A9 63 9D AD AD AF AE 65 AD AD D7 F2 93 A8 1E AC AD AD AD B1 02 D0 B7 B1 02 D0 B7 A8 62 E2 45 E6 98 84 1A FE 99 F7 AD AD AD AD AD C8 AD AD 09 E5 59 E9 9F AD A9 6E F2 AD AD AC AE 65 AD AD D7 F2 93 A8 1E AC AD AE AD CD 0D 2E B7 93 03 D0 B7 A8 B4 FC 4A E6 49 E4 15 FE 18 F4 AD AD AD AD AD C8 AD AD 6E F3 0C EF 59 E9 99 AD A9 6F F2 AD AD AC AE 65 AD AD D7 F2 93 A8 1E AC AD AE AD A5 D4 28 B7 93 03 D0 B7 AB B4 FC 4A E6 49 E4 15 FE 19 F4 9E F6 AD AD AD AD ";

        System.out.println(ShowUtil.show(connectReq));
        System.out.println(ShowUtil.show(connectRes));
        System.out.println(ShowUtil.show(loginReq));
        System.out.println(ShowUtil.showChar(loginReq));
        System.out.println(ShowUtil.show(loginReq2));
        System.out.println(ShowUtil.showChar(loginReq2));
        System.out.println(ShowUtil.show(loginReq3));
        System.out.println(ShowUtil.showChar(loginReq3));
        System.out.println(ShowUtil.show(loginRes));

        String unknownRes1 = "59 E9 98 AD AE 01 32 AC AD AC AF B0 AD AD 4C 83 17 AB 42 AC AD AE AD A9 91 D1 B7 93 03 D0 B7 AB 4E FD 56 E1 B9 8A 66 F9 1E F5 80 F7 AD AD AD AD AD C8 AD AD 09 C7 01 1B 9F 59 E9 BF AD A8 AD 01 32 AC AD 4E FD 56 E1 B9 8A 66 F9 1E F5 80 F7 ";
        String unknownRes2 = "59 E9 AB AD AC AC 01 32 AC AD 59 E9 AB AD AC AC 31 11 AC AD ";
        String chatReq1 = "59 E9 A8 AD AF AF 9C 9F 9E ";
        String chatReq2 = "59 E9 A8 AD AF AF 99 98 9B ";
        String unknownRes3 = "59 E9 98 AD AE 01 32 AC AD AC AF B0 AD AD 4C 83 17 AB 42 AC AD AE AD A9 91 D1 B7 93 03 D0 B7 AB 4E FD 56 E1 B9 8A 66 F9 1E F5 80 F7 AD AD AD AD AD C8 AD AD 09 C7 01 1B 9F 59 E9 BF AD A8 AD 01 32 AC AD 4E FD 56 E1 B9 8A 66 F9 1E F5 80 F7 ";
        String unknownRes4 = "59 E9 AB AD AC AC 01 32 AC AD ";
        String unknownRes5 = "59 E9 AB AD AC AC 68 0B AC AD ";
        String chatReq3 = "59 E9 A8 AD AF AF 9C 9F 9E ";
        String chatReq4 = "59 E9 A8 AD AF AF 99 98 9B ";

        String chatReq5 = "59 E9 A8 AD AF AF 1D 77 CC ";
        String chatReq6 = "59 E9 A8 AD AF AF CC 1D 77 ";
        String chatReq7 = "59 E9 AE AD AF AF CC ";
        String chatReq8 = "59 E9 A5 AD AF AF 9C 9C 9C 9C 9C 9C ";
        String chatReq9 = "59 E9 A5 AD AF AF 9F 9F 9F 9F 9F 9F ";
        String chatReq10 = "59 E9 A7 AD AF AF 9E 9E 9E 9E 9E 9E 9E 9E ";
        String chatReq11 = "59 E9 A6 AD AF AF 99 99 99 99 99 99 99 99 99 ";
        String chatReq12 = "59 E9 BB AD AF AF 9C 9F 9E 99 98 9B 9A 95 94 9D 9C 9F 9E 99 98 9B 9A 95 94 9D ";

        System.out.println();
        System.out.println(ShowUtil.show(unknownRes1));
        System.out.println(ShowUtil.show(unknownRes2));
        System.out.println(ShowUtil.show(unknownRes3));
        System.out.println(ShowUtil.show(unknownRes4));
        System.out.println(ShowUtil.show(unknownRes5));
        System.out.println(ShowUtil.showChar(unknownRes5));

        System.out.println();
        System.out.println(ShowUtil.show(chatReq1));
        System.out.println(ShowUtil.show(chatReq2));
        System.out.println(ShowUtil.show(chatReq3));
        System.out.println(ShowUtil.show(chatReq4));
        System.out.println(ShowUtil.show(chatReq5));
        System.out.println(ShowUtil.show(chatReq6));
        System.out.println(ShowUtil.show(chatReq7));
        System.out.println(ShowUtil.show(chatReq8));
        System.out.println(ShowUtil.show(chatReq9));
        System.out.println(ShowUtil.show(chatReq10));
        System.out.println(ShowUtil.show(chatReq11));
        System.out.println(ShowUtil.show(chatReq12));


        String unknownRes7 = "59 E9 AB AD AC AC 13 C9 AD AD ";
        String unknownRes8 = "59 E9 9F AD AE 31 11 AC AD AF AF AC AD AD 01 82 37 AC 37 AC AD AD AD B1 02 D0 B7 B1 02 D0 B7 AC B4 E0 AD AD AD AD AD C8 AD AD 0B 76 08 63 0A 5A 03 6B 00 71 03 DA 59 E9 A5 AD A8 AD 31 11 AC AD B4 E0 ";
        String unknownRes9 = "59 E9 98 AD AE 01 32 AC AD AC AF B0 AD AD 4C 83 17 AB 42 AC AD AE AD A9 91 D1 B7 93 03 D0 B7 AB 4E FD 56 E1 B9 8A 66 F9 1E F5 80 F7 AD AD AD AD AD C8 AD AD 09 C7 01 1B 9F 59 E9 BF AD A8 AD 01 32 AC AD 4E FD 56 E1 B9 8A 66 F9 1E F5 80 F7 59 E9 AB AD AC AC 01 32 AC AD ";
        String unknownRes10 = "59 E9 A5 AD A8 AF 9D DB AC AD 80 F7 ";

        System.out.println();
        System.out.println(ShowUtil.show(unknownRes7));
        System.out.println(ShowUtil.show(unknownRes8));
        System.out.println(ShowUtil.show(unknownRes9));
        System.out.println(ShowUtil.show(unknownRes10));


        String moveToReq1 = "59 E9 A4 AD AB AC AC 83 AC DA AC E9 F3 ";
        String moveToReq2 = "59 E9 A4 AD AB AC AB 17 AC DA AC E9 FA ";
        String moveToReq3 = "59 E9 A4 AD AB AC A9 0B AC 1E AC E9 87 ";
        String moveToReq4 = "59 E9 A4 AD AB AC AB 5B AC 1E AC E9 F7 ";
        String moveToReq5 = "59 E9 A4 AD AB AC A9 5B AC 6A AC E9 6A ";
        String moveToReq6 = "59 E9 A4 AD AB AC AC C7 AC 26 AC E9 8A ";
        System.out.println();
        System.out.println(ShowUtil.show(moveToReq1));
        System.out.println(ShowUtil.show(moveToReq2));
        System.out.println(ShowUtil.show(moveToReq3));
        System.out.println(ShowUtil.show(moveToReq4));
        System.out.println(ShowUtil.show(moveToReq5));
        System.out.println(ShowUtil.show(moveToReq6));

        String moveTo2Req1 = "59 E9 A4 AD AB AC AD D3 AC DA AC E9 05 ";
        String moveTo2Req2 = "59 E9 A4 AD AB AC AB 4F AC CE AC E9 37 ";
        String moveTo2Req3 = "59 E9 A4 AD AB AC AB EB AF CE AC E9 3A ";
        String moveTo2Req4 = "59 E9 A4 AD AB AC AB 07 AF CE AC E9 25 ";
        String moveTo2Req5 = "59 E9 A4 AD AB AC A8 13 AF 26 AC E9 9E ";
        String moveTo2Req6 = "59 E9 A4 AD AB AC AB 57 AF 26 AC E9 35 ";
        String moveTo2Req7 = "59 E9 A4 AD AB AC AF FB AC DA AC E9 3B ";
        String moveTo2Req8 = "59 E9 A4 AD AB AC AB 7F AF DA AC E9 68 ";
        System.out.println();
        System.out.println(ShowUtil.show(moveTo2Req1));
        System.out.println(ShowUtil.show(moveTo2Req2));
        System.out.println(ShowUtil.show(moveTo2Req3));
        System.out.println(ShowUtil.show(moveTo2Req4));
        System.out.println(ShowUtil.show(moveTo2Req5));
        System.out.println(ShowUtil.show(moveTo2Req6));
        System.out.println(ShowUtil.show(moveTo2Req7));
        System.out.println(ShowUtil.show(moveTo2Req8));
    }

    @Test
    public void testCreateJueseFlow() {
        String loginResNoJuese = "59 E9 AE AD AC AE AD ";
        String loginResNoJueseNext = "59 E9 AF AD EA AC ";

        String setNameAndInitReq = "59 E9 89 AD A4 AC AF AD AB AD 11 98 D2 B7 B1 02 D0 B7 A9 AD AD AD AD AD AB AB 9C 9F 9E 99 98 9B AA 9C 9F 9E 99 98 9B 9A ";
        String setNameAndInitRes = "59 E9 AF AD A4 AC ";
        String unknownReq = "59 E9 AF AD AE AC ";

        String unknownReq2 = "59 E9 BD AD 88 AC 9C 9C 99 83 9C 9C 9F 83 94 9A 83 9C 9E 9A ";
        String unknownReq3 = "59 E9 BF AD 8A BE 4B A8 AD AD 44 A8 AD AD 7D AB AD AD 5F AB AD AD ";
        String unknownReq4 = "59 E9 AE AD B4 84 D6 ";

        System.out.println(ShowUtil.show(loginResNoJuese));
        System.out.println(ShowUtil.showChar(loginResNoJuese));
        System.out.println(ShowUtil.show(loginResNoJueseNext));
        System.out.println(ShowUtil.showChar(loginResNoJueseNext));
        System.out.println();

        System.out.println(ShowUtil.show(setNameAndInitReq));
        System.out.println(ShowUtil.showChar(setNameAndInitReq));
        System.out.println(ShowUtil.show(setNameAndInitRes));
        System.out.println(ShowUtil.showChar(setNameAndInitRes));
        System.out.println(ShowUtil.show(unknownReq));
        System.out.println(ShowUtil.showChar(unknownReq));
        System.out.println();

        System.out.println(ShowUtil.show(unknownReq2));
        System.out.println(ShowUtil.showChar(unknownReq2));//服务器IP
        System.out.println(ShowUtil.show(unknownReq3));
        System.out.println(ShowUtil.showChar(unknownReq3));
        System.out.println(ShowUtil.show(unknownReq4));
        System.out.println(ShowUtil.showChar(unknownReq4));
        System.out.println();

        System.out.println("setIdCard");
        //setIdCard
        String setIdCardReq = "59 E9 B0 AD EA AC A9 1D 77 1D 77 64 AA A7 BB 9B 9E 9F 9B 9F 98 9C 94 94 9E 9C 9D 9F 9F 94 9B 94 9B ";
        System.out.println(ShowUtil.show(setIdCardReq));
        System.out.println(ShowUtil.showChar(setIdCardReq));

        //451324199012135918
        String setIdCardReq2 = "59 E9 B2 AD EA AC AB 14 65 09 C7 12 72 6B AA A1 A0 99 98 9C 9E 9F 99 9C 94 94 9D 9C 9F 9C 9E 98 94 9C 95";
        System.out.println(ShowUtil.show(setIdCardReq2));
        System.out.println(ShowUtil.showChar(setIdCardReq2));
        System.out.println();

        String clickNPC1 = "59 E9 A9 AD B9 AC A8 AD ";
        System.out.println(ShowUtil.show(clickNPC1));
        System.out.println(ShowUtil.showChar(clickNPC1));
        String clickNPCRes1 = "59 E9 BD AD A5 AE 0E 35 AC AD 8E AC 6E AD AD AD AD AD AD AD ";
        String clickNPCRes2 = "59 E9 A8 AD BA AA A1 D0 AC 59 E9 A3 AD BA AB 02 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC AF AD AD AD AD AD AD AD AD 59 E9 AF AD AB AF 59 E9 AF AD B9 A7 ";
        System.out.println(ShowUtil.show(clickNPCRes1));
        System.out.println(ShowUtil.showChar(clickNPCRes1));
        System.out.println(ShowUtil.show(clickNPCRes2));
        System.out.println(ShowUtil.showChar(clickNPCRes2));
        System.out.println("eventOk");
        String eventOK1 = "59 E9 AF AD B9 AB ";
        System.out.println(ShowUtil.show(eventOK1));
        System.out.println(ShowUtil.showChar(eventOK1));
        String eventOKRes = "59 E9 AF AD B9 A5 ";
        System.out.println(ShowUtil.show(eventOKRes));
        System.out.println(ShowUtil.showChar(eventOKRes));

        String clickNPCResStr2 = "59 E9 A8 AD BA AA A1 D0 AC 59 E9 A3 AD BA AB 02 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC AF AD AD AD AD AD AD AD AD 59 E9 AF AD AB AF 59 E9 AF AD B9 A7 ";
        System.out.println(ShowUtil.show(clickNPCResStr2));
        System.out.println(ShowUtil.showChar(clickNPCResStr2));
        //什么都没有的意思
        String clickNPCRes3 = "59 E9 AF AD B9 A5 ";
        System.out.println(ShowUtil.show(clickNPCRes3));
        System.out.println(ShowUtil.showChar(clickNPCRes3));
    }

    @Test
    public void testCreateJueseFlow2() {
        String req1 = "59 E9 A4 AD AB AC AB 8B A9 FE AF AC 97 ";
        String req2 = "59 E9 A9 AD B9 AF A5 AD ";
        String req3 = "59 E9 A4 AD AB AC A8 CF A9 D6 AF AC 1F ";
        String req4 = "59 E9 A4 AD AB AC A8 33 A9 22 AF AC 31 ";
        String req5 = "59 E9 A9 AD B9 A9 AC AD ";
        String req6 = "59 E9 A9 AD B9 A5 AC AD ";
        //点了老头之后
        List<String> reqList = new ArrayList<>();
        reqList.add(req1);
        reqList.add(req2);
        reqList.add(req3);
        reqList.add(req4);
        reqList.add(req5);
        reqList.add(req6);
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD BA 9D ");
        reqList.add("59 E9 AF AD A1 AC ");
        reqList.add("59 E9 AF AD B9 AB ");

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 移动触发
     */
    @Test
    public void testDilei() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A4 AD AB AC AA 93 A8 2A AD AC D7 ");//moveTo
        reqList.add("59 E9 A9 AD B9 A9 AF AD ");//1404 移动触发
        reqList.add("59 E9 A9 AD B9 A5 AF AD ");//1408  move
        reqList.add("59 E9 AF AD B9 AB ");//event ok
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testNandouxingjun() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD B9 AC AF AD ");
        reqList.add("59 E9 AE AD B9 A4 B2 ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD B9 AB ");

        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 A9 AD B9 AC AF AD ");
        reqList.add("59 E9 AF AD B9 AB  ");

        //选否
        reqList.add("59 E9 A4 AD AB AC AA 0F A8 DE AD AC 97 ");
        reqList.add("59 E9 A9 AD B9 A9 AF AD ");
        reqList.add("59 E9 A9 AD B9 A5 AF AD ");
        reqList.add("59 E9 A4 AD AB AF AA 28 A8 29 AD AC C1 ");
        reqList.add("59 E9 AE AD B9 A4 B2 ");
        reqList.add("59 E9 AF AD B9 AB ");


        reqList.add("59 E9 A4 AD AB AC AA 23 A8 DE AD AC 84 ");
        reqList.add("59 E9 A9 AD B9 A9 AF AD ");
        reqList.add("59 E9 A9 AD B9 A5 AF AD ");
        reqList.add("59 E9 AE AD B9 A4 B3 ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD BA 9D ");
        reqList.add("59 E9 AF AD A1 AC ");
        reqList.add("59 E9 AF AD B9 AB ");


        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 山上到涿郡广场
     */
    @Test
    public void testXiashanToZJGC() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A4 AD AB AC AA 23 A8 2A AD AE 14 ");
        reqList.add("59 E9 A9 AD B9 A9 AF AD ");
        reqList.add("59 E9 A9 AD B9 A5 AF AD ");
        reqList.add("59 E9 AE AD B9 A4 B3 ");
        reqList.add("59 E9 AF AD B9 AB ");
        reqList.add("59 E9 AF AD BA 9D ");
        reqList.add("59 E9 AF AD A1 AC ");
        reqList.add("59 E9 AF AD B9 AB ");

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testDelete() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 BC AD 8E AF AB 9C 9F 9E 99 98 9B AA 9C 9F 9E 99 98 9B 9A ");
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testZhenchashu() {
        System.out.println(ShowUtil.show("59 E9 A9 AD B9 AC AC AD "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
        System.out.println(ShowUtil.show("59 E9 AE AD B9 A4 B3 "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
        System.out.println(ShowUtil.show("59 E9 AE AD B9 A4 8D "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
        System.out.println(ShowUtil.show("59 E9 AE AD B9 A4 8D "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
        System.out.println(ShowUtil.show("59 E9 AE AD B9 A4 B2 "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
        System.out.println(ShowUtil.show("59 E9 AF AD B9 AB "));
    }

    @Test
    public void test0345_035E() {
        List<String> reqList = new ArrayList<>();
        reqList.add("F4 44 2F 00 03 5E C1 01 00 02 00 00 68 2F F6 01 3B 01 00 06 00 BC 35 7F 1A 1C AF 7D 1A 01 39 4A 00 00 00 00 65 00 00 B9 C8 A4 6A D8 70 31 35 30 33 38 35");
        reqList.add("F4 44 2D 00 03 45 2F 01 00 02 01 19 00 00 E7 2F F6 01 EF 01 00 00 00 20 CA 6C 20 3E AE 7D 1A 01 19 4D 00 00 00 00 00 65 00 00 B8 AF B4 B4 31 33 34");
        for (String str : reqList) {
            System.out.println(ShowUtil.showOrigin(str));
            System.out.println(ShowUtil.showCharOrigin(str));
        }
    }

    /**
     * 机关盒
     * 451 81 63 48
     * 59 E9 A6 AD EC AC EE EC E9 EF AD AD AD AD AD
     * 都攻击
     * 59 E9 A6 AD EC AC EE EC E9 EF AD AD AD AD AD
     * 暂停机关盒
     * 59 E9 AF AD EC AF
     */
    @Test
    public void testJiguanhe() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A6 AD EC AC EE EC E9 EF AD AD AD AD AD");
        //机关盒?
        reqList.add("59 E9 AF AD EC AF");
        //机关盒?
        reqList.add("59 E9 AF AD EF AE ");
        //chat
        reqList.add("59 E9 96 AD AF AF 18 C3 1E 1D 0B 05 08 F1 81 68 DA 07 42 05 62 08 63 0A F0 00 14 0B 5C 06 FC DB 9F 9D 9C 9B 83 9C 83 9C 83 9A 8D 80 8D C5 D9 D9 DD 97 82 82 DA DA DA 83 98 99 D4 CA 83 CE C3");
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testShangmaXiama() {
        String str = "59 E9 AB AD A2 A9 F0 EB AD AD \n" +
                "59 E9 AF AD A2 A8 \n" +
                "59 E9 A9 AD B9 AF 81 AD \n";
        System.out.println(ShowUtil.show2(str));

        System.out.println();
        String str2 = "59 E9 A9 AD B9 AF 81 AD \n" +
                "59 E9 A9 AD B9 AF 86 AD \n" +
                "59 E9 AB AD A2 A9 F0 EB AD AD \n";

        System.out.println(ShowUtil.show2(str2));
        System.out.println();

        String str3 = "59 E9 AB AD A2 A9 F0 EB AD AD 59 E9 A9 AD B9 AF 81 AD ";
        System.out.println(ShowUtil.show2(str3));
        System.out.println();
    }

    @Test
    public void testShoujingpo() {
        String str = "59 E9 A9 AD B9 AC B8 AD \n" +
                "59 E9 AE AD B9 A4 B3 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AB AD BD AC AF AD AD AD \n" +
                "59 E9 AB AD BD AC AE AD AD AD \n" +
                "59 E9 AB AD BD AC A9 AD AD AD \n" +
                "59 E9 AB AD BD AC AE AD AD AD \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AB ";
        System.out.println(ShowUtil.show2(str));
    }

    @Test
    public void testYanzhouzhengduozhanClickCaocao() {
        String str = "59 E9 AF AD B9 AB \n" +
                "59 E9 AE AD B9 A4 B3 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AB ";
        System.out.println(ShowUtil.show2(str));
    }

    @Test
    public void testBuyItem() {
        String str = "F4 44 04 00 14 01 01 00 \n" +
                "F4 44 03 00 14 09 1E \n" +
                "F4 44 02 00 14 06 \n" +
                "F4 44 05 00 1B 01 01 01 00 \n" +
                "F4 44 03 00 14 09 28 \n" +
                "F4 44 02 00 14 06 ";
        System.out.println(ShowUtil.show2(str));
    }


    @Test
    public void testXiandou() {
        String str = "59 E9 A9 AD B9 AC AC AD  \n" +
                "59 E9 AF AD B9 AB  \n" +
                "59 E9 AE AD B9 A4 B3  \n" +
                "59 E9 AF AD B9 AB  \n" +
                "59 E9 AF AD B9 AB  \n" +
                "59 E9 AF AD B9 AB  ";
        System.out.println(ShowUtil.show2(str));
    }

    @Test
    public void testKezhanMaixunrou() {
        String str = "59 E9 A9 AD B9 AC AC AD \n" +
                "59 E9 AE AD B9 A4 B3 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 A8 AD B6 AC AC AC AD ";
        System.out.println(ShowUtil.show2(str));
    }

    /**
     * [2017-06-17 12:42:28.924] 41590 41590req:F4 44 04 00 14 01 01 00
     * [2017-06-17 12:42:28.925] 41590 41590req:F4 44 02 00 14 06
     * [2017-06-17 12:42:28.925] 41590 41590req:F4 44 03 00 14 09 1E
     * [2017-06-17 12:42:28.926] 41590 41590req:F4 44 02 00 14 06
     * [2017-06-17 12:42:28.926] 41590 41590req:F4 44 02 00 14 06
     * [2017-06-17 12:42:28.926] 41590 41590req:F4 44 05 00 1B 01 01 02 00
     * [2017-06-17 12:42:28.926] 41590 41590req:F4 44 03 00 14 09 28
     * [2017-06-17 12:42:28.927] 41590 41590req:F4 44 02 00 14 06
     */
    @Test
    public void testBuyDao() {
        String str = "59 E9 A9 AD B9 AC AC AD \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AE AD B9 A4 B3 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 A8 AD B6 AC AD AC AD ";
        System.out.println(ShowUtil.show2(str));
    }

    /**
     * F4 44 04 00 14 01 01 00
     * F4 44 03 00 14 09 1F
     * F4 44 02 00 14 06
     * F4 44 02 00 1F 01
     */
    @Test
    public void testKezhan() {
        String str = "59 E9 A9 AD B9 AC AC AD \n" +
                "59 E9 AE AD B9 A4 B2 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B2 AC ";
        System.out.println(ShowUtil.show2(str));
    }

    /**
     * 49910 天祈阁
     */
    @Test
    public void testXiandoulingjiang() {
        String str = "59 E9 A4 AD AB AC AD 97 A9 32 AC E6 8C  \n" +
                "59 E9 A9 AD B9 A5 AF AD  \n" +
                "59 E9 AE AD B9 A4 B3  \n" +
                "59 E9 AF AD B9 AB   \n" +
                "59 E9 AF AD BA 9D   \n" +
                "59 E9 AF AD A1 AC   \n" +
                "59 E9 AF AD B9 AB";

        System.out.println(ShowUtil.show2(str));

        //49910 天祈阁 点击盆 选择1
        String str2 = "59 E9 A9 AD B9 AC AC AD \n" +
                "59 E9 AE AD B9 A4 B3 \n" +
                "59 E9 AF AD B9 AB ";
        System.out.println(ShowUtil.show2(str2));
    }
}
