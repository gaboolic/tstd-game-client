package tk.gbl.mainenter.tianshu;

import tk.gbl.core.GameClient;
import tk.gbl.util.FileReadUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/6/18
 * Time: 21:03
 *
 * @author Tian.Dong
 */
public class MainTankXiandouLingjiang {
    public static void main(String[] args) throws IOException {
        String password = "44444444";
        String all65Account = "account_work_xiandou.txt";
        List<String> allGameClientList = FileReadUtil.getDoneGameClientList(all65Account);
        List<GameClient> gameClientList = new ArrayList<>();
        for (String username : allGameClientList) {
            GameClient gameClient = new GameClient();
            gameClient.setUsername(username);
            gameClient.setPassword(password);
            gameClientList.add(gameClient);
        }

        for (GameClient gameClient : gameClientList) {
            gameClient.init();
            gameClient.waitForLoginSuccess();

            if (gameClient.getCurrentMapId() != 49909 && gameClient.getCurrentMapId() != 49910) {
                System.out.println("当前地图不是竞技场");
                gameClient.close();
                continue;
            }
            if (gameClient.getItemCount(30090) < 1
                    && gameClient.getItemCount(30091) < 1
                    && gameClient.getItemCount(30092) < 1) {
                System.out.println("身上没有天殊令");
                gameClient.close();
                continue;
            }
            if (gameClient.getCurrentMapId() == 49909) {
                gameClient.getChooseQueue().clear();
                gameClient.getChooseQueue().add(1);
                gameClient.simpleMove(2);
            }

            if (gameClient.getCurrentMapId() == 49910) {
                gameClient.segmentMoveTo(390, 260);
                int chooseIndex = 1;
                if (gameClient.getItemCount(30090) > 0) {
                    chooseIndex = 1;
                } else if (gameClient.getItemCount(30091) > 0) {
                    chooseIndex = 2;
                } else if (gameClient.getItemCount(30092) > 0) {
                    chooseIndex = 3;
                }
                gameClient.getChooseQueue().clear();
                gameClient.getChooseQueue().add(chooseIndex);
                gameClient.clickNPC(1);
                gameClient.simpleSleep();
            }
            gameClient.close();
        }
    }
}
