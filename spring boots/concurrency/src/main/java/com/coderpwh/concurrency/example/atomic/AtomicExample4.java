package com.coderpwh.concurrency.example.atomic;

import com.coderpwh.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {

        count.compareAndSet(0, 1);
        count.compareAndSet(0, 2);
        count.compareAndSet(1, 3);
        count.compareAndSet(2, 4);
        count.compareAndSet(3, 5);
        LoggerFactory.getLogger("").info("count:{}", count);


    }

}
