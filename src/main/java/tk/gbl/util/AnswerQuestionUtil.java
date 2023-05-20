package tk.gbl.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2017/5/6
 * Time: 9:38
 *
 * @author Tian.Dong
 */
public class AnswerQuestionUtil {
    static Map<String, Integer> singleNumberMap = new HashMap<>();

    static {
        singleNumberMap.put("一", 1);
        singleNumberMap.put("二", 2);
        singleNumberMap.put("三", 3);
        singleNumberMap.put("四", 4);
        singleNumberMap.put("五", 5);
        singleNumberMap.put("六", 6);
        singleNumberMap.put("七", 7);
        singleNumberMap.put("八", 8);
        singleNumberMap.put("九", 9);
        singleNumberMap.put("十", 10);
    }

    public static int calcAnswer(String questionStr) {
        if (questionStr == null) {
            return 1;
        }
        try {
            questionStr = questionStr.replaceAll("[\u0004|\u0005|\u0006]+", "");
            questionStr = questionStr.replaceAll(" ", "");
            int mryIndex = questionStr.indexOf("有");
            String numberStr = questionStr.charAt(mryIndex + 1) + "";//数量
            int allCount = fromChineseToNumber(numberStr) * 3;
            String quantifierStr = questionStr.charAt(mryIndex + 2) + "";//量词
            String answerPartStr = questionStr.split(":")[1];
            String[] chineseNumbers = answerPartStr.split(quantifierStr);
            for (int i = 0; i < chineseNumbers.length; i++) {
                if (fromChineseToNumber(chineseNumbers[i]) == allCount) {
                    return i + 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static int fromChineseToNumber(String numberStr) {
        if (numberStr.length() == 1) {
            return singleNumberMap.get(numberStr);
        }
        if (numberStr.length() == 2) {
            int a = singleNumberMap.get(numberStr.charAt(0) + "");
            int b = singleNumberMap.get(numberStr.charAt(1) + "");
            return a * 10 + b;
        }
        if (numberStr.length() == 3) {
            int a = singleNumberMap.get(numberStr.charAt(0) + "");
            int b = singleNumberMap.get(numberStr.charAt(1) + "");
            int c = singleNumberMap.get(numberStr.charAt(2) + "");
            if (b == 10) {
                return a * 10 + c;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
//        String str = "7韓猛,閻圃和褚貢每人有三粒奶黃包,則上述總共幾個奶黃包呢:九粒 七粒 八粒十二粒";
//        String str = "陳宮,諸葛原及孫乾各有二粒奶黃包,因此以上總共幾個奶黃包?:\u0004八粒\u0004九粒\u0006十一粒六粒";
        String str = "孫韶及夏侯尚和張敞各自有二個奶黃包,所以以上有幾個奶黃包呢?:\u0004四個\u0004五個\u0004八個六個";
        int answer = AnswerQuestionUtil.calcAnswer(str);
        System.out.println(answer);
    }
}
