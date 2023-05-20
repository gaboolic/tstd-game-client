package tk.gbl.mainenter.zhuansheng;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ItemIdType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.script.work.zhuansheng.Z1_sipanguAction;
import tk.gbl.core.script.work.zhuansheng.Z2_sicangqiongAction;

import java.io.IOException;

/**
 * Date: 2017/5/6
 * Time: 9:13
 *
 * @author Tian.Dong
 */
public class MainTankZhuanshengShangtianWork {
    public static void main(String[] args) throws IOException {
        String password = ProcessConstant.password;
        String usernames = "47809,47810,47812,47813";
        for (String username : usernames.split(",")) {
            final GameClient gameClient = new GameClient();
            gameClient.setServerIndex(1);
            gameClient.setConsoleOutput(true);
            gameClient.setFileOutput(true);
            gameClient.setIndex(6);
            gameClient.setUsername(username);
            gameClient.setPassword(password);
            gameClient.setTeamLeaderId(0);
            gameClient.init();
            gameClient.waitForLoginSuccess();
            gameClient.moveFar(12003);
            if (gameClient.getItemCount(ItemIdType.tongtianmodou.getId()) > 0) {
                BagItem[] bagItems = gameClient.getBagItems();
                for (int i = 1; i <= 25; i++) {
                    BagItem bagItem = bagItems[i];
                    if(bagItem == null) {
                        continue;
                    }
                    if (bagItem.getId() == 46070) {
                        gameClient.useItem(i, 1);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        gameClient.shangtian();
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
            gameClient.close();
        }
    }
}
