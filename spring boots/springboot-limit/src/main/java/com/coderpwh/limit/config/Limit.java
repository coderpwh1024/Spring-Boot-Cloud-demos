package com.coderpwh.limit.config;

import java.lang.annotation.*;

/**
 * @author coderpwh
 * @create 2020-03-08 15:38
 * @desc ${DESCRIPTION}
 **/

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Limit {

    /***
     *  资源名称
     *
     * @return
     */
    String name() default "";

    /***
     *  资源key
     * @return
     */
    String key() default "";

    /***
     * Key的prefix
     * @return
     */
    String prefix() default "";


    /**
     * 给定的时间段
     *
     * @return
     */
    int period();

    /**
     * 访问次数
     *
     * @return
     */
    int count();

    /***
     *  类型
     * @return
     */
    LimitType limitType() default LimitType.CUSTOMER;


}
