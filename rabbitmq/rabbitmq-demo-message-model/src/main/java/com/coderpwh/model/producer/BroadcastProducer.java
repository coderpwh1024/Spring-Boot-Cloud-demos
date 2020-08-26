package com.coderpwh.model.producer;

import com.coderpwh.model.message.BroadcastMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class BroadcastProducer {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        BroadcastMessage message = new BroadcastMessage();
        message.setId(id);
        rabbitTemplate.convertAndSend(BroadcastMessage.EXCHANGE, null, message);
    }

}
