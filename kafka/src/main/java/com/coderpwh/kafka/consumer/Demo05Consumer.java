package com.coderpwh.kafka.consumer;

import com.coderpwh.kafka.message.Demo05Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author coderpwh
 */
@Component
public class Demo05Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @KafkaListener(topics = Demo05Message.TOPIC, groupId = "demo05-consumer-group-" + Demo05Message.TOPIC + "-" + "#{T(java.util.UUID).randomUUID()}")
    public void onMessage(Demo05Message message) {
        logger.info("[消费方--Demo05--onMessage--开始消费][线程编号为:[{}] 消息内容为:[{}]]", Thread.currentThread().getId(), message);
    }

}
