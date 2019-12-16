package com.coderpwh.cloud.eurekaserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author coderpwh
 * @create 2019-12-16 21:46
 * @desc ${DESCRIPTION}
 **/
@RestController
@RequestMapping("/test")
public class UserController {


    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public UserDto test() {

        UserDto userDto = new UserDto();
        userDto.setUserId(123L);
        userDto.setUserNam("张三");
        Date date = new Date();
        userDto.setViewTimeStamp(date.getTime());

        return userDto;
    }

}
