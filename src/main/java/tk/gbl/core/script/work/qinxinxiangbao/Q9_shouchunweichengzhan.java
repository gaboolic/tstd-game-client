package tk.gbl.core.script.work.qinxinxiangbao;

import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.ChatCommandConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:壽春圍城戰
 * 地区:兗州、許昌城
 * 任务条件:需完成清水慘敗的任務
 * 流程:
 * 1.到許昌城點兵台與程昱交談，答應協助。
 * 2.到徐州芒碭山又上方第二層，進入後與左上方的狼兵交談，會與許乾進入戰鬥。
 * 3.網羅許乾，再回到許昌與程昱交談。
 * 4.再到成德山洞，在洞內與殘兵交談，會與袁嗣、張多、鄭寶、李業進入戰鬥。
 * 5.勝利後，前往壽春山洞，在洞內與長刀兵交談，會與袁胤、進入戰鬥。
 * 6.勝利後，在進入與橋蕤交談，會與橋蕤、梁剛、陳紀、李豐、金尚、樂就進入戰鬥。
 * 7.勝利後即可完成任務。
 * 任务奖品:獵隼之羽(火+2)
 * 备注:無
 * <p/>
 * Date: 2017/6/5
 * Time: 10:24
 *
 * @author Tian.Dong
 */
public class Q9_shouchunweichengzhan extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "壽春圍城戰1(程昱) - 13014=13014=27=730,280=0=0=1=0\n";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }

        gameClient.moveFar(15415);
        while (!haveXugan(gameClient)) {
            gameClient.chatMi(114018, ChatCommandConstant.givemexugan.getCommand());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String steps2 =  "壽春圍城戰2(程昱) - 13014=13014=27=730,280=0=0==0\n" +
                "壽春圍城戰3(殘兵) - 15591=15591=16=1830,1820=0=0==0\n" +
                "壽春圍城戰4(長刀兵) - 15567=15567=1=250,240=0=0==0\n" +
                "壽春圍城戰5(橋蕤) - 15568=15568=3=1170,280=0=0==0\n" +
                "壽春圍城戰6(薛悌) - 15568=15568=4=1290,560=0=0==0";

        for (String step : steps2.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成壽春圍城戰");
    }

    private static boolean haveXugan(GameClient gameClient) {
        boolean isHaveManbing = false;
        for (int i = 0; i < gameClient.getPetList().length; i++) {
            RoleInfo pet = gameClient.getPetList()[i];
            if (pet == null) {
                continue;
            }
            if (pet.getId() == 14342) {
                isHaveManbing = true;
            }
        }
        return isHaveManbing;
    }
}
