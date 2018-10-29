package com.coderpwh.concurrency.example.threadLocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/29 16:19
 */


@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {


    @RequestMapping("/test")
    @ResponseBody
    public Long test() {

//        return RequestHolder.getId();

        return  1L;
    }

}
