package com.shakag.common;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import lombok.Data;

@Data
public class CodeGenerator3_5_1 {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("Shakag") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .dateType(DateType.ONLY_DATE)  //设置日期类型
                            .outputDir("C:\\Code\\mybatis"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.shakag"); // 设置父包名
                            //.moduleName("system") // 设置父包模块名
                            //.pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            //.addTablePrefix("t_", "c_") // 设置过滤表前缀

                            //实体配置
                            .entityBuilder()
                            .enableLombok()
                            //逻辑删除数据库字段名
                            .logicDeleteColumnName("deleted")
                            //逻辑删除实体字段名
                            .logicDeletePropertyName("deleted")
                            //.addIgnoreColumns("xxx") //添加忽略字段
                            .enableTableFieldAnnotation()  //开启生成实体时生成字段注解

                            //controller 配置
                            .controllerBuilder()
                            .enableRestStyle() //开启生成@RestController 控制器

                            //service 配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImp")

                            //mapper 配置
                            .mapperBuilder()
                            .enableMapperAnnotation();
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
