package com.codrpwh.rabbitmq.mq.producer;

import com.codrpwh.rabbitmq.config.RabbitMQDelayCConfig;
import com.codrpwh.rabbitmq.config.RabbitMQDelayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DelayMessageSender {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg, Integer type) {

        switch (type) {
            case 10:
                log.info("已进入10");
                rabbitTemplate.convertAndSend(RabbitMQDelayConfig.DELAY_EXCHANGE_NAME, RabbitMQDelayConfig.DELAY_QUEUEA_ROUTING_KEY, msg);
                break;
            case 60:
                log.info("已进入60");
                rabbitTemplate.convertAndSend(RabbitMQDelayConfig.DELAY_EXCHANGE_NAME, RabbitMQDelayConfig.DELAY_QUEUEB_ROUTING_KEY, msg);
                break;
        }

    }

    public void sendMsgs(String msg, Integer delayTime) {
        log.info("消息生产者");
        rabbitTemplate.convertAndSend(RabbitMQDelayCConfig.DELAY_EXCHANGE_NAME, RabbitMQDelayCConfig.DELAY_QUEUEC_ROUTING_KEY, msg, a -> {
            a.getMessageProperties().setExpiration(String.valueOf(delayTime));
            return a;
        });
    }


}
