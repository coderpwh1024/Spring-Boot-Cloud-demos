package com.coderpwh.concurrency.example.aqs;

import org.slf4j.Logger;

import java.util.concurrent.*;

public class SemaphoreExample1 {

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
                    semaphore.acquire();
                    test(threadNum);
                    semaphore.release();
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
