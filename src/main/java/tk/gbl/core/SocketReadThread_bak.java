package tk.gbl.core;

import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.handle.TotalHandler;
import tk.gbl.util.ByteArrayToIntArrayUtil;

import java.io.*;
import java.net.Socket;

/**
 * Date: 2017/3/27
 * Time: 19:29
 *
 * @author Tian.Dong
 */
public class SocketReadThread_bak extends Thread {

    Socket socket;
    GameClient gameClient;

    public SocketReadThread_bak(GameClient gameClient, Socket socket) {
        this.gameClient = gameClient;
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        TotalHandler totalHandler = new TotalHandler();
        InputStream is = null;
        try {
            is = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("socket.getInputStream() 断开连接");
            return;
        }

        File file = new File("F:\\tool\\wpe三件套\\抓包\\output.dat");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            byte[] results = new byte[ProcessConstant.cacheMaxLength];
            try {
                int respLength = is.read(results);
                if (respLength == -1) {
                    try {
                        is.close();
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("服务器-1 断开连接");
                    return;
                    /*System.out.println("服务器-1 断开连接");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;*/
                }
                int[] temp = ByteArrayToIntArrayUtil.xorAndTransform(results, respLength);
                totalHandler.newHandle(0, temp, is, gameClient);
//                totalHandler.handle(temp, is, gameClient);

//                System.err.println(respLength);
//                System.err.println(ShowUtil.show(results, respLength));
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    is.close();
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("IOException 断开连接");
                return;
            }
        }
    }
}
