package tk.gbl.util;

import tk.gbl.core.GameClient;

import java.io.IOException;
import java.util.List;

/**
 * Date: 2017/6/5
 * Time: 10:42
 *
 * @author Tian.Dong
 */
public class PassPubanjinUtil {
    public boolean isPassPubanjin(GameClient gameClient) throws IOException {
        List<String> usernameList = FileReadUtil.getDoneGameClientList("account_work_nanmanqian.txt");
        if(usernameList.contains(gameClient.getUsername())) {
            return true;
        }
        List<String> usernameList2 = FileReadUtil.getDoneGameClientList("account_work_11xin.txt");
        if(usernameList2.contains(gameClient.getUsername())) {
            return true;
        }
        if(gameClient.getCurrentMapId() > 20000) {
            return true;
        }
        return false;
    }
}
