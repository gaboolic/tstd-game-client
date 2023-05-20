package tk.gbl.config;

import tk.gbl.constant.GameConfigType;
import tk.gbl.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/4/19
 * Time: 10:08
 *
 * @author Tian.Dong
 */
public class GameConfig {
    GameConfigType gameConfigType = GameConfigType.work;
    //是否清流
    boolean isQingliu;
    //是否自动快速对话
    boolean isQuickNPCDialog = true;
    //N分钟不进入战斗重连
    boolean isNotFightReconnect;
    //N分钟不进入战斗重连
    boolean isNotGetItemReconnect;
    //是否进入战斗登出(捡材料用)
    boolean isFightReconnect;
    //是否进入战场位置在0 1退出(仙斗)
    boolean isFightOppositeClose;
    //是否同意所有入队请求
    boolean isForceAllowTeamRequest;
    //是否得到物品后自动整理背包
    boolean isAutoMergeBag = true;
    //是否自动接收交易宠物
    boolean isAutoTradePet = true;
    //是否自动捡取物品
    boolean isAutoPickupItem = false;
    //是否新角色断开连接
    boolean isNewRoleClose = false;

    int notFightReconnectTime = 120000;

    List<Long> horseIdList = new ArrayList<>();
    List<Integer> autoUseItemIdList = new ArrayList<>();
    List<Integer> autoThrowItemIdList = new ArrayList<>();
    List<Integer> jiguanchongIdList = new ArrayList<>();
    List<Integer> pickupItemIdList = new ArrayList<>();
    List<Integer> noshowItemIdList = new ArrayList<>();

    static FileUtil globalConfigProperties = new FileUtil("/global_config.properties");

    public GameConfig() {
        String horseIdStr = globalConfigProperties.getProperties("horse_id");
        if (horseIdStr != null) {
            for (String horseIdSingleStr : horseIdStr.split(",")) {
                if (horseIdSingleStr != null) {
                    horseIdSingleStr = horseIdSingleStr.trim();
                    Long horseId = Long.parseLong(horseIdSingleStr);
                    horseIdList.add(horseId);
                }
            }
        }

        String autoUseStr = globalConfigProperties.getProperties("auto_use");
        if (autoUseStr != null) {
            for (String autoUseSingleStr : autoUseStr.split(",")) {
                if (autoUseSingleStr != null) {
                    autoUseSingleStr = autoUseSingleStr.trim();
                    Integer itemId = Integer.parseInt(autoUseSingleStr);
                    autoUseItemIdList.add(itemId);
                }
            }
        }

        String autoThrowStr = globalConfigProperties.getProperties("auto_throw");
        if (autoThrowStr != null) {
            for (String autoThrowSingleStr : autoThrowStr.split(",")) {
                if (autoThrowSingleStr != null) {
                    autoThrowSingleStr = autoThrowSingleStr.trim();
                    Integer itemId = Integer.parseInt(autoThrowSingleStr);
                    autoThrowItemIdList.add(itemId);
                }
            }
        }

        String jiguanchong = globalConfigProperties.getProperties("jiguanchong");
        if (jiguanchong != null) {
            for (String jiguanchongSingleId : jiguanchong.split(",")) {
                if (jiguanchongSingleId != null) {
                    jiguanchongSingleId = jiguanchongSingleId.trim();
                    Integer itemId = Integer.parseInt(jiguanchongSingleId);
                    jiguanchongIdList.add(itemId);
                }
            }
        }

        String pickupItemIdListStr = globalConfigProperties.getProperties("pickupItemIdList");
        if (pickupItemIdListStr != null) {
            for (String pickupItemIdSingle : pickupItemIdListStr.split(",")) {
                if (pickupItemIdSingle != null) {
                    pickupItemIdSingle = pickupItemIdSingle.trim();
                    Integer itemId = Integer.parseInt(pickupItemIdSingle);
                    pickupItemIdList.add(itemId);
                }
            }
        }

        String noshowItemIdListStr = globalConfigProperties.getProperties("noshowItemIdList");
        if (noshowItemIdListStr != null) {
            for (String noshowItemIdSingle : noshowItemIdListStr.split(",")) {
                if (noshowItemIdSingle != null) {
                    noshowItemIdSingle = noshowItemIdSingle.trim();
                    Integer itemId = Integer.parseInt(noshowItemIdSingle);
                    noshowItemIdList.add(itemId);
                }
            }
        }
    }

    public GameConfigType getGameConfigType() {
        return gameConfigType;
    }

    public void setGameConfigType(GameConfigType gameConfigType) {
        this.gameConfigType = gameConfigType;
    }

    public List<Integer> getAutoUseItemIdList() {
        return autoUseItemIdList;
    }

    public void setAutoUseItemIdList(List<Integer> autoUseItemIdList) {
        this.autoUseItemIdList = autoUseItemIdList;
    }

    public List<Integer> getJiguanchongIdList() {
        return jiguanchongIdList;
    }

    public void setJiguanchongIdList(List<Integer> jiguanchongIdList) {
        this.jiguanchongIdList = jiguanchongIdList;
    }

    public List<Integer> getAutoThrowItemIdList() {
        return autoThrowItemIdList;
    }

    public void setAutoThrowItemIdList(List<Integer> autoThrowItemIdList) {
        this.autoThrowItemIdList = autoThrowItemIdList;
    }

    public List<Long> getHorseIdList() {
        return horseIdList;
    }

    public void setHorseIdList(List<Long> horseIdList) {
        this.horseIdList = horseIdList;
    }

    public boolean isQingliu() {
        return isQingliu;
    }

    public void setQingliu(boolean isQingliu) {
        this.isQingliu = isQingliu;
    }

    public boolean isQuickNPCDialog() {
        return isQuickNPCDialog;
    }

    public void setQuickNPCDialog(boolean isQuickNPCDialog) {
        this.isQuickNPCDialog = isQuickNPCDialog;
    }

    public boolean isFightReconnect() {
        return isFightReconnect;
    }

    public void setFightReconnect(boolean isFightReconnect) {
        this.isFightReconnect = isFightReconnect;
    }

    public boolean isNotFightReconnect() {
        return isNotFightReconnect;
    }

    public void setNotFightReconnect(boolean isNotFightReconnect) {
        this.isNotFightReconnect = isNotFightReconnect;
    }

    public List<Integer> getPickupItemIdList() {
        return pickupItemIdList;
    }

    public void setPickupItemIdList(List<Integer> pickupItemIdList) {
        this.pickupItemIdList = pickupItemIdList;
    }

    public boolean isForceAllowTeamRequest() {
        return isForceAllowTeamRequest;
    }

    public void setForceAllowTeamRequest(boolean isForceAllowTeamRequest) {
        this.isForceAllowTeamRequest = isForceAllowTeamRequest;
    }

    public boolean isAutoMergeBag() {
        return isAutoMergeBag;
    }

    public void setAutoMergeBag(boolean isAutoMergeBag) {
        this.isAutoMergeBag = isAutoMergeBag;
    }

    public List<Integer> getNoshowItemIdList() {
        return noshowItemIdList;
    }

    public void setNoshowItemIdList(List<Integer> noshowItemIdList) {
        this.noshowItemIdList = noshowItemIdList;
    }

    public boolean isNotGetItemReconnect() {
        return isNotGetItemReconnect;
    }

    public void setNotGetItemReconnect(boolean isNotGetItemReconnect) {
        this.isNotGetItemReconnect = isNotGetItemReconnect;
    }

    public boolean isFightOppositeClose() {
        return isFightOppositeClose;
    }

    public void setFightOppositeClose(boolean isFightOppositeClose) {
        this.isFightOppositeClose = isFightOppositeClose;
    }

    public boolean isAutoTradePet() {
        return isAutoTradePet;
    }

    public void setAutoTradePet(boolean isAutoTradePet) {
        this.isAutoTradePet = isAutoTradePet;
    }

    public boolean isAutoPickupItem() {
        return isAutoPickupItem;
    }

    public void setAutoPickupItem(boolean isAutoPickupItem) {
        this.isAutoPickupItem = isAutoPickupItem;
    }

    public boolean isNewRoleClose() {
        return isNewRoleClose;
    }

    public void setNewRoleClose(boolean isNewRoleClose) {
        this.isNewRoleClose = isNewRoleClose;
    }

    public int getNotFightReconnectTime() {
        return notFightReconnectTime;
    }

    public void setNotFightReconnectTime(int notFightReconnectTime) {
        this.notFightReconnectTime = notFightReconnectTime;
    }
}
