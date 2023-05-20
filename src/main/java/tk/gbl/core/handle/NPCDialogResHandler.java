package tk.gbl.core.handle;

import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.NpcWordsUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class NPCDialogResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }

        //F4 44 11 00 14 01 00 00 00 01 00 03 0C 00 04 D0 07 00 00 00 00
        //F4 44 11 00 14 01 00 00 00 03 06 03 01 00 00 00 00 00 00 01 00 需要选择
        //F4 44 11 00 14 01 00 00 00 01 06 03 15 00 00 00 00 00 00 12 00 收惊婆
        //F4 44 11 00 14 01 00 00 00 05 06 03 02 00 00 00 00 00 00 01 00
        //F4 44 11 00 14 01 00 00 00 01 01 03 01 00 00 00 00 00 00 EE 4C
        //0  1                        9                            19 20
        //F4 44 11 00 14 01 00 00 00 09 00 03 07 00 04 D0 07 00 00 00 00
        if (data[4] == (ResponseProtocolConstant.u1401.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1401.getId() & 0xFF)) {
            int id = MultiByteToIntUtil.from(data[19], data[20]);
            OutputUtil.output("对话框id" + id + " " + NpcWordsUtil.getWords(id), gameClient);
            if (id > 255 || id == 0) {
                if(!gameClient.getGameConfig().isQuickNPCDialog()) {
                    return true;
                }
                gameClient.eventOk();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Integer choose = gameClient.getChooseQueue().poll();
                if (choose != null) {
                    gameClient.simpleChoose(choose);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        if (data[4] == (ResponseProtocolConstant.u1410.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1410.getId() & 0xFF)) {
            if (data.length == 6) {
                if(!gameClient.getGameConfig().isQuickNPCDialog()) {
                    return true;
                }
                OutputUtil.output("u1410对话框", gameClient, true);
                gameClient.eventOk();
                return true;
            }
        }

        //F4 44 02 00 14 11
        if (data[4] == (ResponseProtocolConstant.u1411.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1411.getId() & 0xFF)) {
            if(!gameClient.getGameConfig().isQuickNPCDialog()) {
                return true;
            }
            OutputUtil.output("u1411对话框", gameClient, true);
            gameClient.eventOk();
            return true;
        }

        if (data[4] == (ResponseProtocolConstant.u140B.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u140B.getId() & 0xFF)) {
            if(!gameClient.getGameConfig().isQuickNPCDialog()) {
                return true;
            }
            OutputUtil.output("u140B对话框", gameClient, true);
            gameClient.eventOk();
            return true;
        }

        if (data[4] == (ResponseProtocolConstant.u140C.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u140C.getId() & 0xFF)) {
            if(!gameClient.getGameConfig().isQuickNPCDialog()) {
                return true;
            }
            OutputUtil.output("u140C对话框", gameClient);
            return true;
        }

        if (data[4] == (ResponseProtocolConstant.u140D.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u140D.getId() & 0xFF)) {
            if(!gameClient.getGameConfig().isQuickNPCDialog()) {
                return true;
            }
            OutputUtil.output("u140D对话框", gameClient, true);
            gameClient.eventOk();
            return true;
        }

        if ((data[4] == 0x14) && (data.length == 6)) {
            int protocol = MultiByteToIntUtil.fromAsc(data[4], data[5]);
            if (protocol == 0x1407) {
                return false;
            }
            if (protocol == 0x1408) {
                return false;
            }
            if (protocol == 0x1409) {
                return false;
            }
            if(!gameClient.getGameConfig().isQuickNPCDialog()) {
                return true;
            }
            OutputUtil.output("u" + Integer.toHexString(protocol).toUpperCase() + "对话框", gameClient, false);
            gameClient.eventOk();
            return true;
        }
        return false;
    }
}
