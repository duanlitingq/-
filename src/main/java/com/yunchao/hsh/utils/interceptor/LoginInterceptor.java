package com.yunchao.hsh.utils.interceptor;

import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.utils.Constant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    private static final String[] LOGIN_URL={"/toLogin","/page/admin/login.html","/page/admin/sysUser/login"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        boolean flag=false;
        String url = request.getRequestURI() ;
        for (int i = 0 ; i < LOGIN_URL.length ; i ++){
            if(LOGIN_URL[i].equals(url)){
                flag=true;
                break;
            }
        }
        if(!flag){
            SysUser sysUser=(SysUser) request.getSession().getAttribute(Constant.LOGIN_USER);
            if(sysUser==null){
                System.err.println("拦截器拦截请求！！");
                request.setAttribute("message","请先登录后在登录网站");
                request.getRequestDispatcher("/toLogin").forward(request,response);
            }else {
                flag = true ;
            }
        }
        return flag;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//      System.out.println("postHandle()------》");
    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//     System.out.println("afterCompletion()-------》");
    }

}
