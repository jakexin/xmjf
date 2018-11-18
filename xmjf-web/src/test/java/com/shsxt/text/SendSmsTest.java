package com.shsxt.text;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试手机短信发送
 */
public class SendSmsTest {

    // 初始化ascClient需要的几个参数
    // 短信API产品名称（短信产品名固定，无需修改）
    public static final String PRODUCE = "Dysmsapi";
    // 短信API产品域名（接口地址固定，无需修改）
    public static final String DOMAIN = "dysmsapi.aliyuncs.com";

    // 替换成你的AK
    public static final String ACCESS_KEY_ID = "LTAIUmb8x11RDwkN";
    public static final String ACCESS_KEY_SECRET = "8Q0r8YrlajzBkgfxkjaxskaAEdV19o";

    // 短信签名
    public static final String SIGN_NAME = "吴宝宇";

    // 短信模板
    public static final String TEMPLATE_CODE = "SMS_141190357";

    public static void main(String[] args) {
        // 设置超时时间-可自行调整
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        try {
            // 初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCE, DOMAIN);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            // 组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            // 使用post提交
            request.setMethod(MethodType.POST);
            // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            request.setPhoneNumbers("13429355233");
            // 必填:短信签名-可在短信控制台中找到
            request.setSignName(SIGN_NAME);
            // 必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
            request.setTemplateCode(TEMPLATE_CODE);
            // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
            Map<String, String> map = new HashMap<>();
            map.put("code", "123");
            request.setTemplateParam(JSON.toJSONString(map));

            // 请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                // 请求成功
                System.out.println("短信发送成功！");
            }else {
                System.out.println("短信发送失败！");
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
