package com.geekbrains.spring.web;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppAspects {
    public static int cartServiceTotalTime = 0;
    public static int orderServiceTotalTime = 0;
    public static int productsServiceTotalTime = 0;
    public static int userServiceTotalTime = 0;

    @Around("execution(public * com.geekbrains.spring.web.services.*.*(..))")
    public Object servicesDurationStatistic(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        if (proceedingJoinPoint.getTarget().getClass().getSimpleName().equals("CartService")) {
            cartServiceTotalTime += duration;
        }
        if (proceedingJoinPoint.getTarget().getClass().getSimpleName().equals("OrderService")) {
            orderServiceTotalTime += duration;
        }
        if (proceedingJoinPoint.getTarget().getClass().getSimpleName().equals("ProductsService")) {
            productsServiceTotalTime += duration;
        }
        if (proceedingJoinPoint.getTarget().getClass().getSimpleName().equals("UserService")) {
            userServiceTotalTime += duration;
        }
        return out;
    }


}
