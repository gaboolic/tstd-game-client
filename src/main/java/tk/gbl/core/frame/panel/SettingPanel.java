package tk.gbl.core.frame.panel;

import tk.gbl.core.frame.constant.ButtonCommand;
import tk.gbl.core.frame.constant.ColorConstant;
import tk.gbl.core.frame.listener.ButtonPressListener;
import tk.gbl.core.frame.widget.LoginWidget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Date: 2017/7/8
 * Time: 9:57
 *
 * @author Tian.Dong
 */
public class SettingPanel extends JPanel {

    ArrayList<LoginWidget> loginWidgetList = new ArrayList<>();

    public SettingPanel() {
        this.setBackground(ColorConstant.gray);
        this.setLayout(null);

        JButton readButton = new JButton("读配置");
        readButton.setBounds(4, 4, 46, 24);
        readButton.setMargin(new Insets(0, 0, 0, 0));
        readButton.setActionCommand(ButtonCommand.readAccountConfig.getCommand());
        readButton.addActionListener(new ButtonPressListener(this));
        this.add(readButton);

        JButton writeButton = new JButton("存配置");
        writeButton.setBounds(52, 4, 46, 24);
        writeButton.setMargin(new Insets(0, 0, 0, 0));
        writeButton.setActionCommand(ButtonCommand.writeAccountConfig.getCommand());
        writeButton.addActionListener(new ButtonPressListener(this));
        this.add(writeButton);

        JButton allConnectButton = new JButton("全部连接");
        allConnectButton.setBounds(104, 4, 64, 24);
        allConnectButton.setMargin(new Insets(0, 0, 0, 0));
        this.add(allConnectButton);

        JButton allCloseButton = new JButton("全部断开");
        allCloseButton.setBounds(174, 4, 64, 24);
        allCloseButton.setMargin(new Insets(0, 0, 0, 0));
        allCloseButton.getMargin();
        this.add(allCloseButton);

        for (int i = 0; i < 5; i++) {
            LoginWidget loginWidget1 = new LoginWidget();
            loginWidget1.setLocation(i * (loginWidget1.getWidth() + 10) + 10, 42);
            loginWidgetList.add(loginWidget1);
            this.add(loginWidget1);
        }
        loginWidgetList.get(0).getUsernameField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String startUsername = loginWidgetList.get(0).getUsernameField().getText();
                if (checkUsernameHaveEn(startUsername)) {
                    startUsername = startUsername.substring(2);
                }
                long startUserId = Long.parseLong(startUsername);
                for (int i = 1; i < 5; i++) {
                    LoginWidget loginWidget1 = loginWidgetList.get(i);
                    if (loginWidget1.getUsernameField().getText() == null || loginWidget1.getUsernameField().getText().equals("")) {
                        long userId = startUserId + i;
                        loginWidget1.getUsernameField().setText(userId + "");
                    }
                }
            }
        });
    }

    private boolean checkUsernameHaveEn(String startUsername) {
        startUsername = startUsername.toLowerCase();
        for (int i = 0; i < 2; i++) {
            char ch = startUsername.charAt(i);
            if (ch > 'a' && ch < 'z') {
                return true;
            }
        }
        return false;
    }

    public ArrayList<LoginWidget> getLoginWidgetList() {
        return loginWidgetList;
    }

    public void setLoginWidgetList(ArrayList<LoginWidget> loginWidgetList) {
        this.loginWidgetList = loginWidgetList;
    }
}
