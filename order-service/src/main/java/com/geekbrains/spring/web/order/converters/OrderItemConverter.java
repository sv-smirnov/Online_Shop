package com.geekbrains.spring.web.order.converters;


import com.geekbrains.spring.web.order.api.ProductApi;
import com.geekbrains.spring.web.order.dto.OrderItemDto;
import com.geekbrains.spring.web.order.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {

    @Autowired
    private ProductApi productApi;

    public OrderItemDto entityToDto(OrderItem orderItem){
        return new OrderItemDto(orderItem.getProductId(), productApi.getProductById(orderItem.getProductId()).getTitle(),
                orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }
}
