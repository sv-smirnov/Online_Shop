package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.dto.Cart;
import com.geekbrains.spring.web.dto.OrderDetailsDto;
import com.geekbrains.spring.web.entities.Order;
import com.geekbrains.spring.web.entities.OrderItem;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductsService productsService;
    private final OrderRepository orderRepository;
    private final RestTemplate cartTemplate;

    @Transactional
    @KafkaListener(topics = "Orders")
//    public void createOrder(String username, OrderDetailsDto orderDetailsDto, String cartName){
    public void createOrder(ConsumerRecord<String, OrderDetailsDto> record){
        String key[] = record.key().split("/");
        String username = key[0];
        String cartName = key[1];
        OrderDetailsDto orderDetailsDto = record.value();

        Cart currentCart = cartTemplate.postForObject("http://localhost:8187/web-market-cart/api/v1/carts", cartName, Cart.class);
        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());
        List<OrderItem> items = currentCart.getItems().stream()
                .map(o -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(o.getQuantity());
                    orderItem.setPricePerProduct(o.getPricePerProduct());
                    orderItem.setPrice(o.getPrice());
                    orderItem.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                    return orderItem;
                }).collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);
        currentCart.clear();
    }

    public List<Order> findOrdersByUsername(String username) {
        try {
            return orderRepository.findByUsername(username);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
