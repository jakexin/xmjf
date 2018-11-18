package com.shsxt.xmjf.server.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.github.pagehelper.PageHelper;
import com.shsxt.xmjf.api.constant.AlipayConfig;
import com.shsxt.xmjf.api.constant.P2pConstant;
import com.shsxt.xmjf.api.po.BasUserSecurity;
import com.shsxt.xmjf.api.po.BusAccount;
import com.shsxt.xmjf.api.po.BusAccountLog;
import com.shsxt.xmjf.api.po.BusAccountRecharge;
import com.shsxt.xmjf.api.service.BusAccountRechargeServiceI;
import com.shsxt.xmjf.api.utils.PageList;
import com.shsxt.xmjf.server.db.dao.BasUserSecurityMapper;
import com.shsxt.xmjf.server.db.dao.BusAccountLogMapper;
import com.shsxt.xmjf.server.db.dao.BusAccountMapper;
import com.shsxt.xmjf.server.db.dao.BusAccountRechargeMapper;
import com.shsxt.xmjf.server.utils.AssertUtil;
import com.shsxt.xmjf.server.utils.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BusAccountRechargeServiceImpl implements BusAccountRechargeServiceI {
    @Resource
    private BusAccountRechargeMapper busAccountRechargeMapper;

    @Resource
    private BasUserSecurityMapper basUserSecurityMapper;

    @Resource
    private BusAccountMapper busAccountMapper;

    @Resource
    private BusAccountLogMapper busAccountLogMapper;
    @Override
    public String  addBusAccountRechargeInfo(String totalAmount, String payPassword, Integer userId) {
        /**
         * 1.参数校验
         *    非空 totalAmount 纯数字
         *    密码非空  密码正确
         *    用户队登录态
         * 2.添加充值订单记录到数据库
         * 3.发起支付请求获取结果并返回
         */
        checkParams(totalAmount,payPassword,userId);

        /**
         * 添加充值记录
         */
        BusAccountRecharge busAccountRecharge=new BusAccountRecharge();
        busAccountRecharge.setStatus(2);// 审核中
        String orderNo=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        busAccountRecharge.setOrderNo(orderNo);
        busAccountRecharge.setRemark("用户充值操作");
        busAccountRecharge.setResource("PC端用户充值");
        busAccountRecharge.setType(3);// 网页端
        busAccountRecharge.setUserId(userId);
        busAccountRecharge.setAddtime(new Date());
        busAccountRecharge.setRechargeAmount(BigDecimal.valueOf(Double.parseDouble(totalAmount)));
        AssertUtil.isTrue(busAccountRechargeMapper.save(busAccountRecharge)<1, P2pConstant.OPS_ERROR_MSG);

        /**
         * 发起充值请求
         */
        String result=null;
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,
                AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json",
                AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setBizContent("{\"out_trade_no\":\"" +orderNo+ "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + "用户充值"+ "\","
                + "\"body\":\"" + "PC端用户充值" + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }




    public  void updateBusAccountRechargeInfo(String orderNo,String status,BigDecimal totalAmount,Map<String,String[]> requestParams,String sellId,String appId){
        Map<String, String> params = new HashMap<String, String>();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //——请在这里编写您的程序（以下代码仅作参考）——
        /* 实际验证过程建议商户务必添加以下校验：

        */
        System.out.println("交易异步处理...");
        if (signVerified) {//验证成功
            if (status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
                System.out.println("trade_status:TRADE_SUCCESS");

                /**
                 * 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
                 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
                 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
                 4、验证app_id是否为该商户本身。
                 */
                BusAccountRecharge busAccountRecharge=busAccountRechargeMapper.queryBusAccountRechargeByOrderNo(orderNo);
                AssertUtil.isTrue((busAccountRecharge.getStatus().equals(1)),"订单已完成!");
                AssertUtil.isTrue(null==busAccountRecharge,"订单不存在,请联系客服!");
                BigDecimal amount=busAccountRecharge.getRechargeAmount();
                AssertUtil.isTrue(totalAmount.compareTo(amount) !=0,"订单金额存在问题,请联系客服!");
                AssertUtil.isTrue(!(sellId.equals(AlipayConfig.sellId)),"商家信息异常!");
                AssertUtil.isTrue(!(appId.equals(AlipayConfig.app_id)),"商家信息异常!");
                /**
                 *   * 更新商户订单系统数据
                 *   订单知否是否成功
                 *      成功:
                 *         充值记录状态 未支付-->已支付
                 *         账户信息更新
                 *             更新账户总金额
                 *             更新账户可用金额
                 *             更新账户可提现金额
                 *         账户金额日志变动添加(添加账户充值日志记录) -bus_account_log
                 *            添加日志记录到账户日志表
                 *         用户操作统计表
                 *            更新充值金额
                 *            更新充值次数
                 *         用户积分表更新
                 *            更新用户积分(总积分  可用积分)
                 */
                busAccountRecharge.setStatus(1);// 支付成功
                busAccountRecharge.setActualAmount(amount);
                busAccountRecharge.setAuditTime(new Date());
                AssertUtil.isTrue(busAccountRechargeMapper.update(busAccountRecharge)<1,P2pConstant.OPS_ERROR_MSG);

                Integer userId= busAccountRecharge.getUserId();
                BusAccount busAccount= busAccountMapper.queryBusAccountByUserId(userId);
                busAccount.setUsable(busAccount.getUsable().add(amount));
                busAccount.setCash(busAccount.getCash().add(amount));
                busAccount.setTotal(busAccount.getTotal().add(amount));
                AssertUtil.isTrue(busAccountMapper.update(busAccount)<1,P2pConstant.OPS_ERROR_MSG);
                // 账户日志
                BusAccountLog busAccountLog=new BusAccountLog();
                busAccountLog.setCash(busAccount.getCash());
                busAccountLog.setAddtime(new Date());
                busAccountLog.setFrozen(busAccount.getFrozen());
                busAccountLog.setOperMoney(amount);
                busAccountLog.setRemark("用户账户充值操作");
                busAccountLog.setRepay(busAccount.getRepay());
                busAccountLog.setBudgetType(1);
                busAccountLog.setOperType("user_recharge");
                busAccountLog.setTotal(busAccount.getTotal());
                busAccountLog.setUsable(busAccount.getUsable());
                busAccountLog.setWait(busAccount.getWait());
                busAccountLog.setUserId(userId);
                AssertUtil.isTrue(busAccountLogMapper.save(busAccountLog)<1,P2pConstant.OPS_ERROR_MSG);

            }
        }

    }

    @Override
    public PageList queryBusAccountRechargesByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> vals= busAccountRechargeMapper.queryBusAccountRechargesByUserId(userId);
        return new PageList(vals);
    }


    private void checkParams(String totalAmount, String payPassword, Integer userId) {
        AssertUtil.isTrue(StringUtils.isBlank(totalAmount),"请输入充值金额!");
        AssertUtil.isTrue(StringUtils.isBlank(payPassword),"请输入支付密码!");
        BasUserSecurity basUserSecurity= basUserSecurityMapper.queryUserSecurityByUserId(userId);
        AssertUtil.isTrue(!(basUserSecurity.getPaymentPassword().equals(MD5.toMD5(payPassword))),"支付密码错误!");
    }
}
