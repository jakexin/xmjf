package com.shsxt.xmjf.server.service;

import com.shsxt.xmjf.api.po.BusItemLoan;
import com.shsxt.xmjf.api.service.BusItemLoanServiceI;
import com.shsxt.xmjf.server.db.dao.BusItemLoanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BasItemLoanServiceImpl implements BusItemLoanServiceI {
    @Resource
    private BusItemLoanMapper busItemLoanMapper;
    @Override
    public BusItemLoan queryItemLoanByItemId(Integer itemId) {
        return busItemLoanMapper.queryItemLoanByItemId(itemId);
    }
}
