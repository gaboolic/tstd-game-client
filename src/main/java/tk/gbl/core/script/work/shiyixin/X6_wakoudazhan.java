package tk.gbl.core.script.work.shiyixin;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;

/**
 * 任务名称:瓦口大战
 * 地区:益州、成都城
 * 任务条件:需完成「曹操定汉中」的任务
 * 流程:
 * 1.进入成都官府与諸葛亮交谈后，取得【!成都佳酿】并答应协助。
 * 2.前往瓦口树林左上方与张飞交谈后，答应收集一個「!稻草人娃娃」(夷州大地图-稻草人娃娃)。
 * 3.取得道具后再返回与张飞交谈后，往右移动一下触发剧情后，会于张郃、征军校x5进行战斗(需五回合完成)。
 * 4.战斗胜利后返回成都官府25301与諸葛亮交谈，即可完成任務。
 * 任务奖品:经验值若干(0.5%)、[隨机取得]白蛇怒(知+31、敏+4,LV50)、回意錦囊、三思錦囊
 * 备注:无
 * <p/>
 * Date: 2017/5/25
 * Time: 19:26
 *
 * @author Tian.Dong
 */
public class X6_wakoudazhan extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String steps222 = "瓦口大战-诸葛=25301=2=400,400=0=0==0\n" +
                "瓦口大战-瓦口张飞=25447=7=360,280=0=0==0\n" +
                "瓦口大战-张合=25447=0=360,280=1=6==0";

        String steps = "1瓦口大战-25301=25301=2=290,320=0=0=0=0\n" +
                "2瓦口大战-25447=25447=0=1482,295=1=5=0=0\n" +
                "3瓦口大战-25447=25447=7=270,260=0=0=0=0\n" +
                "4瓦口大战-25447(给稻草人娃娃)=25447=7=270,260=0=0=0=0\n" +
                "5瓦口大战-25447=25447=0=270,260=1=6=0=0\n" +
                "6瓦口大战-25301=25301=2=290,320=0=0=0=0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成瓦口大战");
    }
}