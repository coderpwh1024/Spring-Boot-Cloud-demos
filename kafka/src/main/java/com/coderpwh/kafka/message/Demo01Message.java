package com.coderpwh.kafka.message;

import java.io.Serializable;

public class Demo01Message {

    public static final String TOPIC = "DEMO_01";

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
