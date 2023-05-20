package tk.gbl.client;

import org.junit.Test;
import tk.gbl.bean.BagItem;
import tk.gbl.core.GameClient;
import tk.gbl.core.SocketReadThread;
import tk.gbl.util.ItemIdUtil;

import java.io.IOException;
import java.net.Socket;

/**
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class GameClientTest {
    public static void main(String[] args) throws IOException {
        GameClient gameClient = new GameClient();
        gameClient.setUsername("wp115594");
        gameClient.setPassword("123456");
        gameClient.setIndex(4);
        gameClient.init();

        while (!gameClient.isLoginSuccess()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("登录成功");
        gameClient.moveFar(12001);
    }

    /**
     * 没匹配到处理器F4 44 02 00 09 01
     * 没匹配到处理器F4 44 05 00 18 05 43 03 00 F4 44 05 00 18 05 44 03 00 F4 44 05 00 18 05 4D 03 00 F4 44 05 00 18 05 4E 03 00 F4 44 05 00 18 05 57 03 00 F4 44 05 00 18 05 58 03 00 F4 44 05 00 18 05 59 03 00 F4 44 05 00 18 05 5C 03 00 F4 44 05 00 18 05 5D 03 00 F4 44 05 00 18 05 60 03 00 F4 44 05 00 18 05 61 03 00 F4 44 05 00 18 05 62 03 00 F4 44 05 00 18 05 46 03 00 F4 44 05 00 18 05 47 03 00 F4 44 05 00 18 05 48 03 00 F4 44 05 00 18 05 67 03 00 F4 44 05 00 18 05 63 03 00 F4 44 05 00 18 05 64 03 00 F4 44 05 00 18 05 6B 03 00 F4 44 05 00 18 05 6C 03 00 F4 44 05 00 18 05 68 03 00 F4 44 05 00 18 05 69 03 00 F4 44 05 00 18 05 49 03 00 F4 44 05 00 18 05 4A 03 00 F4 44 05 00 18 05 84 03 00 F4 44 05 00 18 05 85 03 00 F4 44 05 00 18 05 D2 03 00 F4 44 05 00 18 05 DD 03 00 F4 44 05 00 18 05 E1 03 00 F4 44 05 00 18 05 E2 03 00
     */


    /**
     * req:F4 44 09 00 0B 02 03 F4 2E 00 00 01 00
     * req:F4 44 09 00 0B 02 03 21 4E 00 00 02 00
     * <p/>
     * req:F4 44 09 00 0B 02 03 F9 38 00 00 02 00
     * req:F4 44 09 00 0B 02 03 E6 36 00 00 03 00
     *
     * req:F4 44 09 00 0B 02 03 FF 3A 00 00 01 00
     * req:F4 44 09 00 02 02 31 31 31 31 31 31 31
     */
   // @Test
    public void testPK() throws IOException {
        //F4 44 09 00 0B 02 03 E6 36 00 00 03 00

        //F4 44 09 00 0B 02 03 E6 36 00 00 03 00
        GameClient gameClient = new GameClient();
//        gameClient.pkNPC(14054, 3);
//        gameClient.pkNPC(12020, 1);//张飞
//        gameClient.pkNPC(20001, 2);//宝箱
//        gameClient.pkNPC(14585, 2);//梦梅居士
//        gameClient.pkNPC(14054, 3);//陈宫
//        gameClient.pkNPC(15103, 1);//受伤士兵
//        gameClient.chat("1111111");//聊天
        gameClient.addSkill(4,12025,2);
    }

    @Test
    public void testMergeBag() throws IOException {
        GameClient gameClient = new GameClient();
        gameClient.setUsername("wp114244");
        gameClient.setPassword("123456");
        gameClient.setIndex(4);
        gameClient.init();

        while (!gameClient.isLoginSuccess()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("登录成功,物品");
        BagItem[] bagItems = gameClient.getBagItems();
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            System.out.print(i + " " + bagItems[i].getId() + "("+ ItemIdUtil.getItemName(bagItems[i].getId())+") " + bagItems[i].getCount() + ";");
        }
        System.out.println();
        gameClient.mergeBag();
        System.out.println("mergeBag后");
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            System.out.print(i + " " + bagItems[i].getId() + "("+ ItemIdUtil.getItemName(bagItems[i].getId())+") " + bagItems[i].getCount() + ";");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5秒后");
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            System.out.print(i + " " + bagItems[i].getId() + "("+ ItemIdUtil.getItemName(bagItems[i].getId())+") " + bagItems[i].getCount() + ";");
        }
    }
}
