package tk.gbl.mainenter.tianshu;

import tk.gbl.core.GameClient;
import tk.gbl.util.FileReadUtil;
import tk.gbl.util.OutputUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 仙斗擂台
 * <p/>
 * Date: 2017/6/18
 * Time: 11:00
 *
 * @author Tian.Dong
 */
public class MainTankXiandou {
    public static void main(String[] args) throws IOException {
        //登录10个号 去仙斗
        String password = "44444444";
        String all65Account = "xiandou/65级-51个.txt";
        String doneXiandouAccount = "account_work_xiandou.txt";

        for (int i = 0; i < 1; i++) {
            doFlow(password, all65Account, doneXiandouAccount);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doFlow(String password, String all65Account, String doneXiandouAccount) throws IOException {

        List<String> allGameClientList = FileReadUtil.getDoneGameClientList(all65Account);
        List<String> doneGameClientList = FileReadUtil.getDoneGameClientList(doneXiandouAccount);
        List<GameClient> gameClientList = new ArrayList<>();
        for (String username : allGameClientList) {
            if (doneGameClientList.contains(username)) {
                continue;
            }

            GameClient gameClient = new GameClient();
            if (!username.startsWith("wp")) {
                username = "wp" + username;
            }
            gameClient.setUsername(username);
            gameClient.setPassword(password);
            gameClient.getGameConfig().setFightOppositeClose(true);
            gameClientList.add(gameClient);
            if (gameClientList.size() >= 40) {
                //break;
            }
        }
        if (gameClientList.size() < 11) {
            System.out.println("小号剩余数量不足10，退出程序");
            return;
        }
        System.out.println("本次执行：");
        for (GameClient gameClient : gameClientList) {
            System.out.print(gameClient.getUsername() + ",");
            gameClient.init();
        }
        System.out.println();
        System.out.println("开始点击");
        for (GameClient gameClient : gameClientList) {
            gameClient.waitForLoginSuccess();
            if(gameClient.getCurrentMapId() != 49909) {
                gameClient.close();
                continue;
            }
            gameClient.moveFar(49909);
            gameClient.segmentMoveTo(1010, 560);
            gameClient.getChooseQueue().clear();
            gameClient.getChooseQueue().add(1);
            gameClient.clickNPC(1);
            OutputUtil.output("点击NPC",gameClient,true);
        }
        System.out.println("开始匹配!!!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5秒后，该退的退了!!!");
        for (GameClient gameClient : gameClientList) {
            if (gameClient.getGameParam().isInFight() && gameClient.isLoginSuccess()) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(doneXiandouAccount), true));
                bufferedWriter.write(gameClient.getUsername());
                bufferedWriter.write("\r\n");
                bufferedWriter.flush();
                bufferedWriter.close();
            }
            gameClient.close();
        }
        System.out.println("执行完一次流程!!!");
    }
}
