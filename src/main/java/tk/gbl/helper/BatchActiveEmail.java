package tk.gbl.helper;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import tk.gbl.util.GzipUtil;
import tk.gbl.util.RegexUtil;
import tk.gbl.util.http.HttpClientUtil;
import tk.gbl.util.http.HttpMethodUtil;
import tk.gbl.util.http.HttpUtil;
import tk.gbl.util.warp.json.FastJsonUtil;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Date: 2017/4/10
 * Time: 17:42
 *
 * @author Tian.Dong
 */
public class BatchActiveEmail {
    public static void main2(String[] args) throws IOException {
        for (int i = 101; i <= 300; i++) {
            HttpClient client = HttpClientUtil.getHttpClient();
            String username = "bayue" + i;
//            String email = "ntegbk30241@chacuo.net";
            String email = "bayue005@www.bccto.me";
            String cookie = index(client);
            cookie = cookie + login(client, username, "1387924682");
            String sendMailHtml = sendEmail(client, cookie, email);
            System.out.println(username);

        }
    }

    public static void main(String[] args) throws IOException {
//        String email = "dtph004@www.bccto.me";
        String email = "bayue004@www.bccto.me";
        String url = "http://www.bccto.me/getmail";
        String body = "mail=bayue004%40www.bccto.me&time=1491892650&_=1491892716813";
        String response = HttpUtil.post(url, body, "application/x-www-form-urlencoded", "utf-8");
        System.out.println(response);
        Map map = FastJsonUtil.fromJson(response, Map.class);
        List<List<String>> mailList = (List) map.get("mail");
        for (List<String> mail : mailList) {
            String from = mail.get(1);
            if (from.equals("password@online-game.com.cn")) {
                String key = mail.get(4);
//                String detailUrl = "http://www.bccto.me/winmail/" + email + "/" + key + "";
                String detailUrl = "http://www.bccto.me/win/bayue004(a)www-_-bccto.me/" + key + "";
                String detail = httpGet(detailUrl);
                System.out.println(detail);
                int begin = detail.indexOf("http://");
                int end = detail.indexOf("&url=");
                if(begin == -1 || end == -1) {
                    continue;
                }
                String checkUrl = detail.substring(begin, end+5);

                HttpClient httpClient = HttpClientUtil.getHttpClient();
                GetMethod getMethod = HttpMethodUtil.getGetMethod(checkUrl);
                httpClient.executeMethod(getMethod);
                String checkResponse = getMethod.getResponseBodyAsString();
                if(checkResponse.contains("验证通过")) {
                    System.out.println("验证通过");
                }
                if(checkResponse.contains("连接已失效")) {
                    System.out.println("连接已失效");
                }
            }
        }
        System.out.println(map);
    }

    private static String httpGet(String detailUrl) throws IOException {
        HttpClient httpClient = new HttpClient();
        GetMethod httpMethod = new GetMethod(detailUrl);
        httpMethod.addRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpMethod.addRequestHeader("Connection","keep-alive");
        httpMethod.addRequestHeader("Cache-Control","max-age=0");
        httpMethod.addRequestHeader("Upgrade-Insecure-Requests","1");
        httpMethod.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
        httpMethod.addRequestHeader("DNT", "1");
        httpMethod.addRequestHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpMethod.addRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
        httpClient.executeMethod(httpMethod);
//        InputStream inputStream = httpMethod.getResponseBodyAsStream();
//        byte[] bytes = new byte[20000];
//        inputStream.read(bytes);
//        String str = GzipUtil.uncompress(bytes);
//        return str;
        return httpMethod.getResponseBodyAsString();
    }


    public static String index(HttpClient client) throws IOException {
        String url = "https://online-game.com.cn/";
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
        String url = "https://online-game.com.cn/checkcode.aspx";
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
        String url = "https://online-game.com.cn/index.aspx";
        String body = "__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwUKMTc2NjM0MDAwNWQYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgIFDEltYWdlQnV0dG9uMQUJQ2hlY2tCb3gxqqhPGR%2BUaRPZxwTWnJ7CjFszivg%3D&__VIEWSTATEGENERATOR=90059987&__EVENTVALIDATION=%2FwEWBQLJzJX2CAKtztzDDgLSwpnTCAKezrj3BgKC5Ne7CYcrlFaFWIN6UaxRU%2Fi6zDMpwRVW" +
                "&TextMemId=" +
                username +
                "&ImageButton1.x=30&ImageButton1.y=41" +
                "&TextMemPwd=" +
                password +
                "&CheckBox1=on";
        String cookie = "UM_distinctid=15ab7e033210-000b28a15-67141479-100200-15ab7e0332781; Loginuserid=Memberid=G8q7k%2bsDRRY%3d; online-game=Mid=&Userid=ZvfbcLlzzew%3d&Password=ZvfbcLlzzew%3d; online-game=Mid=&Userid=ZvfbcLlzzew%3d&Password=ZvfbcLlzzew%3d; dnt=userid=&password=ZvfbcLlzzew%3d&sigstatus=1; ASP.NET_SessionId=rcbl1255rdgbw0fj3dybhoeu; CheckCode=84V8F";
        PostMethod method = new PostMethod(url);
        cookie = cookie.trim();
        method.setRequestHeader("Cookie", cookie);
        method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
        method.setRequestHeader("Referer", "https://online-game.com.cn/gameid-t.aspx?gamename=tshao");
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

    public static String registerAccount(String username, String email) throws IOException {
        String url = "https://online-game.com.cn/register.aspx";
        String body = "__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwULLTE2NzMyOTIwNDMPZBYCAgMPZBYCAgMPD2QWAh4Gb25ibHVyBSJqYXZhc2NyaXB0OmNoZWNrVXNlck5hbWVfQ2xpZW50KCk7ZBgBBR5fX0NvbnRyb2xzUmVxdWlyZVBvc3RCYWNrS2V5X18WAgUDbGVlBQRsZWUxOI%2F55t4Ee8O%2FySxVvyJAAVnNzGE%3D&__VIEWSTATEGENERATOR=799CC77D&__EVENTVALIDATION=%2FwEWDAKo7PWSDAKtztzDDgKezrj3BgLi1dPIAQK6tKrWCgKfy%2BT%2FAgLi6r%2BrCgKEkoKgCQKY2YWXBgLVgreqBgLVguOcAgKM54rGBn3dwwDN0LC5i3VjbqICrGgaQ5YQ&TextMemId=" +
                username +
                "&TextMemPwd=1387924682&repwd=1387924682&TextMemCard=532823197904273390&TextMemName=%E4%BA%8E%E4%B8%B0" +
                "&TextMemMail=" +
//                "532373886%40qq.com" +
                URLEncoder.encode(email, "utf-8") +
                "&zhengjian=" +
                "&txtCheckCode=84V8F" +
                "&lee=on&lee1=on&Button1=%E6%B3%A8%E5%86%8C%E8%B4%A6%E5%8F%B7";
        String cookie = "UM_distinctid=15ab7e033210-000b28a15-67141479-100200-15ab7e0332781; Loginuserid=Memberid=G8q7k%2bsDRRY%3d; online-game=Mid=&Userid=ZvfbcLlzzew%3d&Password=ZvfbcLlzzew%3d; online-game=Mid=&Userid=ZvfbcLlzzew%3d&Password=ZvfbcLlzzew%3d; dnt=userid=&password=ZvfbcLlzzew%3d&sigstatus=1; ASP.NET_SessionId=rcbl1255rdgbw0fj3dybhoeu; CheckCode=84V8F";
        PostMethod method = new PostMethod(url);
        cookie = cookie.trim();
        method.setRequestHeader("Cookie", cookie);
        method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
        method.setRequestHeader("Referer", "https://online-game.com.cn/gameid-t.aspx?gamename=tshao");
        //method.setRequestHeader("X-Requested-With","ShockwaveFlash/18.0.0.232");
        method.setRequestEntity(new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf-8"));

        HttpClient client = HttpClientUtil.getHttpClient();
        client.executeMethod(method);
        String html = method.getResponseBodyAsString();
        return html;
    }

    public static String sendEmail(HttpClient client, String cookie, String email) throws IOException {
        String url = "https://online-game.com.cn/email.aspx";
        String body = "__VIEWSTATE=%2FwEPDwUKLTgxNjUzMzk5NQ9kFgICAw9kFgICAQ8PFgIeBFRleHQFHuaCqOeahOmCrueuseWcsOWdgOWwmuacqumqjOivgWRkZLCLliyvcNqTO22g%2BSVQ0kl6xf8c&__VIEWSTATEGENERATOR=1EEA8FE8&__EVENTVALIDATION=%2FwEWAwKvkbHFCALv%2B8JAAoznisYGocGqSc97QctgVjmvATnSkfLZl4g%3D" +
                "&textboxemail=" +
//                "532373886%40qq.com" +
                URLEncoder.encode(email, "utf-8") +
                "&Button1=%E5%8F%91%E9%80%81%E9%AA%8C%E8%AF%81%E4%BF%A1%E6%81%AF%E5%88%B0%E9%82%AE%E7%AE%B1";
        PostMethod method = new PostMethod(url);
        cookie = cookie.trim();
        method.setRequestHeader("Cookie", cookie);
        method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
        method.setRequestHeader("Referer", "https://online-game.com.cn/gameid-t.aspx?gamename=tshao");
        //method.setRequestHeader("X-Requested-With","ShockwaveFlash/18.0.0.232");
        method.setRequestEntity(new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf-8"));
        client.executeMethod(method);

        return method.getResponseBodyAsString();
    }

    public static void applyAccount(HttpClient client, String cookie, String checkCode) throws IOException {
        String url = "https://online-game.com.cn/gameid-t.aspx?gamename=tshao";
        String body = "__VIEWSTATE=/wEPDwULLTE5NjcyNzE5NjIPZBYCAgMPZBYCAgMPDxYCHgRUZXh0BRXlkJ7po5/kuYvlm73ku5Xml6Dlj4xkZGTShPmaV5k5zVrYTd4Mc2RqVXQFWQ==&__VIEWSTATEGENERATOR=1A0D0A05&" +
                "__EVENTVALIDATION=/wEWAwKp4suaCAKY2YWXBgKM54rGBjXkc3XfOmx8M3tJKpHWsgjKw5RM" +
                "&txtCheckCode=" +
                checkCode +
                "&Button1=提交";
        PostMethod method = new PostMethod(url);
        cookie = cookie.trim();
        method.setRequestHeader("Cookie", cookie);
        method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
        method.setRequestHeader("Referer", "https://online-game.com.cn/gameid-t.aspx?gamename=tshao");
        //method.setRequestHeader("X-Requested-With","ShockwaveFlash/18.0.0.232");
        method.setRequestEntity(new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf-8"));

        client.executeMethod(method);
        String location = method.getResponseHeader("Location").getValue();
        GetMethod getMethod = new GetMethod("https://online-game.com.cn" + location);
        getMethod.setRequestHeader("Cookie", cookie);
        getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
        getMethod.setRequestHeader("Referer", "https://online-game.com.cn/gameid-t.aspx?gamename=tshao");

        client.executeMethod(getMethod);
        String html = getMethod.getResponseBodyAsString();
        //WP00114908
        //5FXPNGWR6M
        String username = RegexUtil.regex(html, "WP[\\d]{8}");
        Pattern p = Pattern.compile("[0-9A-Za-z]{2}[0-9A-Z]{8}");
        Matcher m = p.matcher(html);
        m.find();
        m.find();
        String password = m.group();
        File file = new File("E:\\DT\\tstd", "account.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(username + " " + password);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
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
