package com.coderpwh.kafka.message;

public class Demo08Message {

    public static final String TOPIC = "DEMO_08";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Demo08Message{" +
                "id=" + id +
                '}';
    }
}
