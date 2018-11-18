package com.shsxt.xmjf.server.db.dao;

import com.shsxt.xmjf.api.po.BasUser;
import com.shsxt.xmjf.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BasUserMapper extends BaseMapper<BasUser>{
    public  BasUser queryBasUserByPhone(@Param("phone") String phone);



}