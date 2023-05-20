package tk.gbl.mainenter.createrole;

import tk.gbl.core.GameClient;

/**
 * Date: 2017/6/3
 * Time: 14:30
 *
 * @author Tian.Dong
 */
public class BatchCreateRoleMainTank {
    public static void main(String[] args){
      String str = "WP00116695 A4QHRCQXAT\n" +
              "WP00119251 4XJD8Y3H4B\n" +
              "WP00119252 MCP8XX882T\n" +
              "WP00119253 DDW4FARJ6N\n" +
              "WP00119254 AQT4D26WDQ\n" +
              "WP00119255 KY87F5WB45\n" +
              "WP00119256 XFF935TWJ5\n" +
              "WP00119257 KA7CRERDW9\n" +
              "WP00119258 CGH264MDPM\n" +
              "WP00119259 AQA3GK96M2\n" +
              "WP00119260 KA4AAFKHHF\n" +
              "WP00119261 WBGH8F88DJ\n" +
              "WP00119262 JBY9RB5P3D\n" +
              "WP00119263 G4G4H2BK7H\n" +
              "WP00119264 2HGRN5QNCQ\n" +
              "WP00119265 999E7DWTW9\n" +
              "WP00119266 6FFPXEHKR5\n" +
              "WP00119267 FCXM3R3HX8\n" +
              "WP00119268 F66RC2CMAJ\n" +
              "WP00119269 8BJ8DXEHXB\n" +
              "WP00119270 Y4KHMBJYWQ\n" +
              "WP00119271 2NDMBKBQW9\n" +
              "WP00119240 EE5NC2X9MR\n" +
              "WP00119241 YH6KGME3TB\n" +
              "WP00119242 KTGPM5PTYH\n" +
              "WP00119243 HWDFCJENN7\n" +
              "WP00119244 7KH45HQY5A\n" +
              "WP00119245 BR45CR7R39\n" +
              "WP00119246 6Q6XPPQMEQ\n" +
              "WP00119247 BWN4NF4AT9\n" +
              "WP00119248 4DGNYD37JJ\n" +
              "WP00119249 N6HYRP7TFB\n" +
              "WP00119250 N6NYNRY8M3";
        for(String line:str.split("\n")) {
            String username = line.split(" ")[0];
            String password = line.split(" ")[1];
            GameClient gameClient = new GameClient();
            gameClient.setUsername(username);
            gameClient.setPassword(password);
            gameClient.setConsoleOutput(false);
            gameClient.setFileOutput(false);
            gameClient.setIndex(1);
            gameClient.init();
            gameClient.waitForLoginSuccess();
            gameClient.autoUseAndThrow();
            gameClient.moveFar(12001);
            gameClient.autoUseAndThrow();
            gameClient.close();
        }
    }
}
