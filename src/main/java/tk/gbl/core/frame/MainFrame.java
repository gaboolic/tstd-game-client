package tk.gbl.core.frame;

import tk.gbl.core.GameClient;
import tk.gbl.core.GameClientMap;
import tk.gbl.core.GameTeam;
import tk.gbl.core.frame.panel.BattleInfoPanel;
import tk.gbl.core.frame.panel.SettingPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Date: 2017/6/12
 * Time: 17:13
 *
 * @author Tian.Dong
 */
public class MainFrame extends JFrame {
    SettingPanel settingPanel = new SettingPanel();

    BattleInfoPanel battleInfoPanel = new BattleInfoPanel();


    public MainFrame() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("ShutdownHook");
                for (Map.Entry<Long, GameClient> entry : GameClientMap.getGameClientMap().entrySet()) {
                    GameClient gameClient = entry.getValue();
                    if (gameClient != null) {
                        gameClient.close();
                    }
                }
            }
        });
        this.setBounds(366, 28, 624, 590);

        this.setTitle("吞食天地DM");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);

        JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);

        tab.add(settingPanel, "设置");
        tab.add(battleInfoPanel, "战场");
        this.setLayout(new BorderLayout());
        this.add(tab, BorderLayout.CENTER);
    }


    public void refresh(GameTeam gameTeam,String str) {
        battleInfoPanel.refresh(gameTeam,str);
    }
}
