package com.shsxt.xmjf.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.shsxt.xmjf.api.constant.AlipayConfig;
import com.shsxt.xmjf.api.constant.P2pConstant;
import com.shsxt.xmjf.api.exceptions.BusiException;
import com.shsxt.xmjf.api.model.UserModel;
import com.shsxt.xmjf.api.service.BusAccountRechargeServiceI;
import com.shsxt.xmjf.web.anntations.IsLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付模块
 * 1.支付请求发起
 * <p>
 * <p>
 * <p>
 * 2.支付回调操作
 * @author Administrator
 */

@Controller
@RequestMapping("pay")
public class AlipayController {

    @Resource
    private BusAccountRechargeServiceI busAccountRechargeService;

    @RequestMapping("toPay")
    @IsLogin
    public String toPay(String total_amount, String imageCode, String payPassword,
                        HttpSession session, Model model) {
        try {
            UserModel userModel = (UserModel) session.getAttribute("userInfo");
            String result = busAccountRechargeService.addBusAccountRechargeInfo(total_amount, payPassword, userModel.getId());
            model.addAttribute("formStr", result);
        } catch (BusiException e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getMsg());
            return "forward:/account/recharge";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", P2pConstant.OPS_ERROR_MSG);
            return "forward:/account/recharge";
        }
        return "pay";
    }


    /**
     * 异步回调
     */
    @RequestMapping(value = "notifyCallback", method = RequestMethod.POST)
    public String callback(HttpServletRequest request) {
        System.out.println("支付异步通知方法被调用...");
        //获取支付宝POST过来反馈信息
        Map<String, String[]> requestParams = request.getParameterMap();
       // busAccountRechargeService.updateBusAccountRechargeInfo();
        return "success";
    }


    @RequestMapping(value = "returnCallBack",method = RequestMethod.GET)
    public  String  callback02(HttpServletRequest request){
        try {
            //获取支付宝GET过来反馈信息
            Map<String,String> params = new HashMap<String,String>();
            Map<String,String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                    String name = (String) iter.next();
                    String[] values = (String[]) requestParams.get(name);
                    String valueStr = "";
                    for (int i = 0; i < values.length; i++) {
                        valueStr = (i == values.length - 1) ? valueStr + values[i]
                                : valueStr + values[i] + ",";
                    }
                //乱码解决，这段代码在出现乱码时使用
               // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
            //——请在这里编写您的程序（以下代码仅作参考）——
            if(signVerified) {
            /*    //商户订单号
                String out_trade_no =request.getParameter("out_trade_no");

                //支付宝交易号
                String trade_no = request.getParameter("trade_no");

                //付款金额
                String total_amount =request.getParameter("total_amount");

                System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
                request.setAttribute("orderNo",out_trade_no);
                request.setAttribute("tradeNo",trade_no);
                request.setAttribute("totalAmount",total_amount);*/
               return "result";
            }else {
                System.out.println("验签失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return "result";
    }
}
