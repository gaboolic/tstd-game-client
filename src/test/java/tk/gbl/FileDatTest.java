package tk.gbl;

import tk.gbl.show.ShowUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Date: 2017/3/28
 * Time: 8:50
 *
 * @author Tian.Dong
 */
public class FileDatTest {
    public static void main(String[] args) throws Exception {
        File file = new File("E:\\game\\国仕无双\\data\\CityEx.Dat");
        FileInputStream fileInputStream = new FileInputStream(file);
//        int by = -1;
//        StringBuilder sb = new StringBuilder();
//        while((by = fileInputStream.read()) != -1) {
//            sb.append(Integer.toHexString(by));
//            sb.append(" ");
//        }
//        System.out.println(sb.toString());
//        System.out.println(ShowUtil.showOrigin(sb.toString()));
//        System.out.println(ShowUtil.showCharOrigin(sb.toString()));

        byte[] bytes = new byte[102400];
        int length = fileInputStream.read(bytes);
        System.out.println(length);
        String content = new String(bytes,"big5");
        System.out.println(content);
    }
}
