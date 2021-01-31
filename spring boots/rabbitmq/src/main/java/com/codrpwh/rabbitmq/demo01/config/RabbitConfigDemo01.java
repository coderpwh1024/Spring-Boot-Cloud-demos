package com.codrpwh.rabbitmq.demo01.config;

import com.codrpwh.rabbitmq.demo01.message.Demo01Message;
import com.codrpwh.rabbitmq.demo01.message.Demo02Message;
import com.codrpwh.rabbitmq.demo01.message.Demo03Message;
import com.codrpwh.rabbitmq.demo01.message.Demo04Message;
import com.sun.org.apache.bcel.internal.generic.FADD;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author coderpwh
 */
@Configuration
public class RabbitConfigDemo01 {


    /***
     *  DiretExchange 交换机
     */
    public static class DirectExchangeDemoConfiguration {

        /***
         *  声明队列
         * @return
         */
        @Bean
        public Queue demo01Queue() {
            /***
             *  队列名称， 是否持久化，是否排它，是否自动删除
             * Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
             *
             */
            return new Queue(Demo01Message.QUEUE, true, false, false);
        }

        /***
         *  声明交换机
         * @return
         */
        @Bean
        public DirectExchange demo1Exchange() {
            return new DirectExchange(Demo01Message.EXCHANGE, true, false);
        }


        /***
         *  创建Binding
         * @return
         */
        @Bean
        public Binding demo01Binding() {
            return BindingBuilder.bind(demo01Queue()).to(demo1Exchange()).with(Demo01Message.ROUTING_KEY);
        }

    }


    /***
     *   topic 交换机
     */
    public static class TopicExchangeDemoConfiguration {

        @Bean
        public Queue demo02Queue() {
            return new Queue(Demo02Message.QUEUE, true, false, false);
        }

        @Bean
        public TopicExchange demo02Exchange() {
            return new TopicExchange(Demo02Message.EXCHANGE, true, false);
        }


        @Bean
        public Binding demo02Binding() {
            return BindingBuilder.bind(demo02Queue()).to(demo02Exchange()).with(Demo02Message.ROUTING_KEY);
        }

    }


    /****
     *   Fanout Exchange
     */
    public static class FanoutExchangeDemoConfiguration {

        @Bean
        public Queue demo03QueueA() {
            return new Queue(Demo03Message.QUEUE_A, true, false, false);
        }

        @Bean
        public Queue demo03QueueB() {
            return new Queue(Demo03Message.QUEUE_B, true, false, false);
        }


        /***
         *  交换机
         * @return
         */
        @Bean
        public FanoutExchange demo03Exchange() {
            return new FanoutExchange(Demo03Message.EXCHANGE, true, false);
        }

        @Bean
        public Binding demo03BingA() {
            return BindingBuilder.bind(demo03QueueA()).to(demo03Exchange());
        }

        @Bean
        public Binding demo03BindingB() {
            return BindingBuilder.bind(demo03QueueB()).to(demo03Exchange());
        }

    }

    public static class HeadersExchangeDemoConfiguration {

        @Bean
        public Queue demo04Queue() {
            return new Queue(Demo04Message.QUEUE, true, false, false);
        }

        @Bean
        public HeadersExchange demo04Exchange() {
            return new HeadersExchange(Demo04Message.EXCHANGE, true, false);
        }

        @Bean
        public Binding demo04Binding() {

            return BindingBuilder.bind(demo04Queue()).to(demo04Exchange()).where(Demo04Message.HEADER_KEY).matches(Demo04Message.HEADER_VALUE);
        }


    }


}
