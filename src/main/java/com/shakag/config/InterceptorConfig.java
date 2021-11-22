package com.shakag.config;

import com.shakag.interceptor.MethodInterceptorImpl;
import com.shakag.interceptor.TokenInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final MethodInterceptorImpl methodInterceptor;
    private final TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/","/login","/kaptcha");
    }


    /**
     * 切面 MethodInterceptorImpl环绕切面
     * @return advisor
     */
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.shakag.controller..*.*(..))");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        // 指定 切点 路径
        advisor.setPointcut(pointcut);
        // 指定 methodInterceptor 切面实现类
        advisor.setAdvice(methodInterceptor);
        return advisor;
    }
}
