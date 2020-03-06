package com.coderpwh.note.note;

import java.lang.annotation.*;

/**
 * @author coderpwh
 * @create 2020-03-06 15:46
 * @desc ${DESCRIPTION}
 **/

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface KthLog {

    String  value() default "this is log Are you ok?";

}
