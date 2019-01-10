package com.yunchao.hsh.utils.basefileter;

import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.utils.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by wangqi on 2017/11/10
 */
public class LoginFilter implements Filter {
    private static final String[] LOGIN_URL={"/login","/login.jsp"};
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();
        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();
        SysUser sysUser = (SysUser) session.getAttribute(Constant.LOGIN_USER);
        for (String str: LOGIN_URL){
            if(path.contains(str)){
                chain.doFilter(servletRequest, servletResponse);
                return ;
            }
        }
        if (sysUser == null) {
            servletResponse.sendRedirect("/jsp/login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
