package com.shsxt.xmjf.server.db.dao;

import com.shsxt.xmjf.api.po.BusUserIntegral;
import com.shsxt.xmjf.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BusUserIntegralMapper extends BaseMapper<BusUserIntegral>{
    public  BusUserIntegral queryBusUserIntegralByUserId(@Param("userId") Integer userId);

}