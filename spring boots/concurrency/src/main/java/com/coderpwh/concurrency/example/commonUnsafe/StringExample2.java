package com.coderpwh.concurrency.example.commonUnsafe;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/30 17:25
 */
@Slf4j
public class StringExample2 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;
    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");
    private static   StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        //        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {

            // jdk1.8 lambda
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception:", e);
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("stringBuilder:,{}", sb.length());


    }

    public static void update() {
        sb.append("1");
    }


}
