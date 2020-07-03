package com.liuhang.mybatisplus.mybatisplusday01basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liuhang.mybatisplus.mybatisplusday01basic.dao")
public class MybatisplusDay01BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusDay01BasicApplication.class, args);
    }

}
