package cf.hcbzxy.Dianfei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Component
public class SendDianfei {

    public static String getDianfei() {
        //java环境中文传值时，需特别注意字符编码问题
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
        return diangfei;
    }

    @Autowired
    JavaMailSender javaMailSender;


    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("电费来咯");
        message.setFrom("1628880266@qq.com");
        message.setTo("3602869979@qq.com");
        message.setSentDate(new Date());
        message.setText(getDianfei());
        javaMailSender.send(message);
    }


    @Scheduled(cron = "0 0 8 * * ?") // 每天早上八点准时发
    //@Scheduled(cron = "0 10 22 * * ?")  //测试每十秒发一次
    public void Go(){
        sendSimpleMail();
    }

}
