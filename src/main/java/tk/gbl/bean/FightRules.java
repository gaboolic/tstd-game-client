package tk.gbl.bean;

import tk.gbl.constant.SkillType;
import tk.gbl.constant.fight.FightRuleTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/4/28
 * Time: 14:52
 *
 * @author Tian.Dong
 */
public class FightRules extends ArrayList<FightRule> implements Configable {

    @Override
    public String toConfigString() {
        StringBuilder configString = new StringBuilder();
        for (FightRule fightRule : this) {
            configString.append(fightRule.toConfigString());
            configString.append("-");
        }
        configString.deleteCharAt(configString.length() - 1);
        return configString.toString();
    }

    public static FightRules fromConfigString(String configString) {
        String[] fightRuleStrings = configString.split("-");
        FightRules fightRules = new FightRules();
        for(String fightRuleString:fightRuleStrings) {
            FightRule fightRule = FightRule.fromConfigString(fightRuleString);
            fightRules.add(fightRule);
        }
        return fightRules;
    }
}
