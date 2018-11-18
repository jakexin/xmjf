package com.shsxt.xmjf.web.controller;

import com.shsxt.xmjf.api.constant.P2pConstant;
import com.shsxt.xmjf.api.model.ResultInfo;
import com.shsxt.xmjf.api.model.UserModel;
import com.shsxt.xmjf.api.po.BasUser;
import com.shsxt.xmjf.api.service.UserServiceI;
import com.shsxt.xmjf.web.anntations.IsLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceI userService;

    @RequestMapping("/user/{userId}")
    @ResponseBody
    public BasUser queryUserById(@PathVariable("userId") Integer userId) {
        return userService.queryUserById(userId);
    }


    @ResponseBody
    @PostMapping("register")
    public ResultInfo register(String phone,String code,String password){
        ResultInfo resultInfo=new ResultInfo();
        try {
            userService.saveUser(phone,password,code);
            resultInfo.setMessage("用户注册成功!");
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(P2pConstant.OPS_ERROR_CODE);
            resultInfo.setMessage(P2pConstant.OPS_ERROR_MSG);
        }
        return resultInfo;
    }




//    @ResponseBody
//    @PostMapping("register")
//    public ResultInfo register(String phone, String code, String password) {
//        ResultInfo resultInfo = new ResultInfo();
//        userService.saveUser(phone, password, code);
//        resultInfo.setMessage("用户注册成功!");
//        return resultInfo;
//    }


    @ResponseBody
    @RequestMapping("userLogin")
    public ResultInfo userLogin(String phone, String password, HttpSession session) {
        ResultInfo resultInfo = new ResultInfo();
        UserModel userModel = userService.userLogin(phone, password);
        session.setAttribute("userInfo", userModel);
        return resultInfo;
    }


    @ResponseBody
    @RequestMapping("quickLogin")
    public ResultInfo quickLogin(String phone, String code, HttpSession session) {
        ResultInfo resultInfo = new ResultInfo();
        UserModel userModel = userService.quickLogin(phone, code);
        session.setAttribute("userInfo", userModel);
        return resultInfo;
    }

    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.removeAttribute("userInfo");
        return "redirect:/login";
    }



    @RequestMapping("checkUserIsConfirmInfo")
    @ResponseBody
    public  ResultInfo checkUserIsConfirmInfo(HttpSession session){
        UserModel userModel= (UserModel) session.getAttribute("userInfo");
        userService.checkUserIsConfirmInfo(userModel.getId());
        return new ResultInfo();
    }


    @RequestMapping("auth")
    @IsLogin
    public  String toAuth(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
        return "auth";
    }


    @RequestMapping("doAuth")
    @ResponseBody
    @IsLogin
    public  ResultInfo doAuth(String realName,String cardNo,String payPassword,String confirmPassword,HttpSession session){
        UserModel userModel= (UserModel) session.getAttribute("userInfo");
        userService.updateUserRealNameStatus(realName,cardNo,payPassword,confirmPassword,userModel.getId());
        return new ResultInfo();
    }




}
