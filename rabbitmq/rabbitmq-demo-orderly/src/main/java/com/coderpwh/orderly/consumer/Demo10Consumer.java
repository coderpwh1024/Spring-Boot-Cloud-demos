package com.coderpwh.orderly.consumer;

import com.coderpwh.orderly.message.Demo10Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = Demo10Message.QUEUE_0)
@RabbitListener(queues = Demo10Message.QUEUE_1)
@RabbitListener(queues = Demo10Message.QUEUE_2)
@RabbitListener(queues = Demo10Message.QUEUE_3)
public class Demo10Consumer {


    @RabbitHandler(isDefault = true)
    public void onMessage(Message<Demo10Message> message) {
        log.info("[onMessage][线程编号:{} Queue:{} 消息编号：{}]", Thread.currentThread().getId(), getQueue(message),
                message.getPayload().getId());
    }

    private static String getQueue(Message<Demo10Message> message) {
        return message.getHeaders().get("amqp_consumerQueue", String.class);
    }


}
