package com.codderpwh.kafka.controller;


import com.codderpwh.kafka.bean.Producer;
import com.codderpwh.kafka.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author coderpwh
 */
@RestController
@RequestMapping("/kafka")
public class KafKaController {


    @Autowired
    private Producer producer;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseResult<?> test() {
        String str = "hello kafka";
        return ResponseResult.buildSuccessResponse(str);
    }


    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
    }


}
