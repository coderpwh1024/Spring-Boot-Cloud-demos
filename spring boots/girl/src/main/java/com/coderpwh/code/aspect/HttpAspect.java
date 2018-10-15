package com.coderpwh.code.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author coderpwh
 * @Date: 2018/1/29.
 * @Description:
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.coderpwh.code.controller.GirlController.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        /**
         * URL
         */
        logger.info("url={}", request.getRequestURL());


        /**
         * method
         */
        logger.info("url={}", request.getMethod());

        /**
         * IP
         */
        logger.info("ip={}", request.getRemoteAddr());

        /**
         * method
         */
        logger.info("class_name={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());


        /**
         * args
         */
        logger.info("args={}", joinPoint.getArgs());

    }

    @After("log()")
    public void doAfter() {
        logger.info("22222222222");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {

//        logger.info("request={}", object);
    }

}
