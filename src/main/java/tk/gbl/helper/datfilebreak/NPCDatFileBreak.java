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
 * Date: 2017/5/24
 * Time: 17:46
 *
 * @author Tian.Dong
 */
public class NPCDatFileBreak {
    public static void main(String[] args) throws IOException {
//        File file = new File("E:\\吞食天地资料\\data\\Npc.Dat");
        File file = new File("E:\\game\\国仕无双\\data\\Npc.Dat");
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

        byte[] zhangjiaoBytes = new byte[97];
        System.arraycopy(bytes, pos, zhangjiaoBytes, 0, 97);

        int[] zhangjiaoInts = ByteArrayToIntArrayUtil.transform(zhangjiaoBytes, zhangjiaoBytes.length);
        System.out.println(ShowUtil.showOrigin(zhangjiaoInts));

        int id = MultiByteToIntUtil.from(zhangjiaoBytes[11] & 0xFF, zhangjiaoBytes[12] & 0xFF);
        System.out.println(jiemi(id));

        System.out.println();
        for (int i = 13; i < 96; i++) {
            System.out.println(i + " " + zhangjiaoInts[i] + " " + jiemi(MultiByteToIntUtil.from(zhangjiaoBytes[i] & 0xFF, zhangjiaoBytes[i + 1] & 0xFF)));
        }
        System.out.println();
        System.out.println(jiemi(MultiByteToIntUtil.from(zhangjiaoBytes[15] & 0xFF, zhangjiaoBytes[16] & 0xFF)));
        System.out.println(jiemi(MultiByteToIntUtil.from(zhangjiaoBytes[17] & 0xFF, zhangjiaoBytes[18] & 0xFF)));

//        System.exit(0);
        int i = 10002;
        while (pos < bytes.length && pos + 13 < bytes.length) {
            pos += 97;
            int index = i++;
            int oldIndex = (MultiByteToIntUtil.from(bytes[pos + 11] & 0xFF, bytes[pos + 12] & 0xFF));
            String name = genStr(pos, bytes);
            name = ZHConverter.convert(name, ZHConverter.SIMPLIFIED);
            System.out.print(name);
            System.out.print("|");

//            System.out.print(" " + oldIndex + " ");
//            System.out.print(" "+ index +" ");
            System.out.print(jiemi(oldIndex));
            System.out.print("|");

            int hpx = jiemi(MultiByteToIntUtil.from(bytes[pos + 45] & 0xFF, bytes[pos + 46] & 0xFF));
            int spx = jiemi(MultiByteToIntUtil.from(bytes[pos + 47] & 0xFF, bytes[pos + 48] & 0xFF));
            int inz = jiemi(MultiByteToIntUtil.from(bytes[pos + 49] & 0xFF, bytes[pos + 50] & 0xFF));
            int atk = jiemi(MultiByteToIntUtil.from(bytes[pos + 51] & 0xFF, bytes[pos + 52] & 0xFF));
            int def = jiemi(MultiByteToIntUtil.from(bytes[pos + 53] & 0xFF, bytes[pos + 54] & 0xFF));
            int agi = jiemi(MultiByteToIntUtil.from(bytes[pos + 55] & 0xFF, bytes[pos + 56] & 0xFF));

            System.out.print(hpx + "," + spx + "," + inz + "," + atk + "," + def + "," + agi + "|");
            int skill1 = jiemi(MultiByteToIntUtil.from(bytes[pos + 61] & 0xFF, bytes[pos + 62] & 0xFF));
            System.out.print((skill1) + ",");
            int skill2 = jiemi(MultiByteToIntUtil.from(bytes[pos + 63] & 0xFF, bytes[pos + 64] & 0xFF));
            System.out.print((skill2) + ",");
            int skill3 = jiemi(MultiByteToIntUtil.from(bytes[pos + 65] & 0xFF, bytes[pos + 66] & 0xFF));
            System.out.print((skill3));
            System.out.print("|");

            int zhuan = jiemi(MultiByteToIntUtil.from(bytes[pos + 69] & 0xFF, bytes[pos + 70] & 0xFF));
            int zhuan1 = jiemi(MultiByteToIntUtil.from(bytes[pos + 67] & 0xFF, bytes[pos + 68] & 0xFF));
            int zhuan2 = jiemi(MultiByteToIntUtil.from(bytes[pos + 71] & 0xFF, bytes[pos + 72] & 0xFF));
            int zhuan3 = jiemi(MultiByteToIntUtil.from(bytes[pos + 75] & 0xFF, bytes[pos + 76] & 0xFF));
            int zhuan4 = jiemi(MultiByteToIntUtil.from(bytes[pos + 77] & 0xFF, bytes[pos + 78] & 0xFF));
            int zhuan5 = jiemi(MultiByteToIntUtil.from(bytes[pos + 80] & 0xFF, bytes[pos + 81] & 0xFF));
            int zhuan6 = jiemi(MultiByteToIntUtil.from(bytes[pos + 83] & 0xFF, bytes[pos + 84] & 0xFF));
            int zhuan7 = jiemi(MultiByteToIntUtil.from(bytes[pos + 85] & 0xFF, bytes[pos + 86] & 0xFF));
            int zhuan8 = jiemi(MultiByteToIntUtil.from(bytes[pos + 88] & 0xFF, bytes[pos + 89] & 0xFF));
            int zhuan9 = jiemi(MultiByteToIntUtil.from(bytes[pos + 90] & 0xFF, bytes[pos + 91] & 0xFF));
            int zhuan10 = jiemi(MultiByteToIntUtil.from(bytes[pos + 92] & 0xFF, bytes[pos + 93] & 0xFF));
            int zhuan11 = jiemi(MultiByteToIntUtil.from(bytes[pos + 94] & 0xFF, bytes[pos + 95] & 0xFF));
            System.out.print(zhuan + " " + zhuan2 + " " + zhuan3 + " " + zhuan4 + " " + zhuan5 + " " + zhuan6 + " " + zhuan7 + " " + zhuan8 + " " + zhuan9 + " " + zhuan10 + " " + zhuan11);
            if (isJJ_SD(skill1) || isJJ_SD(skill2) || isJJ_SD(skill3)) {
                if (jiemi(oldIndex) < 40000 & agi > 60 && !name.startsWith("真") && !name.startsWith("狂")) {
                    System.out.print("NB!");
                }
            }
            System.out.println();

            int jiemi = jiemi(oldIndex);
            System.out.println(oldIndex+" "+index);
            System.out.println(AddZeroUtil.addZero(Integer.toBinaryString(oldIndex), 16));
            System.out.println(AddZeroUtil.addZero(Integer.toBinaryString(index),16));
            System.out.println();

            String s1 = "1110111001011100";
            String s2 = "1011110001010100";
            String s3 = "0101001000001000";
        }

    }

    private static boolean isJJ_SD(int skill1) {
        if (skill1 == 10010 || skill1 == 13003 || skill1 == 13005 || skill1 == 10015) {
//        if (skill1 == 10015) {
            return true;
        }
        return false;
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

    public static int jiemi(int num) {
//        return (((num - 29978) ^ 1) + 10001);
        return ((num) ^ 21001) - 1;
//        return (num-1) ;
    }

    public static long jiemi(long num) {
//        return (((num - 29978) ^ 1) + 10001);
        return ((num) ^ 21001) - 1;
//        return (num-1) ;
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
        System.out.println(((29979 - 29978) ^ 1) + 10001);
        System.out.println(((29979 - 29978 + 10000) ^ 1) + 1);


        System.out.println(((29978 - 29978) ^ 1) + 10001);
        System.out.println(((29978 - 29978 + 10000) ^ 1) + 1);


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(1 ^ 1);
        System.out.println(2 ^ 1);
        System.out.println(3 ^ 1);
        System.out.println(4 ^ 1);
        System.out.println(5 ^ 1);
        System.out.println(6 ^ 1);
        System.out.println(7 ^ 1);
        System.out.println();
        System.out.println(0 ^ 2);
        System.out.println(1 ^ 2);
        System.out.println(2 ^ 2);
        System.out.println(3 ^ 2);
        System.out.println(4 ^ 2);
        System.out.println(5 ^ 2);
    }
}
