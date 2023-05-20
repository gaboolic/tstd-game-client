package tk.gbl.core.thread;

import tk.gbl.core.GameClient;
import tk.gbl.core.ScriptAction;
import tk.gbl.core.script.work.activity.AttackBossAction;
import tk.gbl.core.script.work.suipian.SJ1_jianhuoba;
import tk.gbl.core.script.work.suipian.SJ2_jiancanpijia;
import tk.gbl.core.script.work.suipian.SJ3_jianshengxiuchangqiang;
import tk.gbl.util.OutputUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Date: 2017/6/9
 * Time: 10:52
 *
 * @author Tian.Dong
 */
public class PickupItemThread implements Runnable {

    int taskId;
    GameClient gameClient;
    String doneAccountFileName = "account_pickup_suipiancailiao.txt";
    String errorAccountFileName = "account_pickup_suipiancailiao_error.txt";

    public PickupItemThread(GameClient gameClient, int taskId) {
        this.gameClient = gameClient;
        this.taskId = taskId;
    }


    @Override
    public void run() {
        try {
            OutputUtil.output("本次,taskId:" + taskId, gameClient, true);
            gameClient.setConsoleOutput(true);
            gameClient.setFileOutput(false);
            gameClient.setIndex(2);
            gameClient.setTeamLeaderId(0);
            gameClient.setInitMapId(0);
            gameClient.init();
            OutputUtil.output("本次登录:" + gameClient.getUserId() + ",taskId:" + taskId, gameClient, true);
            int retryLoginCount = 0;
            while (!gameClient.isLoginSuccess()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                retryLoginCount++;
                if (retryLoginCount > 15) {
                    break;
                }
            }

            if (gameClient.isLoginSuccess() && checkSuipianCailiao(gameClient)) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(doneAccountFileName), true));
                    bufferedWriter.write(gameClient.getUsername());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                gameClient.close();
                return;
            }
            if (!gameClient.isLoginSuccess() || gameClient.getCurrentMapId() < 20000) {
                OutputUtil.output("异常!写入文件"+errorAccountFileName, gameClient, true);
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(errorAccountFileName), true));
                    bufferedWriter.write(gameClient.getUsername());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                gameClient.close();
                return;
            }

            //0 1 2 3 4
            int orderType = (int) (taskId % 5);
            if (orderType == 0 || orderType == 1) {
                try {
                    gameClient.doScriptAction(new SJ1_jianhuoba());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    gameClient.doScriptAction(new SJ3_jianshengxiuchangqiang());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    gameClient.doScriptAction(new SJ2_jiancanpijia());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (orderType == 2) {
                try {
                    gameClient.doScriptAction(new SJ2_jiancanpijia());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    gameClient.doScriptAction(new SJ3_jianshengxiuchangqiang());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    gameClient.doScriptAction(new SJ1_jianhuoba());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    gameClient.doScriptAction(new SJ3_jianshengxiuchangqiang());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    gameClient.doScriptAction(new SJ1_jianhuoba());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    gameClient.doScriptAction(new SJ2_jiancanpijia());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            gameClient.waitForLoginSuccess();
            if (checkSuipianCailiao(gameClient)) {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(doneAccountFileName), true));
                    bufferedWriter.write(gameClient.getUsername());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            gameClient.moveFar(25414);
            OutputUtil.output("捡完碎片材料-done", gameClient, true);
            gameClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                gameClient.close();
            } catch (Exception e1) {

            }
            OutputUtil.output("异常!error", gameClient, true);
        }
        OutputUtil.output("执行完毕!taskId:"+taskId, gameClient, true);
    }

    public boolean checkSuipianCailiao(GameClient gameClient) {
        return gameClient.getItemCount(29232) > 0
                && gameClient.getItemCount(29233) > 0
                && gameClient.getItemCount(29235) > 0;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public GameClient getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    public String getDoneAccountFileName() {
        return doneAccountFileName;
    }

    public void setDoneAccountFileName(String doneAccountFileName) {
        this.doneAccountFileName = doneAccountFileName;
    }

    public String getErrorAccountFileName() {
        return errorAccountFileName;
    }

    public void setErrorAccountFileName(String errorAccountFileName) {
        this.errorAccountFileName = errorAccountFileName;
    }
}
