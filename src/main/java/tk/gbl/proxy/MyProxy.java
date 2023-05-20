package tk.gbl.proxy;

import org.apache.log4j.net.SocketServer;
import tk.gbl.constant.GameConfigType;
import tk.gbl.core.GameClient;
import tk.gbl.core.listener.*;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.HexStringToBytesUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Date: 2017/5/17
 * Time: 10:34
 *
 * @author Tian.Dong
 */
public class MyProxy extends JFrame implements ActionListener {

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("ShutdownHook");
            }
        });
        Socket tsSocket = new Socket("123.59.201.31", 6414);
        ServerSocket serverSocket = new ServerSocket(6414);
        new MyProxy();
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        gameClient.getGameConfig().setGameConfigType(GameConfigType.work);
        gameClient.setInGame(true);
        gameClient.setUserId(114021);
//        gameClient.setUserId(42094);
        gameClient.setIndex(5);
        gameClient.setConsoleOutput(true);
        gameClient.setFileOutput(true);
        gameClient.getListenerMap().put("loginSuccess", new LoginSuccessListener());
        gameClient.getListenerMap().put("fightStart", new FightStartListener());
        gameClient.getListenerMap().put("fightEnd", new FightEndListener());
        gameClient.getListenerMap().put("roundStart", new RoundStartListener());
        gameClient.getListenerMap().put("joinTeam", new JoinTeamRequestListener());
        gameClient.getListenerMap().put("joinTeamConfirm", new JoinTeamConfirmListener());
        gameClient.getListenerMap().put("joinTeamSuccess", new JoinTeamSuccessListener());
        gameClient.getListenerMap().put("teamDismiss", new TeamDismissListener());

        gameClient.getListenerMap().put("tradeRequest", new TradeItemListener());
        gameClient.getListenerMap().put("tradePetRequest", new TradePetListener());

        gameClient.getListenerMap().put("chat", new ChatListener());
        new ServerThread(socket, tsSocket, gameClient).start();
    }

    JTextArea commandText;
    JTextArea responseText;

    public MyProxy() {
        this.setBounds(200, 100, 800, 470);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        init();
        this.setVisible(true);
    }

    public void init() {
        JLabel portLabel = new JLabel();
        portLabel.setText("命令");
        portLabel.setBounds(100, 50, 100, 50);
        this.add(portLabel);

        commandText = new JTextArea();
        commandText.setText("");
        commandText.setBounds(200, 50, 500, 50);
        this.add(commandText);

        JButton startButton = new JButton();
        startButton.setText("发送");
        startButton.setBounds(700, 50, 100, 50);
        startButton.setActionCommand("send");
        startButton.addActionListener(this);
        this.add(startButton);


        JLabel resLabel = new JLabel();
        resLabel.setText("res");
        resLabel.setBounds(100, 150, 100, 50);
        this.add(portLabel);

        responseText = new JTextArea();
        responseText.setText("");
        responseText.setBounds(200, 150, 500, 50);
        this.add(responseText);

        JButton resButton = new JButton();
        resButton.setText("res");
        resButton.setBounds(700, 150, 100, 50);
        resButton.setActionCommand("response");
        resButton.addActionListener(this);
        this.add(resButton);
    }

    static Socket socket;
    static GameClient gameClient = new GameClient();


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("send")) {
            String command = commandText.getText();
            System.out.println("发送命令 " + command);
            gameClient.execute(command);
            commandText.setText("");
        }
        if (e.getActionCommand().equals("response")) {
            String responseStr = responseText.getText();
            byte[] bytes = HexStringToBytesUtil.hexStringToBytes(responseStr);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] ^ 0xAD);
            }
            String aaa = ShowUtil.show(bytes);
            System.out.println("!!!!  " + aaa);
            try {
                socket.getOutputStream().write(bytes);
                socket.getOutputStream().flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
