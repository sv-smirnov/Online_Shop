package com.geekbrains.spring.web.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableFeignClients
@SpringBootApplication
@PropertySource("secrets.properties")
public class OrderApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);}
}
