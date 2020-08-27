package com.coderpwh.concurrency.producer;

import com.coderpwh.concurrency.message.Demo09Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo09Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        Demo09Message message = new Demo09Message();
        message.setId(id);
        rabbitTemplate.convertAndSend(Demo09Message.EXCHANGE, Demo09Message.ROUTING_KEY, message);
    }

}
