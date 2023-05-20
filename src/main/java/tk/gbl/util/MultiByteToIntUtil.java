package tk.gbl.util;

/**
 * Date: 2017/4/7
 * Time: 17:33
 *
 * @author Tian.Dong
 */
public class MultiByteToIntUtil {
    public static int from(int a, int b) {
        return (b << 8) + a;
    }

    public static int fromAsc(int a, int b) {
        return (a << 8) + b;
    }

    public static long from(int a, int b, int c, int d) {
        return (d << 24) + (c << 16) + (b << 8) + a;
    }
}
