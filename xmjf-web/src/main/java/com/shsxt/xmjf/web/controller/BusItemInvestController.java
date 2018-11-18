package com.shsxt.xmjf.web.controller;

import com.shsxt.xmjf.api.model.ResultInfo;
import com.shsxt.xmjf.api.model.UserModel;
import com.shsxt.xmjf.api.service.BusItemInvestServiceI;
import com.shsxt.xmjf.web.anntations.IsLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("invest")
public class BusItemInvestController {

    @Resource
    private BusItemInvestServiceI busItemInvestService;

    @RequestMapping("doInvest")
    @ResponseBody
    public ResultInfo doInvest(BigDecimal amount, Integer itemId, String payPwd, HttpSession session){
        UserModel userModel= (UserModel) session.getAttribute("userInfo");
        busItemInvestService.addBusItemInvest(amount,itemId,userModel.getId(),payPwd);
        return new ResultInfo();
    }

    @RequestMapping("queryFiveMonthInvestInfo")
    @ResponseBody
    @IsLogin
    public Map<String,Object> queryFiveMonthInvestInfo(HttpSession session){
        UserModel userModel= (UserModel) session.getAttribute("userInfo");
        return  busItemInvestService.queryFiveMonthInvestInfo(userModel.getId());
    }
}
