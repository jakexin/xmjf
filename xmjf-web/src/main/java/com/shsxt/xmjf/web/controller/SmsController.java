package com.shsxt.xmjf.web.controller;

import com.shsxt.xmjf.api.constant.P2pConstant;
import com.shsxt.xmjf.api.model.ResultInfo;
import com.shsxt.xmjf.api.service.SmsServiceI;
import com.shsxt.xmjf.api.utils.RandomCodesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * dubbo 消费方
 * @author Administrator
 */
@Controller
public class SmsController {

    @Autowired
    private SmsServiceI smsService;

    @RequestMapping("/sendSms")
    @ResponseBody
    public ResultInfo sendSms(String phone, Integer type, String imageCode, HttpSession session) {
        ResultInfo resultInfo=new ResultInfo();
        if(StringUtils.isBlank(imageCode)){
            resultInfo.setCode(P2pConstant.OPS_ERROR_CODE);
            resultInfo.setMessage("图形验证码不能为空!");
            return resultInfo;
        }
        String sessionImageCode= (String) session.getAttribute(P2pConstant.PICTURE_VERIFY_KEY);
        if(StringUtils.isBlank(sessionImageCode)){
            resultInfo.setCode(P2pConstant.OPS_ERROR_CODE);
            resultInfo.setMessage("图形验证码已失效,请重试!");
            return resultInfo;
        }

        if(!(imageCode.equals(sessionImageCode))){
            resultInfo.setCode(P2pConstant.OPS_ERROR_CODE);
            resultInfo.setMessage("图形验证码不正确!");
            return resultInfo;
        }
        session.removeAttribute(P2pConstant.PICTURE_VERIFY_KEY);
        return smsService.sendSms(phone, RandomCodesUtils.createRandom(true,4), type, 600);
    }

}
