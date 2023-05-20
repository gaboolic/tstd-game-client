import tk.gbl.core.Converter;
import tk.gbl.show.AddZeroUtil;

import java.util.Map;

/**
 * Date: 2017/3/24
 * Time: 19:07
 *
 * @author Tian.Dong
 */
public class ConverterTest {
    public static void main(String[] args) {
        for (Map.Entry<String, String> entry : Converter.passwordCharMap.entrySet()) {
            int number = entry.getKey().charAt(0);
            int mNumber = Integer.parseInt(entry.getValue(), 16);

            // 10101101
//            number = number ^ 0xAD;
            String binaryString = AddZeroUtil.addZero(Integer.toBinaryString(number), 8);
//            binaryString = fan(binaryString, 0);
//            binaryString = fan(binaryString, 2);
//            binaryString = fan(binaryString, 4);
//            binaryString = fan(binaryString, 5);
//            binaryString = fan(binaryString, 7);
            System.out.print(binaryString);
            System.out.print(" ");
        }
        System.out.println();
        for (Map.Entry<String, String> entry : Converter.passwordCharMap.entrySet()) {
            int number = entry.getKey().charAt(0);
            int mNumber = Integer.parseInt(entry.getValue(), 16);
            mNumber = mNumber ^ 0xAD;
            String binaryString = AddZeroUtil.addZero(Integer.toBinaryString(mNumber), 8);
            System.out.print(binaryString);
            System.out.print(" ");
        }

    }

    public static String fan(String str, int index) {
        String code = "";
        if (str.charAt(index) == '0') {
            code = "1";
        } else {
            code = "0";
        }
        return str.substring(0, index) + code + str.substring(index + 1);
    }
}
