package com.nhnacademy.edu.springframework.project.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    @Around("execution(* com.nhnacademy.edu.springframework.project.service..*(..))")
    public Object loggingSpeedMeasurement(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            String className = pjp.getTarget().getClass().getName();
            String methodName = pjp.getSignature().getName();
            System.out.println(className + "." + methodName + " " + stopWatch.getTotalTimeMillis()+"ms");
        }
    }
}
