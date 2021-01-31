package com.codrpwh.rabbitmq.demo01.consumer;

import com.codrpwh.rabbitmq.demo01.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author coderpwh
 */
@Component
@RabbitListener(queues = Demo01Message.QUEUE)
@Slf4j
public class Demo01Consumer {

    @RabbitHandler
    public void onMessage(Demo01Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
