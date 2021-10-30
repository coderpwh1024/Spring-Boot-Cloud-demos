package com.coderpwh.kafka.consumer;

import com.coderpwh.kafka.message.Demo06Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author coderpwh
 */
@Component
public class Demo06Consumer {


    private Logger logger = LoggerFactory.getLogger(getClass());


    @KafkaListener(topics = Demo06Message.TOPIC, groupId = "demo06-consumer-group-" + Demo06Message.TOPIC, concurrency = "2")
    public void onMessage(Demo06Message message) {
        logger.info("[消费方--Demo06--onMessage][线程编号为:[{}] 消息内容为:[{}]]", Thread.currentThread().getId(), message);
    }


}
