package com.coderpwh.servicefeign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** 定义 feign
 *  @FeignClient(value="service-hi")  是来调用 service-hi 这个服务下的， "/hi" 接口
 */
@FeignClient(value="service-hi")
public interface SchedualServiceHi {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne( @RequestParam(value = "name") String name);


}
