package tk.gbl.util;

import tk.gbl.bean.Goods;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2017/4/30
 * Time: 17:39
 *
 * @author Tian.Dong
 */
public class NpcWordsUtil {

    static Map<Integer, String> wordsMapping = new HashMap<>();

    static {
        try {
            File file = new File("E:\\DT\\tstd\\NPCwords.INI");
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] infos = line.split("=");
                    int id = Integer.parseInt(infos[0]);
                    String words = infos[1];
                    wordsMapping.put(id, words);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getWords(int id) {
        String words = wordsMapping.get(id);
        if (words != null) {
            return words;
        }
        return "";
    }
}
