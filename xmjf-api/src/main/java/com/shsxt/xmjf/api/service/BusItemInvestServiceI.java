package com.shsxt.xmjf.api.service;

import java.math.BigDecimal;
import java.util.Map;

public interface BusItemInvestServiceI {

    /**
     * 普通用户投资接口方法定义
     * @param amount  投资金额
     * @param itemId  投资项目if
     * @param userId  投资用户
     * @param payPwd  交易密码
     */
    public  void addBusItemInvest(BigDecimal amount,Integer itemId,Integer userId,String payPwd);

    public Map<String,Object> queryFiveMonthInvestInfo(Integer userId);
}
