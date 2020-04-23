package com.coderpwh.rabbitmq.message;

import java.io.Serializable;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/4/23 0023 16:30
 */
public class Demo02Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_02";

    public static final String EXCHANGE = "EXCHANGE_DEMO_02";

    public static final String ROUTING_KEY = "#.yu.nai";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String
    toString() {
        return "Demo02Message{" +
                "id=" + id +
                '}';
    }
}
