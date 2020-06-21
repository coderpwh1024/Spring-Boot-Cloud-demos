package com.github.lly835.service;


import com.github.lly835.config.WxConfig;
import com.github.lly835.entity.WxUser;
import com.github.lly835.entity.dto.req.OpenIdReq;
import com.github.lly835.entity.dto.req.WxPayReq;
import com.github.lly835.resp.ResponseResult;

public interface WxUserService {

    /***
     *
     *  添加用户
     * @param wxUser
     * @return
     */
    int saveUser(WxUser wxUser);

    /***
     *  获取微信
     * @param req
     * @return
     */
    ResponseResult<WxUser> getOpenId(OpenIdReq req);


    int  saveWxPay(WxPayReq req);

    int updateByOrderId(String orderId);

    ResponseResult<WxConfig> getWxConfig(String url);
}
