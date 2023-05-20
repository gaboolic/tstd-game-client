package tk.gbl.mainenter.activity;

import tk.gbl.core.GameClient;

import java.io.IOException;
import java.util.Scanner;

/**
 * Date: 2017/5/15
 * Time: 12:58
 *
 * @author Tian.Dong
 */
public class AnswerQuestionMainTank {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("ShutdownHook");
            }
        });

        //wp115557,123456;wp115558,123456
        String usernameAndP = "wp114022 123456";
        String username = usernameAndP.split(" ")[0];
        String password = usernameAndP.split(" ")[1];

        final GameClient gameClient = new GameClient();
        gameClient.setServerPrefix("wp");
        gameClient.setServerIndex(1);
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.setIndex(6);
        gameClient.setUsername(username);
        gameClient.setPassword(password);
        gameClient.setTeamLeaderId(0);
        gameClient.init();
        gameClient.waitForLoginSuccess();
        gameClient.moveFar(12001);
        gameClient.doDoDoScriptAction("杂货商人(买水小宠)=12001=7=1790,560=5=0=1=0=False=11-50=0=0");
//        gameClient.doDoDoScriptAction("杂货商人(买地小宠)=12001=7=1790,560=5=0=1=0=False=10-50=0=0\n");
//        gameClient.doDoDoScriptAction("杂货商人(买火小宠)=12001=7=1790,560=5=0=1=0=False=12-50=0=0\n");
        gameClient.sleep(5000);
    }
}
