package com.coderpwh.code.service;

import com.coderpwh.code.domain.Girl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author coderpwh
 * @Date: 2018/1/30.
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {

    @Autowired
    private GirlService girlService;

    @Test
    public void findone() {

        Girl girl = girlService.findone(20);
        Assert.assertEquals(new Integer(25),girl.getAge());

    }
}