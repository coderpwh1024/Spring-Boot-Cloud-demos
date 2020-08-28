package com.coderpwh.rpc.config;

import com.coderpwh.rpc.message.Demo14Message;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    public static class DirectExchangeDemoConfiguration {


        @Bean
        public Queue demo01Queue() {
            return new Queue(Demo14Message.QUEUE, false, false, false);
        }

        @Bean
        public DirectExchange  demo01Exchange(){
            return  new DirectExchange(Demo14Message.EXCHANGE,false,false);
        }

        @Bean
        public Binding  demo01Binding(){
            return BindingBuilder.bind(demo01Queue()).to(demo01Exchange()).with(Demo14Message.ROUTING_KEY);
        }



    }


}
