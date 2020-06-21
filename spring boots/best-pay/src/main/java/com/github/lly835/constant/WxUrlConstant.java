package com.github.lly835.constant;

public class WxUrlConstant {


    /***
     *  获取token
     */
    public static final String getTOkenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";


    public static final String token = "https://api.weixin.qq.com/cgi-bin/token";


    /***
     * 获取openid
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     *
     */
    public static final String getOpenid = "https://api.weixin.qq.com/sns/oauth2/access_token?";


    /***
     *  刷新token
     */
    public static final String acces_token = "https://api.weixin.qq.com/sns/oauth2/refresh_token?";


    /***
     *  判断 access_token是否有效
     */
    public static final String test_acces_token = "https://api.weixin.qq.com/sns/auth?";


    /**
     * 获取微信用户胡
     */
    public static final String wx_user = "https://api.weixin.qq.com/sns/userinfo";

    public static final String tickentUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";


}
