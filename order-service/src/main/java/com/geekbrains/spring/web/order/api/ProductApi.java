package com.geekbrains.spring.web.order.api;

import com.geekbrains.spring.web.order.configs.ApiConfig;
import com.geekbrains.spring.web.order.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="Products", url = "http://localhost:8189/web-market-core/api/v1/products/", configuration = ApiConfig.class)
public interface ProductApi {

    @GetMapping("/{id}")
    ProductDto getProductById(@PathVariable Long id);
}
