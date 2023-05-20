import org.junit.Test;
import tk.gbl.show.ShowUtil;

/**
 * Date: 2017/5/6
 * Time: 13:29
 *
 * @author Tian.Dong
 */
public class ShentinglingShowTest {
    @Test
    public void test(){
      String str1 = "59 E9 AF AD B9 AB \n" +
              "59 E9 A7 AD 9F AC AE AF AE AF C4 EF C7 59 \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 A7 AD 9F AC AE AF AE AF C4 EF 0F 63 \n" +
              "59 E9 A7 AD 9F AC AE AF AE AF C4 EF B5 BD \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 AF AD B9 AB \n" +
              "59 E9 AF AD B9 AB ";

        String str2 = "59 E9 A4 AD AB AC AF 67 AD AE AF B3 9B \n" +
                "59 E9 A9 AD B9 A9 AC AD \n" +
                "59 E9 A9 AD B9 A5 AC AD \n" +
                "59 E9 AF AD BA 9D \n" +
                "59 E9 AF AD A1 AC \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 A9 AD B9 A5 A9 AD \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 A7 AD 9F AC AE AF AE AF C4 EF C9 77 \n" +
                "59 E9 A7 AD 9F AC AE AF AE AF C4 EF 91 87 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 AB ";

        System.out.println(ShowUtil.show2(str1));
        System.out.println();
        System.out.println(ShowUtil.show2(str2));
    }
}
