package com.nhnacademy.edu.springframework.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.nhnacademy.edu.springframework..*(..))")
    public Object speedMeasurement(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            String className = pjp.getTarget().getClass().getName();
            String methodName = pjp.getSignature().getName();
            logger.info("{}.{} {}ms", className, methodName, stopWatch.getTotalTimeMillis());
        }
    }
}
