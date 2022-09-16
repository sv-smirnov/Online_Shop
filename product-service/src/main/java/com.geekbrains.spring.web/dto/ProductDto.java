package com.geekbrains.spring.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "Продукт", description = "Объект продукта")
public class ProductDto {
    private Long id;
    @Schema(description = "Название продукта", example = "Milk")
    private String title;
    @Schema(description = "Цена продукта", example = "100")
    private Integer price;
}
