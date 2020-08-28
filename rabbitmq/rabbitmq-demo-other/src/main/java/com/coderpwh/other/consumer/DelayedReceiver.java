package com.coderpwh.other.consumer;

import com.coderpwh.other.service.DelayedTopic;
import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;


import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j
public class DelayedReceiver {


    @RabbitListener(queues = DelayedTopic.DELAYED_QUEUE_NAME)
     public void receive(Message message,  Channel channel) throws IOException {

         String s = new String(message.getBody());
         log.info("[消费方] Receive time :"+ LocalDateTime.now()+"     Received:"+s);
         channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

     }


}
