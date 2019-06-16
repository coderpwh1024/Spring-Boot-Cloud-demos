package com.neo.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author coderpwh
 * @create 2019-06-16 14:42
 * @desc ${DESCRIPTION}
 **/
@FeignClient(name="spring-cloud-producer")
public interface HelloRemote {

    @RequestMapping(value = "/hello")
    public   String hello(@RequestParam(value = "name") String name);

}
