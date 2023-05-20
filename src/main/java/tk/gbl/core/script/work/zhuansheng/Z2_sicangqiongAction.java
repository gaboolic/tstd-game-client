package tk.gbl.core.script.work.zhuansheng;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/6/5
 * Time: 16:42
 *
 * @author Tian.Dong
 */
public class Z2_sicangqiongAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps = "地苍穹巨兽-伯固 - 56101=56101=1=330,380=0=0=1-4=0=True=2=0=0\n" +
                "地苍穹巨兽-伊夷模 - 56528=56528=1=2050,1140=0=0==0=True=12=6=0\n" +
                "地苍穹巨兽-伯固2 - 56101=56101=1=330,380=0=0==0=True=10=0=0\n" +
                "水苍穹巨兽任务韩当 - 18001=18001=4=1310,280=0=0==0=True=7=0=0\n" +
                "水苍穹巨兽任务程普 - 18301=18301=4=270,400=0=0=1-14=0=True=3=0=0\n" +
                "水苍穹巨兽任务穹苍巨兽 - 18506=18506=1==1=3==0=True=1=0=0\n" +
                "水苍穹巨兽任务穹苍巨兽2 - 18506=18506=1=3650,320=0=0=1=0=True=2=6=0\n" +
                "水苍穹巨兽任务火德星君 - 18506=18506=2=3810,260=0=0==0=True=7=0=0\n" +
                "水苍穹巨兽任务程普2 - 18301=18301=4=270,400=0=0==0=True=7=0=0\n" +
                "风苍穹巨兽任务阿义 - 19011=19011=1=610,840=0=0==0=True=9=0=0\n" +
                "风苍穹巨兽任务凉茂 - 19174=19174=1=470,320=0=0==0=True=10=0=0\n" +
                "风苍穹巨兽任务受伤的士兵 - 19506=19506=2=550,320=0=0==0=True=7=12=0\n" +
                "风苍穹巨兽任务凉茂2 - 19174=19174=1=470,320=0=0==0=True=10=0=0\n" +
                "火苍穹巨兽任务苏仆延 - 19176=19176=1=490,360=0=0==0=True=5=0=0\n" +
                "火苍穹巨兽任务蹋顿 - 19175=19175=1=350,360=0=0=2=0=False=20=0=0\n" +
                "火苍穹巨兽任务牛巨汉 - 19525=19525=1=230,380=0=0==0=True=3=4=0\n" +
                "火苍穹巨兽任务蹋顿2 - 19175=19175=1=350,360=0=0=2=0=False=8=0=0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成四苍穹");
    }
}
