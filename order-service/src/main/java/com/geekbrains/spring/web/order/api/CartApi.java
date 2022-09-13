package com.geekbrains.spring.web.order.api;

import com.geekbrains.spring.web.order.configs.ApiConfig;
import com.geekbrains.spring.web.order.dto.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value="Cart", url = "http://localhost:8187/web-market-cart/api/v1/carts", configuration = ApiConfig.class)
public interface CartApi {

    @PostMapping
    Cart getCurrentCart(@RequestBody String cartName);
}
