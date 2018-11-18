package com.shsxt.xmjf.api.service;

import com.shsxt.xmjf.api.utils.PageList;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Administrator
 */
public interface BusAccountRechargeServiceI {


    /**
     * 用户充值请求发起方法定义
     * @param totalAmount
     * @param payPassword
     * @param userId
     */
    public  String  addBusAccountRechargeInfo(String totalAmount,String payPassword,Integer userId);

    public  void  updateBusAccountRechargeInfo(String orderNo, String status, BigDecimal totalAmount, Map<String,String[]> requestParams, String sellId, String appId);


    public PageList queryBusAccountRechargesByUserId(Integer userId,Integer pageNum,Integer paseSize);
}
