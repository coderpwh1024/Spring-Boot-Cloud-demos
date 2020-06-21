package com.github.lly835.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.lly835.config.WechatAccountConfig;
import com.github.lly835.config.WxConfig;
import com.github.lly835.constant.WxUrlConstant;
import com.github.lly835.entity.WxPay;
import com.github.lly835.entity.WxPayExample;
import com.github.lly835.entity.WxUser;
import com.github.lly835.entity.WxUserExample;
import com.github.lly835.entity.dto.req.OpenIdReq;;
import com.github.lly835.entity.dto.req.WxPayReq;
import com.github.lly835.mapper.WxPayMapper;
import com.github.lly835.mapper.WxUserMapper;
import com.github.lly835.resp.ResponseResult;
import com.github.lly835.service.WxUserService;
import com.github.lly835.util.HttpURLConnectionUtil;
import com.github.lly835.util.RedisUtil;
import com.github.lly835.util.SignUtil;
import com.lly835.bestpay.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WxUserServiceImpl implements WxUserService {

    private static RestTemplate sender = new RestTemplate();


    @Autowired
    private WxUserMapper wxUserMapper;


    @Autowired
    private WechatAccountConfig wechatAccountConfig;


    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private WxPayMapper wxPayMapper;

    @Autowired
    RedisUtil redisUtil;


    @Override
    public int saveUser(WxUser wxUser) {
        log.info("保存用户信息,入参为:{}", wxUser);

        WxUserExample example = new WxUserExample();
        WxUserExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(wxUser.getOpenid());

        List<WxUser> list = wxUserMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            wxUserMapper.updateByExampleSelective(wxUser, example);

        }
        int count = wxUserMapper.insertSelective(wxUser);
        return count;
    }


    public ResponseResult<WxUser> getOpenId(OpenIdReq req) {
        try {
            String url = WxUrlConstant.getOpenid + "appid=" + wechatAccountConfig.getMpAppId() + "&secret=" + wechatAccountConfig.getMpsecret() + "&code=" + req.getCode() + "&grant_type=" + req.getGrant_type();
            String ret = sender.getForObject(url, String.class);
            if (StringUtils.isNotBlank(ret)) {
                JSONObject json = JSONObject.parseObject(ret);
                log.info("获取微信的openid的结果为:{}", json);
                if (json.getString("errcode") != null && json.getString("errmsg") != null) {
                    return ResponseResult.build(json.getInteger("errcode"), json.getString("errmsg"), null);
                } else {
                    String openid = json.getString("openid");
                    String access_token = json.getString("access_token");
                    String refresh_token = json.getString("refresh_token");
                    String scope = json.getString("scope");
                    // 判断 access_token是否失效
                    testAccessToken(access_token, openid, refresh_token);
                    // 获取微信用户
                    WxUser wxUser = getWxUserInfo(access_token, openid);
                    return ResponseResult.buildSuccessResponse(wxUser);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseResult.build(-1, e.getMessage(), null);
        }

        return ResponseResult.build(-1, "服务异常", null);
    }


    /***
     *  刷新access_token
     * @param
     * @param refresh_token
     */
    public void refreshToken(String refresh_token) {
        String url = WxUrlConstant.acces_token + "appid=" + wechatAccountConfig.getMpAppId() + "&grant_type=" + "refresh_token" + "&refresh_token=" + refresh_token;
        String ret = sender.getForObject(url, String.class);

        if (StringUtils.isNotBlank(ret)) {
            JSONObject json = JSONObject.parseObject(ret);
            log.info("获取微信的openid的结果为:{}", json);
            if (json.getString("errcode") != null && json.getString("errmsg") != null) {
                log.error("刷新access_token失败，结果为:{}", json);
            } else {
                String access_token = json.getString("access_token");
                String openid = json.getString("openid");
                String scope = json.getString("scope");
                refresh_token = json.getString("refresh_token");
                Integer expires_in = json.getInteger("expires_in");
                redisTemplate.opsForValue().set(openid, access_token, expires_in);

            }
        }

    }

    /***
     *   判断access_token 是否有效
     */
    public void testAccessToken(String access_token, String openid, String refresh_token) {
        String url = WxUrlConstant.test_acces_token + "access_token=" + access_token + "&openid=" + openid;
        String ret = sender.getForObject(url, String.class);
        if (StringUtils.isNotBlank(ret)) {

            JSONObject json = JSONObject.parseObject(ret);
            log.info("检验access_token是否有效.返回结果为:{}", json);
            if (json.getString("errcode") != null && json.getString("errmsg") != null) {
                if (json.getInteger("errcode").equals(0)) {
                    log.info("当前用户openid：{}的access_toekn 有效", openid);
                } else {
                    // 刷新 access_token
                    refreshToken(refresh_token);
                }
            }
        }
    }

    /***
     *  获取微信用户
     */
    public WxUser getWxUserInfo(String access_token, String openid) throws UnsupportedEncodingException {

//        access_token = (String) redisTemplate.opsForValue().get(openid);

        JSONObject paramJsonObject = new JSONObject();
        paramJsonObject.put("access_token", access_token);
        paramJsonObject.put("openid", openid);
        paramJsonObject.put("lang", "zh_CN");

        String ret = HttpURLConnectionUtil.doGet(WxUrlConstant.wx_user, paramJsonObject);

      /*  String url = WxUrlConstant.wx_user + "access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
        String ret = sender.getForObject(url, String.class);*/
        WxUser wxUser = new WxUser();
        if (StringUtils.isNotBlank(ret)) {
            log.info("用户结果:{}", ret);
            JSONObject json = JSONObject.parseObject(ret);

        /*    String result = new String(jsonResult.getBytes("ISO-8859-1"), "UTF-8");

            JSONObject json = JSONObject.parseObject(result, JSONObject.class);
*/
            log.info("获取当前用户{},的结果为:{}", openid, json);

            if (json.getString("errcode") != null && json.getString("errmsg") != null) {
                log.error("获取用户信息失败");
            } else {

                openid = json.getString("openid");
                String nickname = json.getString("nickname");
                Integer sex = json.getInteger("sex");
                String province = json.getString("province");
                String city = json.getString("city");
                String country = json.getString("country");
                String headimgurl = json.getString("headimgurl");
                String privilege = json.getString("privilege");
                String unionid = json.getString("unionid");

                wxUser.setOpenid(openid);
                wxUser.setNickname(nickname);
                wxUser.setSex(sex);
                wxUser.setProvince(province);
                wxUser.setCity(city);
                wxUser.setCountry(country);
                wxUser.setHeadimgurl(headimgurl);
                wxUser.setPrivilege(privilege);
                wxUser.setUnionid(unionid);

                WxUserExample example = new WxUserExample();
                WxUserExample.Criteria criteria = example.createCriteria();
                criteria.andOpenidEqualTo(wxUser.getOpenid());

                List<WxUser> list = wxUserMapper.selectByExample(example);
                if (list != null && list.size() > 0) {
//                    wxUserMapper.updateByExampleSelective(wxUser, example);
                    log.info("更新用户信息:{}", wxUser);
                } else {
                    int count = wxUserMapper.insertSelective(wxUser);
                    if (count > 0) {
                        log.info("保存微信用户成功:{}", wxUser);
                    }
                }

            }

        }

        return wxUser;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveWxPay(WxPayReq req) {


        Object obj = redisUtil.get(req.getOrderId());
        if (Objects.isNull(obj)) {
            WxPay wxPay = new WxPay();
            wxPay.setPhone(req.getPhone());
            wxPay.setName(req.getName());
            wxPay.setGrade(req.getGrade());
            wxPay.setWxClass(req.getWxClass());
            wxPay.setAmount(req.getAmount());
            wxPay.setOrderid(req.getOrderId());
            wxPay.setOpenid(req.getOpenid());
            wxPay.setState(0);
            int count = wxPayMapper.insertSelective(wxPay);
            redisTemplate.opsForValue().set(req.getOrderId(), req.getOpenid());
            redisUtil.set(req.getOrderId(), req.getOpenid(), 90);
            log.info("保存用户下单提交信息:{}", req);
            return count;
        } else {
            log.info("用户重复提交下单信息:{}", req);
        }
        return 0;
    }

    /***
     *
     * @param orderId
     * @return
     */
    @Override
    public int updateByOrderId(String orderId) {
        log.info("支付回调开始... 入参为:{}", orderId);

        WxPayExample example = new WxPayExample();
        WxPayExample.Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(orderId);

        List<WxPay> list = wxPayMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            WxPay wxPay = list.get(0);
            wxPay.setState(1);
            int count = wxPayMapper.updateByExample(wxPay, example);
            if (count > 0) {
                log.info("商户订单号:{} 更新支付信息成功....", orderId);
            } else {
                log.info("商户订单号:{} 更新支付信息失败....", orderId);
            }

        }
        return 0;
    }

    @Override
    public ResponseResult<WxConfig> getWxConfig(String url) {

        String ticket = getWXJsapiTicket(wechatAccountConfig.getMpAppId(), wechatAccountConfig.getMpsecret());
        WxConfig wxConfig = SignUtil.getConfig(ticket, url);
        return ResponseResult.buildSuccessResponse(wxConfig);
    }

    private String getWXJsapiTicket(String appid, String secret) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String ticket = (String) valueOperations.get(appid);
        if (StringUtil.isEmpty(ticket)) {
            JSONObject paramJsonObject = new JSONObject();
            paramJsonObject.put("access_token", getWXaccessToken(appid, secret));
            paramJsonObject.put("type", "jsapi");
            String resp = HttpURLConnectionUtil.doGet(WxUrlConstant.tickentUrl, paramJsonObject);
            JSONObject resJson = JSONObject.parseObject(resp);
            valueOperations.set(appid, resJson.getString("ticket"), 2, TimeUnit.HOURS);
            return resJson.getString("ticket");
        }
        return ticket;
    }

    private String getWXaccessToken(String appid, String secret) {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        String resp =sender.getForObject(url, String.class);
        JSONObject resJson = JSONObject.parseObject(resp);
        return resJson.getString("access_token");
    }


}
