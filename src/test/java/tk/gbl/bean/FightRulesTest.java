package tk.gbl.bean;

import org.junit.Test;
import tk.gbl.constant.SkillType;
import tk.gbl.constant.fight.FightRuleCompare;
import tk.gbl.constant.fight.FightRuleConditionEnum;
import tk.gbl.constant.fight.FightRuleTarget;
import tk.gbl.util.FightAiUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Date: 2017/4/28
 * Time: 15:23
 *
 * @author Tian.Dong
 */
public class FightRulesTest {
    @Test
    public void testDefault() {
        FightRules fightRules = new FightRules();
        FightRule fightRule = FightRule.getDefaultRule();
        FightRule fightRule2 = FightRule.getDefaultRule();
        fightRules.add(fightRule);
        fightRules.add(fightRule2);
        System.out.println(fightRules.toConfigString());

        FightRules fightRules2 = FightRules.fromConfigString(fightRules.toConfigString());
        System.out.println(fightRules2.toConfigString());
    }


    /**
     * 22级以上 防御
     * 22级 肉搏中间
     */
    @Test
    public void testDefault2() {
        FightRules fightRules = new FightRules();
        FightRule fightRule = new FightRule();
        //大于22 防御
        FightRuleCondition fangyu = new FightRuleCondition();
        fangyu.setFightRuleConditionEnum(FightRuleConditionEnum.enemyMaxLevel);
        fangyu.setFightRuleCompare(FightRuleCompare.gt);
        fangyu.setValue(22);
        fightRule.getFightRuleConditions().add(fangyu);
        fightRule.setSkillId(SkillType.fangyu.getId());
        fightRule.setFightRuleTarget(FightRuleTarget.self);
        fightRules.add(fightRule);

        FightRule fightRule2 = new FightRule();
        //=22 肉搏
        FightRuleCondition roubo = new FightRuleCondition();
        roubo.setFightRuleConditionEnum(FightRuleConditionEnum.enemyMaxLevel);
        roubo.setFightRuleCompare(FightRuleCompare.eq);
        roubo.setValue(22);
        fightRule2.getFightRuleConditions().add(roubo);
        fightRule2.setSkillId(SkillType.roubo.getId());
        fightRule2.setFightRuleTarget(FightRuleTarget.auto);
        fightRules.add(fightRule2);
        System.out.println(fightRules.toConfigString());

        FightRules fightRules2 = FightRules.fromConfigString(fightRules.toConfigString());
        System.out.println(fightRules2.toConfigString());
    }

    @Test
    public void test47809() {
        FightRules fightRules = new FightRules();
        FightRule fightRule = new FightRule();
        //大于22 龙王
        FightRuleCondition fangyu = new FightRuleCondition();
        fangyu.setFightRuleConditionEnum(FightRuleConditionEnum.enemyMaxLevel);
        fangyu.setFightRuleCompare(FightRuleCompare.gt);
        fangyu.setValue(22);
        fightRule.getFightRuleConditions().add(fangyu);
        fightRule.setSkillId(SkillType.longwang.getId());
        fightRule.setFightRuleTarget(FightRuleTarget.auto);
        fightRules.add(fightRule);

        FightRule fightRule2 = new FightRule();
        //=22 肉搏
        FightRuleCondition roubo = new FightRuleCondition();
        roubo.setFightRuleConditionEnum(FightRuleConditionEnum.enemyMaxLevel);
        roubo.setFightRuleCompare(FightRuleCompare.eq);
        roubo.setValue(22);
        fightRule2.getFightRuleConditions().add(roubo);
        fightRule2.setSkillId(SkillType.huojian.getId());
        fightRule2.setFightRuleTarget(FightRuleTarget.auto);
        fightRules.add(fightRule2);
        System.out.println(fightRules.toConfigString());

        FightRules fightRules2 = FightRules.fromConfigString(fightRules.toConfigString());
        System.out.println(fightRules2.toConfigString());
    }

    @Test
    public void test() {
        Properties properties = new Properties();
        InputStream is = null;
        is = FightAiUtil.class.getClassLoader().getResourceAsStream("fight_ai.properties");
        //File propertyFile = new File("C:/Temp/testMDB/TestTranslator/abc.txt");
        if (is != null) {
            try {
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for(Object key:properties.keySet()) {
            System.out.println(key);
            System.out.println(FightRules.fromConfigString(properties.getProperty((String)key)));
        }
    }
}
