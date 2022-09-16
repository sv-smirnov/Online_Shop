package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.converters.ProductConverter;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.services.ProductsService;
import com.geekbrains.spring.web.validators.ProductValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Продукты", description = "Контроллер продуктов")
public class ProductsController {
    private final ProductsService productsService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    @Operation(description = "Получить все продукты")
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productsService.findAll(minPrice, maxPrice, titlePart, page).map(
                p -> productConverter.entityToDto(p)
        );
    }
    @Operation(description = "Получить продукт по id")
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }
    @Operation(description = "Сохранить новый продукт")
    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productsService.save(product);
        return productConverter.entityToDto(product);
    }
    @Operation(description = "Обновить данные продукта")
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productsService.update(productDto);
        return productConverter.entityToDto(product);
    }
    @Operation(description = "Удаление продукта по id")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productsService.deleteById(id);
    }
}
