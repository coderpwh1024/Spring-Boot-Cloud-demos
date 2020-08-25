package com.coderpwh.rabbitmqdemo.consumer;


import com.coderpwh.rabbitmqdemo.message.Demo02Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Demo02Message.QUEUE)
public class Demo02Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @RabbitHandler
    public void onMessage(Demo02Message message) {

        logger.info("[消费方]--[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }


}
