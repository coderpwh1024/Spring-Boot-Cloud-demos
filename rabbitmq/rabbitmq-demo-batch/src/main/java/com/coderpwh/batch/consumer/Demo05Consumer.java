package com.coderpwh.batch.consumer;

import com.coderpwh.batch.message.Demo05Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Demo05Message.QUEUE)
@Slf4j
public class Demo05Consumer {


    @RabbitHandler
    public void onMessage(Demo05Message message) {
        log.info("[消费方]----[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
