package com.shsxt.xmjf.web;

import com.alibaba.fastjson.JSON;

import com.shsxt.xmjf.api.constant.P2pConstant;
import com.shsxt.xmjf.api.exceptions.AuthException;
import com.shsxt.xmjf.api.exceptions.BusiException;
import com.shsxt.xmjf.api.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Created by lp on 2018/1/8.
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if(ex instanceof AuthException){
            try {
                response.sendRedirect(request.getContextPath()+"/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        ModelAndView mv=getDefaultModelAndView(request,ex);



        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod= (HandlerMethod) handler;
            Method method= handlerMethod.getMethod();
            ResponseBody responseBody= method.getAnnotation(ResponseBody.class);
            if(null!=responseBody){
                ResultInfo resultInfo=new ResultInfo();
                resultInfo.setCode(P2pConstant.OPS_ERROR_CODE);
                resultInfo.setMessage(P2pConstant.OPS_ERROR_MSG);
                if(ex instanceof BusiException){
                    BusiException busiException= (BusiException) ex;
                    resultInfo.setCode(busiException.getCode());
                    resultInfo.setMessage(busiException.getMessage());
                }
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter pw =null;
                try {
                    pw=response.getWriter();
                    pw.write(JSON.toJSONString(resultInfo));
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(null!=pw){
                        pw.close();
                    }
                }
                return null;
            }else{
                if(ex instanceof  BusiException){
                    BusiException busiException= (BusiException) ex;
                    mv.addObject("errorMsg",busiException.getMsg());
                    mv.addObject("errorCode",busiException.getCode());
                }
                return mv;
            }
        }else{
            return mv;
        }
    }

    private ModelAndView getDefaultModelAndView(HttpServletRequest request, Exception ex) {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("error");
        mv.addObject("ctx",request.getContextPath());
        mv.addObject("errorMsg",P2pConstant.OPS_ERROR_MSG);
        mv.addObject("errorCode",P2pConstant.OPS_ERROR_CODE);
        mv.addObject("uri",request.getRequestURI());
        return mv;
    }
}
