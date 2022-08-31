package cf.hcbzxy.Push;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class tianqi {

    public static String[] request() {
        //java环境中文传值时，需特别注意字符编码问题
        String httpUrl = "https://v0.yiketianqi.com/api?unescape=1&version=v62&appid=12116879&appsecret=xsd0X8bh";

        BufferedReader reader = null;
        String result = null;
        String[] strs = null;
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
            strs = str.split("\"");
            /*for (int i = 0, len = strs.length; i < len; i++) {
                System.out.println(strs[i].toString());
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

    public static String getCity() {
        String[] strs = request();
        String city = strs[3];
        System.out.println(city);
        return city;
    }

    public static String getWeather() {
        String[] strs = request();
        String weather = strs[35];
        System.out.println(weather);
        return weather;
    }

    public static String getDate() {
        String[] strs = request();
        String date = strs[7];
        System.out.println(date);
        return date;
    }

    public static String getWeek() {
        String[] strs = request();
        String week = strs[11];
        System.out.println(week);
        return week;
    }

    public static String getMin_temperature() {
        String[] strs = request();
        String minT = strs[43];
        System.out.println(minT);
        return minT;
    }

    public static String getMax_temperature() {
        String[] strs = request();
        String maxT = strs[47];
        System.out.println(maxT);
        return maxT;
    }

    public static void main(String[] args) {
        tianqi.getCity();
    }
}
