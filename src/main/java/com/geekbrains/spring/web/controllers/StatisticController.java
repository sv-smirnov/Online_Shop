package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.AppAspects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {

    @GetMapping("/statistic")
    public String getServisesDuration() {
        return "Cart Service: " + AppAspects.cartServiceTotalTime + " ms. \n" +
                "Order Service: " + AppAspects.orderServiceTotalTime + " ms. \n" +
                "Product Service: " + AppAspects.productsServiceTotalTime + " ms. \n" +
                "User Service: " + AppAspects.userServiceTotalTime + " ms. \n";
    }
}
