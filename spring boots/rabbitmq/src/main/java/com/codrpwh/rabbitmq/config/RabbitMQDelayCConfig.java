package com.codrpwh.rabbitmq.config;

import com.sun.tools.internal.xjc.reader.xmlschema.BindGreen;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @author coderpwh
 */
@Configuration
public class RabbitMQDelayCConfig {


    public static final String DELAY_EXCHANGE_NAME = "c-delay.exchange";

    public static final String DELAY_QUEUEC_NAME = "c-delay.queue";

    public static final String DELAY_QUEUEC_ROUTING_KEY = "c-delay.routingkey";


    /***
     *  死信队列，交换机，路由
     */
    public static final String DEAD_LETTER_EXCHANGE = "c-letter.exchange";

    public static final String DEAD_LETTER_QUEUEC_ROUTING_KEY = "c-letter.routingkey";

    public static final String DEAD_LETTER_QUEUEC_NAME = "c-letter.queue";


    /***
     *  延迟交换机
     * @return
     */

    @Bean("c-delayExchange")
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }


    /***
     *  死信交换机
     * @return
     */

    @Bean("c-deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }


    /***
     * 延迟队列
     *
     * @return
     */

    @Bean("c-delayQueueC")
    public Queue delayQueueC() {
        Map<String, Object> args = new HashMap<>(3);
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEC_ROUTING_KEY);
        return QueueBuilder.durable(DELAY_QUEUEC_NAME).withArguments(args).build();
    }


    /***
     *  死信队列
     * @return
     */

    @Bean("c-deadLetterQueueC")
    public Queue deadLetterQueueC() {
        return new Queue(DEAD_LETTER_QUEUEC_NAME);
    }


    /***
     *  延迟队列C与交换机进行绑定
     * @param queue
     * @param exchange
     * @return
     */

    @Bean
    public Binding delayBindingC(@Qualifier("c-delayQueueC") Queue queue, @Qualifier("c-delayExchange") DirectExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUEC_ROUTING_KEY);
    }

    /***
     *   死信队列
     * @param queue
     * @param exchange
     * @return
     */

    @Bean
    public Binding deadLetterBinding(@Qualifier("c-deadLetterQueueC") Queue queue, @Qualifier("c-deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEC_ROUTING_KEY);
    }


}
