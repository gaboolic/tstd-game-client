package tk.gbl.helper;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import tk.gbl.util.RegexUtil;
import tk.gbl.util.http.HttpClientUtil;
import tk.gbl.util.http.HttpUtil;

import java.io.*;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注册网站账号
 * 申请游戏账号
 * <p/>
 * Date: 2017/3/25
 * Time: 11:25
 *
 * @author Tian.Dong
 */
public class BatchRegister {

    /**
     * 申请账号
     */
    public static void main(String[] args) throws IOException {
        for (int i = 101; i <= 300; i++) {
            try {
                HttpClient client = HttpClientUtil.getHttpClient();
                String cookie = index(client);
                cookie = cookie + login(client, "dtph" + i, "1387924682");
//                cookie = cookie + login(client, "mytstd" + i, "1387924682");
                String codeCookie = getCheckCode(client, cookie);
                cookie = cookie + codeCookie;
                String checkCode = codeCookie.substring(10, 15);
                for (int c = 0; c < 5; c++) {
                    applyAccount(client, cookie, checkCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注册通行证
     */
    public static void mainReg(String[] args) throws IOException {
        for (int i = 101; i <= 300; i++) {
            HttpClient client = HttpClientUtil.getHttpClient();
            String username = "bayue" + i;
            String email = "bzofks1@chacuo.net";
            String html = registerAccount(username, "bayue123456", email);
            if (html.contains("Object moved to")) {
                System.out.println(username + "成功");
            } else {
                System.out.println(username + "失败");
            }
            String cookie = index(client);
            cookie = cookie + login(client, username, "bayue123456");
            String sendMailHtml = sendEmail(client, cookie, email);
            System.out.println(sendMailHtml);

        }


    }


    public static String index(HttpClient client) throws IOException {
        String url = "http://online-game.com.cn/";
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);
        Header[] headers = method.getResponseHeaders("Set-Cookie");

        StringBuilder cookieBuilder = new StringBuilder();
        for (Header header : headers) {
            String value = header.getValue();
            String one = value.split(";")[0];
            cookieBuilder.append(one);
            cookieBuilder.append(";");
        }
        return cookieBuilder.toString();
    }

    public static String getCheckCode(HttpClient client, String cookie) throws IOException {
        String url = "http://online-game.com.cn/checkcode.aspx";
        GetMethod method = new GetMethod(url);
        method.setRequestHeader("Cookie", cookie);
        client.executeMethod(method);
        Header[] headers = method.getResponseHeaders("Set-Cookie");

        StringBuilder cookieBuilder = new StringBuilder();
        for (Header header : headers) {
            String value = header.getValue();
            String one = value.split(";")[0];
            cookieBuilder.append(one);
            cookieBuilder.append(";");
        }
        return cookieBuilder.toString();
    }

    public static String login(HttpClient client, String username, String password) throws IOException {
        String url = "http://online-game.com.cn/login.aspx";
        String body = "__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwULLTE1MjgzNTk3OTlkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYCBQxJbWFnZUJ1dHRvbjEFCUNoZWNrQm94MWJsD%2BdCDXbhz%2F1D6Yuew9hNM56C&__VIEWSTATEGENERATOR=C2EE9ABB&__EVENTVALIDATION=%2FwEWBQLSqJW5BQKtztzDDgKezrj3BgLSwpnTCAKC5Ne7CVmXhxemZ%2BjKK5Of%2FoHV5PROqVHF&TextMemId=" +
                username +
                "&TextMemPwd=" +
                password +
                "&ImageButton1.x=53&ImageButton1.y=8";
//        String cookie = "UM_distinctid=15ab7e033210-000b28a15-67141479-100200-15ab7e0332781; Loginuserid=Memberid=G8q7k%2bsDRRY%3d; online-game=Mid=&Userid=ZvfbcLlzzew%3d&Password=ZvfbcLlzzew%3d; online-game=Mid=&Userid=ZvfbcLlzzew%3d&Password=ZvfbcLlzzew%3d; dnt=userid=&password=ZvfbcLlzzew%3d&sigstatus=1; ASP.NET_SessionId=rcbl1255rdgbw0fj3dybhoeu; CheckCode=84V8F";
        PostMethod method = new PostMethod(url);
//        cookie = cookie.trim();
//        method.setRequestHeader("Cookie", cookie);
        method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
//        method.setRequestHeader("Referer", "http://online-game.com.cn/gameid-t.aspx?gamename=tshao");
        //method.setRequestHeader("X-Requested-With","ShockwaveFlash/18.0.0.232");
        method.setRequestEntity(new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf-8"));

        client.executeMethod(method);
        Header[] headers = method.getResponseHeaders("Set-Cookie");

        StringBuilder cookieBuilder = new StringBuilder();
        for (Header header : headers) {
            String value = header.getValue();
            String one = value.split(";")[0];
            cookieBuilder.append(one);
            cookieBuilder.append(";");
        }
        return cookieBuilder.toString();
    }

    public static String registerAccount(String username, String password, String email) throws IOException {
        String url = "http://online-game.com.cn/register.aspx";
        String body = "__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwULLTE2NzMyOTIwNDMPZBYCAgMPZBYCAgMPD2QWAh4Gb25ibHVyBSJqYXZhc2NyaXB0OmNoZWNrVXNlck5hbWVfQ2xpZW50KCk7ZBgBBR5fX0NvbnRyb2xzUmVxdWlyZVBvc3RCYWNrS2V5X18WAgUDbGVlBQRsZWUxOI%2F55t4Ee8O%2FySxVvyJAAVnNzGE%3D&__VIEWSTATEGENERATOR=799CC77D&__EVENTVALIDATION=%2FwEWDAKo7PWSDAKtztzDDgKezrj3BgLi1dPIAQK6tKrWCgKfy%2BT%2FAgLi6r%2BrCgKEkoKgCQKY2YWXBgLVgreqBgLVguOcAgKM54rGBn3dwwDN0LC5i3VjbqICrGgaQ5YQ&TextMemId=" +
                username +
                "&TextMemPwd=" + password + "&repwd=" + password + "&TextMemCard=532823197904273390&TextMemName=%E4%BA%8E%E4%B8%B0" +
                "&TextMemMail=" +
                URLEncoder.encode(email, "utf-8") +
                "&zhengjian=" +
                "&txtCheckCode=84V8F" +
                "&lee=on&lee1=on&Button1=%E6%B3%A8%E5%86%8C%E8%B4%A6%E5%8F%B7";
        String cookie = "UM_distinctid=15ab7e033210-000b28a15-67141479-100200-15ab7e0332781; Loginuserid=Memberid=G8q7k%2bsDRRY%3d; online-game=Mid=&Userid=ZvfbcLlzzew%3d&Password=ZvfbcLlzzew%3d; online-game=Mid=&Userid=ZvfbcLlzzew%3d&Password=ZvfbcLlzzew%3d; dnt=userid=&password=ZvfbcLlzzew%3d&sigstatus=1; ASP.NET_SessionId=rcbl1255rdgbw0fj3dybhoeu; CheckCode=84V8F";
        PostMethod method = new PostMethod(url);
        cookie = cookie.trim();
        method.setRequestHeader("Cookie", cookie);
        method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
        method.setRequestHeader("Referer", "http://online-game.com.cn/gameid-t.aspx?gamename=tshao");
        //method.setRequestHeader("X-Requested-With","ShockwaveFlash/18.0.0.232");
        method.setRequestEntity(new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf-8"));

        HttpClient client = HttpClientUtil.getHttpClient();
        client.executeMethod(method);
        String html = method.getResponseBodyAsString();
        return html;
    }

    public static String sendEmail(HttpClient client, String cookie, String email) throws IOException {
        String url = "http://online-game.com.cn/email.aspx";
        String body = "__VIEWSTATE=%2FwEPDwUKLTgxNjUzMzk5NQ9kFgICAw9kFgICAQ8PFgIeBFRleHQFHuaCqOeahOmCrueuseWcsOWdgOWwmuacqumqjOivgWRkZLCLliyvcNqTO22g%2BSVQ0kl6xf8c&__VIEWSTATEGENERATOR=1EEA8FE8&__EVENTVALIDATION=%2FwEWAwKvkbHFCALv%2B8JAAoznisYGocGqSc97QctgVjmvATnSkfLZl4g%3D" +
                "&textboxemail=" +
//                "532373886%40qq.com" +
                URLEncoder.encode(email, "utf-8") +
                "&Button1=%E5%8F%91%E9%80%81%E9%AA%8C%E8%AF%81%E4%BF%A1%E6%81%AF%E5%88%B0%E9%82%AE%E7%AE%B1";
        PostMethod method = new PostMethod(url);
        cookie = cookie.trim();
        method.setRequestHeader("Cookie", cookie);
        method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
        method.setRequestHeader("Referer", "http://online-game.com.cn/gameid-t.aspx?gamename=tshao");
        //method.setRequestHeader("X-Requested-With","ShockwaveFlash/18.0.0.232");
        method.setRequestEntity(new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf-8"));
        client.executeMethod(method);

        return method.getResponseBodyAsString();
    }

    public static void applyAccount(HttpClient client, String cookie, String checkCode) throws IOException {
//        String url = "http://online-game.com.cn/gameid-t.aspx?gamename=tshao";
        String url = "http://online-game.com.cn/gameid-t.aspx?gamename=tsnew";
        /**
         * __VIEWSTATEGENERATOR:1A0D0A05
         __EVENTVALIDATION:/wEWAwLH1vKYCgKY2YWXBgKM54rGBgJItMQaDuKAbnjMxTcszy/Q80C6
         */
        String body = "__VIEWSTATE=%2FwEPDwULLTE5NjcyNzE5NjIPZBYCAgMPZBYCAgMPDxYCHgRUZXh0BRXlkJ7po5%2FkuYvlm73pmYXkuK3mlodkZGRoiA8PXWtdXdaSvFCDAH%2FBh%2FQAhA%3D%3D&__VIEWSTATEGENERATOR=1A0D0A05&__EVENTVALIDATION=%2FwEWAwKUl5T4AwKY2YWXBgKM54rGBv0NLj8qh862Vx%2BlqMRnUPxYtCoJ&txtCheckCode=" +
                checkCode +
                "&Button1=%E6%8F%90%E4%BA%A4";
        PostMethod method = new PostMethod(url);
        cookie = cookie.trim();
        method.setRequestHeader("Cookie", cookie);
        method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
//        method.setRequestHeader("Referer", "http://online-game.com.cn/gameid-t.aspx?gamename=tshao");
        method.setRequestHeader("Referer", "http://online-game.com.cn/gameid-t.aspx?gamename=tsnew");
        //method.setRequestHeader("X-Requested-With","ShockwaveFlash/18.0.0.232");
        method.setRequestEntity(new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf-8"));

        try {
            client.executeMethod(method);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
            return;
        }
        String location = method.getResponseHeader("Location").getValue();
        GetMethod getMethod = new GetMethod("http://online-game.com.cn" + location);
        getMethod.setRequestHeader("Cookie", cookie);
        getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
        getMethod.setRequestHeader("Referer", "http://online-game.com.cn/gameid-t.aspx?gamename=tshao");

        try {
            client.executeMethod(getMethod);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
            return;
        }
        String html = getMethod.getResponseBodyAsString();
        if (html.contains("每个会员一天")) {
            System.out.println("失败");
            return;
        }
        Document document = Jsoup.parse(html);
        Element usernameEle = document.getElementById("Label1");
        Element passwordEle = document.getElementById("Label2");
        //WP00114908
        //5FXPNGWR6M
        String username = usernameEle.html();
        long userId = Long.parseLong(username.substring(2));
        String password = passwordEle.html();
//        File file = new File("E:\\DT\\tstd", "account.txt");
        File file = new File("E:\\DT\\tstd", "account_gj.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userId + ":" + password + ",");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
        System.out.println(username + " " + password);
    }

    public static void mainTest(String[] args) {
        String html = "e=\"color:Red;\">WP00114908</span>bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码：<span id=\"Label2\" style=\"color:Red;\">5FXPNGWR6M</span>";
        String username = RegexUtil.regex(html, "WP[\\d]{8}");
        Pattern p = Pattern.compile("[0-9A-Za-z]{2}[0-9A-Za-z]{8}");
        Matcher m = p.matcher(html);
        m.find();
        m.find();
        String password = m.group();

        System.out.println(username);
        System.out.println(password);
    }
}
