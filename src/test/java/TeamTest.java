import org.junit.Test;
import tk.gbl.show.ShowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/4/4
 * Time: 20:51
 *
 * @author Tian.Dong
 */
public class TeamTest {
    @Test
    public void testJoinTeam() {
        //59 E9 AB AD A0 AC E8 13 AC AD
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AB AD A0 AC E8 13 AC AD ");//加入队伍
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void test() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AA AD A0 AE AC E8 13 AC AD ");//接受加入队伍
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void test111() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AB AD A0 AC F0 6C AC AD ");
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    /**
     * * 115038申请入队
     * F4 44 06 00 0D 01 5E C1 01 00
     * <p/>
     * F4 44 0A 00 0D 05 5D C1 01 00 5E C1 01 00
     */
    @Test
    public void testFlow() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 AB AD A0 AC E9 13 AC AD ");
        reqList.add("59 E9 AF AD EF AE ");
        reqList.add("59 E9 AB AD A0 AC E8 13 AC AD ");//接收
        reqList.add("59 E9 AA AD A0 AE AC F3 6C AC AD ");
        reqList.add("59 E9 AB AD A0 A8 F3 6C AC AD ");
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testTeam() {
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A6 AD AB AC 5C 6D AC AD AE 5B AC FE AF ");
        reqList.add("59 E9 AB AD A0 AC F3 6C AC AD ");
        reqList.add("59 E9 A6 AD AB AC 5C 6D AC AD A8 F7 AF 1A AF ");
        reqList.add("59 E9 99 AD AE 7E 0E AD AD AF AE 65 AD AD 13 CC 07 AD 7B AC AD BC AD 9D A6 99 8B 9D 1A FF 95 A8 5E FD A6 E0 C9 EA 14 FE 60 F5 AD AD AD AD AD C8 AC AD 08 16 09 58 9E 94 59 E9 BD AD A8 AD 7E 0E AD AD 5E FD A6 E0 C9 EA 14 FE 60 F5 ");
        reqList.add("59 E9 AB AD AC AC 7E 0E AD AD ");
        reqList.add("59 E9 A6 AD AB AC 5C 6D AC AD AA 13 AF FE AF ");
        reqList.add("59 E9 A4 AD B5 A5 94 81 AD AD AF AD BE ");
        reqList.add("59 E9 AB AD A0 A8 F3 6C AC AD ");
        for (String str : reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }
}
