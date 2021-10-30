package com.coderpwh.kafka.message;

/**
 * @author coderpwh
 */
public class Demo02Message {

    public static final String TOPIC = "DEMO_02";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Demo02Message{" +
                "id=" + id +
                '}';
    }
}
