package tk.gbl.maincenter;

import org.junit.Test;

import java.io.IOException;
import java.util.Random;

/**
 * Date: 2017/5/8
 * Time: 22:49
 *
 * @author Tian.Dong
 */
public class MainTankWork {
    @Test
    public void test(){
        boolean success = false;
        while (!success) {
            try {
                doFlow();
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("done");
    }

    private void doFlow() throws IOException {
        System.out.println("1111");
        int r = new Random().nextInt();
        if(r % 2 == 0) {
            throw new IOException("1111");
        }

        System.out.println("2222");
        r = new Random().nextInt();
        if(r % 2 == 0) {
            throw new IOException("2222");
        }

        System.out.println("3333");
        r = new Random().nextInt();
        if(r % 2 == 0) {
            throw new IOException("3333");
        }

        System.out.println("4444");
        r = new Random().nextInt();
        if(r % 2 == 0) {
            throw new IOException("4444");
        }
    }
}
