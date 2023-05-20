package tk.gbl.util;

import java.io.*;
import java.util.Properties;

/**
 * Date: 2017/4/30
 * Time: 17:39
 *
 * @author Tian.Dong
 */
public class FightAiUtil {

    static Properties properties = new Properties();
    static {
        InputStream is = null;
        is = FightAiUtil.class.getClassLoader().getResourceAsStream("fight_ai.properties");
        //File propertyFile = new File("C:/Temp/testMDB/TestTranslator/abc.txt");
        if (is != null) {
            try {
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getValue(String key) {
        return (String) properties.get(key);
    }
}
