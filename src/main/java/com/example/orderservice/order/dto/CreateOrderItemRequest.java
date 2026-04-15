package com.example.orderservice.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrderItemRequest(
        @NotNull(message = "productId is required")
        Long productId,
        @Positive(message = "quantity must be greater than 0")
        int quantity
) {
}
