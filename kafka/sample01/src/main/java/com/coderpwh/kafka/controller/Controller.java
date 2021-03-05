package com.coderpwh.kafka.controller;

import com.coderpwh.kafka.common.Foo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coderpwh
 */
@RestController
public class Controller {

    @Autowired
    private KafkaTemplate<Object, Object> template;


    @PostMapping(path = "/send/foo/{what}")
    public void sendFoo(@PathVariable String what) {
        this.template.send("topic1", new Foo1(what).toString());
    }

}
