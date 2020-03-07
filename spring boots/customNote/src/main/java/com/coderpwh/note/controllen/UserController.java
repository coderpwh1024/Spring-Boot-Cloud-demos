package com.coderpwh.note.controllen;

import com.coderpwh.note.note.CoderLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coderpwh
 * @create 2020-03-06 15:37
 * @desc ${DESCRIPTION}
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger("");

    @RequestMapping(value = "/get/userInfo", method = RequestMethod.GET)
    @CoderLog("专门为慧慧童鞋写的注解")
    public Object getUserInfo(@RequestParam("userName") String userName) {
        logger.info("开始调用接口了");
        return userName;
    }


}
