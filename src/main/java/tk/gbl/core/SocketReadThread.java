package tk.gbl.core;

import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.handle.TotalHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.OutputUtil;

import java.io.*;
import java.net.Socket;

/**
 * Date: 2017/3/27
 * Time: 19:29
 *
 * @author Tian.Dong
 */
public class SocketReadThread extends Thread {

    GameClient gameClient;

    public SocketReadThread(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    @Override
    public void run() {
        TotalHandler totalHandler = new TotalHandler();
        InputStream is = null;
        try {
            is = gameClient.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                gameClient.clear();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            OutputUtil.output("socket.getInputStream() 断开连接", gameClient, true);
            return;
        }

        while (true) {
            try {
                int temp0 = (is.read() & 0xFF) ^ 0xAD;
                int temp1 = (is.read() & 0xFF) ^ 0xAD;
                int temp2 = (is.read() & 0xFF) ^ 0xAD;
                int temp3 = (is.read() & 0xFF) ^ 0xAD;
                int dataStart = (temp0 << 8) + temp1;
                if (dataStart != 0xF444) {
                    OutputUtil.output("协议头不对" + dataStart, gameClient, true);
                    gameClient.clear();
                    break;
                }
                int dataLength = (temp3 << 8) + temp2;
                int[] temp = new int[dataLength + 4];
                temp[0] = temp0;
                temp[1] = temp1;
                temp[2] = temp2;
                temp[3] = temp3;
                for (int i = 0; i < dataLength; i++) {
                    temp[4 + i] = is.read() & 0xFF ^ 0xAD;
                }
                totalHandler.singleHandle(temp, is, gameClient);
            } catch (Exception e) {
                e.printStackTrace();
                OutputUtil.output("is.read 断开连接", gameClient, true);
                gameClient.clear();
                break;
            }
        }

        if (gameClient.isReConnect()) {
            try {
                Thread.sleep(10000);
                OutputUtil.output("重新连接", gameClient, true);
                gameClient.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
