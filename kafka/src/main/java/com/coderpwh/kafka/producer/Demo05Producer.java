package com.coderpwh.kafka.producer;

import com.coderpwh.kafka.message.Demo05Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author coderpwh
 */
@Component
public class Demo05Producer {


    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;


    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        Demo05Message message = new Demo05Message();
        message.setId(id);


        return kafkaTemplate.send(Demo05Message.TOPIC, message).get();


    }

}
