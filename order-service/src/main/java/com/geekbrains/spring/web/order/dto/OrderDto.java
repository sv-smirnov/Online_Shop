package com.geekbrains.spring.web.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    @Schema(description = "Имя пользователя", example = "Bob")
    private String username;
    @Schema(description = "Список продуктов")
    private List<OrderItemDto> itemDtoList;
    @Schema(description = "Сумма заказа", example = "200")
    private Integer totalPrice;
    @Schema(description = "Адрес", example = "Ufa")
    private String address;
    @Schema(description = "Контактный телефон", example = "89696969696")
    private String phone;
}
