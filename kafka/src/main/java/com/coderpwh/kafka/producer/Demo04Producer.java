package com.coderpwh.kafka.producer;

import com.coderpwh.kafka.message.Demo04Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author coderpwh
 */
@Component
public class Demo04Producer {


    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;


    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {

        Demo04Message message = new Demo04Message(id);

        return kafkaTemplate.send(Demo04Message.TOPIC, message).get();

    }

}
