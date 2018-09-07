package com.coderpwh.servicelucy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class ServiceLucyApplication {

    @Value("${server.port}")
    String port;

    public static void main(String[] args) {
        SpringApplication.run(ServiceLucyApplication.class, args);
    }

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name", defaultValue = "coderpwh") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }

}
