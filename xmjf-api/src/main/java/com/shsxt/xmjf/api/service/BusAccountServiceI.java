package com.shsxt.xmjf.api.service;

import com.shsxt.xmjf.api.po.BusAccount;

import java.util.List;
import java.util.Map;

public interface BusAccountServiceI {
    public BusAccount queryBusAccountByUserId(Integer userId);

    public Map<String,Object> queryBusAccountInfoByUserId(Integer userId);
}
