package tk.gbl.mainenter.catchpet;

import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.ChatCommandConstant;
import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.script.catchpet.CatchPetAction;
import tk.gbl.util.FileReadUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date: 2017/4/28
 * Time: 10:44
 *
 * @author Tian.Dong
 */
public class BatchCatchManbing {
    public static void main(String[] args) throws IOException {
        String username = "WP0047811";
        String password = ProcessConstant.password;

        username = "wp39315";
        password = "941384";

//        String allAccountFileName = "account_work_nanmanqian.txt";
//        String accountDoneFileName = "account_trade_manbing.txt";

        String allAccountFileName = "天殊500-1火地.txt";
        String accountDoneFileName = "天殊500-1火地_zhuomanbing.txt";
        if (args != null && args.length == 2) {
            username = args[0];
            password = args[1];
        }
        final GameClient gameClient1 = new GameClient();
        final String finalUsername = username;
        final String finalPassword = password;
        new Thread() {
            public void run() {
                gameClient1.setIndex(1);
                gameClient1.setConsoleOutput(true);
                gameClient1.setFileOutput(false);
                gameClient1.getGameConfig().setGameConfigType(GameConfigType.work);
                gameClient1.setUsername(finalUsername);
                gameClient1.setPassword(finalPassword);
                gameClient1.setInitMapId(0);
                gameClient1.init();
                gameClient1.setTeamLeaderId(gameClient1.getUserId());
                gameClient1.getAllowTeamUsers().add(gameClient1.getUserId());
                gameClient1.getAllowTeamUsers().add(47812L);

                while (!gameClient1.isLoginSuccess()) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("登录成功");
                while (true) {
                    if (haveManbing(gameClient1)) {
                        System.out.println("身上有蛮兵");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    CatchPetAction catchPetAction = new CatchPetAction();
                    catchPetAction.setMapId(30413);
                    try {
                        gameClient1.doScriptAction(catchPetAction);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();



        List<String> gameClientDoneLevelList = FileReadUtil.getDoneGameClientList(accountDoneFileName);
        Queue<GameClient> gameClientList = new LinkedList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(allAccountFileName)));
        String line = null;
        while ((line = fileReader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            GameClient gameClient = new GameClient();
            gameClient.setUsername(line.split(" ")[0]);
            gameClient.setPassword("qazwsx");
            gameClientList.add(gameClient);
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(accountDoneFileName), true));

        while (gameClientList.size() > 0) {
            GameClient gameClient = gameClientList.poll();
            if (gameClient == null) {
                break;
            }
            if(gameClientDoneLevelList.contains(gameClient.getUsername())) {
                continue;
            }
            gameClient.setConsoleOutput(false);
            gameClient.setFileOutput(false);
            gameClient.setIndex(2);
            gameClient.setTeamLeaderId(0);
            gameClient.setInitMapId(0);
            gameClient.init();
            System.out.println("本次登录:" + gameClient.getUserId());

            while (!gameClient.isLoginSuccess()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gameClient.moveFar(30413);

            while (!haveManbing(gameClient)) {
                gameClient.chatMi(gameClient1.getUserId(),ChatCommandConstant.givememanbing.getCommand());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("有蛮兵了，下一个");
            gameClient.close();

            bufferedWriter.write(gameClient.getUsername());
            bufferedWriter.newLine();
            bufferedWriter.flush();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("运行完毕");
        bufferedWriter.close();
    }

    private static boolean haveManbing(GameClient gameClient) {
        boolean isHaveManbing = false;
        for (int i = 0; i < gameClient.getPetList().length; i++) {
            RoleInfo pet = gameClient.getPetList()[i];
            if (pet == null) {
                continue;
            }
            if (pet.getId() == 17771) {
                isHaveManbing = true;
            }
        }
        return isHaveManbing;
    }

    private static List<String> getDoneGameClientList(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
        List<String> usernameList = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            usernameList.add(line);
        }
        reader.close();
        return usernameList;
    }
}
