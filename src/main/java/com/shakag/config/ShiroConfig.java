package com.shakag.config;

import com.shakag.impl_extend.ShiroRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    //将自己的验证方式加入容器
    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroBean = new ShiroFilterFactoryBean();
        shiroBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        //anon:无需认证 authc:必需认证 perms:拥有对某个资源的权限才能访问 role:拥有某个角色权限才能访问
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/index","authc");
        map.put("/add","authc");
        shiroBean.setFilterChainDefinitionMap(map);

        //设置登录的请求(没有权限的页面会跳转到此页面)
        shiroBean.setLoginUrl("/login");

        return shiroBean;
    }
}