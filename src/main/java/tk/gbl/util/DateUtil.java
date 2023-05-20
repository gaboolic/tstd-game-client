package tk.gbl.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 2017/4/25
 * Time: 16:51
 *
 * @author Tian.Dong
 */
public class DateUtil {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static String getNowString() {
        Date date = new Date();
        return sdf.format(date);
    }
}
