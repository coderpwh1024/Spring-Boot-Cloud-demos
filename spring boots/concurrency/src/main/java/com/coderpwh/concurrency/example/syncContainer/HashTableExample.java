package com.coderpwh.concurrency.example.syncContainer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class HashTableExample {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;
    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");
    private static Map<Integer, Integer> map = new Hashtable<>();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        //        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            // jdk1.8 lambda
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception:", e);
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}",map.size());

    }

    public static void update(int i) {
        map.put(i, i);
    }
}
