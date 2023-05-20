package tk.gbl.mainenter;

import tk.gbl.constant.GameConfigType;
import tk.gbl.constant.ProcessConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.script.NewZhenchashuAction;
import tk.gbl.core.script.ZhenchashuAction;
import tk.gbl.core.script.pass.OpenMapAction;
import tk.gbl.core.script.pass.PubanjinAction;
import tk.gbl.core.script.pass.XuzhouKuashengAction;
import tk.gbl.core.script.skill.DuntaoshuAction;
import tk.gbl.core.script.work.activity.NPCLeitaiAction;
import tk.gbl.core.script.work.badouyao.*;
import tk.gbl.core.script.work.qinxinxiangbao.*;
import tk.gbl.core.script.work.shimao.T1_PingmanzhizhangtuAction;
import tk.gbl.core.script.work.shimao.T2_lvkaijishouyongchangAction;
import tk.gbl.core.script.work.shimao.T3_1manbingqian_zhugeliangdianbingzhengnanmanAction;
import tk.gbl.core.script.work.shimao.T3_2manbinghou_zhugeliangdianbingzhengnanmanAction;
import tk.gbl.core.script.work.shiyixin.*;

import java.io.IOException;

/**
 * Date: 2017/5/6
 * Time: 9:13
 *
 * @author Tian.Dong
 */
public class MainTankSingleWork {
    final static GameClient gameClient1 = new GameClient();
    final static GameClient gameClient2 = new GameClient();
    final static GameClient gameClient3 = new GameClient();
    final static GameClient gameClient4 = new GameClient();

    public static void main(String[] args) throws IOException {
        String finalUsername = "WP00114018";
//        String finalUsername = "wp115037";
        String finalPassword = ProcessConstant.p55;

        final GameClient gameClient = new GameClient();
        gameClient.setIndex(5);
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient.setUsername(finalUsername);
        gameClient.setPassword(finalPassword);
        gameClient.setInitMapId(0);
        gameClient.init();
        gameClient.setTeamLeaderId(gameClient.getUserId());
        gameClient.getAllowTeamUsers().add(47809L);
        gameClient.getAllowTeamUsers().add(47810L);
        gameClient.getAllowTeamUsers().add(47812L);
        gameClient.getAllowTeamUsers().add(47813L);

        gameClient1.setIndex(1);
        gameClient1.setConsoleOutput(false);
        gameClient1.setFileOutput(true);
        gameClient1.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient1.setUsername("wp47810");
        gameClient1.setPassword(ProcessConstant.password);
        gameClient1.setInitMapId(0);
        gameClient1.init();
        gameClient1.setTeamLeaderId(gameClient.getUserId());

        gameClient2.setIndex(2);
        gameClient2.setConsoleOutput(false);
        gameClient2.setFileOutput(true);
        gameClient2.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient2.setUsername("wp47812");
        gameClient2.setPassword(ProcessConstant.password);
        gameClient2.setInitMapId(0);
        gameClient2.init();
        gameClient2.setTeamLeaderId(gameClient.getUserId());

        gameClient3.setIndex(3);
        gameClient3.setConsoleOutput(false);
        gameClient3.setFileOutput(true);
        gameClient3.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient3.setUsername("wp47813");
        gameClient3.setPassword(ProcessConstant.password);
        gameClient3.setInitMapId(0);
        gameClient3.init();
        gameClient3.setTeamLeaderId(gameClient.getUserId());

        gameClient4.setIndex(4);
        gameClient4.setConsoleOutput(false);
        gameClient4.setFileOutput(true);
        gameClient4.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient4.setUsername("wp47809");
        gameClient4.setPassword(ProcessConstant.password);
        gameClient4.setInitMapId(0);
        gameClient4.init();
        gameClient4.setTeamLeaderId(gameClient.getUserId());


        while(!gameClient.isLoginSuccess()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("登录成功");
        if(gameClient.getCurrentMapId() == 12001) {
            gameClient.segmentMoveTo(400, 1600);
        }

        //组队
        waitJoinTeam(gameClient);
        System.out.println("组队成功");


//        gameClient.doScriptAction(new Q1_qiaoyuheishanjun());
//        gameClient.doScriptAction(new Q2_jituiheishanjun());
//        gameClient.doScriptAction(new Q3_huangjintaofazhan());
//        gameClient.doScriptAction(new Q4_kuangtingzhuijizhan());
//        gameClient.doScriptAction(new Q5_xuzhoufuchouzhan());
//        gameClient.doScriptAction(new Q6_yanzhouzhengduozhan());
//        gameClient.doScriptAction(new Q7_fengyingtianzi());
        gameClient.doScriptAction(new Q8_qingshuicanbai());
        gameClient.doScriptAction(new Q9_shouchunweichengzhan());

//        gameClient.doScriptAction(new T1_PingmanzhizhangtuAction());
//        gameClient.doScriptAction(new T2_lvkaijishouyongchangAction());
//        gameClient.doScriptAction(new T3_2manbinghou_zhugeliangdianbingzhengnanmanAction());
//        gameClient.moveFar(12001);

//        gameClient.doScriptAction(new NewZhenchashuAction());
//        gameClient.doScriptAction(new DuntaoshuAction());

//        if(gameClient.getCurrentMapId() < 20000) {
//            gameClient.doScriptAction(new XuzhouKuashengAction());
//            gameClient.doScriptAction(new PubanjinAction());
//        }
//        gameClient.doScriptAction(new X1_caocaoxingbingquhanzhong());
//        gameClient.doScriptAction(new X2_chuzhanhanzhong());
//        gameClient.doScriptAction(new X3_duoquyangpingguan());
//        gameClient.doScriptAction(new X4_pangdeguixiang());
//        gameClient.doScriptAction(new X5_caocaodinghanzhong());
//        gameClient.doScriptAction(new X0_getDaocaorenwawa());
//        gameClient.doScriptAction(new X6_wakoudazhan());
//        gameClient.doScriptAction(new X7_zhangfeidouzhanghe());
//        gameClient.doScriptAction(new X8_huangzhongzhishoujianjiaguan());
//        gameClient.moveFar(12001);
        System.out.println("all done!!!");
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
}
