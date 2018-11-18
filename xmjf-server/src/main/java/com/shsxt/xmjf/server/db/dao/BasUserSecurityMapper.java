package com.shsxt.xmjf.server.db.dao;

import com.shsxt.xmjf.api.po.BasUserSecurity;
import com.shsxt.xmjf.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BasUserSecurityMapper extends BaseMapper<BasUserSecurity>{
    public  BasUserSecurity queryLoanUserInfoByItemId(@Param("itemId") Integer itemId);

    public  BasUserSecurity queryUserSecurityByUserId(@Param("userId")Integer userId);


    public  BasUserSecurity queryBasUserSecurityByCardNo(@Param("cardNo")String cardNo);

}