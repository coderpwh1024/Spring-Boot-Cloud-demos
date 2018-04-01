package com.imooc.contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 彭文浩 on 2018/4/1.
 */
@RestController
public class HelloContoller {

     @RequestMapping("/hello")
    public Object hello(){

          return "hello springboot~~";
    }

}
