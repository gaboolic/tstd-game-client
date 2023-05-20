package tk.gbl;

import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.SocketReadThread;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class MainTankTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("114.112.97.137", 6414);

        GameClient gameClient = new GameClient();
        gameClient.setSocket(socket);
        gameClient.connect();

        InputStream is = null;
        try {
            is = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        byte[] results = new byte[ProcessConstant.cacheMaxLength];
        int respLength = is.read(results);
        System.out.println(ShowUtil.show(results));

        gameClient.setUsername("WP00114245");
        gameClient.setPassword("5PXM3545W5");
        gameClient.login();

        respLength = is.read(results);
        int[] temp = ByteArrayToIntArrayUtil.xorAndTransform(results, respLength);
        boolean loginSuccess = false;
        System.out.println(ShowUtil.show(results));
        if (temp[4] == 0x14 && temp[5] == 0x08) {
            System.out.println("登录成功");
            loginSuccess = true;
        }
        if (temp[4] == 0x01 && temp[5] == 0x06) {
            System.out.println("密码错误");
            loginSuccess = false;
        }
        if (temp[4] == 0x00 && temp[5] == 0x13) {
            System.out.println("重复登录断线");
            loginSuccess = false;
        }
        if (temp[4] == 0x01 && temp[5] == 0x03) {
            System.out.println("没有角色");
            respLength = is.read(results);
            temp = ByteArrayToIntArrayUtil.xorAndTransform(results, respLength);
            if (temp[4] == 0x47 && temp[5] == 0x01) {
                System.out.println("需要创建角色");
                createNewRole(gameClient);
            }
        }


//        if (loginSuccess) {
            SocketReadThread socketReadThread = new SocketReadThread(gameClient);
            socketReadThread.start();
//        }
    }

    private static void createNewRole(GameClient gameClient) throws IOException {
        gameClient.checkName("嗷大猫");
    }
}
