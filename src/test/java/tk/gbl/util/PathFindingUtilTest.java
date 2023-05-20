package tk.gbl.util;

import org.junit.Test;

import java.util.List;

/**
 * Date: 2017/4/8
 * Time: 14:36
 *
 * @author Tian.Dong
 */
public class PathFindingUtilTest {

    @Test
    public void test() {
        List<Integer> path4 = PathFindingUtil.getMapPath(12001, 25000);
        System.out.println(path4);
        System.out.println(PathFindingUtil.showPath(12001, path4));

        List<Integer> path5 = PathFindingUtil.getMapPath(12001, 22000);
        System.out.println(path5);
        System.out.println(PathFindingUtil.showPath(12001, path5));

        List<Integer> path6 = PathFindingUtil.getMapPath(22000, 25000);
        System.out.println(path6);
        System.out.println(PathFindingUtil.showPath(22000, path6));
    }

    @Test
    public void testDJ() {
        List<Integer> path1 = PathFindingUtil.getMapPath_dfs(12001, 25000);
        System.out.println(path1);
        System.out.println(PathFindingUtil.showPath(12001, path1));

        List<Integer> path4 = PathFindingUtil.getMapPath(12001, 25000);
        System.out.println(path4);
        System.out.println(PathFindingUtil.showPath(12001, path4));
    }

    @Test
    public void testDFS_Dj_time() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            List<Integer> path1 = PathFindingUtil.getMapPath_dfs(12001, 25000);
//            System.out.println(path1);
//            System.out.println(PathFindingUtil.showPath(12001, path1));
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000);

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            List<Integer> path4 = PathFindingUtil.getMapPath(12001, 25000);
//            System.out.println(path4);
//            System.out.println(PathFindingUtil.showPath(12001, path4));
        }
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000);
    }
}
