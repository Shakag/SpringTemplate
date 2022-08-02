package com.shakag.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
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
    public Docket docket(Environment environment) {
        //设置显示的swagger环境信息,判断是否处在自己设定的环境当中,为了安全生产环境不开放Swagger
        Profiles profiles = Profiles.of("dev", "test");
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //只有当springboot配置文件为dev或test环境时，才开启swaggerAPI文档功能
                .enable(flag)
                .select()
                // 这里指定Controller扫描包路径:设置要扫描的接口类，一般是Controller类
                .apis(RequestHandlerSelectors.basePackage("com.shakag.controller"))
                .build();
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
                new ArrayList<>()
        );
    }
}
