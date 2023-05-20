package tk.gbl.core.script.work.shimao;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ItemIdType;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取任务材料
 * <p/>
 * Date: 2017/5/7
 * Time: 10:29
 *
 * @author Tian.Dong
 */
public class T0_getWorkMaterialAction extends ScriptAction {
    @Override
    public void doAction(GameClient gameClient) throws IOException {
        System.out.println("开始获取任务材料");

        while (!checkItem(gameClient)) {
            System.out.println("身上材料不够");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("获取任务材料完成");
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
        Integer weizhuangwawaCount = itemCountMap.get(48075);
        if (weizhuangwawaCount != null && weizhuangwawaCount > 0) {
            return true;
        }

        Integer shitianluoCount = itemCountMap.get(ItemIdType.shitianluo.getId());
        if (shitianluoCount == 1) {
            return false;
        }
        Integer yinghoupiluoCount = itemCountMap.get(ItemIdType.yinghoupi.getId());
        if (yinghoupiluoCount == null || yinghoupiluoCount < 1) {
            return false;
        }
        Integer heisongmuCount = itemCountMap.get(ItemIdType.heisongmu.getId());
        if (heisongmuCount == null || heisongmuCount < 5) {
            return false;
        }
        Integer qingranbuCount = itemCountMap.get(ItemIdType.qingranbu.getId());
        if (qingranbuCount == null || qingranbuCount < 5) {
            return false;
        }
        return true;
    }
}
