package com.coderpwh.other.producer;

import com.coderpwh.other.service.DelayedTopic;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class DelayedSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void delayedMessage() {
        String context = "test delay message";
        log.info("Send time:" + LocalDateTime.now() + "   Send:" + context);

        rabbitTemplate.convertAndSend(DelayedTopic.DELAYED_EXCHANGE_NAME, DelayedTopic.DELAYED_ROUTING_KEY, context, message -> {
            message.getMessageProperties().setDelay(6000);
            return message;
        });
    }


}
