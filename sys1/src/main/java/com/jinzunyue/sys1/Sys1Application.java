package com.jinzunyue.sys1;

import com.jinzunyue.share.config.SecurityConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class)
@ComponentScan(basePackages = {"com.jinzunyue.share", "com.jinzunyue.sys1"})
@MapperScan("com.jinzunyue.sys1.mapper")
public class Sys1Application {

    public static void main(String[] args) {
        SpringApplication.run(Sys1Application.class, args);
    }

}
