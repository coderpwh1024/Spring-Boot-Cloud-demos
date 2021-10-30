package com.coderpwh.kafka.message;

public class Demo04Message {


    public static final String TOPIC = "DEMO_04";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Demo04Message{" +
                "id=" + id +
                '}';
    }

    public Demo04Message(Integer id) {
        this.id = id;
    }

}

