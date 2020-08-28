package com.coderpwh.other.producer;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Sender {


    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void send() {
        String context = "测试发送消息" + LocalDateTime.now();
        System.out.println("Sender:" + context);
        this.rabbitTemplate.convertAndSend("test.mq", context);
    }

}
