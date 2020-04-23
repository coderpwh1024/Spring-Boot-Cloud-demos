package com.coderpwh.rabbitmq.message;

import java.io.Serializable;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/4/23 0023 15:42
 */
public class Demo01Message implements Serializable {

    public static  final String QUEUE ="QUEUE_DEMO_01";

    public static final  String EXCHANGE="EXCHANGE_DEMO_01";

    public static final  String ROUTING_KEY="ROUTING_KEY_01";


    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Demo01Message{" +
                "id=" + id +
                '}';
    }
}
