package com.shsxt.xmjf.api.service;

import com.shsxt.xmjf.api.po.BasUserSecurity;

/**
 * @author Administrator
 */
public interface BasUserSecurityServiceI {
    public BasUserSecurity queryLoanUserInfoByItemId(Integer itemId);

    public  BasUserSecurity queryBasUserSecurityByUserId(Integer userId);
}
