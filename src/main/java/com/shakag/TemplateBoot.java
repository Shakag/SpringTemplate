package com.shakag;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shakag.mapper")
public class TemplateBoot {
    public static void main(String[] args) {
        SpringApplication.run(TemplateBoot.class,args);
    }
}
