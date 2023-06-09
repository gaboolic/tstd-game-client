package tk.gbl.core.handle.fight;

import tk.gbl.constant.ResponseProtocolConstant;
import tk.gbl.core.GameClient;
import tk.gbl.core.handle.BaseHandler;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.AnswerQuestionUtil;
import tk.gbl.util.ByteArrayToIntArrayUtil;
import tk.gbl.util.OutputUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 北斗星君的问题
 * <p/>
 * Date: 2017/4/6
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public class U1001ResHandler extends BaseHandler {
    @Override
    public boolean handle(int[] data, InputStream is, GameClient gameClient) throws IOException {
        if (!checkData(data)) {
            return false;
        }
        /**
         *F4 44 51 00 10 01 3B AE 5D BB E0 A4 CE AE 4C AB 4A A9 7C A9 4D B1 69 B4 AF A6 55 A6 DB A6 B3 A4 47 AD D3 A5 A4 B6 C0 A5 5D 2C A9 D2 A5 48 A5 48 A4 57 A6 B3 B4 58 AD D3 A5 A4 B6 C0 A5 5D A9 4F 3F 3A 04 A5 7C AD D3 04 A4 AD AD D3 04 A4 4B AD D3 A4 BB AD D3
         ô  D  Q           ;  ®  ]  »  à  ¤  Î  ®  L  «  J  ©  |  ©  M  ±  i  ´  ¯  ¦  U  ¦  Û  ¦  ³  ¤  G  ­  Ó  ¥  ¤  ¶  À  ¥  ]  ,  ©  Ò  ¥  H  ¥  H  ¤  W  ¦  ³  ´  X  ­  Ó  ¥  ¤  ¶  À  ¥  ]  ©  O  ?  :     ¥  |  ­  Ó     ¤  ­  ­  Ó     ¤  K  ­  Ó  ¤  »  ­  Ó
         巆Q ;孫韶及夏侯尚和張敞各自有二個奶黃包,所以以上有幾個奶黃包呢?:四個五個八個六個
         F4 44 51 00 10 01 3B AE 5D BB E0 A4 CE AE 4C AB 4A A9 7C A9 4D B1 69 B4 AF A6 55 A6 DB A6 B3 A4 47 AD D3 A5 A4 B6 C0 A5 5D 2C A9 D2 A5 48 A5 48 A4 57 A6 B3 B4 58 AD D3 A5 A4 B6 C0 A5 5D A9 4F 3F 3A 04 A4 BB AD D3 04 A5 7C AD D3 04 A4 AD AD D3 A4 4B AD D3
         ô  D  Q           ;  ®  ]  »  à  ¤  Î  ®  L  «  J  ©  |  ©  M  ±  i  ´  ¯  ¦  U  ¦  Û  ¦  ³  ¤  G  ­  Ó  ¥  ¤  ¶  À  ¥  ]  ,  ©  Ò  ¥  H  ¥  H  ¤  W  ¦  ³  ´  X  ­  Ó  ¥  ¤  ¶  À  ¥  ]  ©  O  ?  :     ¤  »  ­  Ó     ¥  |  ­  Ó     ¤  ­  ­  Ó  ¤  K  ­  Ó
         巆Q ;孫韶及夏侯尚和張敞各自有二個奶黃包,所以以上有幾個奶黃包呢?:六個四個五個八個
         */
        if (data[4] == (ResponseProtocolConstant.u1001.getId() >> 8 & 0xFF)
                && data[5] == (ResponseProtocolConstant.u1001.getId() & 0xFF)) {
            int[] temp = new int[data.length - 7];
            System.arraycopy(data, 7, temp, 0, data.length - 7);
            byte[] questionBytes = ByteArrayToIntArrayUtil.transform(temp, temp.length);
            String questionStr = null;
            try {
                questionStr = new String(questionBytes, "big5");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            gameClient.getFightInfo().setQuestion(questionStr);

            OutputUtil.output("问题 " + questionStr, gameClient, true);
            if (!gameClient.isFighting()) {
                int answer = AnswerQuestionUtil.calcAnswer(questionStr);
                gameClient.answer(answer);
                OutputUtil.output("回答 " + answer, gameClient, true);
            }
            return true;
        }
        return false;
    }


}
