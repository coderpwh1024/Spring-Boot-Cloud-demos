package com.coderpwh.rabbitmq.message;

import java.io.Serializable;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/4/23 0023 16:35
 */
public class Demo04Message implements Serializable {


    public static final String QUEUE = "QUEUE_DEMO_04_A";

    public static final String EXCHANGE = "EXCHANGE_DEMO_04";

    public static final String HEADER_KEY = "color";
    public static final String HEADER_VALUE = "red";


    private Integer id;

    public Integer getId() {
        return id;
    }

    public Demo04Message setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Demo04Message{" +
                "id=" + id +
                '}';
    }
}
