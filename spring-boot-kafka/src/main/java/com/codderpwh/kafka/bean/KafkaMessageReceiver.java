package com.codderpwh.kafka.bean;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author coderpwh
 */
@Component
public class KafkaMessageReceiver {


    @KafkaListener(topics = "Java")
    public void receiveTopic1(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("接收java主题消息:" + consumerRecord.toString());
    }


    @KafkaListener(topics = "coderpwh-test")
    public void receiveTopic2(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("接收coderpwh-test主题消息:" + consumerRecord.toString());
    }


}
