package com.yunchao.hsh.utils.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangqi on 2018/10/29
 */
public class ContextFiilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse rsp = (HttpServletResponse) response;
        rsp.setHeader("Access-Control-Allow-Origin", "*");
        rsp.setHeader("Access-Control-Allow-Headers", "Authentication");

        chain.doFilter(request, rsp);
    }

    @Override
    public void destroy() {

    }
}
