package tk.gbl.core.listener;

import tk.gbl.bean.Context;
import tk.gbl.core.GameClient;

import java.io.IOException;
import java.util.Map;

/**
 * Date: 2017/4/6
 * Time: 17:49
 *
 * @author Tian.Dong
 */
public abstract class BaseListener {
    public abstract void doAction(GameClient gameClient, Context context) throws IOException;



    public void next() throws IOException {

    }
}
