package tk.gbl.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/5/9
 * Time: 16:11
 *
 * @author Tian.Dong
 */
public class FileReadUtil {
    public static List<String> getDoneGameClientList(String accountDoneFileName)  {
        File file = new File(accountDoneFileName);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> usernameList = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("wp") && !line.startsWith("WP")) {
                    line = "wp" + line;
                }
                usernameList.add(line);
            }
            reader.close();
            return usernameList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
