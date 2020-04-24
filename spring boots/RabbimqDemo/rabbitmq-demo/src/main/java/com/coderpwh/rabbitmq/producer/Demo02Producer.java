package com.coderpwh.rabbitmq.producer;

import com.coderpwh.rabbitmq.message.Demo02Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/4/24 0024 14:48
 */
@Component
public class Demo02Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id, String routingKey) {
        Demo02Message message = new Demo02Message();
        message.setId(id);

        // 同步发送
        rabbitTemplate.convertAndSend(Demo02Message.EXCHANGE, routingKey, message);

    }


}
