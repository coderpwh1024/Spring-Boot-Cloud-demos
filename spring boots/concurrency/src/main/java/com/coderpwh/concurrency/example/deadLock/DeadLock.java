package com.coderpwh.concurrency.example.deadLock;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * 死锁
 */
@Slf4j
public class DeadLock implements Runnable {

    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");

    public int flag = 1;

    // 静态对象是类的所有的对象共享
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag:{}", flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("1");
                }
            }

        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("0");
                }
            }
        }

    }

    public static void main(String[] args) {
        DeadLock td1 = new DeadLock();
        DeadLock td2 = new DeadLock();
        td1.flag=1;
        td2.flag=0;

        new Thread(td1).start();
        new Thread(td2).start();

    }



}
