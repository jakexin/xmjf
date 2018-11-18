package com.shsxt.xmjf.server.db.dao;

import com.shsxt.xmjf.api.po.BusAccount;
import com.shsxt.xmjf.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface BusAccountMapper extends BaseMapper<BusAccount>{
    public BusAccount queryBusAccountByUserId(@Param("userId") Integer userId);


    public Map<String,Object>  queryBusAccountInfoByUserId(@Param("userId")Integer userId);

}