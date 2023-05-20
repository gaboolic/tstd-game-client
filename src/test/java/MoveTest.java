import org.junit.Test;
import tk.gbl.show.ShowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/3/31
 * Time: 13:55
 *
 * @author Tian.Dong
 */
public class MoveTest {
    @Test
    public void test1() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD B9 A5 AC AD ");
        reqList.add("59 E9 AF AD BA 9D ");
        reqList.add("59 E9 AF AD A1 AC ");
        reqList.add("59 E9 AF AD B9 AB ");
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void test2() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD B9 A5 AC AD ");
        reqList.add("59 E9 AF AD BA 9D ");
        reqList.add("59 E9 AF AD A1 AC ");
        reqList.add("59 E9 AF AD B9 AB ");
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void test4() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD B9 A5 A9 AD ");
        reqList.add("59 E9 AF AD BA 9D ");
        reqList.add("59 E9 AF AD A1 AC ");
        reqList.add("59 E9 AF AD B9 AB ");
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testRes() {
        List<String> resList = new ArrayList<>();
        resList.add("59 E9 AF AD B9 AA 59 E9 AB AD B9 8F 4F 83 C5 82 59 E9 A0 AD A1 0D 17 AC AD C5 82 DF AC 03 AC A9 AD ");
        resList.add("59 E9 A2 AD BB A9 AC AD AD AD E3 AF ED AC AD AD AD AD AD 59 E9 AA AD EC AE 0D 17 AC AD AC 59 E9 AF AD A8 A9 59 E9 AF AD B9 A5 ");
        for (String str : resList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testRes2() {
        List<String> resList = new ArrayList<>();
        resList.add("59 E9 AF AD B9 AA 59 E9 AB AD B9 8F 4F 83 4E 83 59 E9 A0 AD A1 0D 17 AC AD 4E 83 17 A9 93 AE A8 AD 59 E9 A6 AD AA 2A 6D AC AD 4E 83 5B AC 42 AC 59 E9 A3 AD A8 AD 2A 6D AC AD 1E E2 5B E1 BC 8A 2F FA ");
        resList.add("59 E9 93 AF BB A9 AC AD AD AD 0B A9 5D AD AD AD AD AD AD AF AD AD AD 0B A9 4D AC AD AD AD AD AD AE AD AD AD 9F AD 09 AC AD AD AD AD AD A9 AD AD AD DB AF 65 AD AD AD AD AD AD A8 AD AD AD 33 AF 5D AD AD AD AD AD AD AB AD AD AD F7 AD F9 AC AD AD AD AD AD AA AD AD AD E3 AF 5D AD AD AD AD AD AD A5 AD AD AD 1F AF F1 AE AD AD AD AD AD A4 AD AD AD B7 A9 15 AC AD AD AD AD AD A7 AD AD AD CF AF DD AE AD AD AD AD AD A6 AD AD AD 3F A9 11 AF AD AD AD AD AD A1 AD AD AD 27 AF C9 AD AD AD AD AD AD A0 AD AD AD F3 AC 49 AF AD AD AD AD AD A3 AD AD AD BF AF E5 AE AD AD AD AD AD A2 AD AD AD 8F AC 71 AD AD AD AD AD AD BD AD AD AD EB AD E9 AF AD AD AD AD AD BC AD AD AD 27 AF DD AE AD AD AD AD AD BF AD AD AD 73 AE F9 AC AD AD AD AD AD BE AD AD AD B3 AD FD AD AD AD AD AD AD B9 AD AD AD E3 AF D5 AD AD AD AD AD AD B8 AD AD AD 07 AD 11 AF AD AD AD AD AD BB AD AD AD 67 AE 0D AD AD AD AD AD AD BA AD AD AD 83 A9 B5 AC AD AD AD AD AD B5 AD AD AD 6F AC 0D AD AD AD AD AD AD B4 AD AD AD 4B AD 61 AC AF AD AD AD AD B7 AD AD AD 23 AE 65 AD AD AD AD AD AD B6 AD AD AD 23 AE 65 AD AD AD AD AD AD B1 AD AD AD C7 A9 D5 AD AD AD AD AD AD B0 AD AD AD 83 A9 21 AD AD AD AD AD AD B3 AD AD AD 6F AC 0D AD AD AD AD AD AD B2 AD AD AD 9F A8 C5 AC AD AD AD AD AD 8D AD AD AD 9F A8 C5 AC AD AD AD AD AD 8C AD AD AD 9F A8 C5 AC AD AD AD AD AD 8F AD AD AD 9F A8 C5 AC AD AD AD AD AD 8E AD AD AD 6F AC 0D AD AD AD AD AD AD 89 AD AD AD 9F AD A5 AF AD AD AD AD AD 88 AD AD AD 57 AD E9 AF AD AD AD AD AD 8B AD AD AD 0B A9 C1 AF AD AD AD AD AD 8A AD AD AD 27 AF 4D AC AD AD AD AD AD 85 AD AD AD 47 AC 71 AD AD AD AD AD AD 84 AD AD AD 6F AC 8D AE AD AD AD AD AD 87 AD AD AD AF AE 99 AE AD AD AD AD AD 86 AD AD AD 63 A9 55 AF AD AD AD AD AD 81 AD AD AD 0B A9 49 AF AD AD AD AD AD 59 E9 AA AD EC AE 0D 17 AC AD AC 59 E9 AF AD A8 A9 59 E9 AF AD B9 A5 ");
        resList.add("59 E9 A8 AD BB AB B4 AD AF ");
        resList.add("59 E9 A4 AD B5 A5 D5 14 AC AD AF AD AF ");
        for (String str : resList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 邺城广场到邺城王宫
     */
    @Test
    public void testYechengguangchangToYechengwanggong() {
        List<String> resList = new ArrayList<>();
        resList.add("59 E9 A4 AD AB AC AE 17 AC 46 A8 AC 3A ");
        resList.add("59 E9 A9 AD B9 A9 AF AD ");
        resList.add("59 E9 A9 AD B9 A5 AF AD ");
        resList.add("59 E9 AF AD BA 9D ");
        resList.add("59 E9 AF AD A1 AC ");
        resList.add("59 E9 AF AD B9 AB ");
        for (String str : resList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 邺城王宫到邺城大街
     */
    @Test
    public void testYechengwanggongToYechengdajie() {
        List<String> resList = new ArrayList<>();
        resList.add("59 E9 A4 AD AB AC AF BB AD 1E AC AC 89 ");
        resList.add("59 E9 A9 AD B9 A9 AC AD ");
        resList.add("59 E9 A9 AD B9 A5 AC AD ");
        resList.add("59 E9 AF AD BA 9D ");
        resList.add("59 E9 AF AD A1 AC  ");
        resList.add("59 E9 AF AD B9 AB ");
        for (String str : resList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * 邺城大街到邺城城门
     */
    @Test
    public void testYechengdajieToYechengchengmen() {
        List<String> resList = new ArrayList<>();
        resList.add("59 E9 A4 AD AB AC AF BB AD 76 AC AC AF ");
        resList.add("59 E9 A9 AD B9 A9 AC AD ");

        for (String str : resList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testMoveReq() {
        List<String> resList = new ArrayList<>();
        resList.add("59 E9 A9 AD B9 A5 AF AD ");
        resList.add("59 E9 AF AD BA 9D ");
        resList.add("59 E9 AF AD A1 AC ");
        resList.add("59 E9 AF AD B9 AB ");

        resList.add("59 E9 A9 AD B9 A5 AF AD ");
        resList.add("59 E9 AF AD BA 9D ");
        resList.add("59 E9 AF AD A1 AC ");
        resList.add("59 E9 AF AD B9 AB ");

        resList.add("59 E9 A9 AD B9 A5 AE AD ");
        resList.add("59 E9 AF AD BA 9D ");
        resList.add("59 E9 AF AD A1 AC ");
        resList.add("59 E9 AF AD B9 AB ");


        for (String str : resList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }
}
