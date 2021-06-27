package com.shakag.config;

import com.shakag.filter.CaptchaFilter;
import com.shakag.interceptor.AuthenticationFailureHandlerImpl;
import com.shakag.interceptor.AuthenticationSuccessHandlerImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@Import(BCryptPasswordEncoder.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Resource
    AuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Resource
    CaptchaFilter captchaFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录之前先确认验证码
        http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        http.authorizeRequests().anyRequest().authenticated();

        //关闭csrf防护
        http.csrf().disable();
    }
}
