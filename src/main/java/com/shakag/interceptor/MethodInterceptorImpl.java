package com.shakag.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切面 aop MethodInterceptor
 */
@Aspect
@Component
public class MethodInterceptorImpl implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("aop methodInterceptor");
        // 原方法执行
        Object proceed = invocation.proceed();
        // 获取原方法的参数
        Object[] arguments = invocation.getArguments();
        // 获取原方法
        Method method = invocation.getMethod();
        String name = method.getName();
        System.out.println("after aop methodInterceptor");
        return proceed;
    }
}
