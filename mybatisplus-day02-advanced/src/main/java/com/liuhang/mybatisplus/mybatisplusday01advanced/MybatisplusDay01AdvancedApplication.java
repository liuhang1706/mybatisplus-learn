package com.liuhang.mybatisplus.mybatisplusday01advanced;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liuhang.mybatisplus.mybatisplusday01advanced.dao")
public class MybatisplusDay01AdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusDay01AdvancedApplication.class, args);
    }

}
