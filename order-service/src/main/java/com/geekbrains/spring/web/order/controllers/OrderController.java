package com.geekbrains.spring.web.order.controllers;


import com.geekbrains.spring.web.order.converters.OrderConverter;
import com.geekbrains.spring.web.order.dto.OrderDetailsDto;
import com.geekbrains.spring.web.order.dto.OrderDto;
import com.geekbrains.spring.web.order.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Контроллер заказов")
public class OrderController {
        private final OrderService orderService;
        private final OrderConverter orderConverter;

        @Qualifier(value = "KafkaTest")
        @Autowired
        private KafkaTemplate<String, OrderDetailsDto> kafkaTemplate;

    @Operation(description = "Создать заказ из текущей корзины")
    @PostMapping("/{cartName}")
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable String cartName){
//       orderService.createOrder(username, orderDetailsDto, cartName);
        String key = username + "/" + cartName;
        kafkaTemplate.send("Orders", key, orderDetailsDto);
    }

    @Operation(description = "Получить текущие заказы по имени пользователя")
    @GetMapping
    public List<OrderDto> getCurrentOrders(@RequestHeader String username){
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
