package tk.gbl.config;

import tk.gbl.bean.BagItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 2017/7/2
 * Time: 19:32
 *
 * @author Tian.Dong
 */
public class GameParam {
    boolean isInFight = false;
    boolean isNewRole = false;
    Map<Integer,Integer> gainBagItemCountMap = new HashMap<>();

    public boolean isInFight() {
        return isInFight;
    }

    public void setInFight(boolean isInFight) {
        this.isInFight = isInFight;
    }

    public boolean isNewRole() {
        return isNewRole;
    }

    public void setNewRole(boolean isNewRole) {
        this.isNewRole = isNewRole;
    }

    public Map<Integer, Integer> getGainBagItemCountMap() {
        return gainBagItemCountMap;
    }

    public void setGainBagItemCountMap(Map<Integer, Integer> gainBagItemCountMap) {
        this.gainBagItemCountMap = gainBagItemCountMap;
    }
}
