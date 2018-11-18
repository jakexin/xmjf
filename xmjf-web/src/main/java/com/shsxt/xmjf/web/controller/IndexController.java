package com.shsxt.xmjf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        request.setAttribute("ctx", request.getContextPath());
        return "index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        request.setAttribute("ctx", request.getContextPath());
        return "login";
    }

    @RequestMapping("/quickLogin")
    public String quickLogin(HttpServletRequest request) {
        request.setAttribute("ctx", request.getContextPath());
        return "quick_login";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request) {
        request.setAttribute("ctx", request.getContextPath());
        return "register";
    }
    @RequestMapping("/setting")
    public String setting(HttpServletRequest request) {
        request.setAttribute("ctx", request.getContextPath());
        return "setting";
    }
    @RequestMapping("/success")
    public String success(HttpServletRequest request) {
        request.setAttribute("ctx", request.getContextPath());
        return "success";
    }

}
