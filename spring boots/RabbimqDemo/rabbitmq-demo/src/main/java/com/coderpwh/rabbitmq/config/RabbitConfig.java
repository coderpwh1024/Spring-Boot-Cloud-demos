package com.coderpwh.rabbitmq.config;


import com.coderpwh.rabbitmq.message.Demo01Message;
import com.coderpwh.rabbitmq.message.Demo02Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/4/23 0023 15:34
 */
@Configuration
public class RabbitConfig {


    /***
     *  Direct Exchange
     */
    public static class DirectExchangeDemoConfiguration {

        @Bean
        public Queue demo01Queue() {
            return new Queue(Demo01Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }


        /***
         *  创建 Direct Exchange
         * @return
         */
        @Bean
        public DirectExchange demo01Exchange() {

            return new DirectExchange(Demo01Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        /***创建 Binding
         Exchange：Demo01Message.EXCHANGE
         Routing key：Demo01Message.ROUTING_KEY
         Queue：Demo01Message.QUEUE
         */
        @Bean
        public Binding demo01Binding() {
            return BindingBuilder.bind(demo01Queue()).to(demo01Exchange()).with(Demo01Message.ROUTING_KEY);
        }


    }


    /****
     *   Topic 模式
     *   
     */

    public static class TopicExchangeDemoConfiguration {

        // 创建 Queue
        @Bean
        public Queue demo02Queue() {
            return new Queue(Demo02Message.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Topic Exchange
        @Bean
        public TopicExchange demo02Exchange() {
            return new TopicExchange(Demo02Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo02Message.EXCHANGE
        // Routing key：Demo02Message.ROUTING_KEY
        // Queue：Demo02Message.QUEUE
        @Bean
        public Binding demo02Binding() {
            return BindingBuilder.bind(demo02Queue()).to(demo02Exchange()).with(Demo02Message.ROUTING_KEY);
        }


    }


}
