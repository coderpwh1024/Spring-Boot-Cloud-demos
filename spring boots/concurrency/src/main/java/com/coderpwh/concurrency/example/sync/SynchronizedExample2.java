package com.coderpwh.concurrency.example.sync;

import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/22 17:17
 */
public class SynchronizedExample2 {

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example2.test2();
        });
        executorService.execute(() -> {
            example2.test2();
        });

    }

    //  修饰一个类
    public void test1() {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                LoggerFactory.getLogger("").info("test1-{}", i);
            }
        }
    }

    // 修饰一个方法
    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            LoggerFactory.getLogger("").info("test2-{}", i);
        }
    }


}
