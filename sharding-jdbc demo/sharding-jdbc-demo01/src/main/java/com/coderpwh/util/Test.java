package com.coderpwh.util;

/**
 * @author coderpwh
 * @create 2019-08-03 23:27
 * @desc ${DESCRIPTION}
 **/
public class Test {


    public static void main(String[] args) {


        for (int i = 0; i < 100; i++) {
            Long id = SnowFlake.nextId();

            System.out.println(id);

        }
    }
}
