package tk.gbl.util;

/**
 * Date: 2017/3/31
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class HexStringToBytesUtil {
    public static byte[] hexStringToBytes(String hexString) {
        String[] hexCharList = hexString.split(" ");
        byte[] bytes = new byte[hexCharList.length];
        for(int i=0;i<hexCharList.length;i++) {
            bytes[i] = (byte) Integer.parseInt(hexCharList[i], 16);
        }
        return bytes;
    }
}
