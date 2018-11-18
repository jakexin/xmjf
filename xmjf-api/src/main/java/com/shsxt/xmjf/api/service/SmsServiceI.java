package com.shsxt.xmjf.api.service;

import com.shsxt.xmjf.api.model.ResultInfo;

/**
 * 短信发送服务
 */
public interface SmsServiceI {

    /**
     * 短信发送方法
     * @param phone  手机号
     * @param code   验证码
     * @param type   发送短信类型 1注册 2登录
     * @param second 短信验证码时效时间 单位秒
     */
    public ResultInfo sendSms(String phone, String code, Integer type, Integer second);

}
