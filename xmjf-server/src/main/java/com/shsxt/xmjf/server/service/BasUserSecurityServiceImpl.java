package com.shsxt.xmjf.server.service;

import com.shsxt.xmjf.api.po.BasUserSecurity;
import com.shsxt.xmjf.api.service.BasUserSecurityServiceI;
import com.shsxt.xmjf.server.db.dao.BasUserSecurityMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BasUserSecurityServiceImpl implements BasUserSecurityServiceI {
    @Resource
    private BasUserSecurityMapper basUserSecurityMapper;
    @Override
    public BasUserSecurity queryLoanUserInfoByItemId(Integer itemId) {
        return basUserSecurityMapper.queryLoanUserInfoByItemId(itemId);
    }

    @Override
    public BasUserSecurity queryBasUserSecurityByUserId(Integer userId) {
        return basUserSecurityMapper.queryUserSecurityByUserId(userId);
    }


}
