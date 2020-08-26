package com.coderpwh.delay.producer;

import java.util.Map;


/***
 *  消息生产方服务接口
 */
public interface IMessageProducerService {

    /**
     * 发送json格式字符串的消息
     *
     * @param exchange   交换机名称
     * @param routingKey 路由key
     * @param delay      延迟时间 单位秒
     * @param object     对象
     * @param headers    消息头
     */
    void sendJsonMessage(String exchange, String routingKey, Integer delay, Object object, Map<String, Object> headers);
}
