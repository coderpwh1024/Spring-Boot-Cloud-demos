package com.coderpwh.rabbitmq.consumer;

import com.coderpwh.rabbitmq.message.Demo02Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/4/24 0024 15:05
 */

@Component
@RabbitListener(queues = Demo02Message.QUEUE)
public class Demo02Consumer {

   private Logger logger = LoggerFactory.getLogger(getClass());


    @RabbitHandler
    public void  onMessage(Demo02Message message){
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }


}
