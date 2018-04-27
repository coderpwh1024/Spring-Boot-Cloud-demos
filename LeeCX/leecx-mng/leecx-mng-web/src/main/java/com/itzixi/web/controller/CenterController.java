package com.itzixi.web.controller;


import com.itzixi.common.utils.CookieUtils;
import com.itzixi.common.utils.LeeJSONResult;
import com.itzixi.common.utils.NumberUtil;
import com.itzixi.pojo.SysUser;
import com.itzixi.service.UserService;
import com.itzixi.web.shiro.ShiroPasswordUtil;
import com.itzixi.web.utils.ItzixiCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @Title: CenterController.java
 * @Package com.itzixi.web.controller
 * @Description: CenterController 
 * Copyright: Copyright (c) 2016
 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
 * 
 * @author leechenxiang
 * @date 2017年7月26日 下午2:08:52
 * @version V1.0
 */
@Controller
@RequestMapping("/")
public class CenterController extends BaseController {


	final static Logger log = LoggerFactory.getLogger(CenterController.class);

	@Autowired
	private UserService userService;


	@Autowired
	private ItzixiCaptcha itzixiCaptcha;

	/*@Autowired
	private ItzixiCaptcha itzixiCaptcha;*/
	
	@RequestMapping("/center")
	public ModelAndView index() {
		
    	ModelAndView modelAndView = new ModelAndView("center");
		
		return modelAndView;
	}

	@RequestMapping(value = "/setRedis")
	@ResponseBody
	public LeeJSONResult setRedis(HttpServletRequest request, HttpServletResponse response) {

		jedis.set("redisTemplateTest", "hello redis template~~~", 5000);

		return LeeJSONResult.ok("设置redis成功");
	}


	@RequestMapping(value = "/getRedisValue")
	@ResponseBody
	public LeeJSONResult getRedisValue(HttpServletRequest request, HttpServletResponse response) {

		String redisValue = jedis.get("redisTemplateTest");
		long ttl = jedis.ttl("redisTemplateTest");

		return LeeJSONResult.ok("获取redis成功，值为：" + redisValue + ", 剩余时间ttl为：" + ttl);
	}

	/**
	 *  生成验证码
	 * @param request
	 * @param response
	 */
	@GetMapping("captcha")
	public void captcha(HttpServletRequest request, HttpServletResponse response) {
		itzixiCaptcha.generate(request, response);
	}

	/**
	 * 登录 get
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	 public String doGetlogin(){
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/";
		}
		return "login";
	 }



	/**
	 *   用户登录
	 * @param username
	 * @param password
	 * @param captcha
	 * @param isRememberMe
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public LeeJSONResult doPostlogin(String username, String password, String captcha, @RequestParam(value="isRememberMe", defaultValue="0") Integer isRememberMe, HttpServletRequest request, HttpServletResponse response) {

		if (StringUtils.isBlank(username)) {
			return LeeJSONResult.errorMsg("用户名不能为空");
		}
		if (StringUtils.isBlank(password)) {
			return LeeJSONResult.errorMsg("密码不能为空");
		}
		if (!itzixiCaptcha.validate(request, response, captcha)) {
			return LeeJSONResult.errorMsg("验证码错误, 请重新输入...");
		}

		 Subject user = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(username ,password.toCharArray());

		if(isRememberMe==1){
			token.setRememberMe(true);
		}

		try {
			user.login(token);
		} catch (UnknownAccountException e) {
			return LeeJSONResult.errorMsg("账号不存在");
		} catch (DisabledAccountException e) {
			return LeeJSONResult.errorMsg("账号未启用");
		} catch (IncorrectCredentialsException e) {
			return LeeJSONResult.errorMsg("密码错误");
		} catch (RuntimeException e) {
			return LeeJSONResult.errorMsg("未知错误,请联系管理员");
		}

		return LeeJSONResult.ok();
	}

	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
     Subject subject = SecurityUtils.getSubject();
     subject.logout();
     return "redirect:/login.action";
	}

	/***
	 * 用户注册
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/regist")
	@ResponseBody
	public LeeJSONResult regist(String username, String password, HttpServletRequest request, HttpServletResponse response){

		SysUser user = new SysUser();
		user.setUsername(username);

		// 生成4位随机组合字符作为权限的盐
		String authSalt = NumberUtil.getStringRandom(4);

		user.setPassword(ShiroPasswordUtil.getShiroPassword(password, authSalt, 2));
         user.setAuthSalt(authSalt);
         user.setNickname(username);

          boolean registFlag = userService.saveUser(user);
          if(!registFlag){
			  return LeeJSONResult.errorMsg("注册失败！请返回首页重新注册.");
		  }

		 Subject userShiro =  SecurityUtils.getSubject();
         UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
         userShiro.login(token);
         return  LeeJSONResult.ok();

	}

	/**
	 * cookie存入值
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/setCookie")
	@ResponseBody
	public LeeJSONResult setCookie(HttpServletRequest request, HttpServletResponse response){

		CookieUtils.setCookie(request,response,"leecxBuyShop","cart-bean-json-value", 7*24*60*60);
		return LeeJSONResult.ok("设置cookie成功");
	}


	/**
	 * 获取 cookie中的值
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCookie")
	@ResponseBody
	public LeeJSONResult getCookie(HttpServletRequest request, HttpServletResponse response){

     String cookieValue =  CookieUtils.getCookieValue(request, "leecxBuyShop");

     return LeeJSONResult.ok("获取cookie成功，值为：" + cookieValue);
	}










}
