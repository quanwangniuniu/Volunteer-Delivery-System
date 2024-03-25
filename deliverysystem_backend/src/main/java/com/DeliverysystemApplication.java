package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.deliverysystem.Mapper")
public class DeliverysystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeliverysystemApplication.class, args);
    }
}
