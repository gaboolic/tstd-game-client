import org.junit.Test;
import tk.gbl.show.ShowUtil;

/**
 * Date: 2017/3/27
 * Time: 15:35
 *
 * @author Tian.Dong
 */
public class ProtocolHeadTest {

    @Test
    public void testRes(){
        System.out.println("connect:");
        String connectRes = "59 E9 A9 AD AC A4 C8 AD ";
        System.out.println(ShowUtil.show(connectRes));
        System.out.println(ShowUtil.showChar(connectRes));

        System.out.println("loginRes");
        String loginResSuccess = "59 E9 AF AD B9 A5 59 E9 A8 AD B5 A8 AF AD AD 59 E9 A8 AD B5 A8 9C AC AD 59 E9 A8 AD B5 A8 CF AC AD 59 E9 A8 AD B5 A8 3C AC AD 59 E9 A8 AD B5 A8 3F AC AD 59 E9 A8 AD B5 A8 DC AE AD 59 E9 A1 AD A5 AC EF AC AD AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC EE AC AD AD AD AD AD AD AD AD 59 E9 A7 AD E3 AC AD AD AD AD AD AD AD AD 59 E9 AE AD B9 8C ";
        System.out.println(ShowUtil.show(loginResSuccess));
        System.out.println(ShowUtil.showChar(loginResSuccess));

//        String loginResSuccess2 = "59 E9 AF AD B9 A5 59 E9 A8 AD B5 A8 AF AD AD 59 E9 A8 AD B5 A8 9C AC AD 59 E9 A8 AD B5 A8 CF AC AD 59 E9 A8 AD B5 A8 3C AC AD 59 E9 A8 AD B5 A8 3F AC AD 59 E9 A8 AD B5 A8 DC AE AD 59 E9 A1 AD A5 AC EF AC AD AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC EE AC AD AD AD AD AD AD AD AD 59 E9 A7 AD E3 AC AD AD AD AD AD AD AD AD 59 E9 AE AD B9 8C ";
//        System.out.println(ShowUtil.show(loginResSuccess2));
//        System.out.println(ShowUtil.showChar(loginResSuccess2));
//
//        String loginResSuccess3 = "59 E9 AF AD B9 A5 59 E9 A8 AD B5 A8 AF AD AD 59 E9 A8 AD B5 A8 9C AC AD 59 E9 A8 AD B5 A8 CF AC AD 59 E9 A8 AD B5 A8 3C AC AD 59 E9 A8 AD B5 A8 3F AC AD 59 E9 A8 AD B5 A8 DC AE AD 59 E9 A1 AD A5 AC EF AC AD AD AD AD AD AD AD AD 59 E9 A1 AD A5 AC EE AC AD AD AD AD AD AD AD AD 59 E9 A7 AD E3 AC AD AD AD AD AD AD AD AD 59 E9 AE AD B9 8C AD 59 E9 A1 AD A5 AC 7B AC AC AD AD AD AD AD AD AD 59 E9 80 AD AE ED 13 AC AD AC AD AD CA 87 DF AE BA AF AD AD AD B1 02 D0 B7 93 03 D0 B7 AC B4 E0 AD AD AD AD C8 AD AD CC CF CE C9 C8 CB CA 9C 9F 9E 59 E9 95 AD A9 09 E1 AD AD AC AF 65 AD AD AD DA F6 AE FF A9 AD A2 AD F4 26 D0 BE 93 03 D0 B7 AB B2 FC E5 E0 98 84 48 F9 77 F5 9E F6 AD AD AD AD AD C8 AC AD 09 7E 08 78 12 C3 1E 1A 59 E9 9B AD A9 0E C0 AD AD AF AF 65 AC AD AD F0 00 AE CC AE AD AD AD 99 3E 2C B7 B1 02 D0 B7 AB B4 FC 44 E6 98 84 48 F9 60 F5 9B F6 AD AD AD AD AD C8 AD AD 1E DF 0A EC 07 1C 59 E9 9E AD A9 40 4E AD AD AC AF 65 AD AD 30 D3 B3 A8 3E AE AD AC AD 21 BE 2B B7 93 03 D0 B7 A8 7C E2 44 E6 98 84 14 FE 18 F4 AD AD AD AD AD C8 AD AD C0 C2 C2 C2 C3 59 E9 9B AD A9 E4 E9 AD AD AC AF 65 AC AD 0E C1 D8 AF 1D AF AD AD AD B1 02 D0 B7 93 03 D0 B7 AB 7C E2 B8 E0 CF EB 15 FE 13 F5 96 F7 AD AD AD AD AD C8 AD AD 16 5C 17 DC 6B D0 59 E9 9B AD A9 22 4A AD AD AC AF 65 AD AD 3B DD BF A9 AC A8 AD AD AD 21 03 2C B7 8D 1C D0 B7 AB 7D E2 4A E6 F4 EA BD F8 18 F4 83 F6 AD AD AD AD AD C8 AD AD 17 6F 0A 67 1C C7 59 E9 9B AD A9 6B 4A AD AD AC AC 65 AD AD 01 D7 E6 A8 B9 A8 AD AA AD A6 8E 14 8A 93 22 DE 8D AB C8 58 4B E6 F1 EA CE 58 19 F4 9E F6 AD AD AD AD AD C8 AC AD 02 5D 09 C1 6D DE 59 E9 9B AD A9 35 19 AC AD AC AE C4 AD AD D0 F8 40 AF 9C A9 AD A8 AD 71 27 06 A2 93 03 D0 B7 A8 4D E2 02 E1 57 87 6B FE 23 F4 AD AD AD AD AD C8 AC AD 05 07 18 E6 00 D5 16 43 59 E9 9B AD A9 95 4A AD AD AF AC D0 AD AD 3B DD BF A9 AC A8 AD AD AD B1 02 D0 B7 B1 02 D0 B7 AB 7C E2 44 E6 95 4F 48 F9 77 F5 5D F4 AD AD AD AD AD C8 AD AD 05 47 6D 71 6D 71 59 E9 99 AD A9 DE 94 AD AD AC AE 23 AD AD 5B CD 6B AF D6 AE AD A5 AD 71 C3 31 98 93 03 D0 B7 AB 7C E2 44 E6 7E 4C 14 FE 77 F5 D5 F7 AD AD AD AD AD C8 AF AF 11 7B 1F E0 59 E9 81 AD A9 85 EE AD AD AC AF 15 AD AD 64 D5 00 AE 6E AE AD A4 AD 39 87 D6 B7 93 03 D0 B7 AD AD AD AD AD AD C8 AC AD 0B CC 1A 74 1D C1 01 FD 59 E9 9F AD A9 35 14 AC AD AC AE D0 AD AD 3B DD BF A9 AC A8 AD AD AD B1 02 D0 B7 93 03 D0 B7 A8 7C E2 44 E6 BD F8 0D FA 9B F6 AD AD AD AD AD C8 AD AD 03 FE 01 58 59 E9 9A AD A9 36 14 AC AD AC AE D0 AD AD 3B DD BF A9 AC A8 AD AD AD B1 02 D0 B7 93 03 D0 B7 A8 7C E2 44 E6 48 F9 0C FA 9B F6 AD AD AD AD AD C8 AD AD 08 65 08 57 09 59 19 14 98 59 E9 9A AD A9 BE EF AD AD AF AE 0F AD AD AD DA F6 AE FF A9 AD BD AD 29 31 45 A3 B1 02 D0 B7 AB 7C E2 44 E6 7E 4C 14 FE 60 F5 9F F6 AD AD AD AD AD C8 AF AF E6 DF D4 DE D9 CC C1 59 E9 9F AD A9 B5 EF AD AD AF AE 0F AD AD AD DA F6 AE FF A9 AD BE AD 91 FA 65 8D B1 02 D0 B7 A8 7C E2 02 E1 7E 4C 14 FE 0C FA AD AD AD AD AD C8 AF AF E3 C4 C6 C2 59 E9 99 AD A9 A0 EF AD AD AC AF 0F AD AD AD DA F6 AE FF A9 AD BD AD 11 0C 88 8B 93 03 D0 B7 AB 7C E2 03 E1 7F 4C 02 FE 0C FA 9F F6 AD AD AD AD AD C8 AF AF 17 56 08 E1 59 E9 9A AD A9 15 EF AD AD AF AF 0F AD AD AD DA F6 AE FF A9 AD B4 AD A9 2D DB 8A B1 02 D0 B7 AB 7C E2 44 E6 7F 4C 02 FE 60 F5 5D F4 AD AD AD AD AD C8 AF AF E7 C8 DE DE C4 CE CC 59 E9 99 AD A9 A4 EF AD AD AC AE 0F AD AD AD DA F6 AE FF A9 AD BC AD F9 12 32 A2 93 03 D0 B7 AB 7C E2 02 E1 7E 4C 14 FE 77 F5 9F F6 AD AD AD AD AD C8 AF AF 1E 1B 1C F2 59 E9 9E AD A9 3D 82 AC AD AF AE 65 AD AD 3B DD BF A9 AC A8 AD AB AD F1 C2 D5 B7 B1 02 D0 B7 AB CC 58 CF 58 CC 4F CE 58 C9 58 F7 F7 AD AD AD AD AD C8 AD AD E1 0C EE 59 E9 99 AD A9 A7 13 AC AD AC AF E4 AD AD 68 C5 22 AE 68 AE AD AD AD 19 D5 D6 B7 93 03 D0 B7 AB 15 E2 7C E6 F5 E4 0C FE 19 F5 5D F4 AD AD AD AD AD C8 AD AD 11 EE 15 D9 59 E9 99 AD A9 0E 35 AC AD AF AE 6C AD AD E5 2D 5A A8 C6 A8 AD A3 AD 41 4D A9 80 B1 02 D0 B7 AB C8 58 CB 58 F5 4F CA 58 18 F4 E0 F6 AD AD AD AD AD C8 AF AF 0B FA 01 D4 59 E9 9F AD A9 97 30 AC AD AF AC 25 AD AD 5B CD 6B AF D6 AE AD A3 AD 71 87 AA 80 B1 02 D0 B7 AB C8 58 CB 58 7C 4C BC F8 19 F4 E0 F6 AD AD AD AD AD C8 AF AF 0B C5 59 E9 99 AD A9 1E CB AD AD AC AE 65 AD AD ED 2D 14 A8 EF AB AD BC AD 79 7A 4B 8D 93 03 D0 B7 A8 C8 58 CB 58 F5 4F CA 58 0B F7 AD AD AD AD AD C8 AF AF 15 1E 05 6B 07 55 59 E9 99 AD A9 9F 1D AC AD AC AF 26 AD AD E4 F0 B3 A3 F3 AF AD AD AD B1 02 D0 B7 93 03 D0 B7 A8 7C E2 A0 E0 CC EB 77 F9 3B FA AD AD AD AD AD C8 AD AD 10 C0 1E C2 07 1C 59 E9 9B AD A9 51 06 AC AD AC AF 13 AF AD F9 F8 14 AF 0F AC AD A9 AD B1 02 D0 B7 93 03 D0 B7 A8 7C E2 44 E6 F6 EA 14 FE 13 F5 AD AD AD AD AD C8 AD AD 01 1D 0A 77 00 57 07 F2 59 E9 9F AD A9 58 02 AC AD AF AC 0F AD AD F1 D5 F2 A8 3C AE AD AB AD D9 37 28 B7 B1 02 D0 B7 A8 BA FC 62 E6 61 F9 2E FA FC F7 AD AD AD AD AD C8 AD AD 07 F4 10 73 59 E9 9F AD A9 16 36 AC AD AF AF 65 AD AD 9B F8 D9 A7 02 AF AD AC AD B1 02 D0 B7 B1 02 D0 B7 A9 5F FD A8 E0 77 F9 60 F5 AD AD AD AD AD C8 AD AD 68 77 09 FF 12 D1 59 E9 99 AD A9 D1 B1 AC AD AF AF 1C AD AD 4D C1 8C AA F9 AF AD AB AD 43 07 D7 B7 52 64 37 96 A9 6A E2 72 E6 02 FE 6E F5 AD AD AD AD AD C8 AD AD 1A 01 18 75 01 D4 02 10 59 E9 87 AD A9 6D 01 AC AD AC AE 2B AD AD AF CC F4 AE 39 AE AD A4 AD 09 63 9E 8B 93 03 D0 B7 AC E0 F6 AD AD AD AD AD C8 AC AD 08 00 08 00 59 E9 95 AD A9 05 93 AD AD AC AE 65 AD AD 41 C9 61 AE B3 AB AD A4 AD E4 EC 8B B2 93 03 D0 B7 AB 7C E2 CB 58 62 4C 15 FE 18 F4 9E F6 AD AD AD AD AD C8 AC AD 68 FC 14 E9 09 DD 09 C1 59 E9 97 AD A9 67 9D AD AD AC AF 65 AD AD 01 D7 9D A9 10 AF AD AD AD B1 02 D0 B7 93 03 D0 B7 AB B4 FC B9 E0 98 84 15 FE 66 F5 0B F7 AD AD AD AD AD C8 AD AD 68 FC 14 E9 07 17 09 DD 09 C1 59 E9 9C AD A9 20 00 ";
//        System.out.println(ShowUtil.show(loginResSuccess3));
//        System.out.println(ShowUtil.showChar(loginResSuccess3));

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

        System.out.println("move");
        String moveStr = "59 E9 A9 AD B9 A5 AC AD ";//邺城城门 12061
        System.out.println(ShowUtil.show(moveStr));
        System.out.println(ShowUtil.showChar(moveStr));

        System.out.println("moveRes");
        //12061 2F1D
        String moveResStr = "59 E9 AF AD B9 AA 59 E9 A0 AD A1 0D 17 AC AD B0 82 B3 A8 CB AE AC AD ";
        System.out.println(ShowUtil.show(moveResStr));
        System.out.println(ShowUtil.showChar(moveResStr));
    }

    @Test
    public void testReq() {
        System.out.println("connect:");
        String connectReq = "59 E9 AC AD AD ";
        System.out.println(ShowUtil.show(connectReq));
        System.out.println(ShowUtil.showChar(connectReq));

        String connectRes = "59 E9 A9 AD AC A4 C8 AD ";
        System.out.println(ShowUtil.show(connectRes));
        System.out.println(ShowUtil.showChar(connectRes));

        System.out.println("login:");
        String loginReq1 = "59 E9 BD AD AC AB AA 1F AC AD DA C3 7C AD 9B 9B 9B 9B 9B 9B 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD ";
        System.out.println(ShowUtil.show(loginReq1));
        System.out.println(ShowUtil.showChar(loginReq1));

        System.out.println("chat:");
        String chatReqStr1 = "59 E9 A5 AD AF AF 14 65 09 C7 12 72 ";
        System.out.println(ShowUtil.show(chatReqStr1));
        System.out.println(ShowUtil.showChar(chatReqStr1));

        String chatReqStr2 = "59 E9 AE AD AF AF CC ";
        System.out.println(ShowUtil.show(chatReqStr2));
        System.out.println(ShowUtil.showChar(chatReqStr2));

        String chatReqStr3 = "59 E9 AE AD AF AF 8C ";
        System.out.println(ShowUtil.show(chatReqStr3));
        System.out.println(ShowUtil.showChar(chatReqStr3));

        System.out.println("checkName:");
        String setNameResStrNextA = "59 E9 AE AD A4 AF CC ";
        String setNameResNextA = ShowUtil.show(setNameResStrNextA);
        String setNameResCharNextA = ShowUtil.showChar(setNameResStrNextA);

        System.out.println(setNameResStrNextA);
        System.out.println(setNameResNextA);
        System.out.println(setNameResCharNextA);

        //嗷大猫 B9 C8 A4 6A BF DF
        String setNameResStrNextAodamao = "59 E9 A5 AD A4 AF 14 65 09 C7 12 72 ";
        String setNameResNextAodamao = ShowUtil.show(setNameResStrNextAodamao);
        String setNameResCharNextAodamao = ShowUtil.showChar(setNameResStrNextAodamao);
        System.out.println(setNameResNextAodamao);
        System.out.println(setNameResCharNextAodamao);

        System.out.println("setNameAndInit");
        String setNameAndInitReqStr = "59 E9 8E AD A4 AC AC AD AD AD B1 02 D0 B7 93 03 D0 B7 AC AC AF AE AD AD AD AB 9C 9F 9E 99 98 9B AB 9C 9F 9E 99 98 9B ";
        String setNameAndInitReq = ShowUtil.show(setNameAndInitReqStr);
        String setNameAndInitReqChar = ShowUtil.showChar(setNameAndInitReqStr);

        System.out.println(setNameAndInitReqStr);
        System.out.println(setNameAndInitReq);
        System.out.println(setNameAndInitReqChar);

        System.out.println("setIdCard:");
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

        System.out.println("clickNPC");
        String clickNPCStr0 = "59 E9 A9 AD B9 AC AC AD ";
        System.out.println(ShowUtil.show(clickNPCStr0));
        System.out.println(ShowUtil.showChar(clickNPCStr0));

        String clickNPCStrFanmaiji = "59 E9 A9 AD B9 AC AB AD  ";
        System.out.println(ShowUtil.show(clickNPCStrFanmaiji));
        System.out.println(ShowUtil.showChar(clickNPCStrFanmaiji));

        System.out.println("moveTo");
        String moveToStr1 = "59 E9 A4 AD AB AC AD C3 AF FE AF A7 34 ";
        System.out.println(ShowUtil.show(moveToStr1));
        System.out.println(ShowUtil.showChar(moveToStr1));

        String moveToStr12 = "59 E9 A4 AD AB AC A9 8D AE 59 AC AD AD ";//800 500
        System.out.println(ShowUtil.show(moveToStr12));
        System.out.println(ShowUtil.showChar(moveToStr12));

        String moveToStr13 = "59 E9 A4 AD AB AC A9 3D AC 3D AC AD AD  ";//400 400
        System.out.println(ShowUtil.show(moveToStr13));
        System.out.println(ShowUtil.showChar(moveToStr13));

        System.out.println("move");
        String moveStr = "59 E9 A9 AD B9 A5 AC AD ";//邺城城门 12061
        System.out.println(ShowUtil.show(moveStr));
        System.out.println(ShowUtil.showChar(moveStr));

        System.out.println("moveRes");
        //12061 2F1D
        String moveResStr = "59 E9 AF AD B9 AA 59 E9 A0 AD A1 0D 17 AC AD B0 82 B3 A8 CB AE AC AD ";
        System.out.println(ShowUtil.show(moveResStr));
        System.out.println(ShowUtil.showChar(moveResStr));
    }
}
