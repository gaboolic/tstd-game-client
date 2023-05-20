package tk.gbl.core.frame;

import tk.gbl.core.GameClient;
import tk.gbl.core.GameClientMap;

import javax.swing.*;
import java.util.Map;

/**
 * Date: 2017/6/12
 * Time: 17:13
 *
 * @author Tian.Dong
 */
public class CloseMainFrame extends JFrame {

    public CloseMainFrame(String windowName) {
        this.setTitle(windowName);
        this.setBounds(1000, 340, 200, 200);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        this.setVisible(true);
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
    }
    public CloseMainFrame() {
        this("");
    }

    public static void main(String[] args){
        new CloseMainFrame();
    }
}
