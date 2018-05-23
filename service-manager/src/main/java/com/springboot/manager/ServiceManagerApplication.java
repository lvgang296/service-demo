package com.springboot.manager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.springboot.manager.mapper")
public class ServiceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceManagerApplication.class, args);
    }
}
