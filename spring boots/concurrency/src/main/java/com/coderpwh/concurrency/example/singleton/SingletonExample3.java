package com.coderpwh.concurrency.example.singleton;

import com.coderpwh.concurrency.annoations.NotRecommend;
import com.coderpwh.concurrency.annoations.ThreadSafe;

/**
 * 懒汉式单例设计模式
 *
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/24 17:49
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    private static SingletonExample3 instance = null;

    private SingletonExample3() {

    }

    public  static synchronized SingletonExample3 getInstance() {

        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }

}
