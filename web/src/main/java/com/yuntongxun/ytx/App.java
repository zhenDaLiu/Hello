package com.yuntongxun.ytx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author luocc
 * @SpringbootApplication 相当于 @Configuration, @EnableAutoConfiguration 和 @ComponentScan 并具有他们的默认属性值
 * @EnableTransactionManagement 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
 *
 * 启动jvm参数加上 -Dytx.center.num=1 -Dytx.instance.num=1
 */
//yuntongxun/admin/controller/sys/SysMenuController.java
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class},scanBasePackages = {"com.yuntongxun"})
@MapperScan({"com.yuntongxun.ytx.mapper"})
@EnableTransactionManagement
@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.yuntongxun.*"})
public class App{
    public static void main(String[] args) {
        System.out.println("------- fast 启动完毕 ");
        SpringApplication.run(App.class, args);
    }
}
