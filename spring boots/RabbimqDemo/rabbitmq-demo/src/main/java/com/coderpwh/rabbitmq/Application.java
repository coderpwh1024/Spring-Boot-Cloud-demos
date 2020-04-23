package com.coderpwh.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description:
 * @author: coderpwh
 * @date: 2020/4/23 0023 15:14
 */
@SpringBootApplication
@EnableAsync // 开启异步
public class Application {
    
     public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
