package tk.gbl.core;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Date: 2017/3/24
 * Time: 17:12
 *
 * @author Tian.Dong
 */
public class Converter {

    public static Map<String,String> passwordCharMap = new LinkedHashMap<>();

    static {
        init();
    }

    private int converter(int number) {
        return number ^ 0xAD;
    }

    //2345678901 9F 9E 99 98 9B 9A 95 94 9D 9C
    //89:;<=>?@A 95 94 97 96 91 90 93 92 ED EC
    private static void init() {
        passwordCharMap.put("0","9D");
        passwordCharMap.put("1","9C");
        passwordCharMap.put("2","9F");
        passwordCharMap.put("3","9E");
        passwordCharMap.put("4","99");
        passwordCharMap.put("5","98");
        passwordCharMap.put("6","9B");
        passwordCharMap.put("7","9A");
        passwordCharMap.put("8","95");
        passwordCharMap.put("9","94");
        passwordCharMap.put(":","97");
        passwordCharMap.put(";","96");
        passwordCharMap.put("<","91");
        passwordCharMap.put("=","90");
        passwordCharMap.put(">","93");
        passwordCharMap.put("?","92");
        passwordCharMap.put("@","ED");
        passwordCharMap.put("A","EC");

    }

    public static String getPasswordMapper(String passwordChar) {
        return passwordCharMap.get(passwordChar);
    }
}
