package tk.gbl.core.frame.constant;

/**
 * Date: 2017/7/11
 * Time: 16:16
 *
 * @author Tian.Dong
 */
public enum ButtonCommand {
    writeAccountConfig("writeAccountConfig"),
    readAccountConfig("readAccountConfig");



    private String command;

    ButtonCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
