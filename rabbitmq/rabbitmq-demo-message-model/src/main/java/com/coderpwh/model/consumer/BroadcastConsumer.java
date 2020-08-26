package com.coderpwh.model.consumer;


import com.coderpwh.model.message.BroadcastMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(
 bindings = @QueueBinding(
         value=@Queue(
                 name = BroadcastMessage.QUEUE + "-" + "#{T(java.util.UUID).randomUUID()}",
                 autoDelete = "true"
         )
         ,exchange =@Exchange(
         name = BroadcastMessage.EXCHANGE,
         type = ExchangeTypes.TOPIC,
         declare = "false"
 )
 )
)
@Slf4j
public class BroadcastConsumer {


    @RabbitHandler
    public void onMessage(BroadcastMessage message) {
        log.info("[消费方]--[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }


}
