package tk.gbl.script;

import org.junit.Test;
import tk.gbl.core.GameClient;

import java.io.IOException;

/**
 * Date: 2017/6/2
 * Time: 9:04
 *
 * @author Tian.Dong
 */
public class DoDoScriptActionTest {
    @Test
    public void test() throws IOException {
        String step = "遁逃术-邹靖 - 12102=12102=1=350,320=0=0=1-2=0=True=1=0=0";
        GameClient gameClient = new GameClient();
        gameClient.doDoDoScriptAction(step);
    }
}
