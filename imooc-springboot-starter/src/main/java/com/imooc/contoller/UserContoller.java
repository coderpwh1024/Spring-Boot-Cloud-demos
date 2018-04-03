package com.imooc.contoller;

import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by 彭文浩 on 2018/4/3.
 */
@Controller
@RequestMapping("/user")
public class UserContoller {


    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser() {

        User user = new User();
        user.setName("哥写的代码");
        user.setAge(18);
        Date date = new Date();
        user.setBirthday(date);
        return user;

    }

     @RequestMapping("/getUserJson")
     @ResponseBody
    public IMoocJSONResult  getUserJson(){
         User u = new User();
         u.setName("imooc");
         u.setAge(18);
         u.setBirthday(new Date());
         u.setPassword("imooc");
         u.setDesc("hello imooc~~hello imooc~~");
         return IMoocJSONResult.ok(u);
    }

}
