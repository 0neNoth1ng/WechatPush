package cf.hcbzxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@SpringBootTest
class WechatPushApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    JavaMailSender javaMailSender;
    @Test
    public void sendSimpleMail() {
        String httpUrl = "https://wap.itzo.cn/api/519.php";

        BufferedReader reader = null;
        String result = null;
        String[] strs = null;
        StringBuffer sbf = new StringBuffer();
        String diangfei = null;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;

            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            String str = result;
            strs = str.split("=>");
            diangfei = strs[3].substring(0, 15);
            /*for (int i = 0, len = strs.length; i < len; i++) {
                System.out.println(strs[i].toString());
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("电费来咯！");
        message.setFrom("1628880266@qq.com");
        message.setTo("3602869979@qq.com");
        message.setSentDate(new Date());
        message.setText(diangfei);
        javaMailSender.send(message);
    }


}
