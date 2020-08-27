package com.coderpwh.orderly.producer;

import com.coderpwh.orderly.message.Demo10Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo10Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void syncSend(Integer id) {
        Demo10Message message = new Demo10Message();
        message.setId(id);

        rabbitTemplate.convertAndSend(Demo10Message.EXCHANGE, this.getRoutingKey(id), message);

    }

    public String getRoutingKey(Integer id) {
        return String.valueOf(id & Demo10Message.QUEUE_COUNT);
    }


}
