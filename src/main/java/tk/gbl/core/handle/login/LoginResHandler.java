package tk.gbl.core.handle.login;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2017/3/29
 * Time: 16:21
 *
 * @author Tian.Dong
 */
public class LoginResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        //F4 44 02 00 01 06 F4 44 02 00 00 12 //错误密码
        /*
         * F4 44 03 00 01 03 00
         * 没有角色
         * F4 44 02 00 47 01
         *需要创建角色
         */
        if (data[4] == 0x01 && data[5] == 0x06) {
            OutputUtil.output("密码错误", gameClient);
            if (gameClient.getPassword().equals("123456")) {
                return true;
            }
            gameClient.setPassword("123456");
            gameClient.clear();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        if (data[4] == 0x01 && data[5] == 0x03 && data[6] == 0x00) {
            OutputUtil.output("没有角色 需要创建角色", gameClient);
            gameClient.setNewRole(true);
            gameClient.getGameParam().setNewRole(true);
            if(gameClient.getGameConfig().isNewRoleClose()) {
                gameClient.close();
                return true;
            }
            String nickname = "嗷大猫";
            if (gameClient.getPreNickname() != null) {
                nickname = gameClient.getPreNickname();
            }
            gameClient.checkName(nickname);
            return true;
        }

        if (data[4] == 0x47 && data[5] == 0x01) {
//            System.out.println(ShowUtil.showOrigin(data));
//            System.out.println("需要创建角色");
//            gameClient.setNewRole(true);
//            int random = (int) (Math.random() * 100);
//            gameClient.checkName("嗷大猫" + random);
            System.out.println("47 01:::");
            return true;
        }

        if (data[4] == 0x14 && data[5] == 0x08) {
            return true;
        }

        return false;
    }
}
