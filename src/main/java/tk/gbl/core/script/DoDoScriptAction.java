package tk.gbl.core.script;

import tk.gbl.bean.BagItem;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;
import tk.gbl.util.OutputUtil;

import java.io.IOException;

/**
 * Date: 2017/5/22
 * Time: 14:20
 *
 * @author Tian.Dong
 */
public class DoDoScriptAction extends ScriptAction {

    private String scriptString;

    public DoDoScriptAction(String scriptString) {
        this.scriptString = scriptString;
    }

    /**
     * 诸葛亮点兵征南蛮5=30413=0=1877,1693=1=1==0
     * 地盘古任务盘盘古-=13519=11=2390,990=1=2=1-2=0=True=1=0=0
     */
    @Override
    public void doAction(GameClient gameClient) throws IOException {
//        gameClient.waitForLoginSuccess();
//        gameClient.waitJoinTeam();

        String[] params = scriptString.split("=");
        if (params[1].startsWith("equip")) {
            String[] infos = params[1].split("-");
            int id = Integer.parseInt(infos[1]);
            gameClient.equipItemWithId(id);
            return;
        }
        if (params[1].startsWith("unequip")) {
            String[] infos = params[1].split("-");
            int id = Integer.parseInt(infos[1]);
            gameClient.unequipItem(id);
            return;
        }
        String jobName = params[0];
        int mapId = Integer.parseInt(params[1]);
        int npcId = Integer.parseInt(params[2]);

        int triggerType = Integer.parseInt(params[4]);
        int triggerIndex = Integer.parseInt(params[5]);
        gameClient.getChooseQueue().clear();
        if (params[6] != null && params[6].length() > 0) {
            String[] chooseIdsStr = params[6].split(",");
            for (String chooseIdStr : chooseIdsStr) {
                if (chooseIdStr.contains("-")) {
                    chooseIdStr = chooseIdStr.split("-")[0];
                }
                int chooseId = Integer.parseInt(chooseIdStr);
                if (chooseId <= 0) {
                    continue;
                }
                gameClient.getChooseQueue().add(chooseId);
            }
        }
        OutputUtil.output("开始动作-" + jobName, gameClient, true);
        if (mapId > 0) {
            gameClient.moveFar(mapId);
        }
        String position = params[3];
        if (position != null && position.length() > 0 && position.contains(",")) {
            int x = Integer.parseInt(position.split(",")[0]);
            int y = Integer.parseInt(position.split(",")[1]);
            gameClient.segmentMoveTo(x, y);
        }
        if (npcId != 0) {
            gameClient.clickNPC(npcId);
            if (params.length > 9 && params[9] != null && params[9].length() > 0 && params[9].contains("-")) {
                gameClient.sleep(200);
                String[] indexAndCountList = params[9].split(",");
                for (String indexAndCount : indexAndCountList) {
                    int index = Integer.parseInt(indexAndCount.split("-")[0]);
                    int count = Integer.parseInt(indexAndCount.split("-")[1]);
                    index-=1;
                    gameClient.buyItem(index, count);
                }
                gameClient.choose(0x28);
                gameClient.eventOk();
            }
            gameClient.simpleSleep();
        }

        if (triggerIndex > 0) {
            if (triggerType == 2) {
                gameClient.moveTrigger(triggerIndex);
            } else {
                gameClient.move(triggerIndex);
            }
            gameClient.simpleSleep();
        }

        if (params[7] != null && params[7].length() > 0) {
            int itemId = Integer.parseInt(params[7]);
            if (itemId > 0) {
                for (int i = 0; i < gameClient.getSceneItems().length; i++) {
                    BagItem sceneItem = gameClient.getSceneItems()[i];
                    if (sceneItem == null) {
                        continue;
                    }
                    if (sceneItem.getId() == itemId) {
                        gameClient.pickUp(i);
                        break;
                    }
                }
                gameClient.pickUp(1);
                gameClient.pickUp(2);
                gameClient.simpleSleep();
            }
        }

    }
}
