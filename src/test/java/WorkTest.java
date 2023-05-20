import org.junit.Test;
import tk.gbl.show.ShowUtil;

/**
 * Date: 2017/4/26
 * Time: 21:39
 *
 * @author Tian.Dong
 */
public class WorkTest {
    @Test
    public void testKuangtingAction(){
        String str = "59 E9 A9 AD B9 AC AF AD \n" +
                "59 E9 AF AD AB AF 59 E9 BC AD B9 AC AD AD AD AC AC AE AF AD AD AD AD AD AD 0A EB \n" +
                "59 E9 AB AD AC AC 96 D7 AD AD \n" +
                "59 E9 AB AD AC AC 91 D7 AD AD 59 E9 AB AD AC AC 90 D7 AD AD 59 E9 AB AD AC AC 93 D7 AD AD 59 E9 AB AD AC AC 92 D7 AD AD 59 E9 A4 AD B5 A5 D2 79 AD AD AF AD AC 59 E9 A4 AD B5 A5 B6 4A AD AD AF AD AC 59 E9 A4 AD B5 A5 F9 44 AD AD AF AD AC 59 E9 A4 AD B5 A5 BB 4A AD AD AF AD AC 59 E9 A4 AD B5 A5 0D 76 AD AD AF AD AC \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 BC AD B9 AC AD AD AD AF AC AE AF AD AD AD AD AD AD 05 EB \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 BC AD B9 AC AD AD AD AE AC AE AF AD AD AD AD AD AD 04 EB \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AB AD BB AE AF AD 15 A6 59 E9 AF AD B9 BD \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 A4 AD B5 A5 5D 6D AC AD AF AD AC 59 E9 A4 AD B5 A5 18 2F AD AD AF AD AC 59 E9 A4 AD B5 A5 F7 E0 AD AD AF AD AC 59 E9 A4 AD B5 A5 1A 2F AD AD AF AD AC 59 E9 A4 AD B5 A5 1B 2F AD AD AF AD AC \n" +
                "59 E9 BC AD B9 AC AD AD AD A8 AB AE AF AD AD AD AD AD AD AC AD \n" +
                "59 E9 AE AD B9 A4 B3 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 A8 AD B5 AC 59 85 AC 59 E9 AF AD B9 A0 \n" +
                "59 E9 AF AD B9 AB \n" +
                "59 E9 AF AD B9 A5 ";
        System.out.println(ShowUtil.show2(str));
        System.out.println();
        String str1 = "59 E9 AB AD AC AC 44 83 AC AD 59 E9 95 AD AE 44 E3 AD AD AF AF 10 AD AD 4E 83 5B AC 42 AC AD BE AD F6 FF 9C 8B B1 02 D0 B7 AB 7C E2 44 E6 98 84 BE F8 19 F5 81 F6 AE AD AC AD AD C8 AF AF 05 E3 06 EF 13 40 00 1A 59 E9 BF AD A8 AD 44 E3 AD AD 7C E2 44 E6 98 84 BE F8 19 F5 81 F6 59 E9 9B AD AE 47 E3 AD AD AF AE 14 AD AD 4E 83 5B AC 42 AC AD BE AD 6E 0D 82 8B B1 02 D0 B7 A8 7C E2 44 E6 98 84 BD F8 19 F5 AE AD AC AD AD C8 AF AF 15 05 07 4C 02 F9 6F 6A 59 E9 BD AD A8 AD 47 E3 AD AD 7C E2 44 E6 98 84 BD F8 19 F5 ";
        String str2 = "59 E9 AB AD AC AC 44 E3 AD AD 59 E9 AB AD AC AC 47 E3 AD AD 59 E9 83 AD AE 46 E3 AD AD AF AC 10 AD AD 4E 83 5B AC 42 AC AD BE AD 76 29 9E 8B B1 02 D0 B7 AE EB EE 98 84 20 F4 AE AD AC AD AD C8 AF AF 9C 9D 0B D3 59 E9 A1 AD A8 AD 46 E3 AD AD EB EE 98 84 20 F4 59 E9 95 AD AE 41 E3 AD AD AF AF 16 AD AD 4E 83 5B AC 42 AC AD BE AD 6E 0D 82 8B B1 02 D0 B7 AB 7D E2 4A E6 98 84 BD F8 18 F4 9E F6 AE AD AC AD AD C8 AF AF 07 4C 6C CE 0B 51 6D EC 59 E9 BF AD A8 AD 41 E3 AD AD 7D E2 4A E6 98 84 BD F8 18 F4 9E F6 59 E9 AB AD AC AC 46 E3 AD AD 59 E9 AB AD AC AC 41 E3 AD AD 59 E9 AB AD BB AE AF AD 15 A6 59 E9 AF AD B9 BD ";
        System.out.println(ShowUtil.show(str1));
        System.out.println(ShowUtil.show(str2));
    }
}
