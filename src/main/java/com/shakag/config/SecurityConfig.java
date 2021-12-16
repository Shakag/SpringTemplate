package com.shakag.config;

import com.shakag.filter.CaptchaFilter;
import com.shakag.impl_extend.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@Import(BCryptPasswordEncoder.class)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final CaptchaFilter captchaFilter;

    /**
     * + 配置认证管理
     * + 用于通过允许AuthenticationProvider容易地添加来建立认证机制。也就是说用来记录账号，密码，角色信息
     *
     * @param auth 权限管理
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //指定要使用的自定义登录实现类
        auth.userDetailsService(userDetailsServiceImpl);

//        // 在内存中配置了一个用户
//        auth.inMemoryAuthentication()
//                // 指定加密方式
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("root")
//                // Spring5 开始，强制要求密码要加密
//                .password(new BCryptPasswordEncoder().encode("3333"))
//                // 权限跟角色都给时，权限会失效
//                .roles("admin")
//                .authorities("sys:add", "sys:update", "sys:delete", "sys:select");
    }

    /**
     * 对角色的权限——所能访问的路径做出限制
     *
     * @param http http 的设置有顺序的要求
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录之前先确认验证码
        http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .successHandler(successHandler())
                .failureHandler(failureHandler());

        //所有请求均拦截
        http.authorizeRequests().anyRequest().authenticated();

        //关闭csrf防护
        http.csrf().disable();
    }

    /**
     * 登录成功时执行此方法
     *
     * @return void
     */
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            System.out.println("成功处理器 AuthenticationSuccessHandler");
        };
    }

    /**
     * 登录失败时执行此方法
     *
     * @return void
     */
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return (httpServletRequest, httpServletResponse, authenticationException) -> {
            System.out.println("失败处理器 AuthenticationFailureHandler");
        };
    }
}
