package tk.gbl.mainenter.boss;

import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/6/21
 * Time: 17:05
 *
 * @author Tian.Dong
 */
public class MainTankBossOpenBox {
    public static void main(String[] args) throws IOException {
        List<String> usernameList = new ArrayList<>();
//        usernameList.add("47809");
//        usernameList.add("47810");
//        usernameList.add("47811");
//        usernameList.add("47812");
//        usernameList.add("47813");
        usernameList.add("27166");
//        usernameList.add("27167");
//        usernameList.add("39259");
//        usernameList.add("38253");
//        usernameList.add("39260");
        final String password = ProcessConstant.password;

        for (final String username : usernameList) {
            new Thread() {
                public void run() {
                    final GameClient gameClient = new GameClient();
                    gameClient.setConsoleOutput(true);
                    gameClient.setFileOutput(true);
                    gameClient.setIndex(5);
                    gameClient.setUsername(username);
                    gameClient.setPassword(password);
                    gameClient.setTeamLeaderId(0);
                    gameClient.init();
                    gameClient.waitForLoginSuccess();
                    gameClient.autoUseAndThrow();
                    gameClient.sleep(1000);

                    for (int i = 0; i < 10; i++) {
                        try {
                            gameClient.event4906();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        gameClient.sleep(1000);
                        gameClient.autoUseAndThrow();
                    }
                    for (int i = 0; i < 10; i++) {
                        gameClient.sleep(1000);
                        gameClient.autoUseAndThrow();
                    }
                    gameClient.sleep(1000);
                    gameClient.close();
                }
            }.start();
        }
    }
}
