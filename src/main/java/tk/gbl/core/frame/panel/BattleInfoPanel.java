package tk.gbl.core.frame.panel;

import tk.gbl.bean.RoleInfo;
import tk.gbl.core.GameClient;
import tk.gbl.core.GameTeam;
import tk.gbl.core.frame.constant.ColorConstant;
import tk.gbl.core.frame.widget.RoleWidget;
import tk.gbl.util.PathFindingUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Date: 2017/7/8
 * Time: 9:52
 *
 * @author Tian.Dong
 */
public class BattleInfoPanel extends JPanel {

    JLabel mapLabel = new JLabel();
    RoleWidget[][] roleWidgets = new RoleWidget[4][5];
    StringBuilder fightInfoString = new StringBuilder();
    JTextArea jtp = new JTextArea();
    JScrollPane jsp;

    public BattleInfoPanel() {
        this.setBackground(ColorConstant.gray);
        this.setLayout(null);
        mapLabel.setText("12001(涿郡城门)");
        mapLabel.setBounds(0, 0, 100, 20);
        this.add(mapLabel);
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 5; column++) {
                RoleWidget roleWidget = new RoleWidget();
//                roleWidget.setVisible(false);
                roleWidget.getPositionLabel().setText(row + "," + column);
                roleWidgets[row][column] = roleWidget;
                this.add(roleWidget);
                if (row == 0 || row == 1) {
                    roleWidget.setLocation((roleWidget.getWidth() + 2) * column + 2, (roleWidget.getHeight() + 2) * row + 22);
                } else {
                    roleWidget.setLocation((roleWidget.getWidth() + 2) * column + 2, (roleWidget.getHeight() + 2) * row + 50);
                }
            }
        }
        jtp.setText(fightInfoString.toString());
        jsp = new JScrollPane(jtp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //VERTICAL 表示垂直的  HORIZONTAL 表示水平的
        //SCROLLBAR_AS_NEEDED 表示需要的时候,就显示滚动条
        //SCROLLBAR_ALWAYS    表示滚动条一直显示
        //SCROLLBAR_NEVER     表示滚动条从不显示
        jsp.setBackground(Color.black);
        jsp.setBounds(5, 250, 590, 270);
        add(jsp);
    }

    public void refresh(GameTeam gameTeam, String str) {
        if(!this.isVisible()) {
            return;
        }
        GameClient teamLeader = gameTeam.getTeamLeader();
        int currentMapId = teamLeader.getCurrentMapId();
        mapLabel.setText(currentMapId + "(" + PathFindingUtil.getMapName(currentMapId) + ")");
        RoleInfo[][] roleInfos = teamLeader.getFightInfo().getRoleArray();

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 5; column++) {
                RoleInfo roleInfo = roleInfos[row][column];
                RoleWidget roleWidget = roleWidgets[row][column];
                if (roleInfo == null) {
                    roleWidget.setVisible(false);
                    continue;
                }
                if (!roleWidget.isVisible()) {
                    roleWidget.setVisible(true);
                }
                roleWidget.refresh(roleInfo);
            }
        }
        if (str != null) {
            if (fightInfoString.length() > 2048) {
                fightInfoString.delete(0, 2048);
            }
            fightInfoString.append(str);
            fightInfoString.append("\r\n");
            jtp.setText(fightInfoString.toString());
            jtp.setCaretPosition(jtp.getText().length());
        }
    }
}
