package com.shakag.config;

import com.shakag.filter.FirstFilter;
import com.shakag.filter.SecondFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class FilterConfig {

    @Resource
    private FirstFilter firstFilter;

    @Resource
    private SecondFilter secondFilter;

    @Bean
    public FilterRegistrationBean registerAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(firstFilter);
        registration.addUrlPatterns("/*");
//        registration.setName("authFilter");
        registration.setOrder(2);  //值越小，Filter越靠前。
        return registration;
    }

    @Bean
    public FilterRegistrationBean SecondFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(secondFilter);
        registration.addUrlPatterns("/*");
//        registration.setName("authFilter");
        registration.setOrder(1);  //值越小，Filter越靠前。
        return registration;
    }

    //如果有多个Filter，再写一个public FilterRegistrationBean registerOtherFilter(){...}即可。
}