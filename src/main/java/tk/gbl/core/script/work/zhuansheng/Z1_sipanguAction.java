package tk.gbl.core.script.work.zhuansheng;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/6/5
 * Time: 16:17
 *
 * @author Tian.Dong
 */
public class Z1_sipanguAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps =
                "地盘古任务-阿志 - 13243=13243=1=190,480=0=0==0=True=6=0=0\n" +
                "地盘古任务-李父 - 13022=13022=2=830,360=0=0==0=True=6=0=0\n" +
                "地盘古任务-千毒洞 - 13513=13513=0==1=5==0=True=5=0=0\n" +
                "地盘古任务-盘古巨兽 - 13519=13519=11=2390,990=1=2=1-2=0=True=1=0=0\n" +
                "地盘古任务-盘古巨兽2 - 13519=13519=11=2390,990=1=3==0=True=5=16=0\n" +
                "水盘古任务-海大哥 - 11021=11021=4=1010,500=0=0=1-2=0=True=5=0=0\n" +
                "水盘古任务-盘古巨兽 - 58000=58000=1==1=2==0=True=3=16=0\n" +
                "风盘古任务-少年侠士 - 56501=56501=2==1=4=1-2=0=True=6=0=0\n" +
                "风盘古任务-盘古巨兽 - 56517=56517=2==1=2==0=True=10=12=0\n" +
                "风盘古任务-盘古巨兽 - 56501=56501=4=2330,370=1=5==0=True=2=17=0\n" +
                "火盘古任务-北斗星君 - 55003=55003=2=410,320=0=0=1-3=0=True=2=0=0\n" +
                "火盘古任务-盘古巨兽 - 57501=57501=1==1=6==0=True=3=0=0\n" +
                "火盘古任务-炬鬼兵 - 57511=57511=2=130,930=1=2==0=True=5=5=0\n" +
                "火盘古任务-盘古巨兽 - 57511=57511=5=130,930=1=3=2-1=0=True=11=12=0\n" +
                "火盘古任务-北斗星君2 - 55003=55003=2=410,320=0=0==0=True=8=0=0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成四盘古");
    }
}
