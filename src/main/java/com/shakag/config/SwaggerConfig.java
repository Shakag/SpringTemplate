package com.shakag.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {
    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    //配置swagger信息=apiInfo
    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("作者", "https://github.com/shakag", "654287340@qq.com");
        return new ApiInfo(
                "Api 文档标题",
                "Api文档描述",
                "v1.0",
                //服务条款路径
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
