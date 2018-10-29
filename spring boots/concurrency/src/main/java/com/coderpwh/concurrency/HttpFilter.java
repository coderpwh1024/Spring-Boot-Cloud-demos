package com.coderpwh.concurrency;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/29 15:32
 */
@Slf4j
public class HttpFilter implements Filter {


    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("do filter,{},{}", Thread.currentThread().getId(), request.getServletPath());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }



}
