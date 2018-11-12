package com.coderpwh.concurrency.example.Lock;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockExample6 {

    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info("wait single");  // 1
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get single");  // 4
            reentrantLock.unlock();
        }).start();


        new Thread(() -> {
            reentrantLock.lock();
            log.info("get single");  // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send single"); // 3
            reentrantLock.unlock();

        }).start();

    }
}
