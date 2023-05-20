package tk.gbl.helper.datfilebreak;

import tk.gbl.show.AddZeroUtil;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.ZHConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 問號51418
 * 鋤頭
 * Date: 2017/5/24
 * Time: 17:46
 *
 * @author Tian.Dong
 */
public class ItemDatFileBreak {
    public static int jiemi(int num) {
        return (num ^ 61379) - 9;
    }

    public static int jiemilei(int num) {
        return (num ^ 154) - 9;
    }

    public static void main3(String[] args) {
        System.out.println(144 ^ (1+9));
        System.out.println(145 ^ (2+9));
        System.out.println(170 ^ (39+9));
        //51417 xor (10001 -x ) = 51427 xor (10007 -x )

        for (int i = 0; i < 10001; i++) {
            if ((144 ^ (1 + i)) == (145 ^ (2 + i))) {
                if ((144 ^ (1 + i)) == (170 ^ (39 + i))) {
                    System.out.println(i);
                }
            }
        }

        /*for (int i = 0; i < 10001; i++) {
            if ((51417 ^ (10001 + i)) == (51416 ^ (10002 + i))) {
                if ((51417 ^ (10001 + i)) == (51427 ^ (10007 + i))) {
                    if ((51417 ^ (10001 + i)) == (51428 ^ (10014 + i))) {
                        System.out.println(i);
                    }
                }
            }
//            System.out.println((51417 ^ (10001 + i)) +","+ (51416 ^ (10002 + i)));
        }*/
    }

    public static void main(String[] args) throws IOException {
//        File file = new File("E:\\吞食天地资料\\data\\Npc.Dat");
        File file = new File("E:\\吞食天地资料\\data\\Item.Dat");
        FileInputStream fileInputStream = new FileInputStream(file);
        int b = 0;
        List<Byte> byteList = new ArrayList<>();
        while ((b = fileInputStream.read()) != -1) {
            byteList.add((byte) b);
        }
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteList.get(i);
        }

        int pos = 0;
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xFF) > 10) {
                pos = i;
                break;
            }
        }
        pos -= 6;
        System.out.println(pos);


        String str1 = genStr(pos, bytes);
        System.out.print(str1);
        int id1 = MultiByteToIntUtil.from(bytes[pos + 11] & 0xFF, bytes[pos + 12] & 0xFF);
        System.out.println(jiemi(id1));

        byte[] zhangjiaoBytes = new byte[1000];
        System.arraycopy(bytes, pos, zhangjiaoBytes, 0, 1000);

        int[] zhangjiaoInts = ByteArrayToIntArrayUtil.transform(zhangjiaoBytes, zhangjiaoBytes.length);
        System.out.println(ShowUtil.showOrigin(zhangjiaoInts));

        int id = MultiByteToIntUtil.from(zhangjiaoBytes[11] & 0xFF, zhangjiaoBytes[12] & 0xFF);
        System.out.println(jiemi(id));

        System.out.println();
        for (int i = 0; i < 378; i++) {
//            System.out.println(i + " " + zhangjiaoInts[i] + " " + jiemi(MultiByteToIntUtil.from(zhangjiaoBytes[i] & 0xFF, zhangjiaoBytes[i + 1] & 0xFF)));
//            System.out.println(i + " " + zhangjiaoInts[i] + " " + jiemi(MultiByteToIntUtil.from(zhangjiaoBytes[i] & 0xFF, zhangjiaoBytes[i + 1] & 0xFF)));
        }
        System.out.println();
        System.out.println(jiemi(MultiByteToIntUtil.from(zhangjiaoBytes[15] & 0xFF, zhangjiaoBytes[16] & 0xFF)));
        System.out.println(jiemi(MultiByteToIntUtil.from(zhangjiaoBytes[17] & 0xFF, zhangjiaoBytes[18] & 0xFF)));

//        System.exit(0);
        int i = 10001;
        while (pos < bytes.length && pos + 13 < bytes.length) {
            pos += 370;
            int index = i++;

            String name = genStr(pos, bytes);
            name = ZHConverter.convert(name, ZHConverter.SIMPLIFIED);

            System.out.print(name);
            System.out.print("|");
//            for(int j=300;j<=360;j++) {
//                name = genStrMiaoshu(pos + j, bytes);
//                name = ZHConverter.convert(name, ZHConverter.SIMPLIFIED);
//                System.out.println(j+name);
//            }
//            int lei = jiemi((MultiByteToIntUtil.from(bytes[pos + 9] & 0xFF, bytes[pos + 10] & 0xFF)));
            int lei = bytes[pos + 10] & 0xFF;
            lei = jiemilei(lei);
            System.out.print(lei);
            System.out.print("|");
            int oldIndex = (MultiByteToIntUtil.from(bytes[pos + 11] & 0xFF, bytes[pos + 12] & 0xFF));
            int realIndex = jiemi(oldIndex);

            System.out.print(realIndex);
            System.out.print("|");
            String miaoshu = genStrMiaoshu(pos + 329, bytes);
            System.out.print(ZHConverter.convert(miaoshu, ZHConverter.SIMPLIFIED));
            System.out.print("|");

            System.out.println();

//            if(i>10010) {
//                break;
//            }
//            int jiemi = jiemi(oldIndex);
//            System.out.println(AddZeroUtil.addZero(Integer.toBinaryString(oldIndex), 16));
//            System.out.println(AddZeroUtil.addZero(Integer.toBinaryString(index + 1), 16));
//            System.out.println();

        }

    }


    public static String genStr(int pos, byte[] bytes) throws UnsupportedEncodingException {
        int index = 0;
        for (int i = pos; i < bytes.length; i++) {
            if (bytes[i] != 0) {
                index = i;
                break;
            }
        }
        byte[] bytes1 = new byte[10 - (index - pos)];
        for (int i = (index - pos); i < 10; i++) {
            bytes1[bytes1.length - i + (index - pos) - 1] = bytes[pos + i];
        }
        String str1 = new String(bytes1, "big5");
        return str1;
    }

    public static String genStrMiaoshu(int pos, byte[] bytes) throws UnsupportedEncodingException {
        int index = pos;
        byte[] bytes1 = new byte[30 - (index - pos)];
        for (int i = (index - pos); i < 30; i++) {
            bytes1[bytes1.length - i + (index - pos) - 1] = bytes[pos + i];
        }
        String str1 = new String(bytes1, "big5");
        return str1;
    }


    public static byte[] reverse(byte[] bytes) {
        for (int i = 0; i < bytes.length / 2; i++) {
            byte temp = bytes[i];
            bytes[i] = bytes[bytes.length - 1 - i];
            bytes[bytes.length - 1 - i] = temp;
        }
        return bytes;
    }


    public static void main2(String[] args) {
        String s1 = "1100100011011001";
        String s2 = "0010011100010010";
        String s3 = "1110111111001011";
        int realId1 = 28005;
        int id1 = 33453;
        id1 = id1 ^ 61387;
        System.out.println((AddZeroUtil.addZero(Integer.toBinaryString(realId1), 16)));
        System.out.println((AddZeroUtil.addZero(Integer.toBinaryString(id1), 16)));
        System.out.println();

        int realId2 = 10001;
        int id2 = 51417;
        id2 ^= 61384;
        System.out.println((AddZeroUtil.addZero(Integer.toBinaryString(realId2), 16)));
        System.out.println((AddZeroUtil.addZero(Integer.toBinaryString(id2), 16)));
        System.out.println();

        int realId3 = 10002;
        int id3 = 51416;
        id3 ^= 61384;
        System.out.println((AddZeroUtil.addZero(Integer.toBinaryString(realId3), 16)));
        System.out.println((AddZeroUtil.addZero(Integer.toBinaryString(id3), 16)));
        System.out.println();
    }
}
