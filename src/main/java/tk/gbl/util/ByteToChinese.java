package tk.gbl.util;

import tk.gbl.show.AddZeroUtil;

import java.io.UnsupportedEncodingException;

/**
 * Date: 2017/3/30
 * Time: 21:40
 *
 * @author Tian.Dong
 */
public class ByteToChinese {
    public static String byteToChinese(String hexString) throws UnsupportedEncodingException {
        String[] hexCharList = hexString.split(" ");
        byte[] bytes = new byte[hexCharList.length];
        for(int i=0;i<hexCharList.length;i++) {
            bytes[i] = (byte) Integer.parseInt(hexCharList[i], 16);
        }
        return new String(bytes,"big5");
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(ByteToChinese.byteToChinese("AF 7D 1A 1C AF 7D"));
    }
}
