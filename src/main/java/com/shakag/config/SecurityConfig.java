package com.shakag.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shakag.common.Result;
import com.shakag.filter.CaptchaFilter;
import com.shakag.impl_extend.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        //开启登录页面，关闭后不再跳转自带的 login页面
        http.formLogin()
                .successHandler(successHandler())
                .failureHandler(failureHandler());


        //配置自定义403权限不足异常处理器
        http.exceptionHandling().accessDeniedHandler((HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)->{
            response.setContentType("application/json;charset=utf-8");
            ServletOutputStream os = response.getOutputStream();
            os.write(new ObjectMapper().writeValueAsBytes(Result.fail(403,"auth invalid")));
            os.flush();
            os.close();
        });

        //未登录时返回提示
        http.httpBasic().authenticationEntryPoint((request,response,authenticationException)->{
            response.setContentType("application/json;charset=utf-8");
            ServletOutputStream os = response.getOutputStream();
            os.write(new ObjectMapper().writeValueAsBytes(Result.fail(401,"not login")));
            os.flush();
            os.close();
        });

        //设置用户登录 session 过期跳转的接口
        http.sessionManagement()
                .invalidSessionUrl("/session/invalid");

        //放行特定的接口
        http.authorizeRequests()
//                .antMatchers("/*").hasRole("user")
                .antMatchers("/session/invalid").permitAll();


        //所有请求均拦截
        http.authorizeRequests().anyRequest().authenticated();

        //关闭csrf防护，开启模拟请求
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
