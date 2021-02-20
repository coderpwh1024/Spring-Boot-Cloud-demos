package com.coderpwh.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/config")
public class ConfigController {


    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @NacosValue("${user.name}")
    private String userName;

    @NacosValue("${user.age}")
    private String userAge;

    @NacosValue("${user.code}")
    private String code;


    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        System.out.println("username:" + userName);
        System.out.println("userage:" + userAge);
        System.out.println("code:" + code);
        return useLocalCache;
    }

}
