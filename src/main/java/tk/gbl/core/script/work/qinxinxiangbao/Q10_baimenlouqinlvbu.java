package tk.gbl.core.script.work.qinxinxiangbao;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ItemIdType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务名称:白門樓擒呂布
 * 地区:兗州、許昌城
 * 任务条件:需完成壽春圍城戰
 * 流程:
 * 1.到許昌城點兵台與郭嘉交談，答應協助。
 * 2.前往徐州相縣樹林，進入第二層後往左方走，與侯諧交談，會與劉何、趙庶、李鄒、秦宜祿、侯諧進入戰鬥。
 * 3.勝利後，到淮陰樹林，進入後往右上方走，與許汜交談，會與王楷、許汜、曹性、郝萌、成廉進入戰鬥。
 * 4.勝利後，再到下邳城，進入下邳大街，與侯成交談，答應協助。
 * 5.蒐集治傷丸x3(鄴城藥舖)、將軍酒x3(徐州客棧)、伏虎金剛繩(徐州城杜平房內)一個。
 * 6.蒐集完後交給侯成。
 * 7.前往下邳北門，走上城門，會與戴乾、徐宣、陳矯進入戰鬥。
 * 8.勝利後，繼續往前走，會與張遼、陳宮、高順、陳群、袁渙進進入戰鬥。
 * 9.勝利後，與呂布交談，會與四色呂布進入戰鬥。
 * 10.勝利後在回去許昌城點兵台與郭嘉交談即可完成任務。
 * 任务奖品:三思錦囊、回憶錦囊、沁心的香包、駿馬x2、名駒x3
 * 备注:無
 * <p/>
 * Date: 2017/6/5
 * Time: 10:24
 *
 * @author Tian.Dong
 */
public class Q10_baimenlouqinlvbu extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        String step1 = "白門樓擒呂布1(郭嘉) - 13014=13014=29=910,380=0=0==0";
        gameClient.doDoDoScriptAction(step1);

        while (!checkItem(gameClient)) {
            try {
                String step2 = "伏虎金剛繩15171=15171=0=236,446=2=0=0=29138";
                gameClient.doDoDoScriptAction(step2);
                gameClient.pickUp(1);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
                gameClient.waitForLoginSuccess();
            }
        }
        String steps = "白門樓擒呂布2(侯諧) - 15822=15822=5=270,2340=0=0==0\n" +
                "白門樓擒呂布3(許汜) - 15831=15831=6=2190,420=0=0==0\n" +
                "白門樓擒呂布4(侯成) - 15014=15014=4=810,380=0=0==0\n" +
                "白門樓擒呂布5(侯成) - 15014=15014=4=810,380=0=0==0\n" +
                "白門樓擒呂布6=15013=0=230,1170=1=2==0\n" +
                "白門樓擒呂布7(張遼) - 15013=15013=27=350,1040=1=3==0\n" +
                "白門樓擒呂布8(呂布) - 15013=15013=14=1090,260=0=0==0\n" +
                "白門樓擒呂布9(郭嘉) - 13014=13014=29=910,380=0=0==0";

        for (String step : steps.split("\n")) {
            gameClient.doDoDoScriptAction(step);
        }
        System.out.println("完成白門樓擒呂布");
    }

    private boolean checkItem(GameClient gameClient) {

        Map<Integer, Integer> itemCountMap = new HashMap<>();
        BagItem[] bagItems = gameClient.getBagItems();
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            Integer count = itemCountMap.get(bagItem.getId());
            if (count == null) {
                itemCountMap.put(bagItem.getId(), bagItem.getCount());
            } else {
                itemCountMap.put(bagItem.getId(), count + bagItem.getCount());
            }
        }
        Integer fuhuCount = itemCountMap.get(ItemIdType.fuhujingangsheng.getId());
        if (fuhuCount == null || fuhuCount < 1) {
            return false;
        }
        return true;
    }
}
