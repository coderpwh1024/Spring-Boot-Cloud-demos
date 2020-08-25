package com.coderpwh.rabbitmqdemo.consumer;

import com.coderpwh.rabbitmqdemo.message.Demo01Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RabbitListener(queues = Demo01Message.QUEUE)
public class Demo01Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(Demo01Message message){
        logger.info("[消费者]--[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }


}
