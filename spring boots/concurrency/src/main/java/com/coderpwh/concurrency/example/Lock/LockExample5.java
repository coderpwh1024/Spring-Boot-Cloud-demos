package com.coderpwh.concurrency.example.Lock;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/11/9 17:36
 */

@Slf4j
public class LockExample5 {


    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;
    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");
    private static StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {


        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception:", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count {}", count);


    }

    public static void add() {
        long stame = lock.writeLock();
        try {
            count++;
        } catch (Exception e) {
            log.info("Exception", e);
        } finally {
            lock.unlock(stame);
        }


    }

}
