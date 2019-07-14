package com.neo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coderpwh
 * @create 2019-07-14 17:56
 * @desc ${DESCRIPTION
 **/

@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(FallbackProvider.class);

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        logger.info("request  name is "+name);
        return "hello "+name+"，this is first messge";
    }
}
