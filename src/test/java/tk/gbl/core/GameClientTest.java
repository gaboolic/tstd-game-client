package tk.gbl.core;

import org.junit.Test;
import tk.gbl.constant.GameConfigType;

import java.util.Scanner;

/**
 * Date: 2017/4/7
 * Time: 15:10
 *
 * @author Tian.Dong
 */
public class GameClientTest {
    @Test
    public void testExecute(){
        GameClient gameClient = new GameClient();
        gameClient.execute("clickNPC 1");
    }

    public static void main(String[] args){
        /*Scanner scanner = new Scanner(System.in);
        String line = null;
        while (!(line = scanner.nextLine()).equals("exit")) {
            System.out.println(line);
        }*/
        final GameClient gameClient3 = new GameClient();
        gameClient3.setIndex(3);
        gameClient3.setConsoleOutput(true);
        gameClient3.setFileOutput(true);
        gameClient3.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient3.setUsername("118049");
        gameClient3.setPassword("123456");
//        gameClient3.setInitMapId(12001);
        gameClient3.setPreNickname("嗷大猫仓库");
        gameClient3.init();
        gameClient3.setTeamLeaderId(0);
    }
}
