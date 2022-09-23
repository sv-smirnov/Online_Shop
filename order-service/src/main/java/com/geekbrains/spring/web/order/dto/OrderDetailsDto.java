package com.geekbrains.spring.web.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDto {
    @Schema(description = "Адрес", example = "Ufa")
    private String address;
    @Schema(description = "Телефон", example = "89696969969")
    private String phone;
    @Schema(description = "Номер заказа")
    private String billId;
    @Schema(description = "Статус оплаты заказа", example = "COMPETED")
    private String status;
}
