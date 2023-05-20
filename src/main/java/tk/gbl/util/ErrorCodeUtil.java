package tk.gbl.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2017/6/13
 * Time: 16:45
 *
 * @author Tian.Dong
 */
public class ErrorCodeUtil {
    static Map<String, String> errorMessageMapping = new HashMap<>();

    static {
        InputStream is = PathFindingUtil.class.getResourceAsStream("/error_connect.ini");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(is, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            if (bufferedReader != null) {
                while ((line = bufferedReader.readLine()) != null) {
                    String[] infos = line.split("=");
                    String code = infos[0];
                    String message = infos[1];
                    errorMessageMapping.put(code, message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getErrorMessage(int id) {
        String name = errorMessageMapping.get("Error" + id);
        if (name != null) {
            return name;
        }
        return "";
    }
}
