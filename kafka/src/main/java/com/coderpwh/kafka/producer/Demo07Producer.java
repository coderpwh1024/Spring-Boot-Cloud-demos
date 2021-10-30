package com.coderpwh.kafka.producer;

import com.coderpwh.kafka.message.Demo07Message;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author coderpwh
 */
@Component
public class Demo07Producer {


    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;


    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {

        Demo07Message message = new Demo07Message();
        message.setId(id);

        return kafkaTemplate.send(Demo07Message.TOPIC, message).get();
    }


    public String syncSendInTransaction(Integer id, Runnable runnable) {

        return kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<Object, Object, String>() {

            @Override
            public String doInOperations(KafkaOperations<Object, Object> operations) {

                Demo07Message message = new Demo07Message();
                message.setId(id);

                try {
                    operations.send(Demo07Message.TOPIC, message).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                runnable.run();


                return "success";
            }
        });
    }


}
