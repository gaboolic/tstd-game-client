package tk.gbl.mainenter.qinxin;

import tk.gbl.bean.BagItem;
import tk.gbl.bean.RoleInfo;
import tk.gbl.constant.ChatCommandConstant;
import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.constant.SkillType;
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
import tk.gbl.core.script.work.qinxinxiangbao.*;
import tk.gbl.core.script.work.shenghuadan.Gongsunzandemori;
import tk.gbl.core.script.work.shenghuadan.Liubeirujingzhou;
import tk.gbl.core.script.work.shimao.T1_PingmanzhizhangtuAction;
import tk.gbl.core.script.work.shimao.T2_lvkaijishouyongchangAction;
import tk.gbl.core.script.work.shimao.T3_1manbingqian_zhugeliangdianbingzhengnanmanAction;
import tk.gbl.core.script.work.shimao.T3_2manbinghou_zhugeliangdianbingzhengnanmanAction;
import tk.gbl.core.script.work.shiyixin.*;
import tk.gbl.util.PathFindingUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date: 2017/5/6
 * Time: 9:13
 *
 * @author Tian.Dong
 */
public class MainTankQinxinWork {

    static GameTeam gameTeam = new GameTeam();

    public static void main(String[] args) throws IOException {
        new Thread() {
            public void run() {
                final GameClient gameClient = new GameClient();
                gameClient.setUsername("WP00114018");
                gameClient.setPassword("");
                gameClient.setFileOutput(false);
                gameClient.init();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameClient.waitForLoginSuccess();
                System.out.println("捉宠***当前地图：" + gameClient.getCurrentMapId());

                CatchPetAction catchPetAction = new CatchPetAction();
                catchPetAction.setClickNpc(true);
                catchPetAction.setMapId(15415);
                catchPetAction.setNpcIndex(6);
                catchPetAction.setNpcId(14342);
                catchPetAction.setX(222);
                catchPetAction.setY(275);

                while (true) {
                    if (haveXugan(gameClient)) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    try {
                        gameClient.doScriptAction(catchPetAction);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

        int initMapId = 0;
//        int initMapId = 0;

        String usernameAndPasswordStr = "wp47810 234567\n" +
                "wp47812 234567\n" +
                "wp47813 234567\n" +
                "wp47809 234567";
        List<GameClient> bigGameClientList = new ArrayList<>();
        gameTeam.setGameClientList(bigGameClientList);
        String[] usernameAndPasswords = usernameAndPasswordStr.split("\n");

        for(int i=0;i<usernameAndPasswords.length;i++) {
            String usernameAndPassword = usernameAndPasswords[i];
            String username = usernameAndPassword.split(" ")[0];
            String password = usernameAndPassword.split(" ")[1];

            GameClient gameClient = new GameClient();
            gameClient.setIndex(i+1);
            gameClient.setConsoleOutput(false);
            gameClient.setFileOutput(true);
            gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
            gameClient.setUsername(username);
            gameClient.setPassword(password);
            gameClient.setInitMapId(initMapId);
            gameClient.init();
            gameClient.setTeamLeaderId(1);
            bigGameClientList.add(gameClient);
        }




        String allAccountFileName = "account_25.txt";
//        String allAccountFileName = "account_work_nanmanhou.txt";
//        String allAccountFileName = "account_trade_hongbao.txt";

//        String accountDoneFileName = "account_gongsunzandemori.txt";
//        String accountDoneFileName = "account_liubeirujingzhou.txt";
//        String accountDoneFileName = "account_work_nanmanqian.txt";
//        String accountDoneFileName = "account_work_nanmanhou_zj.txt";
//        String accountDoneFileName = "account_work_11xin.txt";
//        String accountDoneFileName = "account_work_hongbao.txt";
        String accountDoneFileName = "account_work_qinxin.txt";

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
            break;
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
//        gameClient.doScriptAction(new T0_getWorkMaterialAction());
        boolean success = false;
        while (!success) {
            try {
                gameClient.waitJoinTeam();
                doFlow_qinxin(gameClient);
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



    private static void doFlow_qinxin(GameClient gameClient) throws IOException {
//        if (gameClient.getCurrentMapId() < 20000) {
//            gameClient.doScriptAction(new XuzhouKuashengAction());
//            gameClient.doScriptAction(new PubanjinAction());
//        }
//        gameClient.doScriptAction(new Q1_qiaoyuheishanjun());
//        gameClient.doScriptAction(new Q2_jituiheishanjun());
//        gameClient.doScriptAction(new Q3_huangjintaofazhan());
//        gameClient.doScriptAction(new Q4_kuangtingzhuijizhan());
//        gameClient.doScriptAction(new Q5_xuzhoufuchouzhan());
//        gameClient.doScriptAction(new Q6_yanzhouzhengduozhan());
        gameClient.doScriptAction(new Q7_fengyingtianzi());
        gameClient.doScriptAction(new Q8_qingshuicanbai());
        gameClient.doScriptAction(new Q9_shouchunweichengzhan());
        gameClient.doScriptAction(new Q10_baimenlouqinlvbu());
        gameClient.moveFar(12001);
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

    private static boolean haveXugan(GameClient gameClient) {
        boolean isHaveManbing = false;
        for (int i = 0; i < gameClient.getPetList().length; i++) {
            RoleInfo pet = gameClient.getPetList()[i];
            if (pet == null) {
                continue;
            }
            if (pet.getId() == 14342) {
                isHaveManbing = true;
            }
        }
        return isHaveManbing;
    }
}
