package com.coderpwh.model.consumer;

import com.coderpwh.model.message.ClusteringMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(
                        name = ClusteringMessage.QUEUE + "-" + "GROUP-01"
                ),
                exchange = @Exchange(
                        name = ClusteringMessage.EXCHANGE,
                        type = ExchangeTypes.TOPIC,
                        declare = "false"
                ),
                key = "#"
        )
)
public class ClusteringConsumer {

    @RabbitHandler
    public void onMessage(ClusteringMessage message) {
        log.info("[消费方]----[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
