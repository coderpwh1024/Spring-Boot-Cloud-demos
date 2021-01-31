package com.codrpwh.rabbitmq.demo01.producer;

import com.codrpwh.rabbitmq.demo01.message.Demo04Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

@Component
public class Demo04Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void syncSend(Integer id, String headerValue) {

        MessageProperties messageProperties = new MessageProperties();

        messageProperties.setHeader(Demo04Message.HEADER_KEY, headerValue);

        Message message = rabbitTemplate.getMessageConverter().toMessage(new Demo04Message().setId(id), messageProperties);


        rabbitTemplate.send(Demo04Message.EXCHANGE, null, message);

    }


}
