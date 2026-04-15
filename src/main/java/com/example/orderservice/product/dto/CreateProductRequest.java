package com.example.orderservice.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "name is required")
        String name,
        @NotNull(message = "price is required")
        @Positive(message = "price must be greater than 0")
        BigDecimal price,
        @Min(value = 0, message = "stock must be 0 or greater")
        int stock
) {
}
