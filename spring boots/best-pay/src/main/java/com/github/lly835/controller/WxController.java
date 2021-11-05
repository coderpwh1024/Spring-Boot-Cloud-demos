/*
package com.github.lly835.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.lly835.config.WechatAccountConfig;
import com.github.lly835.config.WxConfig;
import com.github.lly835.constant.WxUrlConstant;
import com.github.lly835.entity.WxUser;
import com.github.lly835.entity.dto.req.OpenIdReq;
import com.github.lly835.resp.ResponseResult;
import com.github.lly835.service.WxUserService;
import com.github.lly835.util.HttpURLConnectionUtil;
import com.github.lly835.util.RedisUtil;

import com.github.lly835.util.WxUtils;
import com.lly835.bestpay.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/wx/")
@Api(value = "微信相关接口", tags = "微信相关接口")
public class WxController {

    private static RestTemplate sender = new RestTemplate();


    @Autowired
    private WechatAccountConfig config;

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;


    RestTemplate restTemplate;

    public void gettoken() {
        String url = WxUrlConstant.getTOkenUrl + "&appid=" + config.getMpAppId() + "&secret=" + config.getMpsecret();
        String ret = sender.getForObject(url, String.class);
    }


    @RequestMapping(value = "/a", method = RequestMethod.GET)
    @ApiOperation(value = "测试数据库和redis", notes = "测试数据库和redis")
    public ResponseResult<?> test() {
        WxUser wxUser = new WxUser();
        wxUser.setOpenid("32141");
        wxUser.setCity("32134");
        wxUser.setGmtCreate(new Date());

        redisTemplate.opsForValue().set("coderpwh", "wx");


        int count = wxUserService.saveUser(wxUser);
        return ResponseResult.buildSuccessResponse(count);
    }

    @RequestMapping(value = "/b", method = RequestMethod.GET)
    public ResponseResult<?> testJson() throws UnsupportedEncodingException {

        String ret = "{\"city\":\"æ·±å³\",\"province\":\"å¹¿ä¸\",\"country\":\"ä¸\u00ADå½\"}";

        JSONObject jsonResult = JSONObject.parseObject(ret);

//        String result = new String(ret.getBytes("ISO-8859-1"), "UTF-8");

        String result = new String(ret.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(result);

        JSONObject json = JSONObject.parseObject(result, JSONObject.class);
        WxUser wxUser = new WxUser();
        wxUser.setCountry(json.getString("country"));
        wxUser.setProvince(json.getString("province"));
        wxUser.setCity(json.getString("city"));


        return ResponseResult.buildSuccessResponse(wxUser);

    }


    @ApiOperation(value = "获取用户openid 接口", notes = "获取用户openid 接口")
    @PostMapping(value = "/get/openid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult<?> getOpenId(@RequestBody OpenIdReq req) {
        return wxUserService.getOpenId(req);
    }


    @RequestMapping("checkSignature")
    public void checkSignature(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        boolean checkSignature = WxUtils.CheckSignature(signature, timestamp, nonce);
        if (checkSignature) {
            System.out.println("success");
            //
            PrintWriter writer = response.getWriter();
            writer.print(echostr);
            writer.flush();
            writer.close();
        } else {
            System.out.println("failed");
        }
    }


    @GetMapping("/signature")
    public ResponseResult<WxConfig> signature(String url) {
        return wxUserService.getWxConfig(url);
    }


    @RequestMapping("/wechatParam")
    public ResponseResult<?> getWXConfigSignature(String url) {
        long timeStampSec = System.currentTimeMillis() / 1000;
        String timestamp = String.format("%010d", timeStampSec);
        //随机字段
        String nonceStr = WxUtils.getRandomString(4, 16);
        //System.out.println("------------------------------------------------");
        String[] urls = url.split("#");
        String newUrl = urls[0];
        //logger.info("随机串："+nonceStr+", 获取签名URL: " + newUrl);
        JSONObject respJson = new JSONObject();
        String wxJsapiTicket = getWXJsapiTicket(config.getMpAppId(), config.getMpsecret());
        String[] signArr = new String[]{"url=" + newUrl, "jsapi_ticket=" + wxJsapiTicket, "noncestr=" + nonceStr, "timestamp=" + timestamp};
        Arrays.sort(signArr);
        String signStr = String.join("&", signArr);
        String resSign = DigestUtils.sha1Hex(signStr);
        //logger.info("返回的签名：" + resSign);
        respJson.put("appId", config.getMpAppId());
        respJson.put("timestamp", timestamp);
        respJson.put("nonceStr", nonceStr);
        respJson.put("signature", resSign);
        respJson.put("url", url);
        respJson.put("jsapi_ticket",wxJsapiTicket);
        System.out.println("appId" + "---" + config.getMpAppId());
        System.out.println("timestamp" + "---" + timestamp);
        System.out.println("nonceStr" + "---" + nonceStr);
        System.out.println("jsapi_ticket" + "---" + wxJsapiTicket);
        System.out.println("signature" + "---" + resSign);
        System.out.println("url" + "---" + url);
        System.out.println("signArr" + "---" + signArr.toString());
        return ResponseResult.buildSuccessResponse(respJson);
    }


    private String getWXJsapiTicket(String appid, String secret) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String ticket = (String) valueOperations.get(appid);
        if (StringUtil.isEmpty(ticket)) {
          */
/*  String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getWXaccessToken(appid,secret) + "&type=jsapi";
            String resp = sender.getForObject(url, String.class);*//*



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
     */
/*   JSONObject paramJsonObject = new JSONObject();
        paramJsonObject.put("access_token", access_token);
        paramJsonObject.put("openid", openid);
        paramJsonObject.put("lang", "zh_CN");

        String ret = HttpURLConnectionUtil.doGet(WxUrlConstant.wx_user, paramJsonObject);*//*



*/
/*      JSONObject paramJsonObject = new JSONObject();
        paramJsonObject.put("grant_type", "client_credential");
        paramJsonObject.put("appid", appid);
        paramJsonObject.put("secret", secret);


        String resp = HttpURLConnectionUtil.doGet(WxUrlConstant.token, paramJsonObject);*//*


        JSONObject resJson = JSONObject.parseObject(resp);
        return resJson.getString("access_token");
    }


}
*/
