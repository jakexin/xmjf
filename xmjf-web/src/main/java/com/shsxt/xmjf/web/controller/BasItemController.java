package com.shsxt.xmjf.web.controller;

import com.shsxt.xmjf.api.model.ResultInfo;
import com.shsxt.xmjf.api.model.UserModel;
import com.shsxt.xmjf.api.po.*;
import com.shsxt.xmjf.api.querys.BasItemQuery;
import com.shsxt.xmjf.api.service.*;
import com.shsxt.xmjf.api.utils.PageList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.parsing.AbstractComponentDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("item")
public class BasItemController {

    @Resource
    private BasItemServiceI basItemService;

    @Resource
    private BusAccountServiceI busAccountService;

    @Resource
    private BasUserSecurityServiceI basUserSecurityService;


    @Resource
    private BusItemLoanServiceI busItemLoanService;

    @Resource
    private SysPictureServiceI sysPictureService;

    @RequestMapping("index")
    public  String index(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
        return "invest_list";
    }

    @RequestMapping("details/{itemId}")
    public  String  details(@PathVariable Integer itemId, HttpServletRequest request, Model model){
        model.addAttribute("ctx",request.getContextPath());
        BasItem basItem= basItemService.queryBasItemById(itemId);
        model.addAttribute("item",basItem);
        UserModel userModel= (UserModel) request.getSession().getAttribute("userInfo");
        if(null !=userModel){
            BusAccount busAccount= busAccountService.queryBusAccountByUserId(userModel.getId());
            model.addAttribute("account",busAccount);
        }

        /**
         * 贷款人用户信息
         */
        BasUserSecurity basUserSecurity=basUserSecurityService.queryLoanUserInfoByItemId(itemId);
        String realName=basUserSecurity.getRealname();
        basUserSecurity.setRealname(realName.substring(0,1)+replaceXing(realName.substring(1,realName.length())));
        basUserSecurity.setIdentifyCard(replaceCardXing(basUserSecurity.getIdentifyCard()));
        model.addAttribute("loanUser",basUserSecurity);


        /**
         * 抵押车辆信息
         */
        BusItemLoan busItemLoan= busItemLoanService.queryItemLoanByItemId(itemId);
        model.addAttribute("busItemLoan",busItemLoan);

        /**w
         * 查询审核图片的信息
         */
        List<SysPicture> pics= sysPictureService.querySysPicturesByItemId(itemId);
        model.addAttribute("pics",pics);
        return "details";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageList queryItemsByParams(BasItemQuery basItemQuery){
        return basItemService.queryBasItemsByParams(basItemQuery);
    }

    @RequestMapping("updateItemToOpen")
    @ResponseBody
    public ResultInfo updateItemToOpen(Integer itemId){
        basItemService.updateItemToOpen(itemId);
        return new ResultInfo();
    }


  /*  public static void main(String[] args) {
        String userName="王大力";
       // userName=userName.substring(0,1);
        String str= replaceXing(userName.substring(1,userName.length()));
        System.out.println(userName.substring(0,1)+str);

        String card="123456789987654321";
        System.out.println(replaceCardXing(card));
    }*/

    public  String replaceXing(String str) {
        StringBuffer stringBuffer=new StringBuffer();
        if(StringUtils.isNotBlank(str)){
           for(int i=0;i<str.length();i++){
              stringBuffer.append("*");
           }
        }
        return stringBuffer.toString();
    }

    public  String replaceCardXing(String str) {
        StringBuffer stringBuffer=new StringBuffer();
        if(StringUtils.isNotBlank(str)){
           stringBuffer=stringBuffer.append(str.substring(0,4)).append(replaceXing(str.substring(4,14))).append(str.substring(14));
        }
        return stringBuffer.toString();
    }

}
