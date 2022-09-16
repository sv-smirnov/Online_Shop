package com.geekbrains.spring.web.order.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;
    @Schema(description = "Название продукта", example = "Milk")
    private String title;
    @Schema(description = "Количество", example = "2")
    private int quantity;
    @Schema(description = "Цена за еденицу", example = "100")
    private int pricePerProduct;
    @Schema(description = "Общая сумма товаров", example = "200")
    private int price;

    public OrderItemDto(ProductDto product){
        this.productId = product.getId();
        this.title = product.getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
    }

    public void changeQuantity(int delta){
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }

}
