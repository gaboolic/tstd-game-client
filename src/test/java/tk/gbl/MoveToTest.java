package tk.gbl;

import org.junit.Test;
import tk.gbl.show.ShowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/4/4
 * Time: 12:01
 *
 * @author Tian.Dong
 */
public class MoveToTest {
    @Test
    public void testMoveTo(){
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A4 AD AB AC A9 AD A9 A9 AF AD AD ");
        reqList.add("59 E9 A4 AD AB AC A9 8D AE F5 AF AD AD ");
        reqList.add("59 E9 A4 AD AB AC A9 4E AE A0 AF AD AD ");
        reqList.add("59 E9 A4 AD AB AC A9 8D AE F5 AF AD AD ");

        for(String str:reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }
}
