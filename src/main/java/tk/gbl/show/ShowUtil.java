package tk.gbl.show;

/**
 * Date: 2017/3/25
 * Time: 9:38
 *
 * @author Tian.Dong
 */
public class ShowUtil {
    public static String show(byte[] bytes, int pox) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < pox; i++) {
            byte b = bytes[i];
            int integer = b & 0xFF;
            stringBuilder.append(Integer.toHexString(integer));
            stringBuilder.append(" ");
        }
        return show(stringBuilder.toString());
    }

    public static String show(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            int integer = b & 0xFF;
            stringBuilder.append(Integer.toHexString(integer));
            stringBuilder.append(" ");
        }
        return show(stringBuilder.toString());
    }

    public static String show(String hexString) {
        String[] hexCharList = hexString.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String hexChar : hexCharList) {
            int mNumber = Integer.parseInt(hexChar, 16);
            int number = mNumber ^ 0xAD;
//            char ch = (char) number;
//            stringBuilder.append(ch);

            String ch = Integer.toHexString(number).toUpperCase();
            ch = AddZeroUtil.addZero(ch, 2);
            stringBuilder.append(ch + ' ');
        }
        return stringBuilder.toString();
    }

    public static String show2(String hexStringStr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String hexString : hexStringStr.split("\n")) {
            String[] hexCharList = hexString.split(" ");
            for (String hexChar : hexCharList) {
                int mNumber = Integer.parseInt(hexChar, 16);
                int number = mNumber ^ 0xAD;
                String ch = Integer.toHexString(number).toUpperCase();
                ch = AddZeroUtil.addZero(ch, 2);
                stringBuilder.append(ch + ' ');
            }
            stringBuilder.append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public static String showChar(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(Integer.toHexString(b & 0xFF));
        }
        return showChar(stringBuilder.toString());
    }

    public static String showChar(String hexString) {
        String[] hexCharList = hexString.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String hexChar : hexCharList) {
            int mNumber = Integer.parseInt(hexChar, 16);
            int number = mNumber ^ 0xAD;
            char ch = (char) number;
            if (ch == '\t') {
                ch = ' ';
            }
            if (ch > 32) {
                stringBuilder.append(ch + " " + ' ');
            } else {
                stringBuilder.append("  " + ' ');
            }
        }
        return stringBuilder.toString();
    }

    public static String showOrigin(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : data) {
            int number = i;
//            char ch = (char) number;
//            stringBuilder.append(ch);

            String ch = Integer.toHexString(number).toUpperCase();
            ch = AddZeroUtil.addZero(ch, 2);
            stringBuilder.append(ch + ' ');
        }
        return stringBuilder.toString();
    }

    public static String showOrigin(String hexString) {
        String[] hexCharList = hexString.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String hexChar : hexCharList) {
            int mNumber = Integer.parseInt(hexChar, 16);
            int number = mNumber;
//            char ch = (char) number;
//            stringBuilder.append(ch);

            String ch = Integer.toHexString(number).toUpperCase();
            ch = AddZeroUtil.addZero(ch, 2);
            stringBuilder.append(ch + ' ');
        }
        return stringBuilder.toString();
    }

    public static String showCharOrigin(String hexString) {
        String[] hexCharList = hexString.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String hexChar : hexCharList) {
            int mNumber = Integer.parseInt(hexChar, 16);
            int number = mNumber;
            char ch = (char) number;
            if (ch == '\t') {
                ch = ' ';
            }
            if (ch > 32) {
                stringBuilder.append(ch + " " + ' ');
            } else {
                stringBuilder.append("  " + ' ');
            }
        }
        return stringBuilder.toString();
    }

    public static String showCharOrigin(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int number : data) {
            char ch = (char) number;
            if (ch == '\t') {
                ch = ' ';
            }
            if (ch > 32) {
                stringBuilder.append(ch + " " + ' ');
            } else {
                stringBuilder.append("  " + ' ');
            }
        }
        return stringBuilder.toString();
    }


}
