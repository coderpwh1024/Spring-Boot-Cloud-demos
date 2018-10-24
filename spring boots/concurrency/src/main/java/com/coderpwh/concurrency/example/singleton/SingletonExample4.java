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
public class SingletonExample4 {

    private static SingletonExample4 instance = null;

    private SingletonExample4() {

    }

    public  static   SingletonExample4 getInstance() {
          // 双重检测机制
        if (instance == null) {
            synchronized (SingletonExample4.class){
                // 同步锁
                if(instance==null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }

}
