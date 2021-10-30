package com.coderpwh.kafka.consumer;

import com.coderpwh.kafka.message.Demo08Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author coderpwh
 */
@Component
public class Demo08Consumer {


    public Logger logger = LoggerFactory.getLogger(getClass());


    @KafkaListener(topics = Demo08Message.TOPIC, groupId = "demo07-consumer-group-" + Demo08Message.TOPIC)
    public void onMessage(Demo08Message message, Acknowledgment acknowledgment) {

        logger.info("[消费方-DEMO08-onMessage][消费id为:[{}],消费内容为:[{}]]", Thread.currentThread().getId(), message);

        if (message.getId() % 2 == 1) {
            acknowledgment.acknowledge();
        }

    }


}
