package com.fym;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//增强的类
@Component
@Aspect//生成代理对象
@Order(3)
public class UserProxy {

    //前置通知
    @Before(value = "execution(* com.fym.User.add(..))")
    public void before(){
        System.out.println("user before");
    }

    //后置通知
    @After(value = "execution(* com.fym.User.add(..))")
    public void after(){
        System.out.println("after");
    }
    //最终通知
    @AfterReturning(value = "execution(* com.fym.User.add(..))")
    public void afterReturning(){
        System.out.println("afterReturning");
    }
    //异常通知
    @AfterThrowing(value = "execution(* com.fym.User.add(..))")
    public void afterThrowing(){
        System.out.println("afterThrowing");
    }
    //环绕通知
    @Around(value = "execution(* com.fym.User.add(..))")
    public void aound(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕之前");
        //被增强方法的执行
        proceedingJoinPoint.proceed();
        System.out.println("环绕之后");
    }
}
