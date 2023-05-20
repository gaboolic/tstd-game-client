package tk.gbl.core.handle;

import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.chat.ChatHandler;
import tk.gbl.core.handle.chat.ServerChatHandler;
import tk.gbl.core.handle.createrole.CheckNameResHandler;
import tk.gbl.core.handle.createrole.SetPropertiesAndInitResHandler;
import tk.gbl.core.handle.fight.*;
import tk.gbl.core.handle.goods.*;
import tk.gbl.core.handle.login.*;
import tk.gbl.core.handle.move.MoveHandler;
import tk.gbl.core.handle.move.U03ResHandler;
import tk.gbl.core.handle.move.U0CResHandler;
import tk.gbl.core.handle.team.JoinTeamHandler;
import tk.gbl.core.handle.trade.TradeHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/3/29
 * Time: 18:08
 *
 * @author Tian.Dong
 */
public class TotalHandler {

    private static List<BaseHandler> handlerList = new ArrayList<>();

    static {
        handlerList.add(new CloseResHandler());
        handlerList.add(new U0101ResHandler());
        handlerList.add(new ChatHandler());
        handlerList.add(new ServerChatHandler());
        handlerList.add(new U0500ResHandler());
        handlerList.add(new RoleInfoResHandler());
        handlerList.add(new U0602ResHandler());
        handlerList.add(new RoleInfoChangeResHandler());
        handlerList.add(new FightEndResHandler());
        handlerList.add(new JoinTeamHandler());
        handlerList.add(new U0B00ResHandler());
        handlerList.add(new FightRoleInfoHandler());
        handlerList.add(new U0B0AResHandler());
        handlerList.add(new FightFrontInfoResHandler());
        handlerList.add(new U1001ResHandler());

        handlerList.add(new U1407ResHandler());
        handlerList.add(new U1408ResHandler());
        handlerList.add(new U1409ResHandler());
        handlerList.add(new NPCDialogResHandler());

        handlerList.add(new StoreBagResHandler());

        handlerList.add(new U1704ResHandler());
        handlerList.add(new LossItemResHandler());
        handlerList.add(new U170AResHandler());
        handlerList.add(new U170BResHandler());
        handlerList.add(new U171BResHandler());
        handlerList.add(new U171CResHandler());
        handlerList.add(new EquipResHandler());
        handlerList.add(new TradeHandler());
        handlerList.add(new PetInfoResHandler());
        handlerList.add(new MoveHandler());
        handlerList.add(new U1805ResHandler());
        handlerList.add(new U1808ResHandler());
        handlerList.add(new FightUseSkillResHandler());
        handlerList.add(new RoundStartResHandler());
        handlerList.add(new U3503ResHandler());
        handlerList.add(new ConnectResHandler());
        handlerList.add(new LoginResHandler());
        handlerList.add(new BagResHandler());
        handlerList.add(new PickUpResHandler());
        handlerList.add(new CheckNameResHandler());
        handlerList.add(new SetPropertiesAndInitResHandler());
        handlerList.add(new U03ResHandler());
        handlerList.add(new U0CResHandler());
    }

    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        for (BaseHandler baseHandler : handlerList) {
            boolean result = baseHandler.handle(data, is, gameClient);
            if (result) {
                return true;
            }
        }
        if (gameClient.isConsoleOutput()) {
            OutputUtil.output("没匹配到处理器" + ShowUtil.showOrigin(data), gameClient, false);
        }
        return false;
    }

    public void newHandle(int dealIndex, int[] data, InputStream is, GameClient gameClient) throws IOException {
        while (true) {
            System.out.println(dealIndex);
            if (dealIndex == data.length) {
                break;
            }
            if (dealIndex + 4 > data.length) {
                byte[] results = new byte[ProcessConstant.cacheMaxLength];
                int respLength = is.read(results);
                if (respLength == -1) {
                    try {
                        is.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("服务器-1 断开连接");
                    return;
                }
                int[] temp = ByteArrayToIntArrayUtil.xorAndTransform(results, respLength);
                int x = data.length - dealIndex;
                int[] newTemp = new int[temp.length + x];
                System.arraycopy(data, dealIndex, newTemp, 0, x);
                System.arraycopy(temp, 0, newTemp, x, temp.length);
                newHandle(0, newTemp, is, gameClient);
            }
            if (data[dealIndex] == 0xF4 && data[dealIndex + 1] == 0x44) {
                int dataLength = (data[dealIndex + 3] << 8) + data[dealIndex + 2];
                int[] segmentData = new int[dataLength + 4];
                if (dealIndex + segmentData.length <= data.length) {
                    System.arraycopy(data, dealIndex, segmentData, 0, segmentData.length);
                    singleHandle(segmentData, is, gameClient);
                    dealIndex += segmentData.length;
                } else {
                    int x = data.length - dealIndex;
                    System.arraycopy(data, dealIndex, segmentData, 0, x);
                    byte[] results = new byte[ProcessConstant.cacheMaxLength];
                    int respLength = is.read(results);
                    if (respLength == -1) {
                        try {
                            is.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        System.out.println("服务器-1 断开连接");
                        return;
                    }
                    int[] temp = ByteArrayToIntArrayUtil.xorAndTransform(results, respLength);
                    try {
                        System.arraycopy(temp, 0, segmentData, x, segmentData.length - x);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("error:dealIndex=" + dealIndex + ",segmentDataLength=" + segmentData.length + ",tempLength=" + temp.length);
                        System.out.println("x:" + (data.length - dealIndex));
                        System.out.print(Integer.toHexString(data[dealIndex]).toUpperCase() + " " + Integer.toHexString(data[dealIndex + 1]).toUpperCase() + " ");
                        System.out.print(Integer.toHexString(data[dealIndex + 2]).toUpperCase() + " " + Integer.toHexString(data[dealIndex + 3]).toUpperCase() + " ");
                        System.out.println(Integer.toHexString(data[dealIndex + 4]).toUpperCase() + " " + Integer.toHexString(data[dealIndex + 5]).toUpperCase());
                        System.out.println("data:" + ShowUtil.showOrigin(data));
                        System.out.println("temp:" + ShowUtil.showOrigin(temp));
                        System.out.println(x + " " + (segmentData.length - x));
                    }

                    singleHandle(segmentData, is, gameClient);
                    newHandle(segmentData.length - (data.length - dealIndex), temp, is, gameClient);
                }
            } else {
                System.out.println("??? " + dealIndex + " " + ShowUtil.showOrigin(data));
                System.out.print(Integer.toHexString(data[dealIndex]).toUpperCase() + " " + Integer.toHexString(data[dealIndex + 1]).toUpperCase() + " ");
                System.out.print(Integer.toHexString(data[dealIndex + 2]).toUpperCase() + " " + Integer.toHexString(data[dealIndex + 3]).toUpperCase() + " ");
                System.out.println(Integer.toHexString(data[dealIndex + 4]).toUpperCase() + " " + Integer.toHexString(data[dealIndex + 5]).toUpperCase());
                break;
            }
            if (dealIndex == data.length) {
                break;
            }
        }
    }

    public boolean singleHandle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        OutputUtil.output("" + ShowUtil.showOrigin(data), gameClient, false);
        for (BaseHandler baseHandler : handlerList) {
            boolean result = false;
            try {
                result = baseHandler.handle(data, is, gameClient);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                OutputUtil.output("发生了异常!" + e.getMessage(), gameClient, true);
            }
            if (result) {
                return true;
            }
        }
        OutputUtil.output("没匹配到处理器", gameClient, false);
        return false;
    }
}
