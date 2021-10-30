package com.coderpwh.kafka.message;

public class Demo07Message {

    public static final String TOPIC = "DEMO_07";

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
