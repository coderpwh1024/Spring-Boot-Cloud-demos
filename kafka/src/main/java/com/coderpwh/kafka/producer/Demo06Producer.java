package com.coderpwh.kafka.producer;

import com.coderpwh.kafka.message.Demo06Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author coderpwh
 */
@Component
public class Demo06Producer {


    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;


    public SendResult send(Integer id) throws ExecutionException, InterruptedException {

        Demo06Message message = new Demo06Message();
        message.setId(id);

        return kafkaTemplate.send(Demo06Message.TOPIC, message).get();
    }


    public SendResult syncSendOrderly(Integer id) throws ExecutionException, InterruptedException {

        Demo06Message message = new Demo06Message();
        message.setId(id);

        return kafkaTemplate.send(Demo06Message.TOPIC, String.valueOf(id), message).get();
    }


}
