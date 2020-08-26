package com.coderpwh.model.producer;

import com.coderpwh.model.message.ClusteringMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClusteringProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void syncSend(Integer id){
        ClusteringMessage message  = new ClusteringMessage();
        message.setId(id);
        rabbitTemplate.convertAndSend(ClusteringMessage.EXCHANGE,null,message);
    }


}
