package com.coderpwh.concurrency.example.commonUnsafe;

import org.slf4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/30 17:59
 */
public class DateFormateExample2 {
    // 请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;
    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");



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

    }

    public static void update() {
        try {
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20180208");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
