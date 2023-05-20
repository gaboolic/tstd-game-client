package tk.gbl;

import org.junit.Test;
import tk.gbl.show.ShowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/4/25
 * Time: 9:59
 *
 * @author Tian.Dong
 */
public class QingliuTest {

    /**
     *                       清流        ID
     * F4 44 0B 00 17 14 02 FC 2A 02 00 76 A2 00 00
     * ô  D                 ü  *        v  ¢
     * F4 44 0B 00 17 14 02 FC 2A 02 00 C4 79 01 00
     * ô  D                 ü  *        Ä  y
     * F4 44 0B 00 17 14 02 FC 2A 04 03 5D 46 00 00 //汗血
     * ô  D                 ü  *        ]  F
     * F4 44 0B 00 17 14 02 FC 2A 04 02 0D 56 00 00 //背包
     * ô  D                 ü  *           V
     * F4 44 0B 00 17 14 02 FC 2A 04 01 88 2B 00 00 //蒋济
     * ô  D                 ü  *          +
     */
    @Test
    public void test() {
        //汗血 18013 5D 46
        //背包 22029 0D 56
        //蒋济 11144 88 2B
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A6 AD BA B9 AF 51 87 AF AD DB 0F AD AD");//自己
        reqList.add("59 E9 A6 AD BA B9 AF 51 87 AF AD 69 D4 AC AD");//别人
        reqList.add("59 E9 A6 AD BA B9 AF 51 87 A9 AE F0 EB AD AD");//hanxue
        reqList.add("59 E9 A6 AD BA B9 AF 51 87 A9 AF A0 FB AD AD");//badou
        reqList.add("59 E9 A6 AD BA B9 AF 51 87 A9 AC 25 86 AD AD");//
        for (String str : reqList) {
            if (str.length() == 0) {
                continue;
            }
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void test2(){
        System.out.println(ShowUtil.show2("59 E9 A6 AD BA B9 AF 51 87 A9 AC 25 86 AD AD "));
      System.out.println(ShowUtil.show2("59 E9 A6 AD BA B9 AF 51 87 A9 AC 25 86 AD AD \n" +
              "59 E9 A6 AD BA B9 AF 51 87 A9 AF A0 FB AD AD\n" +
              "59 E9 A6 AD BA B9 AF 51 87 AF AD 94 0F AC AD \n" +
              "59 E9 A6 AD BA B9 AF 51 87 AF AD 9B 0F AC AD \n" +
              "59 E9 A6 AD BA B9 AF 51 87 AF AD 9B 0F AC AD "));
    }
}
