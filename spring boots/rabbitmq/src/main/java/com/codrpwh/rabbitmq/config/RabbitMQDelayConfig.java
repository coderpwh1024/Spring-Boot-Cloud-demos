package com.codrpwh.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQDelayConfig {


    public static final String DELAY_EXCHANGE_NAME = "coderpwh-delay.queue.demo.business.exchange";

    public static final String DELAY_QUEUEA_NAME = "coderpwh-delay.queue.demo.business.queuea";

    public static final String DELAY_QUEUEB_NAME = "coderpwh-delay.queue.demo.business.queueb";

    public static final String DELAY_QUEUEA_ROUTING_KEY = "coderpwh-delay.queue.demo.business.queuea.routingkey";

    public static final String DELAY_QUEUEB_ROUTING_KEY = "coderpwh-delay.queue.demo.business.queueb.routingkey";

    public static final String DEAD_LETTER_EXCHANGE = "coderpwh-delay.queue.demo.deadletter.exchange";

    public static final String DEAD_LETTER_QUEUEA_ROUTING_KEY = "coderpwh-delay.queue.demo.deadletter.delay_10s.routingkey";

    public static final String DEAD_LETTER_QUEUEB_ROUTING_KEY = "coderpwh-delay.queue.demo.deadletter.delay_60s.routingkey";

    public static final String DEAD_LETTER_QUEUEA_NAME = "coderpwh-delay.queue.demo.deadletter.queuea";

    public static final String DEAD_LETTER_QUEUEB_NAME = "coderpwh-delay.queue.demo.deadletter.queueb";


    /***
     *   延迟队列--交换机-Bean
     * @return
     */
    @Bean("coderpwh-delayExchange")
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }


    /***
     *  死信队列--交换机--Bean
     *
     * @return
     */
    @Bean("coderpwh-deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }


    /***
     *  延迟队列A--6秒
     * @return
     */
    @Bean("coderpwh-delayQueueA")
    public Queue delayQueueA() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEA_ROUTING_KEY);

        args.put("x-message-ttl", 6000);

        return QueueBuilder.durable(DELAY_QUEUEA_NAME).withArguments(args).build();
    }

    /***
     *  延迟队列B
     * @return
     */
    @Bean("coderpwh-delayQueueB")
    public Queue delayQueueB() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEB_ROUTING_KEY);

        args.put("x-message-ttl", 60000);

        return QueueBuilder.durable(DELAY_QUEUEB_NAME).withArguments(args).build();
    }


    /***
     *  死信队列-A
     * @return
     */
    @Bean("coderpwh-deadLetterQueueA")
    public Queue deadLetterQueueA() {
        return new Queue(DEAD_LETTER_QUEUEA_NAME);
    }

    /***
     * 死信队列-B
     * @return
     */
    @Bean("coderpwh-deadLetterQueueB")
    public Queue deadLetterQuueB() {
        return new Queue(DEAD_LETTER_QUEUEB_NAME);
    }


    /***
     *  延迟队列A与延迟交换机进行绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding delayBindingA(@Qualifier("coderpwh-delayQueueA") Queue queue, @Qualifier("coderpwh-delayExchange") DirectExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEA_ROUTING_KEY);
    }


    /***
     *   延迟队列B与延迟交换机进行绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding delayBindingB(@Qualifier("coderpwh-delayQueueB") Queue queue, @Qualifier("coderpwh-delayExchange") DirectExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEB_ROUTING_KEY);
    }


    /***
     *    死信队列A绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding coderpwhdeadLetterBindingA(@Qualifier("coderpwh-deadLetterQueueA") Queue queue, @Qualifier("coderpwh-deadLetterExchange") DirectExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEA_ROUTING_KEY);
    }


    /****
     *   死信队列B绑定关系
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding coderpwhdeadLetterBindingB(@Qualifier("coderpwh-deadLetterQueueB") Queue queue, @Qualifier("coderpwh-deadLetterExchange") DirectExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEB_ROUTING_KEY);
    }


}
