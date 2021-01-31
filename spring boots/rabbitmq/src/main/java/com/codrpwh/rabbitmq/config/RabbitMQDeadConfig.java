package com.codrpwh.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQDeadConfig {

    public static final String BUSINESS_EXCHANGE_NAME = "dead.letter.demo.simple.business.exchange";

    public static final String BUSINESS_QUEUEA_NAME = "dead.letter.demo.simple.business.queuea";

    public static final String BUSINESS_QUEUEB_NAME = "dead.letter.demo.simple.business.queueb";

    public static final String DEAD_LETTER_EXCHANGE = "dead.letter.demo.simple.deadletter.exchange";

    public static final String DEAD_LETTER_QUEUEA_ROUTING_KEY = "dead.letter.demo.simple.deadletter.queuea.routingkey";

    public static final String DEAD_LETTER_QUEUEB_ROUTING_KEY = "dead.letter.demo.simple.deadletter.queueb.routingkey";

    public static final String DEAD_LETTER_QUEUEA_NAME = "dead.letter.demo.simple.deadletter.queuea";

    public static final String DEAD_LETTER_QUEUEB_NAME = "dead.letter.demo.simple.deadletter.queueb";


    /***
     *  普通交换机
     *
     * @return
     */
    @Bean("businessExchange")
    public FanoutExchange businessExchange() {
        return new FanoutExchange(BUSINESS_EXCHANGE_NAME);
    }

    /***
     *  死信队列
     * @return
     */
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    @Bean("businessQueueA")
    public Queue businessQueueA() {
        Map<String, Object> map = new HashMap<>();

        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        map.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEA_ROUTING_KEY);

        return QueueBuilder.durable(BUSINESS_QUEUEA_NAME).withArguments(map).build();
    }

    @Bean("businessQueueB")
    public Queue businessQueueB() {
        Map<String, Object> map = new HashMap<>(2);

        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        map.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEB_ROUTING_KEY);

        return QueueBuilder.durable(BUSINESS_QUEUEB_NAME).withArguments(map).build();
    }


    /****
     *   死信队列A
     * @return
     */
    @Bean("deadLetterQueueA")
    public Queue deadLetterQueueA() {
        return new Queue(DEAD_LETTER_QUEUEA_NAME);
    }


    /****
     *  死信队列B
     * @return
     */
    @Bean("deadLetterQueueB")
    public Queue deadLetterQueueB() {
        return new Queue(DEAD_LETTER_QUEUEB_NAME);
    }

    @Bean
    public Binding businessBindingA(@Qualifier("businessQueueA") Queue queue, @Qualifier("businessExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }


    @Bean
    public Binding businessBindingB(@Qualifier("businessQueueB") Queue queue, @Qualifier("businessExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }


    /***
     *   死信队列A绑定关系
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding deadLetterBindingA(@Qualifier("deadLetterQueueA") Queue queue, @Qualifier("deadLetterExchange") DirectExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEA_ROUTING_KEY);
    }

    /****
     *   死信队列B绑定关系
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding deadLetterBindingB(@Qualifier("deadLetterQueueB") Queue queue, @Qualifier("deadLetterExchange") DirectExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEB_ROUTING_KEY);
    }

}
