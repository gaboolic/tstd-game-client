package tk.gbl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Date: 2017/3/24
 * Time: 16:43
 *
 * @author Tian.Dong
 */
public class TSClientTest {
    /**
     * 59 E9 AC AD AD
     * <p/>
     * 59 E9 A9 AD AC A4 C8 AD
     */
    public static String connect(Socket socket) throws IOException {
        return send(socket, "59 E9 AC AD AD");
    }

    /**
     * 密码错误 59 E9 AF AD AC AB 59 E9 AF AD AD BF
     * 号无角色 59 E9 AE AD AC AE AD 59 E9 AF AD AD BF
     */
    public static String login(Socket socket) throws IOException {
        return send(socket, "59 E9 BD AD AC AB 0D 17 AC AD DA DD 7C AD 98 98 9B 9B 95 95 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD");
    }

    /**
     * 取名：
     * 59 E9 AE AD A4 AE AC 名称重复使用
     * 59 E9 AE AD A4 AE AD 正常
     */
    public static String setName(Socket socket) throws IOException {
        return send(socket, "");
    }

    public static String send(Socket socket, String input) throws IOException {
        String[] byteStrings = input.split(" ");
        byte[] bytes = new byte[byteStrings.length];
        for (int i = 0; i < byteStrings.length; i++) {
            bytes[i] = (byte) Integer.parseInt(byteStrings[i], 16);
        }
        byte[] resp = new byte[32768];
        int respLength = send(socket, bytes, resp);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < respLength; i++) {
            String hex = Integer.toHexString(resp[i] & 0xFF).toUpperCase();
            result.append(hex);
            result.append(" ");
        }
        return result.toString();
    }

    public static int send(Socket socket, byte[] bytes, byte[] results) throws IOException {
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        os.write(bytes);
        os.flush();
        int respLength = is.read(results);
        return respLength;
    }


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("115.231.168.29", 6414);
        OutputStream os=socket.getOutputStream();
        InputStream is=socket.getInputStream();

        String preLoginResponse = connect(socket);
        System.out.println(preLoginResponse.split(" ").length);

        String loginResponse = login(socket);
        System.out.println(loginResponse.split(" ").length);
        is.close();
        os.close();
        socket.close();
    }
}
