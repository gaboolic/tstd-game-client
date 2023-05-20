package tk.gbl.mainenter.boss;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.script.work.activity.AttackBossAction;
import tk.gbl.core.script.work.activity.KezhanxiuxiAction;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * Date: 2017/6/7
 * Time: 18:45
 *
 * @author Tian.Dong
 */
public class MainTankAttackBoss {
    public static void main(String[] args) throws IOException {
//        String username = "47809";
//        String username = "47810";
//        String username = "47813";

//        String username = "47811";//1
//        String username = "47812";
//        String username = "27166";
//        String username = "27167";//没打完
        String username = "39259";//没打
//        String username = "38253";
//        String username = "39260";
        String password = ProcessConstant.password;

        final GameClient gameClient = new GameClient();
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.setIndex(5);
        gameClient.setUsername(username);
        gameClient.setPassword(password);
        gameClient.setTeamLeaderId(0);
        gameClient.init();
        gameClient.waitForLoginSuccess();
        gameClient.doScriptAction(new KezhanxiuxiAction());
        for (int i = 0; i < 5; i++) {
            boolean result = checkWastage(gameClient);
            if (!result) {
                OutputUtil.output("装备损坏,当前次数" + i, gameClient, true);
                break;
            }
            System.out.println("打boss");
            gameClient.doScriptAction(new AttackBossAction());
            gameClient.simpleSleep();
            System.out.println("客栈休息");
            gameClient.doScriptAction(new KezhanxiuxiAction());
            gameClient.simpleSleep();
        }
        gameClient.close();
    }

    private static boolean checkWastage(GameClient gameClient) {
        boolean result = true;
        BagItem[] equipItems = gameClient.getEquipItems();
        for (int i = 1; i <= 5; i++) {
            if(equipItems[i] == null) {
                continue;
            }
            if (equipItems[i].getWastage() > 200) {
                System.out.println(equipItems[i].getId()+"损坏");
                result = false;
            }
        }
        if(gameClient.getPet() != null) {
            BagItem[] petEquipItems = gameClient.getPet().getEquipItems();
            for (int i = 1; i <= 5; i++) {
                if (petEquipItems[i].getWastage() > 200) {
                    System.out.println(petEquipItems[i].getId()+"损坏");
                    result = false;
                }
            }
        }
        return result;
    }
}
