package com.coderpwh.concurrency.example.demo;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者模式
 */
public class Producer implements Runnable {

    BlockingQueue<String> queue;

    /**
     * 阻塞队列
     *
     * @param queue
     */
    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String temp = "A Producer.生成线程" + Thread.currentThread().getName();
            System.out.println(" I have made a producer :" + Thread.currentThread().getName());
            queue.add(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
