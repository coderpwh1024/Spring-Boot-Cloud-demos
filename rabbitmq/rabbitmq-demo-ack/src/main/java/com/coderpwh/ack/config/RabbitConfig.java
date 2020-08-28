package com.coderpwh.ack.config;


import com.coderpwh.ack.message.Demo12Message;
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
        public Queue demo12Queue() {
             return  new Queue(Demo12Message.QUEUE,true,false,false);
        }


        @Bean
        public DirectExchange demo12Exchange(){
            return  new DirectExchange(Demo12Message.EXCHANGE,true,false);
        }



        @Bean
        public Binding demo12Binding(){
            return BindingBuilder.bind(demo12Queue()).to(demo12Exchange()).with(Demo12Message.ROUTING_KEY);
        }

    }

}
