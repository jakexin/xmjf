package com.shsxt.xmjf.server.db.dao;

import com.shsxt.xmjf.api.po.User;

public interface UserDaoI {

    User queryUserById(Integer userId);

}
