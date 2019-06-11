package com.coderpwh.servicefeign.web;

import com.coderpwh.servicefeign.clients.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

     @Autowired
    SchedualServiceHi schedualServiceHi;

     @GetMapping(value ="/hi")
     public String sayHi(@RequestParam String name){
         return  schedualServiceHi.sayHiFromClientOne(name);
     }

}
