package com.coderpwh.confirm.config;

import com.coderpwh.confirm.message.Demo13Message;
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
         public Queue demo13Queue(){
             return  new Queue(Demo13Message.QUEUE,true,false,false);
         }


         @Bean
         public DirectExchange demo13Exchange(){
             return new DirectExchange(Demo13Message.EXCHANGE,
                     true,  // durable: 是否持久化
                     false);  // exclusive: 是否排它
         }

         // 创建 Binding
         // Exchange：Demo13Message.EXCHANGE
         // Routing key：Demo13Message.ROUTING_KEY
         // Queue：Demo13Message.QUEUE
         @Bean
         public Binding demo13Binding() {
             return BindingBuilder.bind(demo13Queue()).to(demo13Exchange()).with(Demo13Message.ROUTING_KEY);
         }


     }


}
