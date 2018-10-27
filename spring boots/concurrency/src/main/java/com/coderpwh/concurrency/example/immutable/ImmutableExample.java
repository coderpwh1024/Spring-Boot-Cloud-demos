package com.coderpwh.concurrency.example.immutable;

import com.coderpwh.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.Map;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/10/27 17:06
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample {

    private final static Integer a = 1;

    private final static String b = "2";

    private final static Map<Integer, Integer> map = Maps.newHashMap();

    public static Logger log = org.slf4j.LoggerFactory.getLogger("aa");

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);

    }

    public static void main(String[] args) {
        map.put(1, 3);
        log.info("{}", map.get(1));
    }





}
