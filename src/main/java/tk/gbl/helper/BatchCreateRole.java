package tk.gbl.helper;

import tk.gbl.core.GameClient;
import tk.gbl.core.SocketReadThread;
import tk.gbl.core.script.InitToZJGCAction;
import tk.gbl.core.script.ZhenchashuAction;

import java.io.*;
import java.net.Socket;

/**
 * Date: 2017/3/25
 * Time: 11:25
 *
 * @author Tian.Dong
 */
public class BatchCreateRole {
    public static void main(String[] args) throws IOException {
        File outFile = new File("E:\\DT\\tstd", "account_update.txt");
        FileWriter fileWriter = new FileWriter(outFile, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);



        File file = new File("E:\\DT\\tstd", "account.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.length() == 0) {
                break;
            }
            String username = line.split(" ")[0].trim();
            String password = line.split(" ")[1].trim();

            Socket socket = new Socket("114.112.97.137", 6414);

            GameClient gameClient = new GameClient();
            gameClient.setReConnect(false);
            String postfix = username.substring(username.length() - 4, username.length());
            gameClient.setPreNickname("嗷大喵" + postfix);
            gameClient.setUsername(username);
            gameClient.setPassword(password);
            gameClient.setSocket(socket);
            gameClient.connect();

            gameClient.login();

            SocketReadThread socketReadThread = new SocketReadThread(gameClient);
            socketReadThread.start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameClient.doScriptAction(new InitToZJGCAction());
            gameClient.doScriptAction(new ZhenchashuAction());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameClient.close();

            bufferedWriter.write(username + " " + gameClient.getPassword());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }

        bufferedWriter.close();
        System.out.println("end!!!!!");
    }
}
