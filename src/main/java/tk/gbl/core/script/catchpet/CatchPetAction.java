package tk.gbl.core.script.catchpet;

import tk.gbl.bean.RoleInfo;
import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Date: 2017/4/19
 * Time: 9:58
 *
 * @author Tian.Dong
 */
public class CatchPetAction extends ScriptAction {
    Random randomNumberGenerator = new Random();

    boolean isPk = false;
    boolean isClickNpc = false;
    int mapId = 30413;
    int x = 1342;
    int y = 695;

    int npcId = 17771;
    int npcIndex;

    @Override
    public void doAction(GameClient gameClient) throws IOException {
        OutputUtil.output("开始捉宠", gameClient, true);
        gameClient.moveFar(mapId);
        while (gameClient.getCurrentMapId() != mapId) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameClient.segmentMoveTo(x, y);
        OutputUtil.output("进入地图" + mapId, gameClient, true);
        if (isPk) {
            gameClient.pkNPC(npcId, npcIndex);
        } else if (isClickNpc) {
            gameClient.clickNPC(npcIndex);
            gameClient.simpleSleep();
        } else {
            gameClient.segmentMoveTo(x, y);

            while (!haveNpc(gameClient)) {
                int xPos = randomNumberGenerator.nextInt(200) - 100;
                int yPos = randomNumberGenerator.nextInt(200) - 100;
                gameClient.segmentMoveTo(x + xPos, y + yPos);

                while (gameClient.isFighting()) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        OutputUtil.output("捉到了宠物", gameClient, true);
    }

    public boolean haveNpc(GameClient gameClient) {
        RoleInfo[] petList = gameClient.getPetList();
        for (RoleInfo pet : petList) {
            if (pet == null) {
                continue;
            }
            if (pet.getId() == npcId) {
                return true;
            }
        }
        return false;
    }

    public boolean isPk() {
        return isPk;
    }

    public void setPk(boolean isPk) {
        this.isPk = isPk;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNpcId() {
        return npcId;
    }

    public void setNpcId(int npcId) {
        this.npcId = npcId;
    }

    public int getNpcIndex() {
        return npcIndex;
    }

    public void setNpcIndex(int npcIndex) {
        this.npcIndex = npcIndex;
    }

    public Random getRandomNumberGenerator() {
        return randomNumberGenerator;
    }

    public void setRandomNumberGenerator(Random randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public boolean isClickNpc() {
        return isClickNpc;
    }

    public void setClickNpc(boolean isClickNpc) {
        this.isClickNpc = isClickNpc;
    }
}
