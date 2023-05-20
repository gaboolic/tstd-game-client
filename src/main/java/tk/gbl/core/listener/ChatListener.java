package tk.gbl.core.listener;

import tk.gbl.bean.BagItem;
import tk.gbl.bean.Context;
import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.ChatCommandConstant;
import tk.gbl.constant.ItemIdType;
import tk.gbl.core.GameClient;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * Date: 2017/5/3
 * Time: 16:36
 *
 * @author Tian.Dong
 */
public class ChatListener extends BaseListener {
    @Override
    public void doAction(final GameClient gameClient, Context context) throws IOException {
        String chatString = (String) context.getParam().get("content");
        final long fromUserId = context.getFromUserId();
        if (fromUserId == gameClient.getUserId()) {
            OutputUtil.output("自己说的话!", gameClient, false);
            return;
        }
        if (chatString.equals(ChatCommandConstant.givemesomemoney.getCommand())) {
            System.out.println(fromUserId + "givemesomemoney");
            gameClient.setTradeMoney(10000);
            gameClient.tradeRequest(fromUserId);
        } else if (chatString.equals(ChatCommandConstant.givemeallmoney.getCommand())) {
            System.out.println(fromUserId + "givemeallmoney");
            gameClient.setTradeMoney(gameClient.getRoleInfo().getMoney());
            gameClient.tradeRequest(fromUserId);
        } else if (chatString.equals(ChatCommandConstant.givememanbing.getCommand())) {
            System.out.println(fromUserId + "givememanbing");
            int manbingIndex = 0;
            for (int i = 0; i < gameClient.getPetList().length; i++) {
                RoleInfo pet = gameClient.getPetList()[i];
                if (pet == null) {
                    continue;
                }
                if (pet.getId() == 17771) {
                    manbingIndex = i;
                    break;
                }
            }
            if (manbingIndex > 0) {
                gameClient.tradePetRequest(fromUserId);
                gameClient.tradePet(manbingIndex);
                gameClient.tradePetConfirm();
                OutputUtil.output("交易给" + fromUserId + "蛮兵", gameClient, true);
            }
        } else if (chatString.equals(ChatCommandConstant.givemexugan.getCommand())) {
            System.out.println(fromUserId + "givemexugan");
            int petIndex = 0;
            for (int i = 0; i < gameClient.getPetList().length; i++) {
                RoleInfo pet = gameClient.getPetList()[i];
                if (pet == null) {
                    continue;
                }
                if (pet.getId() == 14342) {
                    petIndex = i;
                    break;
                }
            }
            if (petIndex > 0) {
                gameClient.tradePetRequest(fromUserId);
                gameClient.tradePet(petIndex);
                gameClient.tradePetConfirm();
                OutputUtil.output("交易给" + fromUserId + "许干", gameClient, true);
            }
        } else if (chatString.equals(ChatCommandConstant.givemebeigou.getCommand())) {
            System.out.println(fromUserId + "givemebeigou");
            int petIndex = gameClient.getPetIndex(15322);
            if (petIndex == -1) {
                OutputUtil.output("找不到宠物," + fromUserId + ",卑枸", gameClient, true);
                return;
            }
            gameClient.tradePetRequest(fromUserId);
            gameClient.tradePet(petIndex);
            gameClient.tradePetConfirm();
            OutputUtil.output("交易给" + fromUserId + "卑枸", gameClient, true);
        } else if (chatString.equals(ChatCommandConstant.givemeshitianluo.getCommand())) {
            //两个石田螺
            final int itemId = ItemIdType.shitianluo.getId();
            final int requireCount = 2;
            new Thread() {
                public void run() {
                    try {
                        tradeItem(gameClient, fromUserId, itemId, requireCount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else if (chatString.equals(ChatCommandConstant.givemeheisongmu.getCommand())) {
            //五个黑松木
            final int itemId = ItemIdType.heisongmu.getId();
            final int requireCount = 5;
            new Thread() {
                public void run() {
                    try {
                        tradeItem(gameClient, fromUserId, itemId, requireCount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else if (chatString.equals(ChatCommandConstant.givemeqingranbu.getCommand())) {
            //5个轻染布
            final int itemId = ItemIdType.qingranbu.getId();
            final int requireCount = 5;
            new Thread() {
                public void run() {
                    try {
                        tradeItem(gameClient, fromUserId, itemId, requireCount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else if (chatString.equals(ChatCommandConstant.givemeyinghoupi.getCommand())) {
            //1个硬厚皮
            final int itemId = ItemIdType.yinghoupi.getId();
            final int requireCount = 1;
            new Thread() {
                public void run() {
                    try {
                        tradeItem(gameClient, fromUserId, itemId, requireCount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else if (chatString.equals(ChatCommandConstant.givemetianzhuzhi.getCommand())) {
            //14个天竹纸
            final int itemId = ItemIdType.tianzhuzhi.getId();
            final int requireCount = 14;
            new Thread() {
                public void run() {
                    try {
                        tradeItem(gameClient, fromUserId, itemId, requireCount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else if (chatString.startsWith("giveme")) {
            String[] params = chatString.split("-");
            final int itemId = Integer.parseInt(params[1]);
            final int requireCount = Integer.parseInt(params[2]);
            new Thread() {
                public void run() {
                    try {
                        tradeItem(gameClient, fromUserId, itemId, requireCount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

    }

    private void tradeItem(GameClient gameClient, long fromUserId, int itemId, int requireCount) throws IOException {
        BagItem[] bagItems = gameClient.getBagItems();
        BagItem[] copyBagItems = new BagItem[26];
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            BagItem copyBagItem = new BagItem();
            copyBagItem.setId(bagItem.getId());
            copyBagItem.setCount(bagItem.getCount());
            copyBagItems[i] = copyBagItem;
        }

        int allCount = 0;
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = copyBagItems[i];
            if (bagItem == null) {
                continue;
            }
            if (bagItem.getId() == itemId) {
                allCount += bagItem.getCount();
            }
        }
        if (allCount < requireCount) {
            OutputUtil.output(itemId + "(" + ItemIdUtil.getItemName(itemId) + ")数量不足" + requireCount + "个!!!!!", gameClient, true);
            return;
        }
        BagItem bagItem25 = copyBagItems[25];
        gameClient.getTradeItemIndexList().clear();
        gameClient.getTradeItemIndexList().add(25);
        //第25个格子 物品数量大于等于需要值 直接交易
        if (bagItem25 != null && bagItem25.getCount() >= requireCount) {
            gameClient.tradeRequest(fromUserId);
            return;
        } else {
            int item25Count = 0;
            if (bagItem25 != null) {
                item25Count = bagItem25.getCount();
            } else {
                bagItem25 = new BagItem();
            }
            for (int i = 1; i <= 24; i++) {
                BagItem bagItem = copyBagItems[i];
                if (bagItem == null) {
                    continue;
                }

                if (bagItem.getId() == itemId) {
                    if (bagItem.getCount() >= (requireCount - item25Count)) {
                        gameClient.splitItem(i, 25, requireCount - item25Count);
                        bagItem25.setCount(bagItem25.getCount() + (requireCount - item25Count));
                    } else {
                        gameClient.splitItem(i, 25, bagItem.getCount());
                        bagItem25.setCount(bagItem25.getCount() + bagItem.getCount());
                    }
                }
                if (bagItem25.getCount() >= requireCount) {
                    break;
                }
            }
        }
        if (bagItem25.getCount() >= requireCount) {
            gameClient.tradeRequest(fromUserId);
        }
    }
}
