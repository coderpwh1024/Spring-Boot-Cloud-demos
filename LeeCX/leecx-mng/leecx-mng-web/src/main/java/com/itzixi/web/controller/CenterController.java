package com.itzixi.web.controller;

import com.itzixi.common.utils.LeeJSONResult;
import com.itzixi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
}
