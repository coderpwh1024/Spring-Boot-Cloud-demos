package com.coderpwh.code.controller;

import com.coderpwh.code.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author coderpwh
 * @Date: 2018/1/26.
 * @Description:
 */
@RestController
public class HelloController {

  /*  @Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;*/


    @Autowired
    GirlProperties girlProperties;

    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public String say() {
        return girlProperties.getCupSize();
    }


    @RequestMapping(value = "/run/{id}", method = RequestMethod.GET)
    public String run(@PathVariable("id") Integer id) {
        return girlProperties.getCupSize();
    }

    @RequestMapping(value = "/run2", method = RequestMethod.GET)
    public String run2(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {

        return "id:" + id;
    }


}
