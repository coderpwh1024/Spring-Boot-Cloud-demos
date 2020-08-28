package com.coderpwh.other.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "test.mq")
@Slf4j
public class Receiver {


    @RabbitHandler
    public void processs(String context) {
        log.info("Receiver:" + context);
    }
}
