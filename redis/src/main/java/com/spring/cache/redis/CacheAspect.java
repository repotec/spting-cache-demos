package com.spring.cache.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Aspect
@Configuration
public class CacheAspect {
    Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    @Around("within(com.spring.cache.redis.*)")
    public Object logPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("*************");
        long startTime = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            long finishTime = System.currentTimeMillis();
            Duration duration = Duration.ofMillis(finishTime - startTime);
            logger.info(String.format("[Method] %s executes for %s", proceedingJoinPoint.getSignature(), duration));
        }
    }
}

