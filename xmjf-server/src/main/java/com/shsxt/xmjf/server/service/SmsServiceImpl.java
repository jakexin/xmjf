package com.shsxt.xmjf.server.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.shsxt.xmjf.api.constant.P2pConstant;
import com.shsxt.xmjf.api.model.ResultInfo;
import com.shsxt.xmjf.api.service.SmsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信发送服务的具体实现 dubbo提供方
 */
@Service
public class SmsServiceImpl implements SmsServiceI {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public ResultInfo sendSms(String phone, String code, Integer type, Integer second) {
        if (null != type && 1 == type) {
            // 注册
            return doSendSms(phone, code, P2pConstant.TEMPLATE_REGISTER_CODE, second);
        }else if (null != type && 2 == type) {
            // 登录
            return doSendSms(phone, code, P2pConstant.TEMPLATE_LOGIN_CODE, second);
        }else {
            System.out.println("无效的短信类型");
            return ResultInfo.error();
        }
    }

    // 具体短信发送的实现代码
    public ResultInfo doSendSms(String phone, String code, String templateCode, Integer second) {
        try {
            // 初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", P2pConstant.ACCESS_KEY_ID, P2pConstant.ACCESS_KEY_SECRET);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", P2pConstant.PRODUCE, P2pConstant.DOMAIN);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            // 组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            // 使用post提交
            request.setMethod(MethodType.POST);
            // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
            request.setPhoneNumbers(phone);
            // 必填:短信签名-可在短信控制台中找到
            request.setSignName(P2pConstant.SIGN_NAME);
            // 必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
            request.setTemplateCode(templateCode);
            // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");

            // 随机生成4位验证码
            Map<String, String> map = new HashMap<>();
            map.put("code", code);

            request.setTemplateParam(JSON.toJSONString(map));

            // 请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                // 请求成功
                System.out.println("短信发送成功！");

                // 将验证码存入redis 设置超时时间
                ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
                String key = "code:" + templateCode + ":phone:" + phone;
                valueOperations.set(key, code, second, TimeUnit.SECONDS);

                return ResultInfo.success();
            }else {
                System.out.println("短信发送失败！");
                return ResultInfo.error();
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return ResultInfo.error();
    }

}
