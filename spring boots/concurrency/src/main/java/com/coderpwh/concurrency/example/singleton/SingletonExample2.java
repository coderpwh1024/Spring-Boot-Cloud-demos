package com.coderpwh.concurrency.example.singleton;

import com.coderpwh.concurrency.annoations.ThreadSafe;

/**
 *  饿汉式单例设计模式
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/24 17:49
 */
@ThreadSafe
public class SingletonExample2 {

  private SingletonExample2(){

  }
  private static SingletonExample2 instance =  new SingletonExample2();

  public static SingletonExample2 getInstance(){

      if(instance==null){
          instance = new SingletonExample2();
      }
      return  instance;
  }

}
