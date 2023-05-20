package tk.gbl.core.frame.listener;

import tk.gbl.core.frame.constant.ButtonCommand;
import tk.gbl.core.frame.panel.SettingPanel;
import tk.gbl.core.frame.widget.LoginWidget;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Date: 2017/7/11
 * Time: 16:15
 *
 * @author Tian.Dong
 */
public class ButtonPressListener implements ActionListener {

    SettingPanel settingPanel;

    public ButtonPressListener(SettingPanel settingPanel) {
        this.settingPanel = settingPanel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(ButtonCommand.writeAccountConfig.getCommand())) {
            //弹出文件选择框
            JFileChooser chooser = new JFileChooser(new File("account"));

            //下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
            int option = chooser.showSaveDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {    //假如用户选择了保存
                File file = chooser.getSelectedFile();
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    ArrayList<LoginWidget> loginWidgets = settingPanel.getLoginWidgetList();
                    for (int i = 0; i < loginWidgets.size(); i++) {
                        LoginWidget loginWidget = loginWidgets.get(i);
                        if (loginWidget.getUsernameField().getText() == null || loginWidget.getUsernameField().getText().equals("")
                                || loginWidget.getPasswordField().getPassword() == null || loginWidget.getPasswordField().getPassword().length == 0) {
                            continue;
                        }
                        String username = loginWidget.getUsernameField().getText();
                        String password = new String(loginWidget.getPasswordField().getPassword());
                        bufferedWriter.write(String.valueOf(i));
                        bufferedWriter.write("|" + username);
                        bufferedWriter.write("|" + password);
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    System.err.println("IO异常");
                    e.printStackTrace();
                }
            }
        } else if (event.getActionCommand().equals(ButtonCommand.readAccountConfig.getCommand())) {
            //弹出文件选择框
            JFileChooser chooser = new JFileChooser(new File("account"));

            //下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
            int option = chooser.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {    //假如用户选择了保存
                File file = chooser.getSelectedFile();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] params = line.split("\\|");
                        int index = Integer.parseInt(params[0]);
                        String username = params[1];
                        String password = params[2];
                        LoginWidget loginWidget = settingPanel.getLoginWidgetList().get(index);
                        loginWidget.getUsernameField().setText(username);
                        loginWidget.getPasswordField().setText(password);
                    }
                    bufferedReader.close();
                } catch (IOException e) {
                    System.err.println("IO异常");
                    e.printStackTrace();
                }
            }
        }
    }
}
