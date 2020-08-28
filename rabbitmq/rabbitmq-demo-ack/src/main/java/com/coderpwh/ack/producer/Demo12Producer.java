package com.coderpwh.ack.producer;

import com.coderpwh.ack.message.Demo12Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo12Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void  syncSend(Integer id){
        Demo12Message message = new Demo12Message();
        message.setId(id);
        rabbitTemplate.convertAndSend(Demo12Message.EXCHANGE, Demo12Message.ROUTING_KEY, message);
    }


}
