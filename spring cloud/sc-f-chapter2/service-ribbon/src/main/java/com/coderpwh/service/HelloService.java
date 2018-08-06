package com.coderpwh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 彭文浩 on 2018/8/6.
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }
}
