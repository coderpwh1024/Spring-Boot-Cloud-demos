package com.coderpwh.cloud.eurekaserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coderpwh
 * @create 2019-12-16 21:46
 * @desc ${DESCRIPTION}
 **/
@RestController
@RequestMapping("/test")
public class UserController {



    @RequestMapping("/user")
    public Object test(){


        return  null;
    }

}
