package tk.gbl.constant.fight;

/**
 * 目标
 *
 * Date: 2017/4/28
 * Time: 14:14
 *
 * @author Tian.Dong
 */
public enum FightRuleTarget {
    auto(0),//中间
    teamDead(1),//死亡队友
    enemyMaxLevel(2),//敌人最高等级
    enemyMaxHp(3),//敌人最高HP
    self(4),//自己
    position(5),//指定位置
    npcId(6),//指定npc
    teamMinLevel(7),//队友最低等级
    ;

    private int type;

    FightRuleTarget(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
