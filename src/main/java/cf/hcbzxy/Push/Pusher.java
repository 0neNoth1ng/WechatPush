package cf.hcbzxy.Push;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @ClassName Pusher
 * @Description TODO
 * @Author OneNoth1ng
 * @Date 2022/8/2
 */
@Component
public class Pusher {

    static String hcb = "ocsDd6nQgMrwQwp4RouDIRDktuq8";
    static String zxy = "ocsDd6rDU4jXIFGyf3jSYOA0q1Ec";

    @Scheduled(cron = "0 0 8 * * ?") // 每天早上八点准时发
    public void Go() throws ParseException {
        push(hcb, "oBGE1t7J_pS4y2yHsMWEoYcTDzlSsFcrGkj14Ss_m8k");
        push(zxy, "oBGE1t7J_pS4y2yHsMWEoYcTDzlSsFcrGkj14Ss_m8k");
    }

    private static String appId = "wxf6619c0aea6ce00f";
    private static String secret = "3f2c846e42968cd04ad030c1d91f34bd";


    public static void push(String openId, String templateId) throws ParseException {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(templateId)
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        templateMessage.addData(new WxMpTemplateData("city", "湖北武汉江夏", "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("date", tianqi.getDate(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("week", tianqi.getWeek(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("weather", tianqi.getWeather(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("min_temperature", tianqi.getMin_temperature(), "#57E0EF"));
        templateMessage.addData(new WxMpTemplateData("max_temperature", tianqi.getMax_temperature(), "#F77F65"));
        templateMessage.addData(new WxMpTemplateData("love_day", jinianri.getJinianri(), "#FF6347"));
        templateMessage.addData(new WxMpTemplateData("caihongpi", caihongpi.request(), "#FF69B4"));
        /*templateMessage.addData(new WxMpTemplateData("lianai",JiNianRi.getLianAi()+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri",JiNianRi.getShengRi()+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("jinju",CaiHongPi.getJinJu()+"","#C71585"));
        //templateMessage.addData(new WxMpTemplateData("jiehun",JiNianRi.getJieHun()+""));
        templateMessage.addData(new WxMpTemplateData("linzhen",JiNianRi.getLinZhen()+"","#FF6347"));*/

        /*String beizhu = "情人节快乐！";
        if(JiNianRi.getJieHun() % 365 == 0){
            beizhu = "今天是结婚纪念日！";
        }
        if(JiNianRi.getLianAi() % 365 == 0){
            beizhu = "今天是恋爱纪念日！";
        }
        if(JiNianRi.getLinZhen() % 365 == 0){
            beizhu = "今天是结婚纪念日！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));*/

        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败 ：" + e.getMessage());
            e.printStackTrace();
        }
    }
}