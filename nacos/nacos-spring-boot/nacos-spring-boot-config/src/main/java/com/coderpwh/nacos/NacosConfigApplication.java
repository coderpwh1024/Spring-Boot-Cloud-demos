package com.coderpwh.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@NacosPropertySource(dataId = "nacos-config-sample.properties", autoRefreshed = true)
public class NacosConfigApplication {


    @Value("${user.name}")
    private String userName;





    @PostConstruct
    public void init() {
        System.out.println("[nacos] username:" + userName);
    }


    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }


}