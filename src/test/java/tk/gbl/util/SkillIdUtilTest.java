package tk.gbl.util;

import org.junit.Test;

/**
 * Date: 2017/5/2
 * Time: 18:43
 *
 * @author Tian.Dong
 */
public class SkillIdUtilTest {
    @Test
    public void test() {
        String name = SkillIdUtil.getSkillName(10000);
        System.out.println(name);
    }
}
