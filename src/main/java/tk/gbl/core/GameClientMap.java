package tk.gbl.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2017/6/13
 * Time: 13:41
 *
 * @author Tian.Dong
 */
public class GameClientMap {
    private static Map<Long,GameClient> gameClientMap = new HashMap<>();

    public static Map<Long, GameClient> getGameClientMap() {
        return gameClientMap;
    }

    public static void setGameClientMap(Map<Long, GameClient> gameClientMap) {
        GameClientMap.gameClientMap = gameClientMap;
    }
}
