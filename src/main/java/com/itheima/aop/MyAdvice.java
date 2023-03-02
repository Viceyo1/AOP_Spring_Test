package com.itheima.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
//@Aspect
public class MyAdvice {
    @Pointcut("execution(* com.itheima.dao.BookDao.findName(..))")
    private void pt(){}


    @Before("pt()")
    public void before(JoinPoint jp) {

        System.out.println("before advice ..." );
    }

    @After("pt()")
    public void after(JoinPoint jp) {

        System.out.println("after advice ...");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Object ret = pjp.proceed();

        return ret;
    }


    @AfterReturning("pt()")
    public void afterReturning() {
        System.out.println("afterReturning advice ...");
    }


    @AfterThrowing("pt()")
    public void afterThrowing() {
        System.out.println("afterThrowing advice ...");
    }
}
