import org.junit.Test;
import tk.gbl.show.ShowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/4/3
 * Time: 21:57
 *
 * @author Tian.Dong
 */
public class GoodsTest {
    @Test
    public void testGetXiangguaFlow() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD B9 AC 86 AD ");//req 1
        reqList.add("59 E9 AF AD AB AF 59 E9 BC AD B9 AC AD AD AD AC AC AE 86 AD AD AD AD AD AD 1B 06 ");//res 1
        reqList.add("59 E9 AF AD B9 AB ");//req 2
        reqList.add("59 E9 BC AD B9 AC AD AD AD AF AC AA AD AD AD AD AD AD AD 1A 06 ");//res 2
        reqList.add("59 E9 AF AD B9 AB ");//req 3
        reqList.add("59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC AC AD AD AD AD AD AD AD AD 59 E9 AF AD B9 A7 ");//res 3
        reqList.add("59 E9 AF AD B9 AB ");//req4
        reqList.add("59 E9 AB AD AC AC 90 82 AC AD ");//res4
        reqList.add("59 E9 AF AD B9 A5 ");//res5
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testJianXiangguaFlow() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD BA AF AC AD ");//req 1

        reqList.add("59 E9 A9 AD BA AF AE AD ");//req 1
        reqList.add("59 E9 A9 AD BA AF AC AD ");//req 1
        reqList.add("59 E9 A9 AD BA AF AF AD ");//req 1

        reqList.add("59 E9 A9 AD BA AF AF AD ");//req 1
        reqList.add("59 E9 A9 AD BA AF AC AD ");//req 1


        reqList.add("59 E9 A9 AD BA AF F1 AD ");//req 1
        reqList.add("59 E9 A9 AD BA AF 96 AD ");//req 1
        reqList.add("59 E9 A9 AD BA AF E4 AD ");//req 1

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testJianXingguaRes() {
        List<String> reqList = new ArrayList<>();
//        reqList.add("F4 44 05 00 17 02 38 00 01 F4 44 0E 00 17 06 BC 65 01 00 00 00 00 00 00 00 00 00 F4 44 0C 00 17 8D 01 04 00 00 00 00 00 00 00 00 F4 44 08 00 17 03 BC 65 94 03 93 04");
//        reqList.add("F4 44 05 00 17 02 39 00 01 F4 44 0E 00 17 06 BC 65 01 00 00 00 00 00 00 00 00 00 F4 44 0C 00 17 8D 01 04 00 00 00 00 00 00 00 00 F4 44 08 00 17 03 BC 65 B2 03 7B 04");
//        reqList.add("F4 44 05 00 17 02 3A 00 01 F4 44 0E 00 17 06 BC 65 01 00 00 00 00 00 00 00 00 00 F4 44 0C 00 17 8D 01 04 00 00 00 00 00 00 00 00 F4 44 08 00 17 03 BC 65 97 03 91 04");
//        reqList.add("F4 44 05 00 17 02 3C 00 01 F4 44 0E 00 17 06 BC 65 01 00 00 00 00 00 00 00 00 00 F4 44 0C 00 17 8D 01 04 00 00 00 00 00 00 00 00 F4 44 08 00 17 03 BC 65 B2 03 8E 04");

        reqList.add("59 E9 A8 AD BA AF 55 AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC AC AD AD AD AD AD AD AD AD ");
        reqList.add("59 E9 A4 AD B5 A5 23 25 AD AD AF AD AD 59 E9 A4 AD B5 A5 F8 24 AD AD AF AD AD 59 E9 A4 AD B5 A5 F9 24 AD AD AF AD AD 59 E9 A4 AD B5 A5 3D 25 AD AD AF AD AD 59 E9 A4 AD B5 A5 22 25 AD AD AF AD AD 59 E9 9B AD AE 6E 00 AC AD AF AF 68 AC AD 04 CC 87 AE DB AF AD AD AD B1 02 D0 B7 B1 02 D0 B7 AB B4 FC 02 E1 7D 97 BE F8 77 F5 5D F4 AD AD AD AD AD C8 AD AD 07 EB 1D C8 02 5E 59 E9 BF AD A8 AD 6E 00 AC AD B4 FC 02 E1 7D 97 BE F8 77 F5 5D F4 ");
        reqList.add("59 E9 99 AD AE 56 0A AC AD AC AC 65 AD AD 48 94 E7 A6 03 AB AD AE AD 89 F7 29 B7 93 03 D0 B7 AB 7C E2 44 E6 99 84 48 F9 60 F5 0B F7 AD AD AD AD AD C8 AD AD 16 68 16 68 59 E9 BF AD A8 AD 56 0A AC AD 7C E2 44 E6 99 84 48 F9 60 F5 0B F7 ");
        reqList.add("59 E9 A8 AD BA AF 56 AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC AC AD AD AD AD AD AD AD AD ");

        reqList.add("59 E9 A8 AD BA AF 3B AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC AF AD AD AD AD AD AD AD AD ");
        reqList.add("59 E9 A8 AD BA AF 38 AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC AF AD AD AD AD AD AD AD AD ");
        reqList.add("59 E9 A8 AD BA AF 3E AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC AF AD AD AD AD AD AD AD AD ");

        reqList.add("59 E9 A8 AD BA AF 53 AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC AE AD AD AD AD AD AD AD AD ");
        reqList.add("59 E9 A8 AD BA AF 69 AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC AE AD AD AD AD AD AD AD AD ");
        reqList.add("59 E9 A8 AD BA AF 7B AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC AE AD AD AD AD AD AD AD AD ");

        reqList.add("59 E9 A8 AD BA AF 53 AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC BE AD AD AD AD AD AD AD AD ");
        reqList.add("59 E9 A8 AD BA AF 56 AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC BE AD AD AD AD AD AD AD AD ");
        reqList.add("59 E9 A8 AD BA AF 55 AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC BE AD AD AD AD AD AD AD AD ");

        reqList.add("59 E9 A8 AD BA AF AF AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC B4 AD AD AD AD AD AD AD AD ");
        reqList.add("59 E9 A8 AD BA AF AF AD AC 59 E9 A3 AD BA AB 11 C8 AC AD AD AD AD AD AD AD AD AD 59 E9 A1 AD BA 20 AC B5 AD AD AD AD AD AD AD AD ");
        for (String str : reqList) {
//            System.out.println(ShowUtil.showOrigin(str));
//            System.out.println(ShowUtil.showCharOrigin(str));

            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testRengXiangguaFlow() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD BA AE AF AC ");//扔第二格
        reqList.add("59 E9 A9 AD BA AE AC AC ");//扔第1格

        reqList.add("59 E9 A9 AD BA AE A6 A8 ");//扔第11格 5个

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 扔 捡
     */
    @Test
    public void testRengXiangguaFlow2() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD BA AE AC AC ");
        reqList.add("59 E9 A9 AD BA AE AC AC ");
        reqList.add("59 E9 A9 AD BA AE AC AC ");
        reqList.add("59 E9 A9 AD BA AF AC AD ");
        reqList.add("59 E9 A9 AD BA AE AC AC ");
        reqList.add("59 E9 A9 AD B9 AF 86 AD ");
        reqList.add("59 E9 A9 AD BA AF AC AD ");

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testSellXiangguaFlow() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD B9 AC AF AD ");//点击NPC
        reqList.add("59 E9 AE AD B9 A4 B2 ");//choose
        reqList.add("59 E9 AF AD B9 AB ");//eventok
        reqList.add("59 E9 A9 AD B6 AF AC AC ");//F4 44 04 00 1B 02 01 01
        reqList.add("59 E9 A9 AD B6 AF AF AF ");//F4 44 04 00 1B 02 02 02

        reqList.add("59 E9 A9 AD B6 AF A8 9F ");//5 50
        reqList.add("59 E9 A9 AD B6 AF BA 9F ");//17
        reqList.add("59 E9 A9 AD B6 AF B5 9F ");//18
        reqList.add("59 E9 A9 AD B6 AF B4 9F ");//19

        reqList.add("59 E9 AF AD B9 AB ");//eventok

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testSellXiangguaResFlow() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AF AD AB AF 59 E9 BC AD B9 AC AD AD AD AC AB AE AF AD AD AD AD AD AD A9 AD ");//点击NPC
        reqList.add("59 E9 AF AD B6 AE 59 E9 AF AD B9 A4 ");//点击NPC

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testSendItem() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AF AD A7 AC");
        reqList.add("59 E9 B9 AD B4 B9 5E F8 AD AD 11 17 AC AD AC AE AD AD AD AD AD AD AD AD ");

        //1 16 21
        //F4 44 14 00 19 14 F3 55 00 00 BC BA 01 00 01 01 10 02 15 03 00 00 00 00
        reqList.add("59 E9 B9 AD B4 B9 5E F8 AD AD 11 17 AC AD AC AC BD AF B8 AE AD AD AD AD ");
        reqList.add("59 E9 AF AD B4 B8 ");

        //1 1   2 18   3 2   4 50
        reqList.add("59 E9 B9 AD B4 B9 5E F8 AD AD 11 17 AC AD AC AC AF BF AE AF A9 9F AD AD ");
        reqList.add("59 E9 B9 AD B4 B9 5E F8 AD AD 11 17 AC AD AC AC BD AF B8 AE AD AD AD AD ");

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }

    }

    @Test
    public void testUse() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AB AD A0 AC E9 13 AC AD ");
        reqList.add("59 E9 AB AD A0 AC E9 13 AC AD ");
        reqList.add("59 E9 AB AD BA A2 AC 9F AD AD ");
        reqList.add("59 E9 AB AD BA A2 AF 9F AD AD ");
        reqList.add("59 E9 AB AD A0 AC E9 13 AC AD  ");
        reqList.add("59 E9 AB AD A0 AC E9 13 AC AD  ");
        reqList.add("59 E9 AB AD BA A2 AE 8C AD AD  ");
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testTrade() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AB AD B4 AC ED 13 AC AD  ");//发起
        reqList.add("59 E9 AB AD A0 AC E9 13 AC AD ");//
        reqList.add("59 E9 AB AD A0 AC E9 13 AC AD  ");//
        reqList.add("59 E9 AA AD B4 AF AD AD AD AD AE 59 E9 AE AD B4 AE AC  ");//
        reqList.add("59 E9 AB AD B4 AF AD AD AD AD 59 E9 AE AD B4 AE AC  ");//
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * F4 44 09 00 19 02 00 00 00 00 01 02 05 F4 44 03 00 19 03 01
     * ô  D                                   ô  D
     * F4 44 07 00 19 02 00 00 00 00 02 F4 44 03 00 19 03 01
     * ô  D                             ô  D
     */
    @Test
    public void testTrade2() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A4 AD B4 AF AD AD AD AD AC AF A8 59 E9 AE AD B4 AE AC ");
        reqList.add("59 E9 AA AD B4 AF AD AD AD AD AF 59 E9 AE AD B4 AE AC ");

        reqList.add("59 E9 A6 AD B4 AF AD AD AD AD AC AF AE A9 A8 59 E9 AE AD B4 AE AC ");
        reqList.add("59 E9 A4 AD B4 AF AD AD AD AD AC AF AE 59 E9 AE AD B4 AE AC ");

        reqList.add("59 E9 A4 AD B4 AF AD AD AD AD AC AF AE 59 E9 AE AD B4 AE AC ");
        reqList.add("59 E9 AB AD B4 AF AD AD AD AD 59 E9 AE AD B4 AE AC ");

        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 交易武将
     */
    @Test
    public void testTradePet() {
        String string = "59 E9 AB AD B4 A7 7E 6A AC AD \n" +
                "59 E9 AA AD B4 A6 AD AD AD AD A9 \n" +
                "59 E9 AE AD B4 A1 AC \n" +
                "\n" +
                "59 E9 AA AD B4 A6 AD AD AD AD AD \n" +
                "59 E9 AE AD B4 A1 AC \n" +
                "\n" +
                "\n" +
                "59 E9 AB AD B4 A7 DB 0F AD AD \n" +
                "59 E9 AA AD B4 A6 AD AD AD AD A9 \n" +
                "59 E9 AE AD B4 A1 AC \n" +
                "\n" +
                "59 E9 AB AD B4 A7 DB 0F AD AD \n" +
                "\n" +
                "59 E9 AA AD B4 A6 AD AD AD AD AD \n" +
                "59 E9 AA AD B4 A6 AD AD AD AD AF \n" +
                "59 E9 AE AD B4 A1 AC \n";
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
    public void testSplit() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A8 AD BA A7 A9 A9 AC ");//4
        reqList.add("59 E9 A8 AD BA A7 AA AB AF ");//6
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }
}
