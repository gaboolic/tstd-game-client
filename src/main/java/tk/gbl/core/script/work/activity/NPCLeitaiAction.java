package tk.gbl.core.script.work.activity;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * ------星期五挑战擂台------=
 * 星期五挑战擂台打1到36=星期五挑战擂台主-36
 * 星期五挑战擂台打1场=星期五擂台(一场战斗)
 * 星期五挑战擂台治疗=星期五擂台华陀
 * <p/>
 * 星期五擂台(进擂台)=12003=0=1210,830=1=8=1=0=False=0=0=0
 * 星期五挑战擂台主-36=10991=5=862,355=0=0=1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2=0=False=0=0=0
 * 星期五擂台(一场战斗)=10991=5=942,375=0=0=1-0,2=0=False=0=1=0
 * 星期五擂台华陀=10991=2=470,180=0=0=Y-1=0=True=0=0=0
 * Date: 2017/5/26
 * Time: 18:59
 *
 * @author Tian.Dong
 */
public class NPCLeitaiAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        if(gameClient.getCurrentMapId() != 10991) {
            String step1 = "星期五擂台(进擂台)=12003=0=1210,830=1=8=1=0=False=0=0=0";
            gameClient.doDoDoScriptAction(step1);
        }

        while (gameClient.getTeamUsers().size() != gameClient.getAllowTeamUsers().size()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String step2 = "星期五挑战擂台主-36=10991=5=990,260=0=0=1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2=0=False=0=0=0";
        gameClient.doDoDoScriptAction(step2);
    }
}
