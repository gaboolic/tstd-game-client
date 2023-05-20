package tk.gbl.core.fightai;

import tk.gbl.bean.FightRule;
import tk.gbl.bean.FightRules;
import tk.gbl.bean.Position;
import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ItemIdType;
import tk.gbl.constant.SkillType;
import tk.gbl.core.GameClient;
import tk.gbl.util.FightAiUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * Date: 2017/4/19
 * Time: 17:15
 *
 * @author Tian.Dong
 */
public class FightAI extends BaseFightAi {

    FightBossDuizhangAi fightBossDuizhangAi = new FightBossDuizhangAi();
    FightBossLiubeiAi fightBossLiubeiAi = new FightBossLiubeiAi();

    public void fight(GameClient gameClient) throws IOException {
        if (calculateHaveEnemyId(38215, gameClient.getFightInfo().getRoleArray())) {
            fightBossLiubeiAi.fightBoss(gameClient);
            return;
        } else if (calculateHaveEnemyId(38199, gameClient.getFightInfo().getRoleArray())) {
            fightBossDuizhangAi.fightBoss(gameClient);
            return;
        }
        fightByConfig(gameClient);
    }


    public void fightBak(GameClient gameClient) throws IOException {
        int petRow = gameClient.getPosition().getRow() - 1;
        int petColumn = gameClient.getPosition().getColumn();
        RoleInfo pet = gameClient.getFightInfo().getRoleArray()[petRow][petColumn];
        Position petPosition = new Position(petRow, petColumn);
        Position toPosition = calculateEnemyPosition(gameClient.getFightInfo().getRoleArray());
        int enemyCount = calculateEnemyCount(gameClient.getFightInfo().getRoleArray());
        OutputUtil.output("目标" + toPosition, gameClient);
        if (gameClient.getGameConfig().getGameConfigType().equals(GameConfigType.gainLevel)) {
            gameClient.fight(gameClient.getPosition(), toPosition, SkillType.roubo.getId());
            if (pet != null) {
                gameClient.fight(petPosition, toPosition, SkillType.roubo.getId());
            }
        } else if (gameClient.getGameConfig().getGameConfigType().equals(GameConfigType.work)) {
            if (gameClient.getRoleInfo().getLevel() == 200) {
                gameClient.fight(gameClient.getPosition(), toPosition, SkillType.huojian.getId());
                if (pet != null) {
                    gameClient.fight(petPosition, toPosition, SkillType.huojian.getId());
                }
            } else {
                //防御
                gameClient.fight(gameClient.getPosition(), gameClient.getPosition(), SkillType.fangyu.getId());
                if (pet != null) {
                    //大乔
                    if (pet.getId() == 45276) {
                        gameClient.fight(petPosition, toPosition, SkillType.benglei.getId());
                    } else {
                        gameClient.fight(petPosition, petPosition, SkillType.fangyu.getId());
                    }
                }
            }
        } else if (gameClient.getGameConfig().getGameConfigType().equals(GameConfigType.catchPet)) {
            gameClient.fight(gameClient.getPosition(), toPosition, SkillType.wangluo.getId());
            if (pet != null) {
                gameClient.fight(gameClient.getPosition(), toPosition, SkillType.roubo.getId());
            }
        }
    }

    private int calculateEnemyCount(RoleInfo[][] roleArray) {
        int count = 0;
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getHp() > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private Position calculateEnemyPosition(RoleInfo[][] roleArray) {
        //价值
        int rowCount = roleArray.length;
        int columnCount = roleArray[0].length;
        int[][] worthArray = new int[rowCount][columnCount];
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getHp() <= 0) {
                    continue;
                }
                int count = 0;
                for (int k = j - 1; k < j + 1; k++) {
                    if (k < 0 || k >= roleArray[i].length) {
                        continue;
                    }
                    if (roleArray[i][k] != null && roleArray[i][k].getHp() > 0) {
                        count++;
                    }
                }
                worthArray[i][j] = count;
            }
        }

        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < worthArray[i].length; j++) {
                int worth = worthArray[i][j];
                if (worth == 3) {
                    return new Position(i, j);
                }
            }
        }
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < worthArray[i].length; j++) {
                int worth = worthArray[i][j];
                if (worth == 2) {
                    return new Position(i, j);
                }
            }
        }
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < worthArray[i].length; j++) {
                int worth = worthArray[i][j];
                if (worth == 1) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    private boolean calculateHaveEnemyId(int enemyId, RoleInfo[][] roleArray) {
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                RoleInfo roleInfo = roleArray[i][j];
                if (roleInfo.getId() == enemyId) {
                    return true;
                }
            }
        }
        return false;
    }
}
