package com.codrpwh.rabbitmq.config;

import com.codrpwh.rabbitmq.common.Constant;
import com.codrpwh.rabbitmq.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author coderpwh
 */
@Configuration
@Slf4j
public class RabbitConfig {


    @Resource
    private CachingConnectionFactory connectionFactory;


    @Resource
    private MsgLogService msgLogService;


    /***
     *  登录 队列名称
     */
    public static final String LOGIN_LOG_QUEUE_NAME = "login.log.queue";

    /***
     *    登录 交换机名称
     */

    public static final String LOGIN_LOG_EXCHANGE_NAME = "login.log.exchange";

    /****
     *   登录 路由名称
     */

    public static final String LOGIN_LOG_ROUTING_KEY_NAME = "login.log.routing.key";


    /***
     *  邮箱 队列名称
     */
    public static final String MAIL_QUEUE_NAME = "mail.queue";


    /***
     *
     *   邮箱 交换机名称
     *
     */
    public static final String MAIL_EXCHANGE_NAME = "mail.exchange";

    /****
     *   邮箱 路由名称
     *
     */
    public static final String MAIL_ROUTING_KEY_NAME = "mail.routing.key";


    /***
     *  邮箱队列Bean
     * @return
     */
    @Bean
    public Queue mailQueue() {
        return new Queue(MAIL_QUEUE_NAME, true);
    }

    /***
     *   邮箱交换机Bean
     * @return
     */
    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange(MAIL_EXCHANGE_NAME, true, false);
    }


    /***
     *  邮箱 Binding 将邮箱队列，交换机都绑定到路由上
     * @return
     */
    @Bean
    public Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MAIL_ROUTING_KEY_NAME);
    }


    /**
     * 队列
     *
     * @return
     */
    @Bean
    public Queue logUserQUeUe() {
        return new Queue(LOGIN_LOG_QUEUE_NAME, true);
    }


    /**
     * 交换机
     *
     * @return
     */
    @Bean
    public DirectExchange logUserExchanage() {
        return new DirectExchange(LOGIN_LOG_EXCHANGE_NAME, true, false);
    }

    /**
     * 将队列通过交换机绑定在路由上
     *
     * @return
     */
    @Bean
    public Binding logUserBinding() {
        return BindingBuilder.bind(logUserQUeUe()).to(logUserExchanage()).with(LOGIN_LOG_ROUTING_KEY_NAME);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }


   /* @Bean
    public RabbitTemplate rabbitTemplate() {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息成功发送到Exchange");
                String msgId = correlationData.getId();
                //   msg 消息记录
                msgLogService.updateStatus(msgId, Constant.MsgLogStatus.DELIVER_SUCCESS);

            } else {
                log.error("消息发送到Exchange失败,{},cause:{}", correlationData, cause);
            }

        });

         //
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
        });
        return rabbitTemplate;

    }*/


}
