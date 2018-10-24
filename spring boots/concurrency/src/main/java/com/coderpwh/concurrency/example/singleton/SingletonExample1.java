package com.coderpwh.concurrency.example.singleton;

import com.coderpwh.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉式单例设计模式
 *
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/24 17:49
 */
@NotThreadSafe
public class SingletonExample1 {

    private static SingletonExample1 instance = null;

    private SingletonExample1() {

    }

    public static SingletonExample1 getInstance() {

        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }

}
