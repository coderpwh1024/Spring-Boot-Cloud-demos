package com.coderpwh.kafka.producer;

import com.coderpwh.kafka.message.Demo02Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * @author coderpwh
 */
@Component
public class Demo02Producer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;


    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {

        Demo02Message message = new Demo02Message();
        message.setId(id);

        return kafkaTemplate.send(Demo02Message.TOPIC, message);
    }

}
