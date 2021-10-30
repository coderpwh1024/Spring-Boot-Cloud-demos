package com.coderpwh.kafka.consumer;

import com.coderpwh.kafka.message.Demo02Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author coderpwh
 */
@Component
public class Demo02Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = Demo02Message.TOPIC, groupId = "demo02-consumer-group-" + Demo02Message.TOPIC)
    public void onMessage(Demo02Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容:{}]", Thread.currentThread().getId(), message);
    }

}
