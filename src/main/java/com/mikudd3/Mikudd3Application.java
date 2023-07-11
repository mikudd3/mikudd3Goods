package com.mikudd3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan //扫描过滤器
public class Mikudd3Application {

    public static void main(String[] args) {
        SpringApplication.run(Mikudd3Application.class, args);
    }

}
