package tk.gbl.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2017/5/25
 * Time: 16:12
 *
 * @author Tian.Dong
 */
public class NpcIdUtil {
    static Map<Integer, String> npcMapping = new HashMap<>();

    static {
        InputStream is = PathFindingUtil.class.getResourceAsStream("/npc.ini");
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
                    try {
                        String[] infos = line.split("\\|");
                        //张角 10001
                        String name = infos[0];
                        int id = Integer.parseInt(infos[1]);
                        npcMapping.put(id, name);
                    } catch (Exception e) {
//                        System.out.println(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getName(int id) {
        String name = npcMapping.get(id);
        if (name != null) {
            return name;
        }
        return "";
    }

    public static String getName(long id) {
        String name = npcMapping.get((int) id);
        if (name != null) {
            return name;
        }
        return ""+id;
    }
}
