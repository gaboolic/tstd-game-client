package tk.gbl.mainenter.shimao;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.*;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.script.NewZhenchashuAction;
import tk.gbl.core.script.catchpet.CatchPetAction;
import tk.gbl.core.script.pass.OpenMapAction;
import tk.gbl.core.script.pass.PenglaiAction;
import tk.gbl.core.script.pass.PubanjinAction;
import tk.gbl.core.script.pass.XuzhouKuashengAction;
import tk.gbl.core.script.skill.DuntaoshuAction;
import tk.gbl.core.script.work.badouyao.*;
import tk.gbl.core.script.work.shenghuadan.Gongsunzandemori;
import tk.gbl.core.script.work.shenghuadan.Liubeirujingzhou;
import tk.gbl.core.script.work.shimao.*;
import tk.gbl.core.script.work.shiyixin.*;
import tk.gbl.util.PathFindingUtil;

import java.io.*;
import java.util.*;

/**
 * Date: 2017/5/6
 * Time: 9:13
 *
 * @author Tian.Dong
 */
public class MainTankShimaoWork {

    static GameTeam gameTeam = new GameTeam();

    public static void main(String[] args) throws IOException {
        new Thread() {
            public void run() {
                String username = "WP0047811";
                String password = ProcessConstant.password;
                GameClient gameClient = new GameClient();
                gameClient.setIndex(6);
                gameClient.setConsoleOutput(false);
                gameClient.setFileOutput(false);
                gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
                gameClient.setUsername(username);
                gameClient.setPassword(password);
                gameClient.setInitMapId(0);
                gameClient.init();
                gameClient.setTeamLeaderId(gameClient.getUserId());
                gameClient.getAllowTeamUsers().add(gameClient.getUserId());
                gameClient.getAllowTeamUsers().add(47812L);
                gameClient.waitForLoginSuccess();
                System.out.println("捉宠号登录成功");
                while (true) {
                    try {
                        if (gameClient.getPetIndex(NpcIdType.keyimanbing.getId()) != -1) {
                            System.out.println("捉宠号身上有蛮兵");
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                        CatchPetAction catchPetAction = new CatchPetAction();
                        catchPetAction.setMapId(30413);
                        try {
                            gameClient.doScriptAction(catchPetAction);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        int initMapId = 0;
        String usernameAndPasswordStr = "wp39260 234567\n" +
                "wp39259 234567\n" +
                "wp27166 234567\n" +
                "wp27167 234567\n";
        List<GameClient> bigGameClientList = new ArrayList<>();
        gameTeam.setGameClientList(bigGameClientList);
        String[] usernameAndPasswords = usernameAndPasswordStr.split("\n");
        for (int i = 0; i < usernameAndPasswords.length; i++) {
            String usernameAndPassword = usernameAndPasswords[i];
            String username = usernameAndPassword.split(" ")[0];
            String password = usernameAndPassword.split(" ")[1];

            GameClient gameClient = new GameClient();
            gameClient.setIndex(i + 1);
            gameClient.setConsoleOutput(false);
            gameClient.setFileOutput(true);
            gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
            gameClient.getGameConfig().setQingliu(true);
            gameClient.setUsername(username);
            gameClient.setPassword(password);
            gameClient.setInitMapId(initMapId);
            gameClient.init();
            gameClient.setTeamLeaderId(1);
            bigGameClientList.add(gameClient);
        }

        String allAccountFileName = "account_25.txt";
//        String allAccountFileName = "account_work_nanmanhou.txt";
//        String accountDoneFileName = "account_work_nanmanqian.txt";
        String accountDoneFileName = "account_work_nanmanhou_zj.txt";

        List<String> gameClient25LevelList = getDoneGameClientList(accountDoneFileName);
        Queue<GameClient> gameClientList = new LinkedList<>();
//        Stack<GameClient> gameClientList = new Stack<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(allAccountFileName)));
        String line = null;
        while ((line = fileReader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }
            GameClient gameClient = new GameClient();
            gameClient.setUsername(line.split(" ")[0]);
            gameClient.setPassword("123456");
            gameClientList.add(gameClient);
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(accountDoneFileName), true));

        while (gameClientList.size() > 0) {
            GameClient gameClient = gameClientList.poll();
            if (gameClient == null) {
                break;
            }
            while (gameClient25LevelList.contains(gameClient.getUsername())) {
                gameClient = gameClientList.poll();
                if (gameClient == null) {
                    break;
                }
            }
            if (gameClient == null) {
                break;
            }
            workSingle(gameClient, bufferedWriter, bigGameClientList);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            break;
        }
        bufferedWriter.close();
    }

    public static void workSingle(GameClient gameClient, BufferedWriter bufferedWriter, List<GameClient> gameClients) throws IOException {
        System.out.println("本次执行" + gameClient.getUsername());
        gameTeam.setTeamLeader(gameClient);
        gameClient.setGameTeam(gameTeam);
        gameClient.setIndex(5);
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.getGameConfig().setQingliu(true);
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient.setInitMapId(0);
        gameClient.init();
        for (GameClient bigGameClient : gameClients) {
            gameClient.getAllowTeamUsers().add(bigGameClient.getUserId());
            bigGameClient.setTeamLeaderId(gameClient.getUserId());
        }
        gameClient.setTeamLeaderId(gameClient.getUserId());


        gameClient.waitForLoginSuccess();
        System.out.println("登录成功");

        gameClient.waitJoinTeam();
        System.out.println("组队成功");
        gameClient.doScriptAction(new T0_getWorkMaterialAction());
        boolean success = false;
        while (!success) {
            try {
                gameClient.waitJoinTeam();
                doFlow_shimaonanmanqian(gameClient);

                BufferedWriter nanmanqianWriter = new BufferedWriter(new FileWriter(new File("account_work_nanmanqian.txt"), true));
                nanmanqianWriter.write(gameClient.getUsername());
                nanmanqianWriter.newLine();
                nanmanqianWriter.flush();
                nanmanqianWriter.close();

                doFlow_zhuochong(gameClient);
                doFlow_shimaonanmanhou(gameClient);
                BufferedWriter nanmanhouWriter = new BufferedWriter(new FileWriter(new File("account_work_nanmanhou.txt"), true));
                nanmanhouWriter.write(gameClient.getUsername());
                nanmanhouWriter.newLine();
                nanmanhouWriter.flush();
                nanmanhouWriter.close();

                gameClient.moveFar(12001);
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

        boolean isHave10mao = false;
        for (BagItem bagItem : gameClient.getBagItems()) {
            if (bagItem == null) {
                continue;
            }
            if (bagItem.getCount() > 0) {
                if (bagItem.getId() == 24065) {
                    isHave10mao = true;
                }
            }
        }
        if (!isHave10mao) {
            System.out.println("失败！！！！没有10毛！！！！");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("all done!!!");
        gameClient.exitTeam();
        gameClient.close();

        bufferedWriter.write(gameClient.getUsername());
        bufferedWriter.newLine();
        bufferedWriter.flush();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int returnMapId = 12001;
        System.out.println("大号们回" + returnMapId + "(" + PathFindingUtil.getMapName(returnMapId) + ")");
        boolean isInJZ = true;
        for (int i = 0; i < gameClients.size(); i++) {
            GameClient bigGameClient = gameClients.get(i);
            if (bigGameClient.getCurrentMapId() != returnMapId) {
                isInJZ = false;
            }
        }
        if (!isInJZ) {
            gameClients.get(0).getAllowTeamUsers().clear();
            gameClients.get(0).getTeamUsers().clear();
            gameClients.get(0).setTeamLeaderId(gameClients.get(0).getUserId());
            for (int i = 1; i < gameClients.size(); i++) {
                GameClient bigGameClient = gameClients.get(i);
                gameClients.get(0).getAllowTeamUsers().add(bigGameClient.getUserId());
                bigGameClient.setTeamLeaderId(gameClients.get(0).getUserId());
            }
            System.out.println("等待大号组队");
            int teamCount = 0;
            while (gameClients.get(0).getAllowTeamUsers().size() != gameClients.get(0).getTeamUsers().size()) {
                System.out.println("等待大号组队循环");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (teamCount > 3) {
                    break;
                }
                teamCount++;
            }
            System.out.println("大号组队完成");
            gameClients.get(0).moveFar(returnMapId);
            System.out.println("大号1回到" + returnMapId + "(" + PathFindingUtil.getMapName(returnMapId) + ")");
            gameClients.get(0).getAllowTeamUsers().clear();
            gameClients.get(0).exitTeam();
            System.out.println("大号们解除队伍");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 1; i < gameClients.size(); i++) {
                GameClient bigGameClient = gameClients.get(i);
                if (bigGameClient.getCurrentMapId() != returnMapId) {
                    bigGameClient.moveFar(returnMapId);
                }
            }
        }
        System.out.println("大号们回到了" + returnMapId + "(" + PathFindingUtil.getMapName(returnMapId) + ")");
    }


    private static void doFlow_shimaonanmanqian(GameClient gameClient) throws IOException {
        if (gameClient.getCurrentMapId() < 20000) {
            gameClient.doScriptAction(new XuzhouKuashengAction());
            gameClient.doScriptAction(new PubanjinAction());
        }
        gameClient.doScriptAction(new T1_PingmanzhizhangtuAction());
        gameClient.doScriptAction(new T2_lvkaijishouyongchangAction());
        gameClient.doScriptAction(new T3_1manbingqian_zhugeliangdianbingzhengnanmanAction());
    }

    private static void doFlow_zhuochong(GameClient gameClient) {
        while (gameClient.getPetIndex(17771) == -1) {
            gameClient.waitForLoginSuccess();
            try {
                gameClient.chatMi(47811, ChatCommandConstant.givememanbing.getCommand());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doFlow_shimaonanmanhou(GameClient gameClient) throws IOException {
        gameClient.doScriptAction(new T3_2manbinghou_zhugeliangdianbingzhengnanmanAction());
    }

    private static void doFlow_badouyao(GameClient gameClient) throws IOException {
        gameClient.doScriptAction(new OpenMapAction());
        gameClient.doScriptAction(new T1_kuangtingAction());
        gameClient.doScriptAction(new T2_ruxuAction());
        gameClient.doScriptAction(new T3_shentinglingAction());
        gameClient.doScriptAction(new T4_wujunAction());
        gameClient.doScriptAction(new T5_dongyeAction());
        gameClient.doScriptAction(new T6_xisaishanAction());
        gameClient.moveFar(12001);
    }

    private static void doFlow_liubeirujingzhou(GameClient gameClient) throws IOException {
        gameClient.doScriptAction(new Liubeirujingzhou());
        gameClient.moveFar(12001);
        //46050 46063
        boolean isHaveShenghuadan = false;
        BagItem[] bagItems = gameClient.getBagItems();
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            if (bagItem.getId() == 46050 || bagItem.getId() == 46063) {
                isHaveShenghuadan = true;
                break;
            }
        }
        if (isHaveShenghuadan) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("account_shenghuadan.txt"), true));
                bufferedWriter.write(gameClient.getUsername());
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doFlow_gongsunzandemori(GameClient gameClient) throws IOException {
        gameClient.doScriptAction(new Gongsunzandemori());
        gameClient.moveFar(12001);
        //46050 46063
        boolean isHaveShenghuadan = false;
        BagItem[] bagItems = gameClient.getBagItems();
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            if (bagItem.getId() == 46050 || bagItem.getId() == 46063) {
                isHaveShenghuadan = true;
                break;
            }
        }
        if (isHaveShenghuadan) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("account_shenghuadan.txt"), true));
                bufferedWriter.write(gameClient.getUsername());
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> getDoneGameClientList(String fileName) throws IOException {
        List<String> usernameList = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return usernameList;
        }
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
        String line = null;
        while ((line = reader.readLine()) != null) {
            usernameList.add(line);
        }
        reader.close();
        return usernameList;
    }
}
