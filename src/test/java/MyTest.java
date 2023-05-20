import java.io.File;

/**
 * Date: 2017/6/1
 * Time: 22:23
 *
 * @author Tian.Dong
 */
public class MyTest {
    public static void main(String[] args) {
        File path = new File("E:\\DT\\赵洪娟_选");
        for (File file : path.listFiles()) {
            System.out.print(file.getName().split("\\.")[0]);
            System.out.print(" ");
        }
    }
}
