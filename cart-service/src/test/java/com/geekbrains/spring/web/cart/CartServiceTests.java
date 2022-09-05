package com.geekbrains.spring.web.cart;

import com.geekbrains.spring.web.cart.dto.Cart;
import com.geekbrains.spring.web.cart.dto.OrderItemDto;
import com.geekbrains.spring.web.cart.dto.ProductDto;
import com.geekbrains.spring.web.cart.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CartServiceTests {
    @Autowired
    private CacheManager cacheManager;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private CartService cartService;

    @BeforeEach
    public void initCart() {cartService.clear("newCart");}



    @Test
    public void getCurrentCartTest() {
        Cart cart = cartService.getCurrentCart("newCart");
        assertThat(cart).isNotNull();
        cart.addProduct(new ProductDto(1L, "Milk", 100));
        cart.addProduct(new ProductDto(1L, "Milk", 100));
        cart.addProduct(new ProductDto(1L, "Milk", 100));
        assertThat(cart.getTotalPrice()).isEqualTo(300);
    }

    @Test
    public void addProductByIdToCartTest() {
        ProductDto product = new ProductDto(1L, "Milk", 100);
        Cart cart = cartService.getCurrentCart("newCart");
        Mockito.doReturn(product).when(restTemplate).getForObject("http://localhost:8189/web-market-core/api/v1/products/" + 1, ProductDto.class);
        cartService.addProductByIdToCart(1L,"newCart");
        assertThat(cartService.getCurrentCart("newCart").getItems().size()).isEqualTo(1);
    }

    @Test
    public void clearCartTest() {
        Cart cart = cartService.getCurrentCart("newCart");
        cart.addProduct(new ProductDto(1L, "Milk", 100));
        assertThat(cart.getTotalPrice()).isGreaterThan(0);
        cartService.clear("newCart");
        assertThat(cartService.getCurrentCart("newCart").getTotalPrice()).isEqualTo(0);
    }
    @Test
    public void removeProductByIdToCartTest() {
        ProductDto product = new ProductDto(1L, "Milk", 100);
        ProductDto product2 = new ProductDto(2L, "Bread", 80);
        Cart cart = cartService.getCurrentCart("newCart");
        Mockito.doReturn(product).when(restTemplate).getForObject("http://localhost:8189/web-market-core/api/v1/products/" + 1, ProductDto.class);
        Mockito.doReturn(product2).when(restTemplate).getForObject("http://localhost:8189/web-market-core/api/v1/products/" + 2, ProductDto.class);
        cartService.addProductByIdToCart(1L,"newCart");
        assertThat(cartService.getCurrentCart("newCart").getTotalPrice()).isEqualTo(100);
        cartService.addProductByIdToCart(2L,"newCart");
        assertThat(cartService.getCurrentCart("newCart").getTotalPrice()).isEqualTo(180);
        cartService.removeProductByIdFromCart(1L,"newCart");
        assertThat(cartService.getCurrentCart("newCart").getTotalPrice()).isEqualTo(80);
        cartService.removeProductByIdFromCart(2L,"newCart");
        assertThat(cartService.getCurrentCart("newCart").getTotalPrice()).isEqualTo(0);
        cartService.removeProductByIdFromCart(1L,"newCart");
    }



}
