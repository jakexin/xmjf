package com.shsxt.xmjf.server.db.dao;

import com.shsxt.xmjf.api.po.BusItemLoan;
import com.shsxt.xmjf.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BusItemLoanMapper  extends BaseMapper<BusItemLoan>{

    public  BusItemLoan queryItemLoanByItemId(@Param("itemId")Integer itemId);

}