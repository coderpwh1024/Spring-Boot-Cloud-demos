package com.imooc.contoller;

import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 彭文浩 on 2018/4/1.
 */
@RestController
public class HelloContoller {

    @Autowired
    private Resource resource;

     @RequestMapping("/hello")
    public Object hello(){

          return "hello springboot~~";
    }

    @RequestMapping("/getResource")
    public IMoocJSONResult getResource() {

        Resource bean = new Resource();
        BeanUtils.copyProperties(resource, bean);

        return IMoocJSONResult.ok(bean);
    }

}
