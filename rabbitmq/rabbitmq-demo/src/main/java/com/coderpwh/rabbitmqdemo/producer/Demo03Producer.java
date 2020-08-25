package com.coderpwh.rabbitmqdemo.producer;

import com.coderpwh.rabbitmqdemo.message.Demo03Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo03Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void  syncSend(Integer id){

        Demo03Message message = new Demo03Message();
        message.setId(id);

        rabbitTemplate.convertAndSend(Demo03Message.EXCHANGE,null,message);
    }



}
