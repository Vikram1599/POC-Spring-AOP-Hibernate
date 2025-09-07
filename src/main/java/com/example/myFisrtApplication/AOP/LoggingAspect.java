package com.example.myFisrtApplication.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
    @Before("execution(* com.example.myFisrtApplication.Service.*.*(..))")
    public void logBefore(JoinPoint jp) {
        System.out.println("Calling: " + jp.getSignature());
        logger.info("Calling method: {}", jp.getSignature());
    }

    @After("execution(* com.example.myFisrtApplication.Service.*.*(..))")
    public void logAfter(JoinPoint jp) {
    	logger.info("Calling method: {}", jp.getSignature());
    }
    
    @Around("execution(* com.example.myFisrtApplication.Service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Proceed with the actual method call
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        logger.info("Method {} executed in {} ms", joinPoint.getSignature(), duration);
        return result;
    }
}
