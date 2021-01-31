package com.codrpwh.rabbitmq.mq.consumer;

import com.alibaba.druid.support.json.JSONUtils;
import com.codrpwh.rabbitmq.config.RabbitMQDeadConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

import java.io.IOException;


@Component
@Slf4j
public class DeadLetterMessageReceiver {


    @RabbitListener(queues = RabbitMQDeadConfig.DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {

        log.info("收到死信消息A:" + new String(message.getBody()));
        log.info("死信消息properties:{}", message.getMessageProperties());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = RabbitMQDeadConfig.DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        log.info("收到死信队列B:" + new String(message.getBody()));
        log.info("死信消息properties:{}",message.getMessageProperties());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
