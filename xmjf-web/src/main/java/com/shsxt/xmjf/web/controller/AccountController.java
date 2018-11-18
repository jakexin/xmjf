package com.shsxt.xmjf.web.controller;

import com.shsxt.xmjf.api.model.UserModel;
import com.shsxt.xmjf.api.service.BasUserSecurityServiceI;
import com.shsxt.xmjf.api.service.BusAccountRechargeServiceI;
import com.shsxt.xmjf.api.service.BusAccountServiceI;
import com.shsxt.xmjf.api.utils.PageList;
import com.shsxt.xmjf.web.anntations.IsLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("account")
public class AccountController {

    @Resource
    private BasUserSecurityServiceI basUserSecurityService;

    @Resource
    private BusAccountRechargeServiceI busAccountRechargeService;

    @Resource
    private BusAccountServiceI busAccountService;
    @RequestMapping("index")
    @IsLogin
    public  String index(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
        UserModel userModel = (UserModel) request.getSession().getAttribute("userInfo");
        request.setAttribute("security",basUserSecurityService.queryBasUserSecurityByUserId(userModel.getId()));
        return "setting";
    }

    @RequestMapping("recharge")
    public  String recharge(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
        return "recharge";
    }


    @RequestMapping("rechargeRecord")
    public  String toRechargeRecodePage(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
        return "recharge_record";
    }


    @RequestMapping("recharges")
    @ResponseBody
    public PageList queryBusAccountRechargesByUserId(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        UserModel userModel= (UserModel) session.getAttribute("userInfo");
        return busAccountRechargeService.queryBusAccountRechargesByUserId(userModel.getId(),pageNum,pageSize);
    }


    @RequestMapping("accountInfo")
    @IsLogin
    public  String accountInfo(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
        return "account_info";
    }


    @RequestMapping("queryBusAccountInfoByUserId")
    @ResponseBody
    public Map<String,Object> queryBusAccountInfoByUserId(HttpSession session){
        UserModel userModel= (UserModel) session.getAttribute("userInfo");
        return  busAccountService.queryBusAccountInfoByUserId(userModel.getId());
    }
}
