package com.coderpwh.servicefeign.clients;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface SchedualServiceHi {

    @RequestMapping(value="/hi",method = RequestMethod.GET)
     String sayHiFromClientOne(@RequestParam(value ="name") String name);
}
