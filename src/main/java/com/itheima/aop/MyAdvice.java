package com.itheima.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(* com.itheima.dao.BookDao.findName(..))")
    private void pt(){}



    /*
    ---------------------获得方法的输入参数----------------------------------------
     */
//    @Before("pt()")
    public void before(JoinPoint jp) {
        Object[] args = jp.getArgs();
        System.out.println(Arrays.toString(args));

        System.out.println("before advice ..." );
    }

//    @After("pt()")
    public void after(JoinPoint jp) {
        Object[] args = jp.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("after advice ...");
    }

//    @Around("pt()")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable{
//
//        Object[] args = pjp.getArgs();
//        System.out.println(Arrays.toString(args));
//        //---------------------修改方法的传入数据------------------------------------
//        args[0] = 666;
//        Object ret = pjp.proceed(args);
//
//        return ret;
//    }



    /*
    ------------------------------------抛异常处理------------------------------------
     */
    //    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp){

        Object[] args = pjp.getArgs();
        System.out.println(Arrays.toString(args));

        args[0] = 666;
        Object ret = null;
        try {
            ret = pjp.proceed(args);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return ret;
    }




/*
------------------------------------获得方法的输出值------------------------------------
 */
    @AfterReturning(value = "pt()", returning = "ret")
    public void afterReturning(JoinPoint jp , String ret) {                //用该返回值的类型接
        System.out.println("afterReturning advice ..." + ret);
    }

    /*
    ------------------------------------获得方法的抛异常的数据------------------------------------
     */
    @AfterThrowing(value = "pt()",throwing = "t")
    public void afterThrowing(Throwable t) {
        System.out.println("afterThrowing advice ..." + t);
    }
}
