package com.coderpwh.concurrency.example.immutable;

import com.coderpwh.concurrency.annoations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xinzhixuan
 * @version V1.0
 * @date 2018/10/27 17:35
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList<? extends Number> list = ImmutableList.of(1, 2, 3.);


    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);

    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).put(5, 6).build();


    public static void main(String[] args) {
        //        list.add(4);
//        set.add(4);

//        map2.put(1, 4);
        System.out.println(map2.get(3));


    }

}
