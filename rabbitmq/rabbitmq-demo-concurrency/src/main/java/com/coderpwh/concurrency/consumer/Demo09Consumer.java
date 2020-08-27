package com.coderpwh.concurrency.consumer;

import com.coderpwh.concurrency.message.Demo09Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = Demo09Message.QUEUE, concurrency = "2")
public class Demo09Consumer {


    @RabbitHandler
    public void onMessage(Demo09Message message) {
        log.info("[消费方]---[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }


}
