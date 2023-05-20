package tk.gbl.core.handle.createrole;

import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Date: 2017/4/1
 * Time: 17:09
 *
 * @author Tian.Dong
 */
public class CheckNameResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 03 00 09 03 00 成功
        //F4 44 03 00 09 03 01 重复
        if (data[4] == 0x09 && data[5] == 0x03) {
            if (data[6] == 0x00) {
                OutputUtil.output("checkName success", gameClient);
                gameClient.setPropertiesAndInit("123456", "1234567");
                return true;
            }
            if (data[6] == 0x01) {
                OutputUtil.output("checkName repeat", gameClient);
                int random = (int) (Math.random() * 100);
                String postfix = gameClient.getUsername().substring(gameClient.getUsername().length() - 4, gameClient.getUsername().length());
                String nickname = gameClient.getPreNickname();
                if (nickname == null) {
                    nickname = "嗷大猫";
                }
                if (gameClient.getPreNickname() != null && gameClient.getPreNickname().equals(nickname + postfix)) {
                    gameClient.setPreNickname(gameClient.getPreNickname() + random);
                } else {
                    gameClient.setPreNickname(nickname + postfix);
                }
                gameClient.checkName(gameClient.getPreNickname());
                return true;
            }
        }
        return false;
    }
}

