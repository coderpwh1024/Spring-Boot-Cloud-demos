package com.neo.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author coderpwh
 * @create 2019-06-17 23:24
 * @desc ${DESCRIPTION}
 **/
@Component
public class HelloRemoteHystrix implements  HelloRemote {

    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "hello " +name+", this messge send failed ";
    }

}
