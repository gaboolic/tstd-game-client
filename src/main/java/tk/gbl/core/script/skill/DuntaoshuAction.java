package tk.gbl.core.script.skill;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 学遁逃术
 * <p/>
 * Date: 2017/6/2
 * Time: 9:54
 *
 * @author Tian.Dong
 */
public class DuntaoshuAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "遁逃术-揭榜=12001=0=642,1495=1=13=1-1=0=True=0=4=0\n" +
                "遁逃术-刘焉 - 12304=12304=1=550,320=0=0=1-4,2-1=0=True=1=0=0\n" +
                "遁逃术-邹靖 - 12102=12102=1=350,320=0=0=1-2=0=True=1=0=0\n" +
                "遁逃术-丘力居 - 12820=12820=1=1490,120=0=0==0=True=2=3=0";

        for(String step:steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成遁逃术");
    }
}
