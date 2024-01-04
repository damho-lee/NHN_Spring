package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.annotation.Tracing;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.nhnacademy.edu.springframework.messagesender.MessageSender.sendMessage(..))")
    private void messaging() {}

    @Around("@annotation(tracing)")
    private Object timeCheck(ProceedingJoinPoint pjp, Tracing tracing) throws Throwable{
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();

            return pjp.proceed();
        } finally {
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
        }
    }
}
