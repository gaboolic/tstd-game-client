package tk.gbl.script;

import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.SocketReadThread;
import tk.gbl.show.ShowUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Date: 2017/3/28
 * Time: 12:30
 *
 * @author Tian.Dong
 */
public class MainTankXianggua {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("114.112.97.137", 6414);

        GameClient gameClient = new GameClient();
        gameClient.setConsoleOutput(false);
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

//        loginRequest.setUsername("WP00114245");
//        loginRequest.setPassword("123456");
        gameClient.setUsername("WP00113312");
        gameClient.setPassword("123456");
        gameClient.login();



        /*respLength = is.read(results);
        int[] temp = ByteArrayToIntArrayUtil.xorAndTransform(results, respLength);
        boolean loginSuccess = false;
        System.out.println(ShowUtil.show(results,respLength));
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
            System.out.println(ShowUtil.show(results,respLength));

            temp = ByteArrayToIntArrayUtil.xorAndTransform(results, respLength);
            if (temp[4] == 0x47 && temp[5] == 0x01) {
                System.out.println("需要创建角色");
                createNewRole(gameClient);
            }
        }*/


//        if (loginSuccess) {
            SocketReadThread socketReadThread = new SocketReadThread(gameClient);
            socketReadThread.start();
//        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.moveTo(1010, 1160);//邺城广场 小绿豆对话
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始扔东西");
        int count = 0;
        while(true) {
            gameClient.clickNPC(0x2B);//43 小绿豆
            gameClient.eventOk();
            gameClient.eventOk();
            gameClient.eventOk();
            gameClient.throwAway(1, 1);
            count++;
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count % 1000 == 0) {
                System.out.println("扔了"+count+"个");
            }
        }
    }

    private static void createNewRole(GameClient gameClient) throws IOException {
        gameClient.checkName("嗷大猫");
    }
}
