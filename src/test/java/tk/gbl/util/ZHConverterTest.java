package tk.gbl.util;

/**
 * Date: 2017/4/1
 * Time: 16:43
 *
 * @author Tian.Dong
 */
public class ZHConverterTest {
    public static void main(String[] args){
        String traditionalStr = ZHConverter.convert("嗷大猫", ZHConverter.TRADITIONAL);
        System.out.println(traditionalStr);
    }
}
