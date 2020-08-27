package com.coderpwh.transaction.consumer;

import com.coderpwh.transaction.message.Demo11Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Demo11Message.QUEUE)
@Slf4j
public class Demo11Consumer {


    @RabbitHandler
    public void onMessage(Demo11Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
