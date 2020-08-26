package com.coderpwh.retry.consumer;

import com.coderpwh.retry.message.Demo07Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Demo07Message.DEAD_QUEUE)
@Slf4j
public class Demo07DeadConsumer {


    @RabbitHandler
    public void onMessage(Demo07Message message) {
        log.info("[]---[onMessage][【死信队列】线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }


}
