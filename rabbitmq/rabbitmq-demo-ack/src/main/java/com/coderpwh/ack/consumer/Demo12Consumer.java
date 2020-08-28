package com.coderpwh.ack.consumer;

import com.coderpwh.ack.message.Demo12Message;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = Demo12Message.QUEUE)
@Slf4j
public class Demo12Consumer {


    @RabbitHandler
    public void onMessage(Demo12Message message, Channel channel,
                          @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        log.info("[消费方]---[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);

        if(message.getId()%2==1){
            channel.basicAck(deliveryTag, false);
        }
    }


}
