package com.coderpwh.rabbitmqdemo.consumer;


import com.coderpwh.rabbitmqdemo.message.Demo04Message;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
public class TestConsumer {

    private static final String TEST_CONSUMER = "QUEUE_DEMO_01";


    /***
     *
     * @param message
     * @param channel
     * @param headers
     * @throws IOException
     */
    @RabbitListener(queues = TEST_CONSUMER)
    public void testConsumer(Message message, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        log.info("<<<---+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+---->>> testConsumer start！");
        log.info("<<<---+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+---->>> testConsumer message:[{}]", message.toString());
        long tag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        byte[] bytes = message.getBody();
        String str = new String(bytes, StandardCharsets.UTF_8);
        log.info("<<<---+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+---->>> testConsumer 消息内容body:[{}]", str);
        try {
            log.info("-------------------------------------------------------------------------------");
            log.info("<<<---+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+---->>> testConsumer 消息内容body:[{}]", str);
            log.info("-------------------------------------------------------------------------------");
        } catch (Exception e) {
            log.error("<<<---+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+---->>>testConsumer 消息异常:", e);
            // 第二参数 true 则进行重新投递，false 不进行重新投递
            channel.basicReject(tag, false);
        }
        log.info("<<<---+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+---->>> testConsumer end！");
    }

}
