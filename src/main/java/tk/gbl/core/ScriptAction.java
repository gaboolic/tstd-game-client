package tk.gbl.core;

import java.io.IOException;

/**
 * Date: 2017/4/4
 * Time: 18:10
 *
 * @author Tian.Dong
 */
public abstract class ScriptAction {
    public abstract void doAction(GameClient gameClient) throws IOException;
}
