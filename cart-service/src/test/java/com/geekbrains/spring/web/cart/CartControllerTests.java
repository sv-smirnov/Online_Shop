package com.geekbrains.spring.web.cart;

import com.geekbrains.spring.web.cart.dto.Cart;
import com.geekbrains.spring.web.cart.dto.OrderItemDto;
import com.geekbrains.spring.web.cart.dto.ProductDto;
import com.geekbrains.spring.web.cart.services.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CartService service;

    @Test
    public void getCurrentCartTest() {
        Cart cart = restTemplate.postForObject("/api/v1/carts", "cartName", Cart.class);
        assertThat(cart).isNotNull();

    }

}
