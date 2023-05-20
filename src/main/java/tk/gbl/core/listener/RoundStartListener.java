package tk.gbl.core.listener;

import tk.gbl.bean.Context;
import tk.gbl.bean.FightInfo;
import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.SkillType;
import tk.gbl.core.GameClient;
import tk.gbl.core.fightai.FightAI;
import tk.gbl.util.AnswerQuestionUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.util.Map;

/**
 * Date: 2017/4/6
 * Time: 17:48
 *
 * @author Tian.Dong
 */
public class RoundStartListener extends BaseListener {

    FightAI fightAI = new FightAI();

    @Override
    public void doAction(GameClient gameClient, Context context) throws IOException {
        gameClient.refreshMainFrame("回合开始,回合数" + gameClient.getFightInfo().getRoundIndex());
        OutputUtil.output("fight:回合数" + gameClient.getFightInfo().getRoundIndex(), gameClient, gameClient.isConsoleOutput());
        if (requireAnswer(gameClient.getFightInfo())) {
            System.out.println("有北斗星君");
            String questionStr = gameClient.getFightInfo().getQuestion();
            System.out.println(questionStr);
            int selectId = AnswerQuestionUtil.calcAnswer(questionStr);
            System.out.println("选择了" + selectId);
            gameClient.answer(selectId);
            return;
        }
        if (gameClient.getGameConfig().isFightOppositeClose()) {
            if (gameClient.getPosition().getRow() == 0 || gameClient.getPosition().getRow() == 1) {
                OutputUtil.output("回合开始时位置在对面，退出!", gameClient);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameClient.close();
            }
            return;
        }
        if (!gameClient.isInGame()) {
            fightAI.fight(gameClient);
        }
        gameClient.getFightInfo().setRoundIndex(gameClient.getFightInfo().getRoundIndex() + 1);
    }

    private boolean requireAnswer(FightInfo fightInfo) {
        RoleInfo[][] roleArray = fightInfo.getRoleArray();
        for (int i = 0; i < roleArray.length; i++) {
            for (int j = 0; j < roleArray[i].length; j++) {
                if (roleArray[i][j] != null && roleArray[i][j].getId() == 15049) {
                    return true;
                }
            }
        }
        return false;
    }
}
