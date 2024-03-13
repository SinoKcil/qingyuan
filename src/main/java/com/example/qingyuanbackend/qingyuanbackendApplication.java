package com.example.qingyuanbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.qingyuanbackend.mapper")
public class qingyuanbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(qingyuanbackendApplication.class, args);
    }

}
