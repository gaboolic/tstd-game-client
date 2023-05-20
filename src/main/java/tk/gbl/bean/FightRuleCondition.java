package tk.gbl.bean;

import tk.gbl.constant.fight.FightRuleCompare;
import tk.gbl.constant.fight.FightRuleConditionEnum;

/**
 * Date: 2017/4/28
 * Time: 14:54
 *
 * @author Tian.Dong
 */
public class FightRuleCondition implements Configable {
    FightRuleConditionEnum fightRuleConditionEnum = FightRuleConditionEnum.fightRound;
    FightRuleCompare fightRuleCompare = FightRuleCompare.gteq;
    int value = 0;

    public FightRuleConditionEnum getFightRuleConditionEnum() {
        return fightRuleConditionEnum;
    }

    public void setFightRuleConditionEnum(FightRuleConditionEnum fightRuleConditionEnum) {
        this.fightRuleConditionEnum = fightRuleConditionEnum;
    }

    public FightRuleCompare getFightRuleCompare() {
        return fightRuleCompare;
    }

    public void setFightRuleCompare(FightRuleCompare fightRuleCompare) {
        this.fightRuleCompare = fightRuleCompare;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FightRuleCondition{" +
                "fightRuleConditionEnum=" + fightRuleConditionEnum +
                ", fightRuleCompare=" + fightRuleCompare +
                ", value=" + value +
                '}';
    }

    /**
     * 1,2,4&2,3,5
     */
    @Override
    public String toConfigString() {
        StringBuilder configString = new StringBuilder();
        configString.append(fightRuleConditionEnum).append(",")
                .append(fightRuleCompare).append(",")
                .append(value);
        return configString.toString();
    }

    /**
     * fightRound,gteq,0
     */
    public static FightRuleCondition fromConfigString(String fightRuleConditionsString) {
        String[] params = fightRuleConditionsString.split(",");
        FightRuleConditionEnum fightRuleConditionEnum = FightRuleConditionEnum.valueOf(params[0]);
        FightRuleCompare fightRuleCompare = FightRuleCompare.valueOf(params[1]);
        int value = Integer.parseInt(params[2]);
        FightRuleCondition fightRuleCondition = new FightRuleCondition();
        fightRuleCondition.setFightRuleConditionEnum(fightRuleConditionEnum);
        fightRuleCondition.setFightRuleCompare(fightRuleCompare);
        fightRuleCondition.setValue(value);
        return fightRuleCondition;
    }
}
