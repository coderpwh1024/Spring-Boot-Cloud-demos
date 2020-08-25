package com.coderpwh.rabbitmqdemo.producer.impl;

import com.coderpwh.rabbitmqdemo.producer.IMessageProducerService;
import com.coderpwh.rabbitmqdemo.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class ProducerServiceImpl   implements IMessageProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendJsonMessage(String exchange, String routingKey, Integer delay, Object object, Map<String, Object> headers) {
        log.info("<<---+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--->> exchange:[{}] routingKey:[{}] delay:[{}] object:[{}] headers:[{}]",
                exchange,routingKey,delay, JsonUtils.toJsonString(object),JsonUtils.toJsonString(headers));
        MessageProperties props = MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        if (headers != null){
            headers.forEach(props::setHeader);
        }
        if (delay != null && delay>0){
            // 单位转换成秒
            props.setDelay(delay*1000);
        }
        Message message = MessageBuilder.withBody(JsonUtils.toJsonString(object).getBytes())
                .andProperties(props)
                .build();
        rabbitTemplate.send(exchange, routingKey,message);
    }


}
