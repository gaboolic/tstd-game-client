package tk.gbl.core.script.work.qinxinxiangbao;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:兗州爭奪戰
 * 地区:青州、東郡
 * 任务条件:需完成徐州復仇戰的任務
 * 流程:
 * 1.到東郡官邸語曹操交談，棗祇會出現，答應協助。
 * 2.前往泰山山洞，在第二層會遇見玄武兵，與他交談，會與張超、張邈、詹茲、劉翊進入戰鬥。
 * 3.勝利後在前往第四層，在裡面會遇見臧霸，與他交談，會與毛暉、臧霸、曹性、郝萌、成廉、徐翕進入戰鬥。
 * 4.勝利後，與家僕交談。
 * 5.再回到東郡，進入東郡近郊與兗州兵交談，會與宋憲、張遼、陳宮、高順、魏續、侯成進入戰鬥。
 * 6.勝利後，在前往陳留樹林第二層與高雅交談，會與郭貢、張超、高雅、張邈、吳資、侯成、氾嶷進入戰鬥。
 * 7.勝利後，再前往許昌山洞與薛蘭交談，會與成廉、曹性、郝萌、李封、侯成進入戰鬥。
 * 8.勝利後繼續進入，會遇見一個黑衫軍，與他交談，再到他身後與呂布交談，會與魏續、陳宮、呂布、張遼、臧霸、高順、宋憲進入戰鬥。
 * 9.勝利後，進入許昌城與曹崇交談，曹操會出現，再與曹操交談，答應協助即可完成任務。
 * 任务奖品:合金戰甲(防+18、敏-3、Lv.50)
 * 备注:無
 * <p/>
 * Date: 2017/6/5
 * Time: 10:22
 *
 * @author Tian.Dong
 */
public class Q6_yanzhouzhengduozhan extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("兗州爭奪戰1(曹操)");
        gameClient.moveFar(11002);
        gameClient.simpleMove(3);
        gameClient.segmentMoveTo(570, 320);
        gameClient.getChooseQueue().clear();
        gameClient.getChooseQueue().add(1);
        gameClient.clickNPC(1);
        gameClient.simpleSleep();

        String steps = "兖州争夺战2(玄武军) - 11502=11502=20=3110,320=0=0==0=True=8=5=0\n" +
                "兖州争夺战3(韩忠) - 11521=11521=2=270,280=1=2==0=True=6=9=0\n" +
                "兖州争夺战4(臧霸) - 11505=11505=5=290,280=0=0==0=True=1=5=0\n" +
                "兖州争夺战5(臧霸) - 11505=11505=5=290,280=0=0==0=False=0=0=0\n" +
                "兖州争夺战6(家仆) - 11505=11505=6=530,260=0=0==0=True=3=0=0\n" +
                "兖州争夺战7(兖州兵) - 11003=11003=2=1350,420=0=0==0=True=5=7=0\n" +
                "兖州争夺战8(高雅) - 13812=13812=8=370,340=0=0==0=True=3=8=0\n" +
                "兖州争夺战9(薛兰) - 13503=13503=6=290,280=0=0==0=True=9=9=0\n" +
                "兖州争夺战10(黑衫队) - 13502=13502=5=890,1040=0=0==0=True=9=0=0\n" +
                "兖州争夺战11(吕布) - 13502=13502=8=1290,920=0=0==0=True=1=11=0\n" +
                "兖州争夺战12(曹嵩) - 13104=13104=2=410,280=0=0==0=True=4=0=0\n" +
                "兖州争夺战13(曹操) - 13104=13104=4=530,360=0=0=1-3=0=True=4=0=0";

        /**
         * 1     兖州争夺战12(曹嵩) - 13104=13104=2=410,280=0=0==0=True=4=0=0
         * 1     兖州争夺战13(曹操) - 13104=13104=4=530,360=0=0=1-3=0=True=4=0=0
         */

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
            if (!gameClient.isLoginSuccess()) {
                gameClient.waitForLoginSuccess();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("完成兗州爭奪戰");
    }
}
