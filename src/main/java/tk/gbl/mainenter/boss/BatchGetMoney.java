package tk.gbl.mainenter.boss;

import tk.gbl.constant.ChatCommandConstant;
import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.NpcIdType;
import tk.gbl.constant.SkillType;
import tk.gbl.core.GameClient;
import tk.gbl.util.FileReadUtil;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Date: 2017/7/5
 * Time: 10:16
 *
 * @author Tian.Dong
 */
public class BatchGetMoney {
    public static void main(String[] args) throws Exception {
        String storeUsername = "114243";
        String storePassword = "123456";
        GameClient storeGameClient = new GameClient();
        storeGameClient.setIndex(1);
        storeGameClient.setConsoleOutput(true);
        storeGameClient.setFileOutput(true);
        storeGameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        storeGameClient.setUsername(storeUsername);
        storeGameClient.setPassword(storePassword);
        storeGameClient.setInitMapId(0);
        storeGameClient.init();
        storeGameClient.setTeamLeaderId(0);
        storeGameClient.waitForLoginSuccess();
        storeGameClient.moveFar(12001);

        String allAccountFileName = "account_trade_beigou.txt";
        String doneAccountFileName = "account_trade_beigou_money.txt";
        List<String> gameClientDoneList = FileReadUtil.getDoneGameClientList(doneAccountFileName);

        LinkedList<GameClient> gameClientList = new LinkedList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(allAccountFileName)));
        String line = null;
        while ((line = fileReader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            GameClient gameClient = new GameClient();
            gameClient.setUsername(line.split(" ")[0]);
//            gameClient.setPassword(line.split(" ")[1]);
            gameClient.setPassword("123456");
            gameClientList.add(gameClient);
        }

        while (gameClientList.size() > 0) {
            GameClient gameClient = gameClientList.poll();
            if (gameClient == null) {
                break;
            }
            if (gameClientDoneList.contains(gameClient.getUsername())) {
                continue;
            }
            gameClient.setConsoleOutput(true);
            gameClient.setFileOutput(true);
            gameClient.setIndex(2);
            gameClient.setTeamLeaderId(0);
            gameClient.setInitMapId(0);
            gameClient.init();
            System.out.println("本次登录:" + gameClient.getUserId());
            gameClient.waitForLoginSuccess();
            if(gameClient.getRoleInfo().getMoney() < 30000) {
                //拿钱
                gameClient.chatMi(storeGameClient.getUserId(), ChatCommandConstant.givemesomemoney.getCommand());
                gameClient.sleep(1000);
            }
            gameClient.close();
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(doneAccountFileName), true));
                bufferedWriter.write(gameClient.getUsername());
                bufferedWriter.write("\r\n");
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println("运行完毕");
    }
}
