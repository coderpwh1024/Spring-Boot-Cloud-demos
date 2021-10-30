package com.coderpwh.kafka.consumer;

import com.coderpwh.kafka.message.Demo04Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author coderpwh
 */
@Component
public class Demo04Consumer {

    private AtomicInteger count = new AtomicInteger(0);

    private Logger logger = LoggerFactory.getLogger(getClass());


    @KafkaListener(topics = Demo04Message.TOPIC,
            groupId = "demo04-consumer-group-" + Demo04Message.TOPIC)
    public void onMessage(Demo04Message message) {

        logger.info("[onMessage][线程编号:{} 消息内容:{}]", Thread.currentThread().getId(), message);

        throw new RuntimeException("我就是故意抛出一个异常");
    }

}
