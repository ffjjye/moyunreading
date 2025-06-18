package com.moyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.moyun.mapper")
public class MoyunApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoyunApplication.class, args);
    }
}