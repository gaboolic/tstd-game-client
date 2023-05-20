package tk.gbl.constant;

/**
 101.大将军*123.59.22.166
 104.大都督*123.59.22.151
 * <p/>
 * 101.风云再起*123.59.201.27
 * 102.战神世界*123.59.201.28
 * 103.久别重逢*123.59.201.29
 * <p/>
 * <p/>
 * Date: 2017/6/12
 * Time: 15:28
 *
 * @author Tian.Dong
 */
public class ServerInfo {
    public static String getServerIp(String serverPrefix, int serverIndex) {
        if(serverPrefix.toLowerCase().equals("wp")) {
            if (serverIndex == 1) {
                return "123.59.22.166";
            }
            if (serverIndex == 2) {
                return "123.59.22.151";
            }
        } else if(serverPrefix.toLowerCase().equals("wn")) {
            if (serverIndex == 1) {
                return "123.59.201.27";
            }
            if (serverIndex == 2) {
                return "123.59.201.28";
            }
            if (serverIndex == 3) {
                return "123.59.201.29";
            }
        }
        return "123.59.201.31";
    }
}
