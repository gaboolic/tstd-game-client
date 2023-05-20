package tk.gbl.mainenter;

import tk.gbl.show.AddZeroUtil;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.HexStringToBytesUtil;
import tk.gbl.util.MultiByteToIntUtil;
import tk.gbl.util.ZHConverter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

/**
 * Date: 2017/4/21
 * Time: 16:51
 *
 * @author Tian.Dong
 */
public class SocketPacketAnalysis extends JFrame implements ActionListener {

    JTextArea originText;
    JTextArea outputText;

    JTextArea numberText;
    JTextArea numberOutputText;

    JTextArea chineseText;
    JTextArea chineseHexStringText;

    public static void main(String[] args) {
        new SocketPacketAnalysis();
    }

    public SocketPacketAnalysis() {
        this.setBounds(200, 100, 800, 470);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        init();
        this.setVisible(true);
    }

    public void init() {
        JLabel portLabel = new JLabel();
        portLabel.setText("原始封包");
        portLabel.setBounds(100, 50, 100, 50);
        this.add(portLabel);

        originText = new JTextArea();
        originText.setText("");
        originText.setBounds(200, 50, 500, 50);
        this.add(originText);

        JLabel pathLabel = new JLabel();
        pathLabel.setText("分析结果");
        pathLabel.setBounds(100, 150, 100, 50);
        this.add(pathLabel);

        outputText = new JTextArea();
        outputText.setText("");
        outputText.setBounds(200, 150, 500, 50);
        this.add(outputText);

        JLabel numberLabel = new JLabel();
        numberLabel.setText("原始数字");
        numberLabel.setBounds(100, 250, 100, 50);
        this.add(numberLabel);

        numberText = new JTextArea();
        numberText.setText("");
        numberText.setBounds(200, 250, 100, 50);
        this.add(numberText);

        JLabel outputNumberLabel = new JLabel();
        outputNumberLabel.setText("十六进制");
        outputNumberLabel.setBounds(500, 250, 100, 50);
        this.add(outputNumberLabel);

        numberOutputText = new JTextArea();
        numberOutputText.setText("");
        numberOutputText.setBounds(600, 250, 100, 50);
        this.add(numberOutputText);


        JLabel chineseLabel = new JLabel();
        chineseLabel.setText("中文");
        chineseLabel.setBounds(100, 350, 100, 50);
        this.add(chineseLabel);

        chineseText = new JTextArea();
        chineseText.setText("");
        chineseText.setBounds(200, 350, 100, 50);
        this.add(chineseText);

        JLabel chineseHexStringLabel = new JLabel();
        chineseHexStringLabel.setText("十六进制");
        chineseHexStringLabel.setBounds(500, 350, 100, 50);
        this.add(chineseHexStringLabel);

        chineseHexStringText = new JTextArea();
        chineseHexStringText.setText("");
        chineseHexStringText.setBounds(600, 350, 100, 50);
        this.add(chineseHexStringText);


        JButton startButton = new JButton();
        startButton.setText("分析");
        startButton.setBounds(700, 50, 100, 50);
        startButton.setActionCommand("start");
        startButton.addActionListener(this);
        this.add(startButton);

        JButton rightButton = new JButton();
        rightButton.setText("----->");
        rightButton.setBounds(350, 250, 100, 24);
        rightButton.setActionCommand("right");
        rightButton.addActionListener(this);
        this.add(rightButton);

        JButton leftButton = new JButton();
        leftButton.setText("<-----");
        leftButton.setBounds(350, 275, 100, 24);
        leftButton.setActionCommand("left");
        leftButton.addActionListener(this);
        this.add(leftButton);

        JButton chineseRightButton = new JButton();
        chineseRightButton.setText("----->");
        chineseRightButton.setBounds(350, 350, 100, 24);
        chineseRightButton.setActionCommand("chineseRight");
        chineseRightButton.addActionListener(this);
        this.add(chineseRightButton);

        JButton chineseLeftButton = new JButton();
        chineseLeftButton.setText("<-----");
        chineseLeftButton.setBounds(350, 375, 100, 24);
        chineseLeftButton.setActionCommand("chineseLeft");
        chineseLeftButton.addActionListener(this);
        this.add(chineseLeftButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("start")) {
            String str = originText.getText().trim();
            String output = ShowUtil.show2(str);
            outputText.setText(output);
        }

        if (e.getActionCommand().equals("right")) {
            String str = numberText.getText().trim();
            int number = Integer.parseInt(str);
            int a = ((number >> 0) & 0xFF);
            int b = ((number >> 8) & 0xFF);
            int c = ((number >> 16) & 0xFF);
            int d = ((number >> 24) & 0xFF);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(AddZeroUtil.addZero(Integer.toHexString(a).toUpperCase(), 2));
            stringBuilder.append(" ");
            stringBuilder.append(AddZeroUtil.addZero(Integer.toHexString(b).toUpperCase(), 2));
            stringBuilder.append(" ");
            if (c != 0 || d != 0) {
                stringBuilder.append(AddZeroUtil.addZero(Integer.toHexString(c).toUpperCase(), 2));
                stringBuilder.append(" ");
                stringBuilder.append(AddZeroUtil.addZero(Integer.toHexString(d).toUpperCase(), 2));
            }
            numberOutputText.setText(stringBuilder.toString());
        }

        if (e.getActionCommand().equals("left")) {
            String str = numberOutputText.getText().trim();
            long number = 0;
            if (str.split(" ").length == 1) {
                int a = Integer.parseInt(str.split(" ")[0], 16);
                number = a;
            }
            if (str.split(" ").length == 2) {
                int a = Integer.parseInt(str.split(" ")[0], 16);
                int b = Integer.parseInt(str.split(" ")[1], 16);
                number = MultiByteToIntUtil.from(a, b);
            }
            if (str.split(" ").length == 3) {
                int a = Integer.parseInt(str.split(" ")[0], 16);
                int b = Integer.parseInt(str.split(" ")[1], 16);
                int c = Integer.parseInt(str.split(" ")[2], 16);
                int d = 0;
                number = MultiByteToIntUtil.from(a, b, c, d);
            }
            if (str.split(" ").length == 4) {
                int a = Integer.parseInt(str.split(" ")[0], 16);
                int b = Integer.parseInt(str.split(" ")[1], 16);
                int c = Integer.parseInt(str.split(" ")[2], 16);
                int d = Integer.parseInt(str.split(" ")[3], 16);
                number = MultiByteToIntUtil.from(a, b, c, d);
            }
            numberText.setText(String.valueOf(number));
        }

        if (e.getActionCommand().equals("chineseRight")) {
            String str = chineseText.getText().trim();
            str = ZHConverter.convert(str, ZHConverter.TRADITIONAL);
            chineseText.setText(str);
            byte[] bytes = null;
            try {
                bytes = str.getBytes("big5");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
                return;
            }
            int[] data = ByteArrayToIntArrayUtil.transform(bytes, bytes.length);
            String output = ShowUtil.showOrigin(data);
            chineseHexStringText.setText(output);
        }

        if (e.getActionCommand().equals("chineseLeft")) {
            String str = chineseHexStringText.getText().trim();
            byte[] bytes = HexStringToBytesUtil.hexStringToBytes(str);
            try {
                String chinese = new String(bytes, "big5");
                chineseText.setText(chinese);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
    }


}
