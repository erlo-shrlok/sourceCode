package com.jinzunyue.sys1;

import com.jinzunyue.share.config.SecurityConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class)
@ComponentScan(basePackages = {"com.jinzunyue.share", "com.jinzunyue.sys1"})
@MapperScan("com.jinzunyue.**.mapper")
@EnableCaching
public class Sys1Application {

    public static void main(String[] args) {
        SpringApplication.run(Sys1Application.class, args);
        System.out.println("程序启动成功");
    }

}
