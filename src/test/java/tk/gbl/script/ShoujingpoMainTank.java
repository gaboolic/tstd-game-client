package tk.gbl.script;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.EquipType;
import tk.gbl.constant.ItemIdType;
import tk.gbl.core.GameClient;
import tk.gbl.core.script.ShoujingpoAction;
import tk.gbl.core.script.work.activity.KezhanxiuxiAction;
import tk.gbl.core.script.work.qixiqiqiaojie.JixianghuashengAction;
import tk.gbl.core.script.work.qixiqiqiaojie.XiaoxiangguaAction;
import tk.gbl.core.script.work.suipian.SJ2_jiancanpijia;
import tk.gbl.core.script.work.suipian.SJ3_jianshengxiuchangqiang;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.util.Scanner;

/**
 * Date: 2017/5/15
 * Time: 12:58
 *
 * @author Tian.Dong
 */
public class ShoujingpoMainTank {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("ShutdownHook");
            }
        });

        //wp115557,123456;wp115558,123456
        String usernameAndP = "wp124000 123456";
//        String usernameAndP = "wp115037 123456";
        String username = usernameAndP.split(" ")[0];
        String password = usernameAndP.split(" ")[1];

        final GameClient gameClient = new GameClient();
        gameClient.setServerIndex(2);
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.setIndex(6);
        gameClient.setUsername(username);
        gameClient.setPassword(password);
        gameClient.setTeamLeaderId(0);
        gameClient.init();
        gameClient.waitForLoginSuccess();

//        gameClient.doScriptAction(new KezhanxiuxiAction());
        for(int i=0;i<255;i++) {
            gameClient.pickUp(i);
        }
        while (true) {
//            gameClient.moveFar(12002);
//            gameClient.segmentMoveTo(890,400);
//            gameClient.pkNPC(17128,10);
//            gameClient.simpleSleep();

//            gameClient.moveFar(12001);
//            gameClient.segmentMoveTo(830,980);
//            gameClient.pkNPC(45299,56);
            gameClient.simpleSleep();
        }
    }
}
