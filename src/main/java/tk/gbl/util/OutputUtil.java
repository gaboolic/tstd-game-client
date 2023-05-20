package tk.gbl.util;

import tk.gbl.core.GameClient;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Date: 2017/4/20
 * Time: 17:17
 *
 * @author Tian.Dong
 */
public class OutputUtil {

    static boolean isAppend = false;
    static BufferedWriter bufferedWriter = null;
    static BufferedWriter bufferedWriter1 = null;
    static BufferedWriter bufferedWriter2 = null;
    static BufferedWriter bufferedWriter3 = null;
    static BufferedWriter bufferedWriter4 = null;
    static BufferedWriter bufferedWriter5 = null;
    static BufferedWriter bufferedWriter6 = null;

    static {
        File logPath = new File("./logs");
        if (!logPath.exists()) {
            logPath.mkdir();
        }
        File file = new File("./logs/output.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter1 = new BufferedWriter(new FileWriter(new File("./logs/output-1.txt"), isAppend));
            bufferedWriter2 = new BufferedWriter(new FileWriter(new File("./logs/output-2.txt"), isAppend));
            bufferedWriter3 = new BufferedWriter(new FileWriter(new File("./logs/output-3.txt"), isAppend));
            bufferedWriter4 = new BufferedWriter(new FileWriter(new File("./logs/output-4.txt"), isAppend));
            bufferedWriter5 = new BufferedWriter(new FileWriter(new File("./logs/output-5.txt"), isAppend));
            bufferedWriter6 = new BufferedWriter(new FileWriter(new File("./logs/output-6.txt"), isAppend));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void output(String str) {
        System.out.println(str);
        try {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void output(String str, GameClient gameClient, boolean isShow) {
        if (isShow) {
            System.out.println("[" + DateUtil.getNowString() + "] " + gameClient.getUserId() + " " + str);
        }

        BufferedWriter writer = bufferedWriter;
        if (gameClient.getIndex() == 1) {
            writer = bufferedWriter1;
        } else if (gameClient.getIndex() == 2) {
            writer = bufferedWriter2;
        } else if (gameClient.getIndex() == 3) {
            writer = bufferedWriter3;
        } else if (gameClient.getIndex() == 4) {
            writer = bufferedWriter4;
        } else if (gameClient.getIndex() == 5) {
            writer = bufferedWriter5;
        } else if (gameClient.getIndex() == 6) {
            writer = bufferedWriter6;
        }

        if (gameClient.isFileOutput()) {
            try {
                writer.write("[" + DateUtil.getNowString() + "] ");
                writer.write("[" + gameClient.getUserId() + "] ");
                writer.write(str);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void output(String str, GameClient gameClient) {
        output(str, gameClient, gameClient.isConsoleOutput());
    }
}
