package tk.gbl.maincenter;

import tk.gbl.core.GameClient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Date: 2017/5/21
 * Time: 8:29
 *
 * @author Tian.Dong
 */
public class MergeAccountFile {
    public static void main(String[] args) {
        List<String> usernameList1 = getGameClientList("account_25.txt");
        List<String> usernameList2 = getGameClientList("account_251.txt");
        System.out.println(usernameList1);
        System.out.println(usernameList2);

        Set<String> usernameSet = new TreeSet<>();
        usernameSet.addAll(usernameList1);
        usernameSet.addAll(usernameList2);
        System.out.println(usernameSet);
        System.out.println(usernameSet.size());


        for (String gameClient25Level : usernameSet) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("account_25_all"), true));
                bufferedWriter.write(gameClient25Level);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> getGameClientList(String fileName) {
        File file = new File(fileName);
        List<String> usernameList = new ArrayList<>();
        if (!file.exists()) {
            return usernameList;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                usernameList.add(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usernameList;
    }
}
