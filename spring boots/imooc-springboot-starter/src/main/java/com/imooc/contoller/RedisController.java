package com.imooc.contoller;

import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.SysUser;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by 彭文浩 on 2018/4/14.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {


    @Autowired
    private StringRedisTemplate strRedis;

    @Autowired
    private RedisOperator redis;

    @RequestMapping("/test")
    public IMoocJSONResult test(){

        SysUser user = new SysUser();
        user.setId("100111");
        user.setUsername("imooc");
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());


        strRedis.opsForValue().set("json:user",JsonUtils.objectToJson(user));

          SysUser jsonUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"),SysUser.class);
//        strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(user));

//        SysUser jsonUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), SysUser.class);

        return IMoocJSONResult.ok(jsonUser);
    }
}
