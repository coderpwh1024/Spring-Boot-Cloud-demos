package com.coderpwh.concurrency.example.aqs;

import org.slf4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample2 {

    private final static int threadCount = 20;
    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");


    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();
//        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        final Semaphore semaphore = new Semaphore(3);


        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    // 获取3个许可
                    semaphore.acquire(3);
                    test(threadNum);
                    // 释放3个许可
                    semaphore.release(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

        log.info("finish");
        exec.shutdown();

    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }


}
