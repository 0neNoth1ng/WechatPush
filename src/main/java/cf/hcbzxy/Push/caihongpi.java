package cf.hcbzxy.Push;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class caihongpi {
    public static String request() {
        //java环境中文传值时，需特别注意字符编码问题
        String httpUrl = "http://api.tianapi.com/caihongpi/index";
        String httpArg = "key=1e0acd70898f2c724ba94bbc990e0132";

        httpUrl = httpUrl + "?" + httpArg;

        BufferedReader reader = null;
        String result = null;
        String[] strs=null;
        StringBuffer sbf = new StringBuffer();
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
            strs=str.split("\"");
            for(int i=0,len=strs.length;i<len;i++){
                System.out.println(strs[i].toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return strs[11];
    }

    public static void main(String[] args) {
        String request = caihongpi.request();

    }
}
