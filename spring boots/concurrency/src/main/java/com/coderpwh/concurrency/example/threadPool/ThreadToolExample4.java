package com.coderpwh.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadToolExample4 {

    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}", index);
                }
            });

        }
        executorService.shutdown();

    }
}
