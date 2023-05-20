package tk.gbl.proxy;

import tk.gbl.constant.ProcessConstant;
import tk.gbl.constant.ProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.TotalHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.OutputUtil;

import java.io.*;
import java.net.Socket;

/**
 * Date: 2017/5/17
 * Time: 10:50
 *
 * @author Tian.Dong
 */
public class ServerThread extends Thread {
    Socket socket;
    Socket tsSocket;
    GameClient gameClient;

    TotalHandler totalHandler = new TotalHandler();

    public ServerThread(Socket socket, Socket tsSocket, GameClient gameClient) {
        this.socket = socket;
        this.tsSocket = tsSocket;
        this.gameClient = gameClient;
    }

    @Override
    public void run() {
        //socket的inputStream 和 tsSocket的outputStream连接
        InputStream is = null;
        OutputStream os = null;

        InputStream tsInputStream = null;
        OutputStream tsOutputStream = null;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();

            tsInputStream = tsSocket.getInputStream();
            tsOutputStream = tsSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        gameClient.setSocket(tsSocket);
        gameClient.setInputStream(tsInputStream);
        gameClient.setOutputStream(tsOutputStream);
        final InputStream finalIs = is;
        final OutputStream finalTsOutputStream = tsOutputStream;
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        int temp0 = (finalIs.read() & 0xFF) ^ 0xAD;
                        int temp1 = (finalIs.read() & 0xFF) ^ 0xAD;
                        int temp2 = (finalIs.read() & 0xFF) ^ 0xAD;
                        int temp3 = (finalIs.read() & 0xFF) ^ 0xAD;
                        int dataStart = (temp0 << 8) + temp1;
                        if (dataStart != 0xF444) {
                            break;
                        }
                        int dataLength = (temp3 << 8) + temp2;
                        int[] temp = new int[dataLength + 4];
                        temp[0] = temp0;
                        temp[1] = temp1;
                        temp[2] = temp2;
                        temp[3] = temp3;
                        for (int i = 0; i < dataLength; i++) {
                            temp[4 + i] = finalIs.read() & 0xFF ^ 0xAD;
                        }
                        System.out.println("发送 " + ShowUtil.showOrigin(temp));
                        if(temp.length >= 6) {
                            if (MultiByteToIntUtil.fromAsc(temp[4], temp[5]) == ProtocolConstant.move.getId()
                                    || MultiByteToIntUtil.fromAsc(temp[4], temp[5]) == ProtocolConstant.unknown1730.getId()
                                    || MultiByteToIntUtil.fromAsc(temp[4], temp[5]) == ProtocolConstant.unknown0C01.getId()
                                    || MultiByteToIntUtil.fromAsc(temp[4], temp[5]) == ProtocolConstant.eventOk.getId()
                                    ) {
//                                System.out.println("屏蔽");
//                                continue;
                            }
                        }
                        byte[] bytes = ByteArrayToIntArrayUtil.xorAndTransform(temp, temp.length);
                        finalTsOutputStream.write(bytes);
                        finalTsOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        OutputUtil.output("is.read 断开连接", gameClient, true);
                        break;
                    }
                }
            }
        }.start();


        final InputStream finalTsInputStream = tsInputStream;
        final OutputStream finalOs = os;
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        int temp0 = (finalTsInputStream.read() & 0xFF) ^ 0xAD;
                        int temp1 = (finalTsInputStream.read() & 0xFF) ^ 0xAD;
                        int temp2 = (finalTsInputStream.read() & 0xFF) ^ 0xAD;
                        int temp3 = (finalTsInputStream.read() & 0xFF) ^ 0xAD;
                        int dataStart = (temp0 << 8) + temp1;
                        if (dataStart != 0xF444) {
                            break;
                        }
                        int dataLength = (temp3 << 8) + temp2;
                        int[] temp = new int[dataLength + 4];
                        temp[0] = temp0;
                        temp[1] = temp1;
                        temp[2] = temp2;
                        temp[3] = temp3;
                        for (int i = 0; i < dataLength; i++) {
                            temp[4 + i] = finalTsInputStream.read() & 0xFF ^ 0xAD;
                        }
                        System.out.println("接收 " + ShowUtil.showOrigin(temp));
                        totalHandler.singleHandle(temp, finalTsInputStream, gameClient);
                        byte[] bytes = ByteArrayToIntArrayUtil.xorAndTransform(temp, temp.length);
                        finalOs.write(bytes);
                        finalOs.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }.start();
    }
}
