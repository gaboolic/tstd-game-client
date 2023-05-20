package tk.gbl.mainenter;

import tk.gbl.bean.BagItem;
import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.constant.SkillType;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.script.NewZhenchashuAction;
import tk.gbl.core.script.ShoujingpoAction;
import tk.gbl.core.script.pass.*;
import tk.gbl.core.script.skill.DuntaoshuAction;
import tk.gbl.core.script.work.badouyao.*;
import tk.gbl.core.script.work.horse.HanxuebaomaAction;
import tk.gbl.core.script.work.shenghuadan.Gongsunzandemori;
import tk.gbl.core.script.work.shenghuadan.Liubeirujingzhou;
import tk.gbl.core.script.work.shimao.*;
import tk.gbl.core.script.work.shiyixin.*;

import java.io.*;
import java.util.*;

/**
 * Date: 2017/5/6
 * Time: 9:13
 *
 * @author Tian.Dong
 */
public class MainTankWork {
    final static GameClient gameClient1 = new GameClient();
    final static GameClient gameClient2 = new GameClient();
    final static GameClient gameClient3 = new GameClient();
    final static GameClient gameClient4 = new GameClient();

    public static void main(String[] args) throws IOException {
        int initMapId = 0;
//        int initMapId = 0;

        gameClient1.setIndex(1);
        gameClient1.setConsoleOutput(false);
        gameClient1.setFileOutput(false);
        gameClient1.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient1.setUsername("wp47810");
        gameClient1.setPassword(ProcessConstant.password);
        gameClient1.setInitMapId(initMapId);
        gameClient1.init();
        gameClient1.setTeamLeaderId(1);

        gameClient2.setIndex(2);
        gameClient2.setConsoleOutput(false);
        gameClient2.setFileOutput(false);
        gameClient2.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient2.setUsername("wp47812");
        gameClient2.setPassword(ProcessConstant.password);
        gameClient2.setInitMapId(initMapId);
        gameClient2.init();
        gameClient2.setTeamLeaderId(1);

        gameClient3.setIndex(3);
        gameClient3.setConsoleOutput(false);
        gameClient3.setFileOutput(false);
        gameClient3.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient3.setUsername("wp47813");
        gameClient3.setPassword(ProcessConstant.password);
        gameClient3.setInitMapId(initMapId);
        gameClient3.init();
        gameClient3.setTeamLeaderId(1);

        gameClient4.setIndex(4);
        gameClient4.setConsoleOutput(false);
        gameClient4.setFileOutput(false);
        gameClient4.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient4.setUsername("wp47809");
        gameClient4.setPassword(ProcessConstant.password);
        gameClient4.setInitMapId(initMapId);
        gameClient4.init();
        gameClient4.setTeamLeaderId(1);


        String allAccountFileName = "account_25.txt";
//        String allAccountFileName = "account_trade_hongbao.txt";
//        String accountDoneFileName = "account_gongsunzandemori.txt";
//        String accountDoneFileName = "account_work_liubeirujingzhou.txt";
//        String accountDoneFileName = "account_work_nanmanqian.txt";
        String accountDoneFileName = "account_work_11xin.txt";
//        String accountDoneFileName = "account_work_hongbao.txt";

        List<String> gameClient25LevelList = getDoneGameClientList(accountDoneFileName);
//        Queue<GameClient> gameClientList = new LinkedList<>();
        Stack<GameClient> gameClientList = new Stack<>();
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
            GameClient gameClient = gameClientList.pop();
            if (gameClient == null) {
                break;
            }
            while (gameClient25LevelList.contains(gameClient.getUsername())) {
                gameClient = gameClientList.pop();
                if (gameClient == null) {
                    break;
                }
            }
            if (gameClient == null) {
                break;
            }
            workSingle(gameClient, bufferedWriter, gameClient1, gameClient2, gameClient3, gameClient4);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            break;
        }
        bufferedWriter.close();
    }

    public static void workSingle(GameClient gameClient, BufferedWriter bufferedWriter, GameClient... gameClients) throws IOException {
        System.out.println("本次执行" + gameClient.getUsername());
        gameClient.setIndex(5);
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(false);
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

        waitJoinTeam(gameClient);
        System.out.println("组队成功");
//        gameClient.doScriptAction(new T0_getWorkMaterialAction());
        boolean success = false;
        while (!success) {
            try {
                waitJoinTeam(gameClient);
//                doFlow_shimaonanmanqian(gameClient);
                doFlow_11xin(gameClient);
//                doFlow_hongbao(gameClient);
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
        System.out.println("大号们回涿郡");
        boolean isInJZ = true;
        for (int i = 0; i < gameClients.length; i++) {
            GameClient bigGameClient = gameClients[i];
            if(bigGameClient.getCurrentMapId() != 12001) {
                isInJZ = false;
            }
        }
        if(!isInJZ) {
            gameClients[0].getAllowTeamUsers().clear();
            gameClients[0].getTeamUsers().clear();
            gameClients[0].setTeamLeaderId(gameClients[0].getUserId());
            for (int i = 1; i < gameClients.length; i++) {
                GameClient bigGameClient = gameClients[i];
                gameClients[0].getAllowTeamUsers().add(bigGameClient.getUserId());
                bigGameClient.setTeamLeaderId(gameClients[0].getUserId());
            }
            System.out.println("等待大号组队");
            int teamCount = 0;
            while (gameClients[0].getAllowTeamUsers().size() != gameClients[0].getTeamUsers().size()) {
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
            gameClients[0].moveFar(12001);
            System.out.println("大号1回到了涿郡");
            gameClients[0].getAllowTeamUsers().clear();
            gameClients[0].exitTeam();
            System.out.println("大号们解除队伍");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 1; i < gameClients.length; i++) {
                GameClient bigGameClient = gameClients[i];
                if (bigGameClient.getCurrentMapId() != 12001) {
                    bigGameClient.moveFar(12001);
                }
            }
        }
        System.out.println("大号们回到了涿郡");
    }

    private static void waitJoinTeam(GameClient gameClient) {
        gameClient1.waitForLoginSuccess();
        gameClient2.waitForLoginSuccess();
        gameClient3.waitForLoginSuccess();
        gameClient4.waitForLoginSuccess();
        if (gameClient.getCurrentMapId() != gameClient1.getCurrentMapId()) {
            gameClient1.moveFar(gameClient.getCurrentMapId());
        }
        if (gameClient.getCurrentMapId() != gameClient2.getCurrentMapId()) {
            gameClient2.moveFar(gameClient.getCurrentMapId());
        }
        if (gameClient.getCurrentMapId() != gameClient3.getCurrentMapId()) {
            gameClient3.moveFar(gameClient.getCurrentMapId());
        }
        if (gameClient.getCurrentMapId() != gameClient4.getCurrentMapId()) {
            gameClient4.moveFar(gameClient.getCurrentMapId());
        }

        //组队
        while (gameClient.getTeamUsers().size() != gameClient.getAllowTeamUsers().size()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doFlow_hongbao(GameClient gameClient) throws IOException {
        if(gameClient.getCurrentMapId() < 20000) {
            gameClient.moveFar(12000);
            gameClient.simpleMove(30);
            while (gameClient.getCurrentMapId() != 11000) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        gameClient.doScriptAction(new PenglaiAction());
        gameClient.moveFar(12001);
        gameClient.autoUseAndThrow();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.autoUseAndThrow();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void doFlow_11xin(GameClient gameClient) throws IOException {
        if(!gameClient.getRoleInfo().getSkillList().contains(SkillType.zhenchashu.getId())) {
            System.out.println("没有侦察术，去学侦察术");
            gameClient.doScriptAction(new NewZhenchashuAction());
        }
        if(!gameClient.getRoleInfo().getSkillList().contains(SkillType.duntaoshu.getId())) {
            System.out.println("没有遁逃术，去学遁逃术");
            gameClient.doScriptAction(new DuntaoshuAction());
        }
        if(gameClient.getCurrentMapId() < 20000) {
            gameClient.doScriptAction(new XuzhouKuashengAction());
            gameClient.doScriptAction(new PubanjinAction());
        }
        gameClient.doScriptAction(new X1_caocaoxingbingquhanzhong());
        gameClient.doScriptAction(new X2_chuzhanhanzhong());
        gameClient.doScriptAction(new X3_duoquyangpingguan());
        gameClient.doScriptAction(new X4_pangdeguixiang());
        gameClient.doScriptAction(new X5_caocaodinghanzhong());
        gameClient.doScriptAction(new X0_getDaocaorenwawa());
        gameClient.doScriptAction(new X6_wakoudazhan());
        gameClient.doScriptAction(new X7_zhangfeidouzhanghe());
        gameClient.doScriptAction(new X8_huangzhongzhishoujianjiaguan());
        gameClient.moveFar(12001);
    }

    private static void doFlow_shimaonanmanqian(GameClient gameClient) throws IOException {
        if(gameClient.getCurrentMapId() < 20000) {
            gameClient.doScriptAction(new XuzhouKuashengAction());
            gameClient.doScriptAction(new PubanjinAction());
        }
        gameClient.doScriptAction(new T1_PingmanzhizhangtuAction());
        gameClient.doScriptAction(new T2_lvkaijishouyongchangAction());
        gameClient.doScriptAction(new T3_1manbingqian_zhugeliangdianbingzhengnanmanAction());
    }

    private static void doFlow_shimaonanmanhou(GameClient gameClient) throws IOException {
        gameClient.doScriptAction(new T3_2manbinghou_zhugeliangdianbingzhengnanmanAction());
        gameClient.moveFar(12001);
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
