package tk.gbl.mainenter.gainlevel;

import tk.gbl.constant.ChatCommandConstant;
import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.script.catchpet.CatchPetAction;
import tk.gbl.core.script.taskgainlevel.HuguanTiejiaAction;
import tk.gbl.core.script.taskgainlevel.XiangkouwulaiAction;
import tk.gbl.util.FileReadUtil;
import tk.gbl.util.OutputUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 12   打40
 * 27打55
 * 37打65
 *
 * Date: 2017/6/13
 * Time: 16:20
 *
 * @author Tian.Dong
 */
public class BatchTrainPet {

    static GameTeam gameTeam = new GameTeam();


    public static void main(String[] args) {
        //捉宠 练级 交易
        String usernameAndPasswordStr = "wp42095 234567\n" +
                "wp41570 234567\n" +
                "wp22054 234567\n" +
                "wp22055 234567\n";
        int npcId = 15322;
        int initMapId = 0;
        GameClient teamLeader = new GameClient();
        gameTeam.setTeamLeader(teamLeader);
        teamLeader.setGameTeam(gameTeam);
        teamLeader.setIndex(1);
        teamLeader.setConsoleOutput(true);
        teamLeader.setFileOutput(true);
        teamLeader.getGameConfig().setGameConfigType(GameConfigType.work);
        teamLeader.getGameConfig().setNotFightReconnect(true);
        teamLeader.getGameConfig().setQingliu(true);
        teamLeader.setUsername("wp42094");
        teamLeader.setPassword("234567");
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
            gameClient.setFileOutput(true);
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
            doFlow(teamLeader, npcId, bigGameClientList);
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
        if (teamLeader.getPet() == null) {
            try {
                teamLeader.chuzhan(npcId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (GameClient bigGameClient : bigGameClientList) {
            bigGameClient.waitForLoginSuccess();
            if (bigGameClient.getPet() == null) {
                try {
                    bigGameClient.chuzhan(npcId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //练级 直到宠物等级到达N级
        while (!allPet25(gameTeam)) {
            OutputUtil.output("进入while (!allPet25())循环", teamLeader, true);
            teamLeader.waitForLoginSuccess();
            teamLeader.waitJoinTeam();
            if (teamLeader.getTeamUsers().size() == 4 && teamLeader.isLoginSuccess()) {
                try {
                    teamLeader.doScriptAction(new HuguanTiejiaAction() {
                        @Override
                        public boolean customJudge() {
                            return allPet25(gameTeam);
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
        System.out.println("全部25级，开始打巷口");
        while (!allPet50(gameTeam)) {
            OutputUtil.output("进入while (!allPet50())循环", teamLeader, true);
            teamLeader.waitForLoginSuccess();
            teamLeader.waitJoinTeam();
            if (teamLeader.getTeamUsers().size() == 4 && teamLeader.isLoginSuccess()) {
                try {
                    teamLeader.doScriptAction(new XiangkouwulaiAction() {
                        @Override
                        public boolean customJudge() {
                            return allPet50(gameTeam);
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
        //交易宠物给小号
        System.out.println("宠物都50级了，交易宠物给小号");
        teamLeader.moveFar(12001);
        System.out.println("回到涿郡");

        String allAccountFileName = "account_25.txt";
        String doneAccountFileName = "account_trade_beigou.txt";
        String errorAccountFileName = "account_trade_beigou_error.txt";
        List<String> allGameClientList = FileReadUtil.getDoneGameClientList(allAccountFileName);
        List<String> doneGameClientList = FileReadUtil.getDoneGameClientList(doneAccountFileName);
        List<String> errorGameClientList = FileReadUtil.getDoneGameClientList(errorAccountFileName);

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
            gameClient.waitForLoginSuccess();
            if (gameClient.getCurrentMapId() > 20000 || gameClient.getPetCount() == 4 || gameClient.getPetIndex(npcId) != -1) {
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

    private static boolean allPet50(GameTeam gameTeam) {
        boolean isAll25 = true;
        if (gameTeam.getTeamLeader().getPet().getExp() < 1127642) {
            isAll25 = false;
        }
        for (GameClient gameClient : gameTeam.getGameClientList()) {
            if (gameClient.getPet().getExp() < 1127642) {
                isAll25 = false;
            }
        }
        return isAll25;
    }

    private static boolean allPet25(GameTeam gameTeam) {
        boolean isAll25 = true;
        if (gameTeam.getTeamLeader().getPet().getExp() < 78500) {
            isAll25 = false;
        }
        for (GameClient gameClient : gameTeam.getGameClientList()) {
            if (gameClient.getPet().getExp() < 78500) {
                isAll25 = false;
            }
        }
        return isAll25;
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
}
