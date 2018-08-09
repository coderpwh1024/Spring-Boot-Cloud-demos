package com.coderpwh.servicefeign.clients.fallback;

import com.coderpwh.servicefeign.clients.SchedualServiceHi;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry you are fail,"+name;
    }
}
