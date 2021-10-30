package com.coderpwh.kafka.producer;

import com.coderpwh.kafka.message.Demo06Message;
import com.coderpwh.kafka.message.Demo08Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author coderpwh
 */
@Component
public class Demo08Producer {


    private Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;


    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {

        Demo08Message message = new Demo08Message();
        message.setId(id);

        return kafkaTemplate.send(Demo08Message.TOPIC, message).get();
    }


}
