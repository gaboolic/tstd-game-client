package tk.gbl.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Date: 2017/4/30
 * Time: 17:39
 *
 * @author Tian.Dong
 */
public class SkillIdUtil {

    static Map<Integer, String> skillMapping = new HashMap<>();

    static {
        InputStream is = PathFindingUtil.class.getResourceAsStream("/skill_code.ini");
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
                    String[] infos = line.split("\t");
                    //10000	肉搏	0
                    int id = Integer.parseInt(infos[0]);
                    String name = infos[1];
                    skillMapping.put(id, name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSkillName(int id) {
        String name = skillMapping.get(id);
        if (name != null) {
            return name;
        }
        return "";
    }
}
