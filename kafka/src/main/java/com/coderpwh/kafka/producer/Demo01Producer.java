package com.coderpwh.kafka.producer;

import com.coderpwh.kafka.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * 生产者生产消息
 *
 * @author coderpwh
 */
@Slf4j
@Component
public class Demo01Producer {


    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;


    /***
     *  同步
     * @param id
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {

        Demo01Message message = new Demo01Message();
        message.setId(id);

        return kafkaTemplate.send(Demo01Message.TOPIC, message).get();
    }


    /***
     *  异步
     * @param id
     * @return
     */
    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {

        Demo01Message message = new Demo01Message();
        message.setId(id);
        return kafkaTemplate.send(Demo01Message.TOPIC, message);
    }

    public ListenableFuture<SendResult<Object, Object>> send(Integer id) {

        Demo01Message message = new Demo01Message();
        message.setId(id);

        return kafkaTemplate.send(Demo01Message.TOPIC, message);

    }


}
