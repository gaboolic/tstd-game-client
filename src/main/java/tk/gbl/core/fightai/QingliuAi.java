package tk.gbl.core.fightai;

import tk.gbl.constant.SkillType;
import tk.gbl.core.GameClient;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.util.*;

/**
 * Date: 2017/6/2
 * Time: 12:53
 *
 * @author Tian.Dong
 */
public class QingliuAi {
    public static void qingliu(GameClient theGameClient) throws IOException {
        if (!theGameClient.getGameConfig().isQingliu()) {
            return;
        }
        if (theGameClient.getGameTeam() == null) {
            return;
        }
        List<GameClient> gameClientList = new ArrayList<>();
        gameClientList.add(theGameClient.getGameTeam().getTeamLeader());
        gameClientList.addAll(theGameClient.getGameTeam().getGameClientList());
        //清流一遍自己的宠物
        for (GameClient gameClient : gameClientList) {
            if (gameClient.getPet() != null) {
                if (gameClient.getRoleInfo().getSkillList().contains(SkillType.qingliu.getId())) {
                    OutputUtil.output("清流宠物", gameClient);
                    if (gameClient.isLoginSuccess()) {
                        gameClient.useSkill(SkillType.qingliu.getId(), true, gameClient.getPet().getIndex(), gameClient.getPet().getId());
                    }
                }
            }
        }

        //向后清流
        for (int i = 0; i < gameClientList.size(); i++) {
            GameClient gameClient = gameClientList.get(i);
            int nextIndex = i + 1;
            if (nextIndex == gameClientList.size()) {
                nextIndex = 0;
            }
            GameClient nextGameClient = gameClientList.get(nextIndex);
            int hpPoint = nextGameClient.getRoleInfo().getHp() * 100 / nextGameClient.getRoleInfo().getHpMax();
            int mpPoint = nextGameClient.getRoleInfo().getMp() * 100 / nextGameClient.getRoleInfo().getMpMax();
            if (hpPoint < 80 || mpPoint < 80) {
                OutputUtil.output("清流" + nextGameClient.getUserId(), gameClient);
                if (gameClient.isLoginSuccess()) {
                    gameClient.useSkill(SkillType.qingliu.getId(), false, 0, nextGameClient.getUserId());
                }
            }
        }

        //向前清流
        for (int i = 0; i < gameClientList.size(); i++) {
            GameClient gameClient = gameClientList.get(i);
            int prevIndex = i - 1;
            if (prevIndex < 0) {
                prevIndex = gameClientList.size() - 1;
            }
            GameClient prevGameClient = gameClientList.get(prevIndex);
            int hpPoint = prevGameClient.getRoleInfo().getHp() * 100 / prevGameClient.getRoleInfo().getHpMax();
            int mpPoint = prevGameClient.getRoleInfo().getMp() * 100 / prevGameClient.getRoleInfo().getMpMax();
            if (hpPoint < 80 || mpPoint < 80) {
                OutputUtil.output("清流" + prevGameClient.getUserId(), gameClient);
                if (gameClient.isLoginSuccess()) {
                    gameClient.useSkill(SkillType.qingliu.getId(), false, 0, prevGameClient.getUserId());
                }
            }
        }
    }

    public static void zhinengQingliu(GameClient theGameClient) throws IOException {
        if (!theGameClient.getGameConfig().isQingliu()) {
            return;
        }
        List<GameClient> gameClientList = new ArrayList<>();
        gameClientList.add(theGameClient.getGameTeam().getTeamLeader());
        gameClientList.addAll(theGameClient.getGameTeam().getGameClientList());
        //清流一遍自己的宠物
        for (GameClient gameClient : gameClientList) {
            if (gameClient.getPet() != null) {
                if (gameClient.getRoleInfo().getSkillList().contains(SkillType.qingliu.getId())) {
                    OutputUtil.output("清流宠物", gameClient);
                    gameClient.useSkill(SkillType.qingliu.getId(), true, gameClient.getPet().getIndex(), gameClient.getPet().getId());
                }
            }
        }

        int retry = 20;
        while (requireQingliu(gameClientList) && retry-- > 0) {
            //先找出一个最需要清流的人
            Collections.sort(gameClientList, new Comparator<GameClient>() {
                @Override
                public int compare(GameClient g1, GameClient g2) {
                    int hpPoint1 = g1.getRoleInfo().getHp() * 100 / g1.getRoleInfo().getHpMax();
                    int mpPoint1 = g1.getRoleInfo().getMp() * 100 / g1.getRoleInfo().getMpMax();
                    int hpPoint2 = g2.getRoleInfo().getHp() * 100 / g2.getRoleInfo().getHpMax();
                    int mpPoint2 = g2.getRoleInfo().getMp() * 100 / g2.getRoleInfo().getMpMax();
                    if (hpPoint1 > 70 && hpPoint2 > 70) {
                        return mpPoint1 - mpPoint2;
                    }
                    if (mpPoint1 > 70 && mpPoint2 > 70) {
                        return hpPoint1 - hpPoint2;
                    }
                    if (mpPoint1 == mpPoint2) {
                        return hpPoint1 - hpPoint2;
                    }
                    return mpPoint1 - mpPoint2;
                }
            });
            GameClient requireGameClient = gameClientList.get(0);

            Collections.sort(gameClientList, new Comparator<GameClient>() {
                @Override
                public int compare(GameClient g1, GameClient g2) {
                    int mpPoint1 = g1.getRoleInfo().getMp() * 100 / g1.getRoleInfo().getMpMax();
                    int mpPoint2 = g2.getRoleInfo().getMp() * 100 / g2.getRoleInfo().getMpMax();
                    return mpPoint1 - mpPoint2;
                }
            });
            for (int i = gameClientList.size() - 1; i >= 0; i--) {
                GameClient gameClient = gameClientList.get(i);
                if (gameClient.getUserId() == requireGameClient.getUserId()) {
                    continue;
                }
                if (gameClient.getRoleInfo().getSkillList().contains(SkillType.qingliu.getId())) {
                    gameClient.useSkill(SkillType.qingliu.getId(), false, 0, requireGameClient.getUserId());
                    break;
                }
            }
        }
    }

    private static boolean requireQingliu(List<GameClient> gameClientList) {
        for (int i = 0; i < gameClientList.size(); i++) {
            GameClient gameClient = gameClientList.get(i);
            boolean require = requireQingliu(gameClient);
            if (require) {
                return true;
            }
        }
        return false;
    }

    private static boolean requireQingliu(GameClient gameClient) {
        int hpPoint = gameClient.getRoleInfo().getHp() * 100 / gameClient.getRoleInfo().getHpMax();
        int mpPoint = gameClient.getRoleInfo().getMp() * 100 / gameClient.getRoleInfo().getMpMax();
        if (hpPoint < 70 || mpPoint < 70) {
            return true;
        }
        return false;
    }
}