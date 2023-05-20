package tk.gbl;

import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.listener.FightEndListener;
import tk.gbl.core.listener.FightStartListener;
import tk.gbl.core.listener.RoundStartListener;
import tk.gbl.core.script.InitToZJGCAction;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class MainTank {
   public static void main2(String[] args) {
        GameTeam gameTeam = new GameTeam();
        gameTeam.setUsernames("WP00115037 "
                        + "WP00115038 "
//                        + "WP00115039 "
//                        + "WP00115040 "
//                        + "WP00115041 "
        );
        gameTeam.setInitMapId(12001);
        gameTeam.init();

    }
    public static void main(String[] args) throws IOException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(30);

        String aaa =
                "WP00138213 123456\n" +
                "WP00138214 123456\n" +
                "WP00138215 123456\n" +
                "WP00138216 123456\n" +
                "WP00138217 123456\n" +
                "WP00138218 123456\n" +
                "WP00138219 123456\n" +
                "WP00138220 123456\n" +
                "WP00138221 123456\n" +
                "WP00138222 123456\n" +
                "WP00138223 123456\n" +
                "WP00138224 123456\n" +
                "WP00138225 123456\n" +
                "WP00138226 123456\n" +
                "WP00138227 123456\n" +
                "WP00138228 123456\n" +
                "WP00138229 123456\n" +
                "WP00138230 GJR8A7FW5J\n" +
                "WP00138231 CMKJ625QYN\n" +
                "WP00138232 EKMEWTR9Q5\n" +
                "WP00138233 F3C92NY6DJ\n" +
                "WP00138234 339NA95JT9\n" +
                "WP00138235 JN5C39JJCC\n" +
                "WP00138236 DH4MW8WQ2K\n" +
                "WP00138237 QFF7JBFF2Q\n" +
                "WP00138238 JNXNAEBCAA\n" +
                "WP00138239 GC34Q7WR86\n" +
                "WP00138240 CBBBDYCCMQ\n" +
                "WP00138241 R4A53Y3FJD\n" +
                "WP00138242 TDCCYYGQ36\n" +
                "WP00138243 X8JEPA6M8T\n" +
                "WP00138244 ENRYQ86GQR\n" +
                "WP00138245 JKEFJQFHGP\n" +
                "WP00138246 P546KWH39R\n" +
                "WP00138247 E2B2PWHMK6\n" +
                "WP00138248 GC5HFJH7XC\n" +
                "WP00138249 D68GRN238J\n" +
                "WP00138250 FCW9YCM9YR";
        for(final String up:aaa.split("\n")) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    final GameClient gameClient = new GameClient();
                    //两袖清风赵德汉，一生清廉丁义珍，贪污腐败李达康，诚实守信蔡成功，贤妻良助欧阳菁，与世无争祁同伟，坐怀不乱高育良，火眼金睛张树立，胸怀宇宙孙连城
                    gameClient.setPreNickname("装备仓库");
                    gameClient.setUsername(up.split(" ")[0]);
                    gameClient.setPassword("123456");

                    gameClient.init();
                    gameClient.waitForLoginSuccess();
                    gameClient.moveFar(12001);
                    System.out.println("***当前地图：" + gameClient.getCurrentMapId());
                    gameClient.close();
                }
            });

        }
    }

}
