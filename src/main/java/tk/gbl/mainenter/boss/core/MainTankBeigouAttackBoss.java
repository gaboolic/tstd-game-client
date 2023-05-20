package tk.gbl.mainenter.boss.core;

import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 卑枸 批量打boss
 * Date: 2017/4/28
 * Time: 10:44
 *
 * @author Tian.Dong
 */
public class MainTankBeigouAttackBoss {
    public static void main(String[] args) {
        String storeUsername = "119248";
        String storePassword = "123456";
        GameClient storeGameClient = new GameClient();
        storeGameClient.setIndex(1);
        storeGameClient.setConsoleOutput(true);
        storeGameClient.setFileOutput(true);
        storeGameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        storeGameClient.setUsername(storeUsername);
        storeGameClient.setPassword(storePassword);
        storeGameClient.setInitMapId(0);
        storeGameClient.init();
        storeGameClient.setTeamLeaderId(0);
        storeGameClient.waitForLoginSuccess();
        storeGameClient.moveFar(12001);
        //装备                护腕   鞋    刀     甲    头    攻锦囊
        String itemListStr = "21766,22956,10529,19245,20932,23344";
        List<Integer> storeItemList = new ArrayList<>();
        for (String storeItemStr : itemListStr.split(",")) {
            storeItemList.add(Integer.parseInt(storeItemStr));
        }

        String username = "115040";
        String password = ProcessConstant.p123456;
        final GameClient gameClient = new GameClient();
        gameClient.setUsername(username);
        gameClient.setPassword(password);

        try {
            BeigouAttackBossUtil.doFlow(gameClient,storeGameClient,storeItemList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        storeGameClient.close();
    }
}
