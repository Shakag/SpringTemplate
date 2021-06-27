package com.shakag.config;

import com.shakag.interceptor.AuthenticationFailureHandlerImpl;
import com.shakag.interceptor.AuthenticationSuccessHandlerImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@Import(BCryptPasswordEncoder.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Resource
    AuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        http.authorizeRequests().anyRequest().authenticated();

        //关闭csrf防护
        http.csrf().disable();
    }
}
