package com.yunchao.hsh.utils.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangqi on 2018/5/15
 */
public class CheckLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object method) throws Exception {
        Object userName = request.getSession().getAttribute("userName");
        if(userName != null){ //登录过
            //允许继续执行后续流程
            return true;
        }else{
            //未登录
         //   response.sendRedirect("/toLogin");
            System.err.println("拦截器拦截请求！！用户未登录");
            request.getRequestDispatcher("/toLogin").forward(request,response);
            //阻止后续controller等组件流程调用
            return  false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
