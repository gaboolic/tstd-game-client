package tk.gbl.core.script.work.badouyao;

import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 吴郡平定战
 * 1.进入建业城，见到吴景、孙贲、孙辅，与吴景交谈，答应住他们。前去吴郡平定水贼。
 * 2.在太湖水洞内，遇见严舆，与他交谈，会与严舆、邹陀、钱铜、王晟等人进入战斗。
 * 3.胜利后，再到吴郡树林，遇见严白虎、许贡、三名许贡家客。
 * 4.战胜后进吴郡大街，与诸葛瑾会交谈，答应协助。
 * 5.再到泾县山涯，与陈瑀交谈，会与严白虎、陈瑀、陈牧、祖郎、焦已、万演、凌操。
 * 6.战胜后即可完成任务。
 * 任务条件：需完成神亭岭大战
 * 地区：扬州、建业城
 * 获得物品：随机获得：熔钢剑、流金刀、黄河戟、铁环刀、熏风枪
 * <p/>
 * Date: 2017/4/26
 * Time: 15:54
 *
 * @author Tian.Dong
 */
public class T4_wujunAction extends ScriptAction {

    /**
     * 4吴郡平定战 - 吴景=18001=10=1542,555=0=0==0
     * 4吴郡平定战 - 严舆=18504=1=2162,2215=0=0==0
     * 4吴郡平定战 - 严白虎=18881=7=322,555=0=0==0
     * 4吴郡平定战 - 诸葛瑾=18012=4=222,415=0=0==0
     * 4吴郡平定战 - 陈瑀=18454=1=520,540=0=0==0
     */
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        System.out.println("开始吴郡平定战 ");
        gameClient.moveFar(18001);
        System.out.println("来到18001建业");
        gameClient.segmentMoveTo(1542, 555);
        gameClient.clickNPC(10);
        System.out.println("点吴景");
        gameClient.simpleSleep();

        gameClient.moveFar(18504);
        System.out.println("来到18504太湖水洞");
        gameClient.segmentMoveTo(2162, 2215);
        gameClient.clickNPC(1);
        System.out.println("点严舆");
        gameClient.simpleSleep();

        gameClient.moveFar(18881);
        System.out.println("来到18881吴郡树林");
        gameClient.segmentMoveTo(322, 555);
        gameClient.clickNPC(7);
        System.out.println("点严白虎");
        gameClient.simpleSleep();

        /**
         * 4吴郡平定战 - 诸葛瑾=18012=4=222,415=0=0==0
         * 4吴郡平定战 - 陈瑀=18454=1=520,540=0=0==0
         */
        gameClient.moveFar(18012);
        System.out.println("来到18012吴郡大街");
        gameClient.segmentMoveTo(222, 415);
        gameClient.clickNPC(4);
        System.out.println("点诸葛瑾");
        gameClient.simpleSleep();

        gameClient.moveFar(18454);
        System.out.println("来到18454泾县山涯");
        gameClient.segmentMoveTo(520, 540);
        gameClient.clickNPC(1);
        System.out.println("点陈瑀");
        gameClient.simpleSleep();
        System.out.println("吴郡平定战完毕");
    }
}
