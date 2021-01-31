package com.codrpwh.rabbitmq.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import java.io.IOException;

/**
 * @author coderpwh
 */
public interface BaseConsumer {

    public void consume(Message message, Channel channel) throws IOException;
}
