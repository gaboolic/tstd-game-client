package tk.gbl.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Date: 2017/6/25
 * Time: 18:24
 *
 * @author Tian.Dong
 */
public class StoreConfigUtil {
    static Properties properties = new Properties();
    static {
        InputStream is = null;
        is = FightAiUtil.class.getClassLoader().getResourceAsStream("store.properties");
        if (is != null) {
            try {
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getValue(String key) {
        return (String) properties.get(key);
    }
}
