package tk.gbl.core.frame.widget;

import javax.swing.*;
import java.awt.*;

/**
 * Date: 2017/7/9
 * Time: 10:45
 *
 * @author Tian.Dong
 */
public class LoginWidget extends JPanel {
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton connectButton;

    public LoginWidget() {
        this.setVisible(true);
        this.setBounds(0, 0, 108, 82);
        this.setLayout(null);

        jLabel1 = new JLabel("账号");
        jLabel1.setBounds(4, 12, 32, 16);
        this.add(jLabel1);
        usernameField = new JTextField();
        usernameField.setBounds(32, 12, 72, 16);
        this.add(usernameField);

        jLabel2 = new JLabel("密码");
        jLabel2.setBounds(4, 32, 32, 14);
        this.add(jLabel2);

        passwordField = new JPasswordField();
        passwordField.setBounds(32, 32, 72, 16);
        this.add(passwordField);

        connectButton = new JButton("连接");
        connectButton.setBounds(4, 50, 96, 28);
        this.add(connectButton);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }
}
