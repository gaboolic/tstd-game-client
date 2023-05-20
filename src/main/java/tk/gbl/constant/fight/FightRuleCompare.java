package tk.gbl.constant.fight;

/**
 * 比较
 *
 * Date: 2017/4/28
 * Time: 14:13
 *
 * @author Tian.Dong
 */
public enum FightRuleCompare {
    lt(0),
    eq(1),
    gt(2),
    lteq(3),
    gteq(4),
    ueq(5),
    ;

    private int type;

    FightRuleCompare(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
