package com.geekbrains.spring.web.endpoints;

import com.geekbrains.spring.web.services.ProductsService;
import com.geekbrains.spring.web.soap.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {


    private static final String NAMESPACE_URI = "http://www.mvg.com/spring/ws/products";
    private final ProductsService productsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productsService.findAll().forEach(u -> {
            Product product = new Product();
            product.setId(u.getId());
            product.setTitle(u.getTitle());
            product.setPrice(u.getPrice());
            response.getProducts().add(product);
        });
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProductById(@RequestPayload GetProductsRequest request) {
        GetProductsResponse response = new GetProductsResponse();
        Optional<com.geekbrains.spring.web.entities.Product> product = productsService.findById(request.getId());
            response.getProduct(product);
        return response;
    }
}
