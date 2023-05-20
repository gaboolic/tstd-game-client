package tk.gbl.constant.fight;

/**
 * 条件
 * <p/>
 * Date: 2017/4/28
 * Time: 14:12
 *
 * @author Tian.Dong
 */
public enum FightRuleConditionEnum {
    fightRound(0),//回合数
    teamDead(1),//队友死亡
    teamHp(2),//队友hp
    teamMp(3),//队友mp
    selfLevel(4),//自己等级

    enemyId(10),//敌人id
    enemyCount(11),//敌人数量
    enemyAvgLevel(12),//敌人平均等级
    enemyMaxLevel(13),//敌人最高等级
    enemyMaxHp(14),//敌人最高等级

    currentMapId(20),//当前地图id
    ;

    private int type;

    FightRuleConditionEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
