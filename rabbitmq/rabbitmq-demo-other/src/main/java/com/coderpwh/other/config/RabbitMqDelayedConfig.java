package com.coderpwh.other.config;

import com.coderpwh.other.service.DelayedTopic;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqDelayedConfig {


    @Bean
    public Queue delayedQueue() {
        return new Queue(DelayedTopic.DELAYED_QUEUE_NAME);
    }


    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DelayedTopic.DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingNotify(@Qualifier("delayedQueue") Queue queue,
                                 @Qualifier("delayExchange") CustomExchange customExchange) {
        return BindingBuilder.bind(queue).to(customExchange).with(DelayedTopic.DELAYED_ROUTING_KEY).noargs();
    }





}
