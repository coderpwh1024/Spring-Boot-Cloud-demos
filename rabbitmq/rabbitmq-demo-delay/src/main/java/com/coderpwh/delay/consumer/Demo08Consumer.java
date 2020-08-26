package com.coderpwh.delay.consumer;

import com.coderpwh.delay.message.Demo08Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Demo08Message.DELAY_QUEUE)
@Slf4j
public class Demo08Consumer {

    @RabbitHandler
    public void onMessage(Demo08Message message){
        log.info("[消费方]-------[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
