package tk.gbl.core.script.work.zhuansheng;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * Date: 2017/5/26
 * Time: 18:17
 *
 * @author Tian.Dong
 */
public class YizhuanAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String npcBatch = "8大巨兽(打完全部巨兽)=水盘古任务-海大哥 - 11021,Warp1,Warp11,水盘古任务-盘古巨兽 - 58000,地盘古任务-阿志 - 13243,地盘古任务-李父 - 13022,地盘古任务-千毒洞 - 13513,地盘古任务-盘古巨兽 - 13519,地盘古任务-盘古巨兽2 - 13519,风盘古任务-少年侠士 - 56501,风盘古任务-盘古巨兽 - 56517,风盘古任务-盘古巨兽 - 56501,火盘古任务-北斗星君 - 55003,火盘古任务-盘古巨兽 - 57501,火盘古任务-炬鬼兵 - 57511,火盘古任务-盘古巨兽 - 57511,火盘古任务-北斗星君2 - 55003,伯固 - 56101,伊夷模 - 56528,伯固2 - 56101,水苍穹巨兽任务韩当 - 18001,水苍穹巨兽任务程普 - 18301,水苍穹巨兽任务穹苍巨兽 - 18506,水苍穹巨兽任务穹苍巨兽2 - 18506,水苍穹巨兽任务火德星君 - 18506,水苍穹巨兽任务程普2 - 18301,风苍穹巨兽任务阿义 - 19011,风苍穹巨兽任务凉茂 - 19174,风苍穹巨兽任务受伤的士兵 - 19506,风苍穹巨兽任务凉茂2 - 19174,火苍穹巨兽任务苏仆延 - 19176,火苍穹巨兽任务蹋顿 - 19175,火苍穹巨兽任务牛巨汉 - 19525,火苍穹巨兽任务蹋顿2 - 19175\n" +
                "地盘古=地盘古任务-阿志 - 13243,地盘古任务-李父 - 13022,地盘古任务-千毒洞 - 13513,地盘古任务-盘古巨兽 - 13519,地盘古任务-盘古巨兽2 - 13519\n" +
                "水盘古=水盘古任务-海大哥 - 11021,水盘古任务-GOTO 58000,水盘古任务-盘古巨兽 - 58000\n" +
                "火盘古=火盘古任务-北斗星君 - 55003,火盘古任务-盘古巨兽 - 57501,火盘古任务-炬鬼兵 - 57511,火盘古任务-盘古巨兽 - 57511,火盘古任务-北斗星君2 - 55003\n" +
                "风盘古=风盘古任务-少年侠士 - 56501,风盘古任务-盘古巨兽 - 56517,风盘古任务-盘古巨兽 - 56501\n" +
                "地苍穹=伯固 - 56101,伊夷模 - 56528,伯固2 - 56101\n" +
                "水苍穹=水苍穹巨兽任务韩当 - 18001,水苍穹巨兽任务程普 - 18301,水苍穹巨兽任务穹苍巨兽 - 18506,水苍穹巨兽任务穹苍巨兽2 - 18506,水苍穹巨兽任务火德星君 - 18506,水苍穹巨兽任务程普2 - 18301\n" +
                "火苍穹=火苍穹巨兽任务苏仆延 - 19176,火苍穹巨兽任务蹋顿 - 19175,火苍穹巨兽任务牛巨汉 - 19525,火苍穹巨兽任务蹋顿2 - 19175\n" +
                "风苍穹=风苍穹巨兽任务阿义 - 19011,风苍穹巨兽任务凉茂 - 19174,风苍穹巨兽任务受伤的士兵 - 19506,风苍穹巨兽任务凉茂2 - 19174";

        String npcInfo = "---4系盘古巨兽+4系苍穹巨兽---==0==0=0==0=False=0=0=0\n" +
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
                "火盘古任务-北斗星君2 - 55003=55003=2=410,320=0=0==0=True=8=0=0\n" +
                "地苍穹巨兽-伯固 - 56101=56101=1=330,380=0=0=1-4=0=True=2=0=0\n" +
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
    }
}