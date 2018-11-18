package com.shsxt.xmjf.web.proxy;

import com.shsxt.xmjf.api.exceptions.AuthException;
import com.shsxt.xmjf.api.model.UserModel;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

//@Component
//@Aspect
public class NoAccessPorxy {

    @Autowired
    private HttpSession session;

    @Pointcut("@annotation(com.shsxt.xmjf.web.anntations.IsLogin)")
    public  void cut(){}

    @Before(value = "cut()")
    public  void before(){
        System.out.println("方法执行前输出...");
        UserModel userModel= (UserModel) session.getAttribute("userInfo");
        if(null ==userModel){
           throw new AuthException("用户未登录!!!");
        }
        //System.out.println(userModel);
    }

}
