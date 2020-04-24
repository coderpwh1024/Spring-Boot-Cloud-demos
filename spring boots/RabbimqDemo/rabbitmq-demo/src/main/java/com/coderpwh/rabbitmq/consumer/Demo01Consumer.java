package com.coderpwh.rabbitmq.consumer;

import com.coderpwh.rabbitmq.message.Demo01Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @RabbitListener 监听队里中的消息
 * @description:
 * @author: pengwenhao
 * @date: 2020/4/24 0024 9:53
 */
@Component
@RabbitListener(queues = Demo01Message.QUEUE)
public class Demo01Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());


    /****
     * @RabbitListener 在类上监听
     * @RabbitHander 用在方法上
     *
     * @param message
     */
    @RabbitHandler
    public void onMessage(Demo01Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }


}
