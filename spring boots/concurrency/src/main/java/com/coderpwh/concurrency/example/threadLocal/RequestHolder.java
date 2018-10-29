package com.coderpwh.concurrency.example.threadLocal;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/29 16:02
 */
public class RequestHolder {


    private final static ThreadLocal<Long> requestHollder = new ThreadLocal<>();


    public static void add(Long id) {
        requestHollder.set(id);
    }

    public static Long getId() {
        return requestHollder.get();
    }

    public static void remove() {
        requestHollder.remove();
        ;
    }


}
