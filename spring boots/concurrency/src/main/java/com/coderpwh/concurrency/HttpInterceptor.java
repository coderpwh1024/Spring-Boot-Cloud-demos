package com.coderpwh.concurrency;

import com.coderpwh.concurrency.example.threadLocal.RequestHolder;
import org.slf4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/29 16:06
 */
public class HttpInterceptor extends HandlerInterceptorAdapter {

    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("preHandle");
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        RequestHolder.remove();

        log.info("afterCompletion");
        return;

    }
}
