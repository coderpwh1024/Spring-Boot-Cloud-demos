package com.coderpwh.servicefeign.web;

import com.coderpwh.servicefeign.clients.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coderpwh
 * @date 2019/9/6 13:57
 */
@RestController
public class HiController {

    @Autowired
    private SchedualServiceHi schedualServiceHi;


    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String sayHi(@RequestParam String param) {
     return    schedualServiceHi.sayHiFromClientOne(param);
    }

}
