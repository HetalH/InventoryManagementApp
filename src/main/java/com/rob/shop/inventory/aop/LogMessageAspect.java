package com.rob.shop.inventory.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogMessageAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(LogMessage)")
    public void logMessages(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Class: {} Method :{} started...",methodName);
        joinPoint.proceed();
        logger.info("Class: {} Method :{} execution completed...",methodName);

    }
}
