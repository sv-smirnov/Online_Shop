package com.geekbrains.spring.web.cart;

import com.geekbrains.spring.web.cart.dto.Cart;
import com.geekbrains.spring.web.cart.dto.ProductDto;
import com.geekbrains.spring.web.cart.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CartService service;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    public void initCart() {service.clear("newCart");}

    @Test
    public void getCurrentCartTest() throws Exception {
        Cart cart = testRestTemplate.postForObject("/api/v1/carts", "newCart", Cart.class);
        assertThat(cart).isNotNull();
        mvc.perform(
                post("/api/v1/carts").contentType(MediaType.APPLICATION_JSON).content("newCart"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void addProductToCartTest() throws Exception {
        ProductDto product = new ProductDto(1L, "Milk", 100);
        Mockito.doReturn(product).when(restTemplate).getForObject("http://localhost:8189/web-market-core/api/v1/products/" + 1, ProductDto.class);
        mvc.perform(
                        post("/api/v1/carts/add/" + 1).contentType(MediaType.APPLICATION_JSON).content("newCart"))
                .andDo(print())
                .andExpect(status().isOk());
        Cart cart = testRestTemplate.postForObject("/api/v1/carts", "newCart", Cart.class);
        assertThat(cart.getTotalPrice()).isEqualTo(100);
    }


}
