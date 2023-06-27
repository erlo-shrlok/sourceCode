package com.jinzunyue.share.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 使用AOP进行日志记录
 */
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.jinzunyue.share.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint)throws Throwable{
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature() + " executed in "+executionTime +"ms");
        return proceed;
    }
}
