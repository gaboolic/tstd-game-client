import org.junit.Test;
import tk.gbl.show.ShowUtil;

import javax.net.ssl.SNIHostName;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/3/30
 * Time: 21:20
 *
 * @author Tian.Dong
 */
public class ClickNPCTest {
    @Test
    public void testWangbadianyuan() {
        String clickNPC1Req = "59 E9 A9 AD B9 AC AC AD ";
        String clickNPC1Req2 = "59 E9 AF AD B9 AB ";//event OK
        String clickNPC1Req3 = "59 E9 AF AD B9 AB ";

        System.out.println(ShowUtil.show(clickNPC1Req));
        System.out.println(ShowUtil.show(clickNPC1Req2));
        System.out.println(ShowUtil.show(clickNPC1Req3));
    }

    @Test
    public void testShoushangShibing(){
        String clickNPCReq = "59 E9 A9 AD B9 AC AA AD ";
        String clickNPCReqRes1 = "59 E9 AF AD AB AF 59 E9 BC AD B9 AC AD AD AD AC AC AE AA AD AD AD AD AD AD DC 61 ";
        String clickNPCReqRes2 = "59 E9 95 AD AE BF 4A AD AD AF AE B3 AD AD 4C 83 D7 A5 FF AE AD AD AD B1 02 D0 B7 B1 02 D0 B7 AB C1 E3 56 E1 5B 85 75 FF 19 F5 E1 F7 AD AD AD AD AD C8 AD AD 09 C7 00 15 06 E1 98 9D 59 E9 BF AD A8 AD BF 4A AD AD C1 E3 56 E1 5B 85 75 FF 19 F5 E1 F7 59 E9 AB AD AC AC BF 4A AD AD ";

        System.out.println(ShowUtil.show(clickNPCReq));
        System.out.println(ShowUtil.showChar(clickNPCReq));
        System.out.println(ShowUtil.show(clickNPCReqRes1));
        System.out.println(ShowUtil.showChar(clickNPCReqRes1));
        System.out.println(ShowUtil.show(clickNPCReqRes2));
        System.out.println(ShowUtil.showChar(clickNPCReqRes2));
        System.out.println();

        String eventOkReq1 = "59 E9 AF AD B9 AB ";
        String eventOkRes = "59 E9 BC AD B9 AC AD AD AD AF AC AE AA AD AD AD AD AD AD DF 61 ";
        String eventOkReq2 = "59 E9 AF AD B9 AB ";
        String res1 = "59 E9 AF AD B9 A5 ";
        String res2 = "59 E9 95 AD AE BF 4A AD AD AF AE B3 AD AD 4C 83 D7 A5 FF AE AD AD AD B1 02 D0 B7 B1 02 D0 B7 AB C1 E3 56 E1 5B 85 75 FF 19 F5 E1 F7 AD AD AD AD AD C8 AD AD 09 C7 00 15 06 E1 98 9D 59 E9 BF AD A8 AD BF 4A AD AD C1 E3 56 E1 5B 85 75 FF 19 F5 E1 F7 59 E9 AB AD AC AC BF 4A AD AD ";
        System.out.println(ShowUtil.show(eventOkReq1));
        System.out.println(ShowUtil.showChar(eventOkReq1));
        System.out.println(ShowUtil.show(eventOkRes));
        System.out.println(ShowUtil.showChar(eventOkRes));

        System.out.println(ShowUtil.show(eventOkReq2));
        System.out.println(ShowUtil.showChar(eventOkReq2));

        System.out.println(ShowUtil.show(res1));
        System.out.println(ShowUtil.showChar(res1));
        System.out.println(ShowUtil.show(res2));
        System.out.println(ShowUtil.showChar(res2));
    }

    /**
     * 邺城城门杂货商人
     */
    @Test
    public void testZahuoshangren(){
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD B9 AC AF AD ");//点击NPC
        reqList.add("59 E9 AE AD B9 A4 B3 ");//choose
        reqList.add("59 E9 AF AD B9 AB ");//eventOk
        reqList.add("59 E9 AE AD B9 A4 85 ");//1409 28
        reqList.add("59 E9 AF AD B9 AB  ");//eventOk

        for(String str:reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }

    @Test
    public void testSongyong(){
        List<String> reqList = new ArrayList<>();
        reqList.add("59 E9 A9 AD B9 AC AF AD ");//点击NPC
        reqList.add("59 E9 AE AD B9 A4 B3 ");//choose
        reqList.add("59 E9 AF AD B9 AB ");//eventOk

        for(String str:reqList) {
            System.out.println(ShowUtil.show(str));
            System.out.println(ShowUtil.showChar(str));
        }
    }
}
