package cf.hcbzxy.Push;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class jinianri {

    public static String getJinianri() throws ParseException {
        String birStr = "2020-11-28";
        //设置生日字符串解析格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //解析生日字符串，并转换为Data对象
        Date bir = sdf.parse(birStr);
        //创建当前系统时间对象
        Date now = new Date();
        String day = String.valueOf((now.getTime()-bir.getTime())/1000/60/60/24);
        return day;
    }

}
