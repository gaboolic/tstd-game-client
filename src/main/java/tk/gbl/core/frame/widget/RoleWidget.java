package tk.gbl.core.frame.widget;

import tk.gbl.bean.RoleInfo;
import tk.gbl.core.frame.constant.ColorConstant;
import tk.gbl.util.NpcIdUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Date: 2017/7/7
 * Time: 17:48
 *
 * @author Tian.Dong
 */
public class RoleWidget extends JPanel {
    private JLabel positionLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel levelLabel = new JLabel();
    private ProgressWidget hpWidget = new ProgressWidget(ColorConstant.hp);
    private ProgressWidget mpWidget = new ProgressWidget(ColorConstant.mp);

    public RoleWidget() {
        this.setBackground(ColorConstant.white);
        this.setBounds(0, 0, 118, 48);
        this.setVisible(true);
        this.setLayout(null);
        positionLabel.setText("0,0");
        positionLabel.setBounds(0, 0, 30, 16);
        this.add(positionLabel);
        nameLabel.setText("卑枸");
        nameLabel.setBounds(30, 0, 86, 16);
        this.add(nameLabel);

        levelLabel.setText("15级");
        levelLabel.setBounds(0, 34, 30, 14);
        this.add(levelLabel);

        hpWidget.setCurrent(500);
        hpWidget.setMax(673);
        hpWidget.setForegroundColor(ColorConstant.hp);
        hpWidget.setBackgroundColor(ColorConstant.hpEmpty);
        hpWidget.setBorderColor(Color.red);
        hpWidget.setLocation(32, 16);
        hpWidget.refresh();
        this.add(hpWidget);

        mpWidget.setCurrent(155);
        mpWidget.setMax(206);
        mpWidget.setForegroundColor(ColorConstant.mp);
        mpWidget.setBackgroundColor(ColorConstant.mpEmpty);
        mpWidget.setBorderColor(Color.blue);
        mpWidget.setLocation(32, 32);
        mpWidget.refresh();
        this.add(mpWidget);
    }


    public JLabel getPositionLabel() {
        return positionLabel;
    }

    public void setPositionLabel(JLabel positionLabel) {
        this.positionLabel = positionLabel;
    }

    public void refresh(RoleInfo roleInfo) {
        if(!this.isVisible()) {
            return;
        }
        if (roleInfo == null) {
            return;
        }
        if (roleInfo.getType() == 1) {
            this.setBackground(ColorConstant.earth);
        } else if (roleInfo.getType() == 2) {
            this.setBackground(ColorConstant.water);
        } else if (roleInfo.getType() == 3) {
            this.setBackground(ColorConstant.fire);
        } else if (roleInfo.getType() == 4) {
            this.setBackground(ColorConstant.wind);
        } else if (roleInfo.getType() == 5) {
            this.setBackground(ColorConstant.light);
        } else if (roleInfo.getType() == 6) {
            this.setBackground(ColorConstant.dark);
        } else {
            this.setBackground(ColorConstant.white);
        }
        String name = NpcIdUtil.getName(roleInfo.getId());
        nameLabel.setText(name);
        levelLabel.setText(roleInfo.getLevel() + "级");
        hpWidget.setCurrent(roleInfo.getHp());
        hpWidget.setMax(roleInfo.getHpMax());
        hpWidget.refresh();
        mpWidget.setCurrent(roleInfo.getMp());
        mpWidget.setMax(roleInfo.getMpMax());
        mpWidget.refresh();
    }
}
