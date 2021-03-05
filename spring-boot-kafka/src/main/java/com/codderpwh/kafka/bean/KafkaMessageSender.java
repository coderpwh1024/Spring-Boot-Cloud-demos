package com.codderpwh.kafka.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author coderpwh
 */
@Component
public class KafkaMessageSender {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void send(String topic, String payload) {
        kafkaTemplate.send(topic, payload);
        System.out.println("发送到主题:" + topic + ",消息:" + payload);
    }



}
