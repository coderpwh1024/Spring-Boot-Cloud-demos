package com.coderpwh.concurrency.example.publish;

import com.coderpwh.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {

        UnsafePublish unsafePublish = new UnsafePublish();
        LoggerFactory.getLogger("").info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        LoggerFactory.getLogger("").info("{}", Arrays.toString(unsafePublish.getStates()));


    }
}
