package com.coderpwh.concurrency.example.aqs;

import org.slf4j.Logger;

import java.util.concurrent.*;

public class CyclicBarrierExample2 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");


    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.info("exception", e);
                }

            });
        }


    }

    public static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);

        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (BrokenBarrierException | TimeoutException e) {
            log.warn("BrokenBarrierException", e);
        }

        log.info("{} continue");
    }
}
