package com.coderpwh.rabbitmq.message;

import java.io.Serializable;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/4/23 0023 16:33
 */
public class Demo03Message implements Serializable {

    public static final String QUEUE_A = "QUEUE_DEMO_03_A";
    public static final String QUEUE_B = "QUEUE_DEMO_03_B";

    public static final String EXCHANGE = "EXCHANGE_DEMO_03";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Demo03Message{" +
                "id=" + id +
                '}';
    }
}
