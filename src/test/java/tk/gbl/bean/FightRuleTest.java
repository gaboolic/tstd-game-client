package tk.gbl.bean;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/4/28
 * Time: 15:23
 *
 * @author Tian.Dong
 */
public class FightRuleTest {
    @Test
    public void testDefault() {
        FightRule fightRule = FightRule.getDefaultRule();
        System.out.println(fightRule);
        System.out.println(fightRule.toConfigString());
    }

    @Test
    public void test12034() {
        FightRule fightRule = new FightRule();
        List<FightRuleCondition> fightRuleConditions = new ArrayList<>();
        FightRuleCondition fightRuleCondition = new FightRuleCondition();
        fightRuleConditions.add(fightRuleCondition);
        fightRule.setFightRuleConditions(fightRuleConditions);
        fightRule.setSkillId(12034);
        System.out.println(fightRule.toConfigString());
    }

    @Test
    public void testXiaohao() {
        FightRule fightRule = new FightRule();
        List<FightRuleCondition> fightRuleConditions = new ArrayList<>();
        FightRuleCondition fightRuleCondition = new FightRuleCondition();
        fightRuleConditions.add(fightRuleCondition);
        FightRuleCondition fightRuleCondition2 = new FightRuleCondition();
        fightRuleConditions.add(fightRuleCondition2);
        fightRule.setFightRuleConditions(fightRuleConditions);
        fightRule.setSkillId(12034);
        System.out.println(fightRule.toConfigString());

        FightRule fightRule2 = FightRule.fromConfigString(fightRule.toConfigString());
        System.out.println(fightRule2.toConfigString());
    }
}
