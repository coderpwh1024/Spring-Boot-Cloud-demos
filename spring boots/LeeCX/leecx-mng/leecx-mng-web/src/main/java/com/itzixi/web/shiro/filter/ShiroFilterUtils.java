package com.itzixi.web.shiro.filter;

import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author coderpwh
 * @Date: 2018/4/27.
 * @Description:
 */
public class ShiroFilterUtils {

    //登录页面
    static final String LOGIN_URL = "/login.shtml";
    //踢出登录提示
    final static String KICKED_OUT = "/login.shtml";
    //没有权限提醒
    final static String UNAUTHORIZED = "/login.shtml";

    // session过期，登录超时
    public static final int HTTP_STATUS_SESSION_EXPIRE = 401;

    // 限制IP请求的错误状态码
    public static final int HTTP_STATUS_LIMIT_IP_REQUEST = 507;

    // 没有权限访问资源
    public static final int HTTP_STATUS_NO_PERMISSIONS = 506;

    /***
     * 判断是否是AjAX请求
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request){
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return  (httpRequest.getHeader("X-Requested-With") != null  && "XMLHttpRequest".equals( httpRequest.getHeader("X-Requested-With").toString()) ) ;
    }


}
