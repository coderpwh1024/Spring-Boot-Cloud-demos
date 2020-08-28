package com.coderpwh.rpc.consumer;

import com.coderpwh.rpc.message.Demo14Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = Demo14Message.QUEUE)
public class Demo14Consumer {



    @RabbitHandler
    public String  onMessage(Demo14Message message){
        log.info("[消费方]---[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // 返回结果
        return "nicai";
    }


}
