package com.shakag;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.shakag.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TemplateBoot {
    public static void main(String[] args) {
        SpringApplication.run(TemplateBoot.class,args);
    }
}
