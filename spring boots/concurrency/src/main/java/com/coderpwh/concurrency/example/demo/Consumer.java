package com.coderpwh.concurrency.example.demo;

import java.util.concurrent.BlockingQueue;

/**
 *  消费者
 */
public class Consumer implements Runnable {

    BlockingQueue<String> queue;


    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {

        try {
            // 如果队列为null,会阻塞当前线程
            String temp = queue.take();
            System.out.println(temp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
