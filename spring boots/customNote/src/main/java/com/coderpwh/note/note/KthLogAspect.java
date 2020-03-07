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
public class KthLogAspect {

    @Pointcut("@annotation(com.coderpwh.note.note.KthLog)")
    private void pointcut() {
    }

    
    @Before("pointcut() &&@annotation(logger)")
    public void advice(KthLog logger) {
        System.out.println(" ---- 哥写的日志内容为:[ " + logger.value() + "] -----");
    }


}
