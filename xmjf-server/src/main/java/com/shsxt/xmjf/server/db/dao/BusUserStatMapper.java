package com.shsxt.xmjf.server.db.dao;

import com.shsxt.xmjf.api.po.BusUserStat;
import com.shsxt.xmjf.api.po.BusUserStatKey;
import com.shsxt.xmjf.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BusUserStatMapper extends BaseMapper<BusUserStat>{

    public  BusUserStat queryBusUserStatByUserId(@Param("userId") Integer userId);

}