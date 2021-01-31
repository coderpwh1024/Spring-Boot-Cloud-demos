package com.codrpwh.rabbitmq.demo01.message;

import java.io.Serializable;

public class Demo07Message implements Serializable {


    public static final String QUEUE = "QUEUE_DEMO_07";

    // 死信队列
    public static final String DEAD_QUEUE = "DEAD_QUEUE_DEMO_07";


    public static final String EXCHNAGE = "EXCHANGE_DEMO_07";


    public static final String ROUTING_KEY = "ROUTING_KEY_07";

    //  死信路由器
    public static final String DEAD_ROUTING_KEY = "DEAD_ROUTING_KEY_07";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Demo07Message{" +
                "id=" + id +
                '}';
    }
}
