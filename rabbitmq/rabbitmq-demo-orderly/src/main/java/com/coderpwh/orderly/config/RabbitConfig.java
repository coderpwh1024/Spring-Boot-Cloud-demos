package com.coderpwh.orderly.config;

import com.coderpwh.orderly.message.Demo10Message;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static  class  DirectExchangeDemoConfiguration{

        @Bean
        public Queue demo10Queue0(){
            return  new Queue(Demo10Message.QUEUE_0);
        }

        @Bean
        public Queue demo10Queue1(){
            return  new Queue(Demo10Message.QUEUE_1);
        }

        @Bean
        public Queue demo10Queue2(){
            return  new Queue(Demo10Message.QUEUE_2);
        }

        @Bean
        public Queue demo10Queue3() {
            return new Queue(Demo10Message.QUEUE_3);
        }

        @Bean
        public DirectExchange demo10Exchange(){
            return  new DirectExchange(Demo10Message.EXCHANGE,true,false);
        }

        // 创建 Binding
        @Bean
        public Binding demo10Binding0() {
            return BindingBuilder.bind(demo10Queue0()).to(demo10Exchange()).with("0");
        }
        @Bean
        public Binding demo10Binding1() {
            return BindingBuilder.bind(demo10Queue1()).to(demo10Exchange()).with("1");
        }
        @Bean
        public Binding demo10Binding2() {
            return BindingBuilder.bind(demo10Queue2()).to(demo10Exchange()).with("2");
        }
        @Bean
        public Binding demo10Binding3() {
            return BindingBuilder.bind(demo10Queue3()).to(demo10Exchange()).with("3");
        }


    }


}
