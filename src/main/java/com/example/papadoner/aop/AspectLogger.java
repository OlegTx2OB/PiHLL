package com.example.papadoner.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j2
@Component
public class AspectLogger {

    @Before("execution(* com.example.papadoner.service.impl.*.*(..))")
    public final void logBeforeServiceCommand(final JoinPoint joinPoint) {
        log.info(() -> String.format("Running method %s with args %s", joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())));
    }

    @AfterReturning(pointcut = "execution(* com.example.papadoner.service.impl.*.*(..))")
    public final void logAfterServiceCommand(final JoinPoint joinPoint) {
        log.info(() -> String.format("Result of %s: success ", joinPoint.getSignature().getName()));
    }

    @AfterThrowing(pointcut = "execution(* com.example.papadoner.*.*.*(..))", throwing = "exception")
    public final void logAfterError(final JoinPoint joinPoint, final Exception exception) {
        log.error(
                () -> String.format("Error while running %s with args %s: %s", joinPoint.getSignature().getName(),
                        Arrays.toString(joinPoint.getArgs()), exception.getMessage()));
    }
}
