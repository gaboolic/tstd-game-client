package tk.gbl.mainenter.gainlevel;

import tk.gbl.bean.Point;
import tk.gbl.constant.ChatCommandConstant;
import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.script.catchpet.CatchPetAction;
import tk.gbl.core.script.taskgainlevel.HuguanTiejiaAction;
import tk.gbl.core.script.taskgainlevel.PaoyeAction;
import tk.gbl.core.script.taskgainlevel.XiangkouwulaiAction;
import tk.gbl.util.FileReadUtil;
import tk.gbl.util.OutputUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 12   打40-41 15832
 * 28打56-57 13873
 * 37打64-65 13804
 * <p/>
 * Date: 2017/6/13
 * Time: 16:20
 *
 * @author Tian.Dong
 */
public class BatchTrainPetPaoye {

    static GameTeam gameTeam = new GameTeam();


    public static void main(String[] args) {
        //捉宠 练级 交易
//        String teamLeaderUsernameAndPassword = "wp42094 234567";
//        String usernameAndPasswordStr = "wp42095 234567\n" +
//                "wp41570 234567\n" +
//                "wp22054 234567\n" +
//                "wp22055 234567\n";


//        String teamLeaderUsernameAndPassword = "103911 6355809";
//        String usernameAndPasswordStr = "103910 6355809\n" +
//                "103912 6355809\n" +
//                "103946 6355809\n" +
//                "103947 6355809\n";

        String teamLeaderUsernameAndPassword = "78081 1324000";
        String usernameAndPasswordStr = "106599 6355809\n" +
                "106600 6355809\n" +
                "106601 6355809\n" +
                "106602 6355809\n";

        int npcId = 15322;
        int initMapId = 0;
        GameClient teamLeader = new GameClient();
        gameTeam.setTeamLeader(teamLeader);
        teamLeader.setGameTeam(gameTeam);
        teamLeader.setIndex(1);
        teamLeader.setConsoleOutput(true);
        teamLeader.setFileOutput(false);
        teamLeader.getGameConfig().setGameConfigType(GameConfigType.work);
        teamLeader.getGameConfig().setNotFightReconnect(true);
        teamLeader.getGameConfig().setQingliu(true);
        teamLeader.setUsername(teamLeaderUsernameAndPassword.split(" ")[0]);
        teamLeader.setPassword(teamLeaderUsernameAndPassword.split(" ")[1]);
        teamLeader.setInitMapId(initMapId);
        teamLeader.init();
        teamLeader.setTeamLeaderId(teamLeader.getUserId());

        List<GameClient> bigGameClientList = new ArrayList<>();
        gameTeam.setGameClientList(bigGameClientList);
        String[] usernameAndPasswords = usernameAndPasswordStr.split("\n");
        for (int i = 0; i < usernameAndPasswords.length; i++) {
            String usernameAndPassword = usernameAndPasswords[i];
            String username = usernameAndPassword.split(" ")[0];
            String password = usernameAndPassword.split(" ")[1];

            GameClient gameClient = new GameClient();
            gameClient.setIndex(i + 2);
            gameClient.setConsoleOutput(false);
            gameClient.setFileOutput(false);
            gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
            gameClient.getGameConfig().setNotFightReconnect(true);
            gameClient.getGameConfig().setQingliu(true);
            gameClient.setUsername(username);
            gameClient.setPassword(password);
            gameClient.setInitMapId(initMapId);
            gameClient.init();
            gameClient.setTeamLeaderId(teamLeader.getUserId());
            bigGameClientList.add(gameClient);
        }
        teamLeader.waitForLoginSuccess();
        for (GameClient bigGameClient : bigGameClientList) {
            bigGameClient.waitForLoginSuccess();
            teamLeader.getAllowTeamUsers().add(bigGameClient.getUserId());
            bigGameClient.setTeamLeaderId(teamLeader.getUserId());
        }

        while (true) {
            System.out.println("开始doFlow");
            try {
                doFlow(teamLeader, npcId, bigGameClientList);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static boolean isInOneMap(GameClient teamLeader, List<GameClient> bigGameClientList) {
        boolean isInOneMap = true;
        int mapId = teamLeader.getCurrentMapId();
        for (GameClient gameClient : bigGameClientList) {
            if (gameClient.getCurrentMapId() != mapId) {
                isInOneMap = false;
            }
        }
        return isInOneMap;
    }

    private static void doFlow(GameClient teamLeader, int npcId, List<GameClient> bigGameClientList) {
        //1 如果身上没宠物，捉宠
        int mapId = 57534;
        int x = 2330;
        int y = 2210;
        teamLeader.waitForLoginSuccess();
        teamLeader.waitJoinTeam();


        String allAccountFileName = "account_25.txt";
        String doneAccountFileName = "account_trade_beigou.txt";
        String errorAccountFileName = "account_trade_beigou_error.txt";
        List<String> allGameClientList = FileReadUtil.getDoneGameClientList(allAccountFileName);
        List<String> doneGameClientList = FileReadUtil.getDoneGameClientList(doneAccountFileName);
        List<String> errorGameClientList = FileReadUtil.getDoneGameClientList(errorAccountFileName);


        if (havePetLevel(gameTeam, 50)) {
            OutputUtil.output("有人宠物50级了", teamLeader, true);
            teamLeader.moveFar(12001);

            if (gameTeam.getTeamLeader().getPet() != null && gameTeam.getTeamLeader().getPet().getLevel() >= 50) {
                OutputUtil.output("队长宠物50级了，交易", teamLeader, true);
                tradeOneBeigou(gameTeam.getTeamLeader(), allGameClientList, doneGameClientList, errorGameClientList, npcId, doneAccountFileName, errorAccountFileName);
            }
            for (GameClient gameClient : gameTeam.getGameClientList()) {
                if (gameClient.getPet() != null && gameClient.getPet().getLevel() >= 50) {
                    OutputUtil.output(gameClient.getUserId() + "队员宠物50级了，交易", teamLeader, true);
                    tradeOneBeigou(gameClient, allGameClientList, doneGameClientList, errorGameClientList, npcId, doneAccountFileName, errorAccountFileName);
                }
            }

        }
        OutputUtil.output("开始进行捉宠判断", teamLeader, true);
        //捉宠
        while (!isAllHavePet(teamLeader, npcId, bigGameClientList)) {
            teamLeader.getAllowTeamUsers().clear();
            for (GameClient bigGameClient : bigGameClientList) {
                bigGameClient.waitForLoginSuccess();
                teamLeader.getAllowTeamUsers().add(bigGameClient.getUserId());
                bigGameClient.setTeamLeaderId(teamLeader.getUserId());
            }

            teamLeader.waitForLoginSuccess();
            teamLeader.waitJoinTeam();
            teamLeader.moveFar(mapId);
            try {
                teamLeader.segmentMoveTo(x, y);
            } catch (IOException e) {
                e.printStackTrace();
            }
            teamLeader.getAllowTeamUsers().clear();
            try {
                teamLeader.exitTeam();
                teamLeader.getTeamUsers().clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
            CatchPetAction catchPetAction = new CatchPetAction();
            catchPetAction.setMapId(mapId);
            catchPetAction.setPk(true);
            catchPetAction.setNpcIndex(1);
            catchPetAction.setNpcId(npcId);
            catchPetAction.setX(x);
            catchPetAction.setY(y);
            try {
                if (teamLeader.getPetIndex(npcId) == -1) {
                    teamLeader.doScriptAction(catchPetAction);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (GameClient gameClient : bigGameClientList) {
                gameClient.waitForLoginSuccess();
                try {
                    if (gameClient.getPetIndex(npcId) == -1) {
                        gameClient.doScriptAction(catchPetAction);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        OutputUtil.output("所有人都有宠物", teamLeader, true);
        teamLeader.waitForLoginSuccess();
        try {
            teamLeader.chuzhan(npcId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (GameClient bigGameClient : bigGameClientList) {
            bigGameClient.waitForLoginSuccess();
            try {
                bigGameClient.chuzhan(npcId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //出战
        while (!isAllChuzhanPet(teamLeader, npcId, bigGameClientList)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        OutputUtil.output("所有人宠物全部出战", teamLeader, true);

        OutputUtil.output("开始组队", teamLeader, true);
        teamLeader.getAllowTeamUsers().clear();
        for (GameClient bigGameClient : bigGameClientList) {
            bigGameClient.waitForLoginSuccess();
            teamLeader.getAllowTeamUsers().add(bigGameClient.getUserId());
            bigGameClient.setTeamLeaderId(teamLeader.getUserId());
        }
        teamLeader.waitForLoginSuccess();
        teamLeader.waitJoinTeam();
        OutputUtil.output("组队成功" + teamLeader.getAllowTeamUsers().size() + "," + teamLeader.getTeamUsers(), teamLeader, true);


        /**
         * 12 打40-41 15832
         * 28 打56-57 13873
         * 37 打64-65 18837
         */
        //练级 直到宠物等级到达N级 铁甲
        OutputUtil.output("开始打铁甲到12级", teamLeader, true);
        while (!allPetLevel(gameTeam, 12)) {
            OutputUtil.output("进入while (!allPet12())循环", teamLeader, true);
            teamLeader.waitForLoginSuccess();
            teamLeader.waitJoinTeam();
            if (teamLeader.getTeamUsers().size() == 4 && teamLeader.isLoginSuccess()) {
                try {
                    teamLeader.doScriptAction(new HuguanTiejiaAction() {
                        @Override
                        public boolean customJudge() {
                            return allPetLevel(gameTeam, 12);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //12-28  打40
        OutputUtil.output("12-28打15832 40级灵怪", teamLeader, true);
        while (!allPetLevel(gameTeam, 28)) {
            OutputUtil.output("进入while (!allPet28())循环", teamLeader, true);
            teamLeader.waitForLoginSuccess();
            teamLeader.waitJoinTeam();
            if (teamLeader.getTeamUsers().size() == 4 && teamLeader.isLoginSuccess()) {
                PaoyeAction paoyeAction = new PaoyeAction();
                paoyeAction.setMapId(15832);
                paoyeAction.setEndLevel(28);
                paoyeAction.getPointList().add(new Point(1217, 846));
                paoyeAction.getPointList().add(new Point(937, 554));
                paoyeAction.getPointList().add(new Point(578, 932));
                try {
                    teamLeader.doScriptAction(paoyeAction);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //28-37 打55 13873
        OutputUtil.output("28-37打13873 56级灵怪", teamLeader, true);
        while (!allPetLevel(gameTeam, 37)) {
            OutputUtil.output("进入while (!allPet37())循环", teamLeader, true);
            teamLeader.waitForLoginSuccess();
            teamLeader.waitJoinTeam();
            if (teamLeader.getTeamUsers().size() == 4 && teamLeader.isLoginSuccess()) {
                PaoyeAction paoyeAction = new PaoyeAction();
                paoyeAction.setMapId(13873);
                paoyeAction.setEndLevel(37);
                paoyeAction.getPointList().add(new Point(971, 1577));
                paoyeAction.getPointList().add(new Point(1165, 1011));
                paoyeAction.getPointList().add(new Point(947, 969));
                try {
                    teamLeader.doScriptAction(paoyeAction);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //37打65-66 13804
        OutputUtil.output("37-50打13804 65级灵怪", teamLeader, true);
        while (!allPetLevel(gameTeam, 50)) {
            OutputUtil.output("进入while (!allPet50())循环", teamLeader, true);
            teamLeader.waitForLoginSuccess();
            teamLeader.waitJoinTeam();
            if (teamLeader.getTeamUsers().size() == 4 && teamLeader.isLoginSuccess()) {
                PaoyeAction paoyeAction = new PaoyeAction();
                paoyeAction.setMapId(13804);
                paoyeAction.setEndLevel(50);
                paoyeAction.getPointList().add(new Point(451, 1579));
                paoyeAction.getPointList().add(new Point(1153, 2271));
                paoyeAction.getPointList().add(new Point(531, 1978));
                try {
                    teamLeader.doScriptAction(paoyeAction);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //交易宠物给小号
        System.out.println("宠物都50级了，交易宠物给小号");
        teamLeader.moveFar(12001);
        System.out.println("回到涿郡");


        allGameClientList = FileReadUtil.getDoneGameClientList(allAccountFileName);
        doneGameClientList = FileReadUtil.getDoneGameClientList(doneAccountFileName);
        errorGameClientList = FileReadUtil.getDoneGameClientList(errorAccountFileName);
        List<GameClient> gameClientList = new ArrayList<>();

        for (String username : allGameClientList) {
            if (doneGameClientList.contains(username)) {
                continue;
            }
            if (errorGameClientList.contains(username)) {
                continue;
            }
            GameClient gameClient = new GameClient();
            gameClient.setUsername(username);
            gameClient.setPassword("123456");
            gameClient.setIndex(6);
            gameClient.init();
            boolean loginResult = gameClient.waitForLoginSuccess();
            if (!loginResult) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(errorAccountFileName), true));
                    bufferedWriter.write(gameClient.getUsername());
                    bufferedWriter.write("\r\n");
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gameClient.close();
                continue;
            }
            if (gameClient.getCurrentMapId() > 20000 || gameClient.getPetCount() == 4) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(errorAccountFileName), true));
                    bufferedWriter.write(gameClient.getUsername());
                    bufferedWriter.write("\r\n");
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gameClient.close();
                continue;
            }
            if (gameClient.getPetIndex(npcId) != -1) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(doneAccountFileName), true));
                    bufferedWriter.write(gameClient.getUsername());
                    bufferedWriter.write("\r\n");
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gameClient.close();
                continue;
            }
            gameClient.moveFar(12001);
            gameClientList.add(gameClient);
            if (gameClientList.size() == 5) {
                break;
            }
        }
        StringBuilder teamMemberString = new StringBuilder();
        for (GameClient gameClient : gameClientList) {
            gameClient.getGameConfig().setAutoTradePet(true);

            teamMemberString.append(gameClient.getUsername());
            teamMemberString.append(" ");
        }
        System.out.println("本次交易小号：" + teamMemberString);
        for (GameClient gameClient : gameClientList) {
            gameClient.waitForLoginSuccess();
            gameClient.moveFar(12001);
        }
        System.out.println("while (!isAllHavePet外");
        while (!isAllHavePet(gameClientList.get(0), npcId, gameClientList)) {
            try {
                if (gameClientList.get(0).getPetIndex(npcId) == -1) {
                    gameClientList.get(0).chatMi(teamLeader.getUserId(), ChatCommandConstant.givemebeigou.getCommand());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 1; i < 5; i++) {
                GameClient gameClient = gameClientList.get(i);
                GameClient bigGameClient = bigGameClientList.get(i - 1);
                try {
                    if (gameClient.getPetIndex(npcId) == -1) {
                        gameClient.chatMi(bigGameClient.getUserId(), ChatCommandConstant.givemebeigou.getCommand());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("小号全部获得宠物");
        for (GameClient gameClient : gameClientList) {
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
        for (GameClient gameClient : gameClientList) {
            gameClient.close();
        }
        OutputUtil.output("小号全部关闭", teamLeader, true);
    }

    private static void tradeOneBeigou(GameClient bigGameClient, List<String> allGameClientList, List<String> doneGameClientList, List<String> errorGameClientList,
                                       int npcId, String doneAccountFileName, String errorAccountFileName) {
        for (String username : allGameClientList) {
            if (doneGameClientList.contains(username)) {
                continue;
            }
            if (errorGameClientList.contains(username)) {
                continue;
            }
            GameClient gameClient = new GameClient();
            gameClient.setUsername(username);
            gameClient.setPassword("123456");
            gameClient.setIndex(6);
            gameClient.init();
            boolean loginResult = gameClient.waitForLoginSuccess();
            if (!loginResult) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(errorAccountFileName), true));
                    bufferedWriter.write(gameClient.getUsername());
                    bufferedWriter.write("\r\n");
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gameClient.close();
                continue;
            }
            if (gameClient.getCurrentMapId() > 20000 || gameClient.getPetCount() == 4) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(errorAccountFileName), true));
                    bufferedWriter.write(gameClient.getUsername());
                    bufferedWriter.write("\r\n");
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gameClient.close();
                continue;
            }
            if (gameClient.getPetIndex(npcId) != -1) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(doneAccountFileName), true));
                    bufferedWriter.write(gameClient.getUsername());
                    bufferedWriter.write("\r\n");
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gameClient.close();
                continue;
            }
            gameClient.moveFar(12001);
            try {
                while (gameClient.getPetIndex(npcId) == -1) {
                    gameClient.chatMi(bigGameClient.getUserId(), ChatCommandConstant.givemebeigou.getCommand());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(doneAccountFileName), true));
                bufferedWriter.write(gameClient.getUsername());
                bufferedWriter.write("\r\n");
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    private static boolean allPetLevel(GameTeam gameTeam, int level) {
        boolean isAll25 = true;
        if (gameTeam.getTeamLeader().getPet().getLevel() < level) {
            isAll25 = false;
        }
        for (GameClient gameClient : gameTeam.getGameClientList()) {
            if (gameClient.getPet().getLevel() < level) {
                isAll25 = false;
            }
        }
        return isAll25;
    }

    private static boolean havePetLevel(GameTeam gameTeam, int level) {
        boolean isHaveLevel = false;
        if (gameTeam.getTeamLeader().getPet() != null && gameTeam.getTeamLeader().getPet().getLevel() >= level) {
            isHaveLevel = true;
        }
        for (GameClient gameClient : gameTeam.getGameClientList()) {
            if (gameClient.getPet() != null && gameClient.getPet().getLevel() >= level) {
                isHaveLevel = true;
            }
        }
        return isHaveLevel;
    }


    private static boolean isAllHavePet(GameClient teamLeader, int npcId, List<GameClient> bigGameClientList) {
        boolean isAllHavePet = true;
        if (teamLeader.getPetIndex(npcId) == -1) {
            isAllHavePet = false;
        }
        for (GameClient bigGameClient : bigGameClientList) {
            if (bigGameClient.getPetIndex(npcId) == -1) {
                isAllHavePet = false;
            }
        }
        return isAllHavePet;
    }

    private static boolean isAllChuzhanPet(GameClient teamLeader, int npcId, List<GameClient> bigGameClientList) {
        boolean isAllHavePet = true;
        teamLeader.waitForLoginSuccess();
        if (teamLeader.getPet() == null) {
            try {
                teamLeader.chuzhan(npcId);
            } catch (IOException e) {
                e.printStackTrace();
            }
            isAllHavePet = false;
        }
        for (GameClient bigGameClient : bigGameClientList) {
            bigGameClient.waitForLoginSuccess();
            if (bigGameClient.getPet() == null) {
                try {
                    bigGameClient.chuzhan(npcId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isAllHavePet = false;
            }
        }
        return isAllHavePet;
    }
}
