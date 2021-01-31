package com.codrpwh.rabbitmq.mq.producer;

import com.codrpwh.rabbitmq.config.RabbitMQDeadConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg) {
        rabbitTemplate.convertSendAndReceive(RabbitMQDeadConfig.BUSINESS_EXCHANGE_NAME, "", msg);
    }


}
