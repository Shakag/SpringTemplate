package com.shakag.entity;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@Data
public class BeanLifeCycle implements InitializingBean, DisposableBean {
    private String name;
    private int age;

    // bean 初始化阶段调用
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("bean init");
    }

    // bean 销毁阶段调用
    @Override
    public void destroy() throws Exception {
        System.out.println("bean destroy");
    }
}
