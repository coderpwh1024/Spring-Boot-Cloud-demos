package com.codrpwh.rabbitmq.mq.consumer;

import com.codrpwh.rabbitmq.config.RabbitMQDeadConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

import java.io.IOException;

@Slf4j
@Component
public class BusinessMessageReceiver {


    @RabbitListener(queues = RabbitMQDeadConfig.BUSINESS_QUEUEA_NAME)
    public void receiverA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());

        log.info("收到业务消息A:{}", msg);
        boolean ack = true;

        Exception exception = null;

        try {
            if (msg.contains("deadletter")) {
                throw new RuntimeException("dead letter exception");
            }

        } catch (Exception e) {
            ack = false;
            exception = e;
        }

        if (!ack) {
            log.error("消息消费发生异常，error msg:{}", exception.getMessage(), exception);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } else {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = RabbitMQDeadConfig.BUSINESS_QUEUEB_NAME)
    public void receiverB(Message message, Channel channel) throws IOException {
        log.info("收到业务消息B:" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
