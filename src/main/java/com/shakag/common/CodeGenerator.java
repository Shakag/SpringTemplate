package com.shakag.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {
    public static void main(String[] args) {
        //代码生成器 对象
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        // gc.setOutputDir(System.getProperty("user.dir")+"/src/main/java"); 相对路径
        gc.setOutputDir("C:\\Users\\Administrator\\Desktop\\Shakag\\spring-template\\src\\main\\java"); //绝对路径
        gc.setAuthor("Shakag");
        gc.setOpen(false); //生成完是否打开文件夹
        gc.setFileOverride(false);//是否覆盖
        gc.setServiceName("%sService");//去Service 的 I 前缀
        mpg.setGlobalConfig(gc);

        //设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/vueadmin?serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("3333");
        dsc.setDbType(DbType.MYSQL); //设置数据库类型
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName("edu");
        pc.setParent("com.shakag");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //设置要映射的表名,如果不设置就是生成所有的表; 多个用逗号 , 隔开
        //setInclude("sys_user","sys_role");
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行, NamingStrategy.underline_to_camel
        strategy.setColumnNaming(NamingStrategy.no_change);
        strategy.setEntityLombokModel(true); //自动lombook
        mpg.setStrategy(strategy);

        //执行
        mpg.execute();
    }
}
