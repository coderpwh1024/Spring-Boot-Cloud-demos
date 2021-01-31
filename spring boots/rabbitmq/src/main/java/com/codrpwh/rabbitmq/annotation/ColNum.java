package com.codrpwh.rabbitmq.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColNum {

    int colNum();

}
