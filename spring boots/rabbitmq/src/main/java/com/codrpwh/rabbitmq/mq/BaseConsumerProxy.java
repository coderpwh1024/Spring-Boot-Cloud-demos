package com.codrpwh.rabbitmq.mq;

import com.codrpwh.rabbitmq.common.Constant;
import com.codrpwh.rabbitmq.pojo.MsgLog;
import com.codrpwh.rabbitmq.service.MsgLogService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.lang.reflect.Proxy;
import java.util.Map;

@Slf4j
public class BaseConsumerProxy {

    private Object target;

    private MsgLogService msgLogService;

    public BaseConsumerProxy(Object target, MsgLogService msgLogService) {
        this.target = target;
        this.msgLogService = msgLogService;
    }

    public Object getProxy() {

        ClassLoader classLoader = target.getClass().getClassLoader();

        Class[] interfaces = target.getClass().getInterfaces();


        Object proxy = Proxy.newProxyInstance(classLoader, interfaces, (proxy1, method, args) -> {

            Message message = (Message) args[0];

            Channel channel = (Channel) args[1];


            /***
             *  获取 correlationId
             */
            String correlationId = getCorrelationId(message);


            /***
             *  判断消息是否已经被消费
             */
            if (isConsumed(correlationId)) {
                log.info("消息已经被消费,correlationId:{}", correlationId);
                return null;
            }

            MessageProperties properties = message.getMessageProperties();

            long tag = properties.getDeliveryTag();

            try {
                Object result = method.invoke(target, args);
                msgLogService.updateStatus(correlationId, Constant.MsgLogStatus.DELIVER_SUCCESS);
                channel.basicAck(tag, false);
                return result;

            } catch (Exception e) {
                log.error("getProxy error", e);
                channel.basicNack(tag, false, true);
                return null;
            }


        });

        return proxy;


    }


    /***
     *   获取CorrelationId
     * @param message
     * @return
     */
    public String getCorrelationId(Message message) {
        String correlationId = null;

        MessageProperties properties = message.getMessageProperties();

        Map<String, Object> headers = properties.getHeaders();

        for (Map.Entry entry : headers.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (key.equals("spring_returned_message_correlation")) {
                correlationId = value;
            }
        }

        return correlationId;

    }


    /***
     *  消息是否已经被消费
     * @param correlationId
     * @return
     */
    private boolean isConsumed(String correlationId) {
        MsgLog msgLog = msgLogService.selectByMsgId(correlationId);

        if (msgLog == null || msgLog.getStatus().equals(Constant.MsgLogStatus.CONSUMED_SUCCESS)) {
            return true;
        }
        return false;
    }


}
