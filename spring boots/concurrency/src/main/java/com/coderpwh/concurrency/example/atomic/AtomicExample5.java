package com.coderpwh.concurrency.example.atomic;

import com.coderpwh.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    //    @Getter
    public volatile int count = 100;

    public int getCount() {
        return count;
    }


    private static AtomicExample5 example5 = new AtomicExample5();

    public static void main(String[] args) {
        if (updater.compareAndSet(example5, 100, 120)) {
            LoggerFactory.getLogger("").info("update success 1,{}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            LoggerFactory.getLogger("").info("update success 2 ,{}", example5.getCount());
        } else {
            LoggerFactory.getLogger("").info("update fail,{}", example5.getCount());
        }

    }


}
