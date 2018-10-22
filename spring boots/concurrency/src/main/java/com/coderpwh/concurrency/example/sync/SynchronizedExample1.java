package com.coderpwh.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/22 16:44
 */

@Slf4j
public class SynchronizedExample1 {


    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test4(1);

        });
        executorService.execute(() -> {
            example2.test4(2);
        });

    }

    //  修饰一个代码块
    public void test1() {
        synchronized (this) {
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

    public void test3(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                LoggerFactory.getLogger("").info("test3-{} -{}", j, i);
            }
        }
    }

    public  synchronized  void test4(int j){
        for(int i=0;i<10;i++){
            LoggerFactory.getLogger("").info("test4-{} -{}", j, i);
        }
    }




}
