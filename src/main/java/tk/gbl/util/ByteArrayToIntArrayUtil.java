package tk.gbl.util;

/**
 * Date: 2017/3/31
 * Time: 17:33
 *
 * @author Tian.Dong
 */
public class ByteArrayToIntArrayUtil {
    public static int[] xorAndTransform(byte[] results, int pos) {
        int[] temp = new int[pos];
        for (int i = 0; i < pos; i++) {
            temp[i] = (results[i] ^ 0xAD) & 0xFF;
        }
        return temp;
    }

    public static int[] transform(byte[] results, int pos) {
        int[] temp = new int[pos];
        for (int i = 0; i < pos; i++) {
            temp[i] = (results[i]) & 0xFF;
        }
        return temp;
    }

    public static byte[] xorAndTransform(int[] results, int pos) {
        byte[] temp = new byte[pos];
        for (int i = 0; i < pos; i++) {
            temp[i] = (byte) (results[i] ^ 0xAD);
        }
        return temp;
    }

    public static byte[] transform(int[] results, int pos) {
        byte[] temp = new byte[pos];
        for (int i = 0; i < pos; i++) {
            temp[i] = (byte) results[i];
        }
        return temp;
    }
}
