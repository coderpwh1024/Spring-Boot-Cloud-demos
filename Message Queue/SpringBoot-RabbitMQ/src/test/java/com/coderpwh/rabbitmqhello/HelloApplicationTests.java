package com.coderpwh.rabbitmqhello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = HelloApplication.class)
@SpringBootTest
public class HelloApplicationTests {

    @Autowired
    private Sender sender;


    @Test
    public void hello() throws Exception{
        sender.send();
    }


}
