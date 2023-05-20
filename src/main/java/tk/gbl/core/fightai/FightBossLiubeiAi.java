package tk.gbl.core.fightai;

import tk.gbl.bean.Position;
import tk.gbl.constant.ItemIdType;
import tk.gbl.constant.NpcIdType;
import tk.gbl.constant.SkillType;
import tk.gbl.core.GameClient;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * Date: 2017/6/18
 * Time: 18:48
 *
 * @author Tian.Dong
 */
public class FightBossLiubeiAi extends BaseFightAi {
    public void fightBoss(GameClient gameClient) throws IOException {
        OutputUtil.output("开始打光刘备AI", gameClient, true);
        //分身 佛光掌
        //霸意 霸意
        //震电 震电
        //震电 震电
        //震电 佛光掌
        Position fromPosition = new Position(3, 2);
        Position fromPosition2 = new Position(2, 2);
        Position toPosition = new Position(0, 2);
        if (gameClient.getUserId() == 47812) {
            if (gameClient.getFightInfo().getRoundIndex() % 5 == 1) {
                gameClient.fight(fromPosition, fromPosition, SkillType.yinfenshen.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.foguangzhang.getId());
            } else if (gameClient.getFightInfo().getRoundIndex() % 5 == 2) {
                gameClient.fight(fromPosition, fromPosition, SkillType.bayi.getId());
                gameClient.fight(fromPosition2, fromPosition2, SkillType.bayi.getId());
            } else if (gameClient.getFightInfo().getRoundIndex() % 5 == 3) {
                gameClient.fight(fromPosition, toPosition, SkillType.zhendian.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.zhendian.getId());
            } else if (gameClient.getFightInfo().getRoundIndex() % 5 == 4) {
                gameClient.fight(fromPosition, toPosition, SkillType.zhendian.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.zhendian.getId());
            } else if (gameClient.getFightInfo().getRoundIndex() % 5 == 0) {
                gameClient.fight(fromPosition, toPosition, SkillType.zhendian.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.foguangzhang.getId());
            }
        } else if (gameClient.getUserId() == 47811) {
            //1霸意 佛光掌
            //震电 佛光掌
            //震电 佛光掌
            //震电 佛光掌
            //霸意 佛光掌
            //震电 佛光掌
            //震电 佛光掌
            //8吃药
            //霸意 佛光掌
            //15
            if (gameClient.getFightInfo().getRoundIndex() == 9) {
                gameClient.fightUseItem(fromPosition, fromPosition, 26398);
                gameClient.fight(fromPosition2, toPosition, SkillType.foguangzhang.getId());
            } else if (gameClient.getFightInfo().getRoundIndex() == 1
                    || gameClient.getFightInfo().getRoundIndex() == 5
                    || gameClient.getFightInfo().getRoundIndex() == 10
                    || gameClient.getFightInfo().getRoundIndex() == 14
                    ) {
                gameClient.fight(fromPosition, toPosition, SkillType.bayi.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.foguangzhang.getId());
            } else {
                gameClient.fight(fromPosition, toPosition, SkillType.zhendian.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.foguangzhang.getId());
            }
        } else if (gameClient.getUserId() == 27166) {
            if (gameClient.getFightInfo().getRoundIndex() == 7 || gameClient.getFightInfo().getRoundIndex() == 11 || gameClient.getFightInfo().getRoundIndex() == 14) {
                gameClient.fightUseItem(fromPosition, fromPosition, ItemIdType.yaonaing.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.zhanqilinyishan.getId());
            } else {
                gameClient.fight(fromPosition, toPosition, SkillType.lieshuhaohuo.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.zhanqilinyishan.getId());
            }
        } else if (gameClient.getUserId() == 27167) {
            if (gameClient.getFightInfo().getRoundIndex() == 7 || gameClient.getFightInfo().getRoundIndex() == 11 || gameClient.getFightInfo().getRoundIndex() == 14) {
                gameClient.fightUseItem(fromPosition, fromPosition, ItemIdType.yaonaing.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.heixuanzhan.getId());
            } else {
                gameClient.fight(fromPosition, toPosition, SkillType.zhendian.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.heixuanzhan.getId());
            }
        } else if (gameClient.getUserId() == 39259) {
            if (gameClient.getFightInfo().getRoundIndex() == 7 || gameClient.getFightInfo().getRoundIndex() == 14) {
                gameClient.fightUseItem(fromPosition, fromPosition, ItemIdType.yaonaing.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.zhendian.getId());
            } else {
                gameClient.fight(fromPosition, toPosition, SkillType.juelongbaliezhan.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.zhendian.getId());
            }
        } else if (gameClient.getPetIndex(NpcIdType.beigou.getId()) != -1) {
            if (gameClient.getFightInfo().getRoundIndex() % 3 == 1) {
                gameClient.fight(fromPosition, toPosition, SkillType.roubo.getId());
                gameClient.fight(fromPosition2, fromPosition2, SkillType.shanduo.getId());
            } else if (gameClient.getPet().getMp() < 40) {
                gameClient.fight(fromPosition, toPosition, SkillType.roubo.getId());
                gameClient.fightUseItem(fromPosition2, fromPosition2, ItemIdType.bdyttyl.getId());
            } else {
                gameClient.fight(fromPosition, toPosition, SkillType.roubo.getId());
                gameClient.fight(fromPosition2, toPosition, SkillType.lianji.getId());
            }
        } else {
            fightByConfig(gameClient);
        }
    }
}
