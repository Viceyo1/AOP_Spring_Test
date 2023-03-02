package com.itheima.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProjectAdvice {


    @Pointcut("execution(* com.itheima.service.*Service.*(..))")
    private void servicePt(){};


    @Around("servicePt()")
    public void runSpeed(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            joinPoint.proceed();
        }
        long end = System.currentTimeMillis();
        System.out.println("万次执行时间：" + className + '.'+ methodName+ "-------->"+ (end - start) + "ms");

    }
}
