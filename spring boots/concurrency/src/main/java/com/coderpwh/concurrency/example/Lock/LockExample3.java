package com.coderpwh.concurrency.example.Lock;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/11/9 17:36
 */

@Slf4j
public class LockExample3 {


    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<String, Date> map = new HashMap<>();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public static void main(String[] args) throws InterruptedException {

    }

    public Date get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        }  finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Date put(String key, Date value) {

        writeLock.lock();

        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }

    }


    class Date {

    }
}


