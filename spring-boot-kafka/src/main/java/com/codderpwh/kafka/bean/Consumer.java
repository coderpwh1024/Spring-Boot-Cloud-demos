package com.codderpwh.kafka.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * @author coderpwh
 */
@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consumer(String message) throws IOException {
        logger.info(String.format("###->Consumed message->%s", message));
    }

}

