package tk.gbl.core.fightai;

import tk.gbl.bean.FightRule;
import tk.gbl.bean.FightRules;
import tk.gbl.bean.Position;
import tk.gbl.bean.RoleInfo;
import tk.gbl.core.GameClient;
import tk.gbl.util.FightAiUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * Date: 2017/6/25
 * Time: 17:45
 *
 * @author Tian.Dong
 */
public class BaseFightAi {
    public void fightByConfig(GameClient gameClient) throws IOException {
        long userId = gameClient.getUserId();
        FightRules commonFightRules = null;
        String commonFightRulesConfigString = FightAiUtil.getValue("common");
        if (commonFightRulesConfigString != null) {
            commonFightRules = FightRules.fromConfigString(commonFightRulesConfigString);
        }

        String fightRulesConfigString = FightAiUtil.getValue(userId + "");
        if (fightRulesConfigString == null) {
            fightRulesConfigString = FightAiUtil.getValue("default");
        }
        FightRules userFightRules = FightRules.fromConfigString(fightRulesConfigString);
        FightRules fightRules = new FightRules();
        if (commonFightRules != null) {
            fightRules.addAll(commonFightRules);
        }
        fightRules.addAll(userFightRules);
        fightRules.add(FightRule.getDefaultRule());

        for (FightRule fightRule : fightRules) {
            if (fightRule.match(gameClient)) {
//                System.out.println("匹配到规则"+fightRule.toConfigString());
                fightRule.executeFightRule(gameClient.getPosition(), gameClient);
                break;
            }
        }
        if (gameClient.getPet() != null) {
            FightRules commonPetFightRules = null;
            String commonPetFightRulesConfigString = FightAiUtil.getValue("common_pet");
            if (commonPetFightRulesConfigString != null) {
                commonPetFightRules = FightRules.fromConfigString(commonPetFightRulesConfigString);
            }

            RoleInfo pet = gameClient.getPet();
            Position manPosition = gameClient.getPosition();
            Position petPosition = new Position(manPosition.getRow() - 1, manPosition.getColumn());
            String petFightRulesConfigString = FightAiUtil.getValue(userId + "_pet_" + gameClient.getPet().getId());

            if (petFightRulesConfigString == null) {
                petFightRulesConfigString = FightAiUtil.getValue("pet_" + gameClient.getPet().getId());
            }
            if (petFightRulesConfigString == null) {
                petFightRulesConfigString = FightAiUtil.getValue(userId + "_pet");
            }
            if (petFightRulesConfigString == null) {
                petFightRulesConfigString = FightAiUtil.getValue("default_pet");
            }
            OutputUtil.output("宠物ai:" + petFightRulesConfigString, gameClient, gameClient.isConsoleOutput());
            FightRules petConfigFightRules = FightRules.fromConfigString(petFightRulesConfigString);

            FightRules petFightRules = new FightRules();
            if (commonPetFightRules != null) {
                petFightRules.addAll(commonPetFightRules);
            }
            petFightRules.addAll(petConfigFightRules);
            petFightRules.add(FightRule.getDefaultRule());
            for (FightRule fightRule : petFightRules) {
                if (fightRule.match(gameClient)) {
                    fightRule.executeFightRule(petPosition, gameClient);
                    break;
                }
            }
        }
    }
}
