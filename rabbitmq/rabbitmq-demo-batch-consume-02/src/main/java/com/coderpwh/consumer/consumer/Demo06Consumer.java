package com.coderpwh.consumer.consumer;


import com.coderpwh.consumer.message.Demo06Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RabbitListener(queues = Demo06Message.QUEUE,
        containerFactory = "consumerBatchContainerFactory")
@Slf4j
public class Demo06Consumer {

    @RabbitHandler
     public void onMessage(List<Demo06Message> messages){
        log.info("[onMessage][线程编号:{} 消息数量：{}]", Thread.currentThread().getId(), messages.size());
     }
}
