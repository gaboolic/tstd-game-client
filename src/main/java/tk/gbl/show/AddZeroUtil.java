package tk.gbl.show;

/**
 * Date: 2017/3/25
 * Time: 10:19
 *
 * @author Tian.Dong
 */
public class AddZeroUtil {
    public static String addZero(String str, int length) {
        if (str.length() >= length) {
            return str;
        }
        int miss = length - str.length();
        StringBuilder zeroStr = new StringBuilder();
        for (int i = 0; i < miss; i++) {
            zeroStr.append("0");
        }
        return zeroStr.append(str).toString();
    }
}
