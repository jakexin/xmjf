package com.shsxt.xmjf.server.db.dao;

import com.shsxt.xmjf.api.po.BusAccountRecharge;
import com.shsxt.xmjf.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BusAccountRechargeMapper extends BaseMapper<BusAccountRecharge> {

    public  BusAccountRecharge queryBusAccountRechargeByOrderNo(@Param("orderNo")String orderNo);

    public List<Map<String,Object>>  queryBusAccountRechargesByUserId(@Param("userId")Integer userId);


}