package com.coderpwh.servicefeign.clients.fallback;

import com.coderpwh.servicefeign.clients.SchedualServiceHi;
import org.springframework.stereotype.Component;

/**
 * @author pengwenhao
 * @date 2019/9/6 13:55
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {


    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry, you are fail," + name;
    }
}
