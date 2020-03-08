package com.coderpwh.limit.controller;

import com.coderpwh.limit.config.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author coderpwh
 * @create 2020-03-08 16:06
 * @desc ${DESCRIPTION}
 **/
@RestController
public class LimiterController {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    @Limit(key = "test", period = 100, count = 10, name="resource", prefix = "limit")
    @GetMapping("/test")
    public int testLimiter() {
        // 意味着100S内最多可以访问10次
        return ATOMIC_INTEGER.incrementAndGet();
    }

}
