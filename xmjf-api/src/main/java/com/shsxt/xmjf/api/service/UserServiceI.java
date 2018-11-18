package com.shsxt.xmjf.api.service;

import com.shsxt.xmjf.api.model.UserModel;
import com.shsxt.xmjf.api.po.BasUser;
import com.shsxt.xmjf.api.po.User;

/**
 * 提供用户查询服务
 */
public interface UserServiceI {

    BasUser queryUserById(Integer userId);

    void saveUser(String phone,String password,String code);

    public UserModel userLogin(String phone, String password);


    public  UserModel quickLogin(String phone,String code);


    public  void checkUserIsConfirmInfo(Integer userId);

    public void updateUserRealNameStatus(String realName, String cardNo, String payPassword, String confirmPassword, Integer userId);
}
