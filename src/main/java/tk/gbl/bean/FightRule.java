package tk.gbl.bean;

import tk.gbl.constant.SkillType;
import tk.gbl.constant.fight.FightRuleCompare;
import tk.gbl.constant.fight.FightRuleConditionEnum;
import tk.gbl.constant.fight.FightRuleTarget;
import tk.gbl.core.GameClient;
import tk.gbl.util.SkillIdUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/4/28
 * Time: 14:52
 *
 * @author Tian.Dong
 */
public class FightRule implements Configable {
    /**
     * 1 生效
     * 2 不生效
     */
    int enable = 1;
    List<FightRuleCondition> fightRuleConditions = new ArrayList<>();
    int skillId = SkillType.roubo.getId();
    FightRuleTarget fightRuleTarget = FightRuleTarget.auto;
    Position position = new Position();

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public List<FightRuleCondition> getFightRuleConditions() {
        return fightRuleConditions;
    }

    public void setFightRuleConditions(List<FightRuleCondition> fightRuleConditions) {
        this.fightRuleConditions = fightRuleConditions;
    }

    public FightRuleTarget getFightRuleTarget() {
        return fightRuleTarget;
    }

    public void setFightRuleTarget(FightRuleTarget fightRuleTarget) {
        this.fightRuleTarget = fightRuleTarget;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "FightRule{" +
                "enable=" + enable +
                ", fightRuleConditions=" + fightRuleConditions +
                ", skillId=" + skillId +
                ", fightRuleTarget=" + fightRuleTarget +
                '}';
    }

    /**
     * 生效|规则1,规则2|技能|目标|位置
     * 1|1,2,4&2,3,5|10000|0|3,2
     */
    public String toConfigString() {
        StringBuilder configString = new StringBuilder();
        configString.append(enable).append(separator);
        for (FightRuleCondition condition : fightRuleConditions) {
            configString.append(condition.toConfigString());
            configString.append("&");
        }
        configString.deleteCharAt(configString.length() - 1);
        configString.append(separator).append(skillId)
                .append(separator).append(fightRuleTarget)
                .append(separator).append(position.toConfigString());
        return configString.toString();
    }

    public static FightRule getDefaultRule() {
        FightRule fightRule = new FightRule();
        List<FightRuleCondition> fightRuleConditions = new ArrayList<>();
        fightRuleConditions.add(new FightRuleCondition());
        fightRule.setFightRuleConditions(fightRuleConditions);
        return fightRule;
    }

    /**
     * 1|fightRound,gteq,0&fightRound,gteq,0|12034|auto|0,0
     */
    public static FightRule fromConfigString(String fightRuleString) {
        String[] params = fightRuleString.split("\\|");
        FightRule fightRule = new FightRule();
        int enable = Integer.parseInt(params[0]);
        String fightRuleConditionsString = params[1];
        List<FightRuleCondition> fightRuleConditionList = new ArrayList<>();
        for (String fightRuleConditionString : fightRuleConditionsString.split("&")) {
            FightRuleCondition fightRuleCondition = FightRuleCondition.fromConfigString(fightRuleConditionString);
            fightRuleConditionList.add(fightRuleCondition);
        }
        int skillId = Integer.parseInt(params[2]);
        FightRuleTarget fightRuleTarget = FightRuleTarget.valueOf(params[3]);
        Position position = Position.fromConfigString(params[4]);

        fightRule.setEnable(enable);
        fightRule.setFightRuleConditions(fightRuleConditionList);
        fightRule.setSkillId(skillId);
        fightRule.setFightRuleTarget(fightRuleTarget);
        fightRule.setPosition(position);
        return fightRule;
    }

    public boolean match(GameClient gameClient) {
        for (FightRuleCondition fightRuleCondition : fightRuleConditions) {
            if (fightRuleCondition.getFightRuleConditionEnum().equals(FightRuleConditionEnum.enemyId)) {
                boolean isHaveEnemyId = calculateHaveEnemyId(fightRuleCondition.getValue(), gameClient.getFightInfo().getRoleArray());
                if (fightRuleCondition.getFightRuleCompare().equals(FightRuleCompare.eq)) {
                    //如果是eq 必须有enemyId
                    if (!isHaveEnemyId) {
                        return false;
                    }
                } else {
                    if (isHaveEnemyId) {
                        return false;
                    }
                }
                continue;
            }
            int value = -1;
            if (fightRuleCondition.getFightRuleConditionEnum().equals(FightRuleConditionEnum.fightRound)) {
                value = gameClient.getFightInfo().getRoundIndex();
            } else if (fightRuleCondition.getFightRuleConditionEnum().equals(FightRuleConditionEnum.teamDead)) {
                value = calculateTeamDeadCount(gameClient.getFightInfo().getRoleArray());
            } else if (fightRuleCondition.getFightRuleConditionEnum().equals(FightRuleConditionEnum.teamHp)) {
                value = calculateTeamHp(gameClient.getFightInfo().getRoleArray());
            } else if (fightRuleCondition.getFightRuleConditionEnum().equals(FightRuleConditionEnum.teamMp)) {
                value = calculateTeamMp(gameClient.getFightInfo().getRoleArray());
            } else if (fightRuleCondition.getFightRuleConditionEnum().equals(FightRuleConditionEnum.selfLevel)) {
                value = gameClient.getRoleInfo().getLevel();
            } else if (fightRuleCondition.getFightRuleConditionEnum().equals(FightRuleConditionEnum.enemyCount)) {
                value = calculateEnemyCount(gameClient.getFightInfo().getRoleArray());
            } else if (fightRuleCondition.getFightRuleConditionEnum().equals(FightRuleConditionEnum.enemyAvgLevel)) {
                value = calculateEnemyAvgLevel(gameClient.getFightInfo().getRoleArray());
            } else if (fightRuleCondition.getFightRuleConditionEnum().equals(FightRuleConditionEnum.enemyMaxLevel)) {
                value = calculateEnemyMaxLevel(gameClient.getFightInfo().getRoleArray());
            } else if (fightRuleCondition.getFightRuleConditionEnum().equals(FightRuleConditionEnum.enemyMaxHp)) {
                value = calculateEnemyMaxHp(gameClient.getFightInfo().getRoleArray());
            } else if (fightRuleCondition.getFightRuleConditionEnum().equals(FightRuleConditionEnum.currentMapId)) {
                value = gameClient.getCurrentMapId();
            }

            if (fightRuleCondition.getFightRuleCompare().equals(FightRuleCompare.lt)) {
                if (!(value < fightRuleCondition.getValue())) {
                    return false;
                }
            } else if (fightRuleCondition.getFightRuleCompare().equals(FightRuleCompare.eq)) {
                if (!(value == fightRuleCondition.getValue())) {
                    return false;
                }
            } else if (fightRuleCondition.getFightRuleCompare().equals(FightRuleCompare.gt)) {
                if (!(value > fightRuleCondition.getValue())) {
                    return false;
                }
            } else if (fightRuleCondition.getFightRuleCompare().equals(FightRuleCompare.lteq)) {
                if (!(value <= fightRuleCondition.getValue())) {
                    return false;
                }
            } else if (fightRuleCondition.getFightRuleCompare().equals(FightRuleCompare.gteq)) {
                if (!(value >= fightRuleCondition.getValue())) {
                    return false;
                }
            } else if (fightRuleCondition.getFightRuleCompare().equals(FightRuleCompare.ueq)) {
                if (value == fightRuleCondition.getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean calculateHaveEnemyId(int enemyId, RoleInfo[][] roleArray) {
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                RoleInfo roleInfo = roleArray[i][j];
                if (roleInfo.getId() == enemyId) {
                    if (roleInfo.getHp() > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int calculateEnemyMaxLevel(RoleInfo[][] roleArray) {
        int maxLevel = 0;
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                RoleInfo roleInfo = roleArray[i][j];
                int level = roleInfo.getLevel();
                if (level > maxLevel) {
                    maxLevel = level;
                }
            }
        }
        return maxLevel;
    }

    private int calculateEnemyMaxHp(RoleInfo[][] roleArray) {
        int maxHp = 0;
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                RoleInfo roleInfo = roleArray[i][j];
                int hp = roleInfo.getHp();
                if (hp > maxHp) {
                    maxHp = hp;
                }
            }
        }
        return maxHp;
    }

    private int calculateEnemyAvgLevel(RoleInfo[][] roleArray) {
        int sumLevel = 0;
        int enemyCount = 0;
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getHp() <= 0) {
                    continue;
                }
                RoleInfo roleInfo = roleArray[i][j];
                int level = roleInfo.getLevel();
                sumLevel += level;
                enemyCount++;
            }
        }
        return sumLevel / enemyCount;
    }

    private int calculateEnemyCount(RoleInfo[][] roleArray) {
        int enemyCount = 0;
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getHp() <= 0) {
                    continue;
                }
                enemyCount++;
            }
        }
        return enemyCount;
    }

    private int calculateTeamMp(RoleInfo[][] roleArray) {
        int teamMp = 100;
        for (int i = 2; i < 4; i++) {//排 2 3
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getHp() <= 0) {
                    continue;
                }
                RoleInfo roleInfo = roleArray[i][j];
                int mp = roleInfo.getMp() * 100 / roleInfo.getMpMax();
                if (mp < teamMp) {
                    teamMp = mp;
                }
            }
        }
        return teamMp;
    }

    private int calculateTeamHp(RoleInfo[][] roleArray) {
        int teamHp = 100;
        for (int i = 2; i < 4; i++) {//排 2 3
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getHp() <= 0) {
                    continue;
                }
                RoleInfo roleInfo = roleArray[i][j];
                int hp = roleInfo.getHp() * 100 / roleInfo.getHpMax();
                if (hp < teamHp) {
                    teamHp = hp;
                }
            }
        }
        return teamHp;
    }

    private int calculateTeamDeadCount(RoleInfo[][] roleArray) {
        int teamDeadCount = 0;
        for (int i = 2; i < 4; i++) {//排 2 3
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getHp() <= 0) {
                    teamDeadCount++;
                }
            }
        }
        return teamDeadCount;
    }

    public void executeFightRule(Position fromPosition, GameClient gameClient) throws IOException {
        Position toPosition = null;
        if (fightRuleTarget.equals(FightRuleTarget.auto)) {
            toPosition = calculateEnemyPosition(gameClient.getFightInfo().getRoleArray());
        } else if (fightRuleTarget.equals(FightRuleTarget.teamDead)) {
            toPosition = calculateTeamDeadPosition(gameClient.getFightInfo().getRoleArray());
        } else if (fightRuleTarget.equals(FightRuleTarget.enemyMaxLevel)) {
            toPosition = calculateEnemyMaxLevelPosition(gameClient.getFightInfo().getRoleArray());
        } else if (fightRuleTarget.equals(FightRuleTarget.enemyMaxHp)) {
            toPosition = calculateEnemyMaxHpPosition(gameClient.getFightInfo().getRoleArray());
        } else if (fightRuleTarget.equals(FightRuleTarget.self)) {
            toPosition = fromPosition;
        } else if (fightRuleTarget.equals(FightRuleTarget.position)) {
            toPosition = this.position;
        } else if (fightRuleTarget.equals(FightRuleTarget.teamMinLevel)) {
            toPosition = calculateTeamMinLevelPosition(gameClient.getFightInfo().getRoleArray());
        } else if (fightRuleTarget.equals(FightRuleTarget.npcId)) {
            toPosition = calculateNPCIdPosition(gameClient.getFightInfo().getRoleArray(), this.position.getRow());
        } else {
            toPosition = calculateEnemyPosition(gameClient.getFightInfo().getRoleArray());
        }
        if (skillId == SkillType.taopao.getId()) {
            if (gameClient.getRoleInfo().getSkillList().contains(SkillType.duntaoshu.getId())) {
                skillId = SkillType.duntaoshu.getId();
            }
            System.out.println("逃跑!!!!!" + skillId + "(" + SkillIdUtil.getSkillName(skillId) + ")");
        }
        gameClient.fight(fromPosition, toPosition, skillId);
    }

    private Position calculateNPCIdPosition(RoleInfo[][] roleArray, int npcId) {
        Position position = new Position(3, 2);
        for (int i = 2; i < 4; i++) {//排 2 3
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getId() == npcId) {
                    return roleArray[i][j].getPosition();
                }
            }
        }
        return position;
    }


    private Position calculateTeamDeadPosition(RoleInfo[][] roleArray) {
        Position position = new Position(3, 2);
        for (int i = 2; i < 4; i++) {//排 2 3
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getHp() <= 0) {
                    return roleArray[i][j].getPosition();
                }
            }
        }
        return position;
    }

    private Position calculateTeamMinLevelPosition(RoleInfo[][] roleArray) {
        int minLevel = 200;
        Position position = new Position();
        for (int i = 2; i < 4; i++) {//2 3
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getHp() <= 0) {
                    continue;
                }
                RoleInfo roleInfo = roleArray[i][j];
                int level = roleInfo.getLevel();
                if (level < minLevel) {
                    minLevel = level;
                    position = roleInfo.getPosition();
                }
            }
        }
        return position;
    }

    private Position calculateEnemyMaxLevelPosition(RoleInfo[][] roleArray) {
        int maxLevel = 0;
        Position position = new Position();
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getHp() <= 0) {
                    continue;
                }
                RoleInfo roleInfo = roleArray[i][j];
                int level = roleInfo.getLevel();
                if (level > maxLevel) {
                    maxLevel = level;
                    position = roleInfo.getPosition();
                }
            }
        }
        return position;
    }

    private Position calculateEnemyMaxHpPosition(RoleInfo[][] roleArray) {
        int maxHp = 0;
        Position position = new Position();
        for (int i = 0; i < 2; i++) {//排 0 1为对面排
            for (int j = 0; j < roleArray[i].length; j++) {//列
                if (roleArray[i][j] == null) {
                    continue;
                }
                if (roleArray[i][j].getHp() <= 0) {
                    continue;
                }
                RoleInfo roleInfo = roleArray[i][j];
                int hp = roleInfo.getHp();
                if (hp > maxHp) {
                    maxHp = hp;
                    position = roleInfo.getPosition();
                }
            }
        }
        return position;
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
}
