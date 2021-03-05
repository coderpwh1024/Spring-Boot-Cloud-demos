package com.codderpwh.kafka.controller;


import com.codderpwh.kafka.bean.KafkaMessageSender;
import com.codderpwh.kafka.bean.Producer;
import com.codderpwh.kafka.common.Foo1;
import com.codderpwh.kafka.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author coderpwh
 */
@RestController
@RequestMapping("/kafka")
public class KafKaController {

    private String topicName = "Java";
    private String message = "Java Spring Boot 2.0 Kafka Alibaba:";


    @Autowired
    private KafkaMessageSender kafkaMessageSender;


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


    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendMessageToKafkaTopic() {
        System.out.println("发送100万条消息");

        for (int i = 0; i < 1000000; i++) {
            kafkaMessageSender.send(topicName, message + i);
        }

        System.out.println("成功，发送100万条消息");
        return "success";

    }


}

