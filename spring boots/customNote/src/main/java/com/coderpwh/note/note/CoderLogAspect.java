package com.coderpwh.note.note;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author coderpwh
 * @create 2020-03-06 16:51
 * @desc ${DESCRIPTION}
 **/
@Component
@Aspect
public class CoderLogAspect {

    @Pointcut("@annotation(com.coderpwh.note.note.CoderLog)")
    private void pointcut() {
    }

    
    @Before("pointcut() &&@annotation(logger)")
    public void advice(CoderLog logger) {
        System.out.println(" ---- 哥写的日志内容为:[ " + logger.value() + "] -----");
    }


}
