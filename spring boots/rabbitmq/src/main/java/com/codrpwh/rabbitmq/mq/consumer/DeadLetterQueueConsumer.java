package com.codrpwh.rabbitmq.mq.consumer;

import com.codrpwh.rabbitmq.config.RabbitMQDelayConfig;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

import java.io.IOException;
import java.util.Date;

import static com.codrpwh.rabbitmq.config.RabbitMQDelayConfig.DEAD_LETTER_QUEUEA_NAME;
import static com.codrpwh.rabbitmq.config.RabbitMQDelayConfig.DEAD_LETTER_QUEUEB_NAME;
import static com.codrpwh.rabbitmq.config.RabbitMQDelayCConfig.DEAD_LETTER_QUEUEC_NAME;

@Slf4j
@Component
public class DeadLetterQueueConsumer {


    @RabbitListener(queues = DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());

        log.info("当前时间:{},死信队列A收到的消息为:{}", new Date().toString(), msg);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

    }


    @RabbitListener(queues = DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());

        log.info("当前时间:{},死信队列B收到的消息为:{}", new Date().toString(), msg);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = DEAD_LETTER_QUEUEC_NAME)
    public void receiveC(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());

        log.info("当前时间:{},死信队列C收到消息为:{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

    }


}
