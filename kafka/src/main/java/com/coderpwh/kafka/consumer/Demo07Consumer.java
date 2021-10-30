package com.coderpwh.kafka.consumer;

import com.coderpwh.kafka.message.Demo07Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author coderpwh
 */
@Component
public class Demo07Consumer {


    public Logger logger = LoggerFactory.getLogger(getClass());


    @KafkaListener(topics = Demo07Message.TOPIC, groupId = "demo07-consumer-group-" + Demo07Message.TOPIC)
    public void onMessage(Demo07Message message) {
        logger.info("[消费方--DEMO07--onMessage][线程编号:[{}],消息内容为:[{}]]", Thread.currentThread().getId(), message);
    }


}
