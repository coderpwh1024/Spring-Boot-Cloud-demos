package com.neo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coderpwh
 * @create 2019-07-06 16:30
 * @desc ${DESCRIPTION}
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return ""+name+"，this is two messge";
    }


}
